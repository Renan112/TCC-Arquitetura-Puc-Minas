package microservice.associados.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import microservice.associados.DTOs.PlanoDisponivelDTO;
import microservice.associados.models.AssociadoModel;
import microservice.associados.models.PlanoModel;
import microservice.associados.rabbitmq.mensagens.SituacaoAssociadoMsg;
import microservice.associados.rabbitmq.mensagens.SalvaDadosGenericoMsg;
import microservice.associados.repository.AssociadoRepository;
import microservice.associados.repository.PlanosRepository;
import microservice.associados.rabbitmq.Produtor;

@Service
public class AssociadosService {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	PlanosRepository planosRepository;
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	@Autowired
	Produtor produtor;
	
	@Autowired
	Gson gson;
	
	@Value("${rabbitmq.retornoSituacaoAssociado.nomeExchange}")
	private String exchangeRetornoSituacaoAssociado;
	
	@Value("${rabbitmq.retornoSituacaoAssociado.nomeRota}")
	private String rotaRetornoSituacaoAssociado;
	
	@Value("${rabbitmq.salvarDadosGenerico.nomeExchange}")
	private String exchangeSalvarDadosGenerico;
	
	@Value("${rabbitmq.salvarDadosGenerico.nomeRota}")
	private String rotaSalvarDadosGenerico;
	
	
	public List<PlanoDisponivelDTO> listarPlanosDisponiveis(String token){
		
		List<PlanoDisponivelDTO> ListaPlanosDisponiveisDTO = new ArrayList<PlanoDisponivelDTO>(); 
		String login = jwtTokenProvider.buscarLogin(token);
		
		AssociadoModel associadoModel = associadoRepository.buscaAssociadoPorLogin(login);
		List<PlanoModel> listaPlanosModel = planosRepository.listaPlanos();
		
		for(PlanoModel planoModel : listaPlanosModel) {
			PlanoDisponivelDTO planosDisponiveisDTO = new PlanoDisponivelDTO();
			
			BigDecimal valorPlano = this.calculaValorPlano(planoModel, associadoModel.getIdade());
			
			planosDisponiveisDTO.setCodigoPlanoInteger(planoModel.getCodigo());
			planosDisponiveisDTO.setNomePlano(planoModel.getNome());
			planosDisponiveisDTO.setDescricaoPlano(planoModel.getDescricao());
			planosDisponiveisDTO.setValorPlano(valorPlano);
			
			ListaPlanosDisponiveisDTO.add(planosDisponiveisDTO);
		}
		
		return ListaPlanosDisponiveisDTO;
	}
	
	
	public BigDecimal calculaValorPlano(PlanoModel planoModel, Integer idadeAssociado) {
		
		BigDecimal valorPlano = planoModel.getValorBase();
		
		if(idadeAssociado >= 0 && idadeAssociado <= 18) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(0.3));
		} else if(idadeAssociado >= 19 && idadeAssociado <= 23) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(0.5));
		} else if(idadeAssociado >= 24 && idadeAssociado <= 28) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(0.8));
		} else if(idadeAssociado >= 29 && idadeAssociado <= 33) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(1.0));
		} else if(idadeAssociado >= 34 && idadeAssociado <= 38) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(1.2));
		} else if(idadeAssociado >= 39 && idadeAssociado <= 43) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(1,5));
		} else if(idadeAssociado >= 44 && idadeAssociado <= 48) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(2.0));
		} else if(idadeAssociado >= 49 && idadeAssociado <= 53) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(2.8));
		} else if(idadeAssociado >= 54 && idadeAssociado <= 58) {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(3.5));
		} else {
			valorPlano = valorPlano.multiply(BigDecimal.valueOf(5,0));
		}
		
		return valorPlano;
	}
	
	public AssociadoModel salvarPlanoAssociado(String token, Integer codigoPlano, Integer odontologico) {
		
		String login = jwtTokenProvider.buscarLogin(token);
		AssociadoModel associadoModel = associadoRepository.buscaAssociadoPorLogin(login);
		List<PlanoModel> listaPlanosModel = planosRepository.listaPlanos();
		for(PlanoModel planoModel : listaPlanosModel) {
			if (codigoPlano == planoModel.getCodigo()) {
				associadoModel.setCodigoPlano(codigoPlano);
				associadoModel.setOdontologico(odontologico);
				if (odontologico == 1 && codigoPlano != 3) {
					associadoModel.setValorPlano(planoModel.getValorBase().multiply(BigDecimal.valueOf(1.15)));
				} else {
					associadoModel.setValorPlano(planoModel.getValorBase());
				}
			}
		}
		associadoRepository.salvaPlanoAssociado(associadoModel);
		
		//Envia mensagem para a aplicação Salvar Dados Genericos
		SalvaDadosGenericoMsg salvaDadosGenericoMsg = new SalvaDadosGenericoMsg();
		salvaDadosGenericoMsg.setCodigoTabela(1);
		salvaDadosGenericoMsg.setJson(gson.toJson(associadoModel));
		produtor.envioMensagem(exchangeSalvarDadosGenerico, "direct", rotaSalvarDadosGenerico, salvaDadosGenericoMsg);
		
		return associadoModel;
	}


	public void buscaSituacaoAssociado(SituacaoAssociadoMsg situacaoAssociadoMsg) {
		AssociadoModel associadoModel = associadoRepository.buscaAssociadoPorCodigo(situacaoAssociadoMsg.getCodigoAssociado());
		situacaoAssociadoMsg.setSituacao(associadoModel.getSituacao());
		
		produtor.envioMensagem(exchangeRetornoSituacaoAssociado, "direct", rotaRetornoSituacaoAssociado, situacaoAssociadoMsg);
	}
	
	
	
}
