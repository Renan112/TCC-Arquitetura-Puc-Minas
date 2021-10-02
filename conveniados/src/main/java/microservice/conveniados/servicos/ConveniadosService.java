package microservice.conveniados.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import microservice.conveniados.excecoes.ExcecaoRegraNegocio;
import microservice.conveniados.rabbitmq.Produtor;
import microservice.conveniados.rabbitmq.mensagens.SituacaoAssociadoMsg;

@Service
public class ConveniadosService {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	Produtor produtor;
	
	@Value("${rabbitmq.associados.nomeExchange}")
	private String exchangeAssociado;
	
	@Value("${rabbitmq.associados.nomeRota}")
	private String rotaAssociado;
	
	@Value("${rabbitmq.autorizacaoExame.nomeExchange}")
	private String exchangeAutorizacaoExame;
	
	@Value("${rabbitmq.autorizacaoExame.nomeRota}")
	private String rotaAutorizacaoExame;
	
	//Lista utilizada somente para exemplificar, o ideal é utilizar o banco de dados para armazenar essa informação
	private List<SituacaoAssociadoMsg> listaAutorizados = new ArrayList<SituacaoAssociadoMsg>();

	public String solicitaAutorizacaoExame(String token, Integer codigoAssociado, Integer codigoExame) throws InterruptedException {
		
		
		SituacaoAssociadoMsg msg = new SituacaoAssociadoMsg();
		msg.setCodigoAssociado(codigoAssociado);
		msg.setCodigoExame(codigoExame);
		
		produtor.envioMensagem(exchangeAssociado, "direct", rotaAssociado, msg);
		
		//tenta processar o retorno durante 6 segundos, se não conseguir é exibida uma falha
		for (int i = 0; i <= 3; i++) {
			  for(SituacaoAssociadoMsg situacaoAssociadoMsg : listaAutorizados) {
				  if (situacaoAssociadoMsg.getCodigoExame() == msg.getCodigoExame() && situacaoAssociadoMsg.getCodigoAssociado() == msg.getCodigoAssociado()) {
					  if (situacaoAssociadoMsg.getSituacao().equals("Ativo")) {

							produtor.envioMensagem(exchangeAutorizacaoExame, "direct", rotaAutorizacaoExame, situacaoAssociadoMsg);
							if(situacaoAssociadoMsg.getAutorizacaoSgps() != null) {
								
								listaAutorizados.remove(situacaoAssociadoMsg);
								
								//Retorna mensagem autorizado ou nao que já vem do SGPS, caso o sistema legado de algum retorno diferente de uma String pode ser feito o tratamento aqui.
								return situacaoAssociadoMsg.getAutorizacaoSgps();
								
							} else listaAutorizados.remove(situacaoAssociadoMsg);
					  }
				  } else return "Não Autorizado";
			  }
			  Thread.sleep(2000);
		}
		
		/*
		Caso ocorra alguma falha e o exame não seja autorizado por que o consumidor não recebeu a mensagem a tempo ou o serviço estava fora pode ser implementado algum tratamento para essa situação como:
		- Envio de e-mail informando da autorização do exame
		- Disponilização da informação em outro canal, por exemplo, um SAC.
		*/
		
		throw new ExcecaoRegraNegocio("Problema no Servidor, tente novamente mais tarde");
	}
	
	public void atualizaRetornoRabbitMQ(SituacaoAssociadoMsg situacaoAssociadoMsg) {
		 
		//Evita adicionar registros repetidos
		for(SituacaoAssociadoMsg buscaSituacaoAssociadoMsg : listaAutorizados) {
			if(buscaSituacaoAssociadoMsg.equals(situacaoAssociadoMsg)) return;
		  }
		
		listaAutorizados.add(situacaoAssociadoMsg);
	}
	
}
