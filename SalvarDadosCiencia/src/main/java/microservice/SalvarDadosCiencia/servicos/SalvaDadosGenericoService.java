package microservice.SalvarDadosCiencia.servicos;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import microservice.SalvarDadosCiencia.models.AssociadoModel;
import microservice.SalvarDadosCiencia.models.PlanoModel;
import microservice.SalvarDadosCiencia.rabbitmq.mensagens.SalvaDadosGenericoMsg;

@Service
public class SalvaDadosGenericoService {
	
	private Gson gson;
	
	public SalvaDadosGenericoService() throws IOException {

		this.gson = new Gson();
	}

	public void salvarDados(SalvaDadosGenericoMsg salvaDadosGenericoMsg) {
		
		if(salvaDadosGenericoMsg.getCodigoTabela() == 1) {
			AssociadoModel associadoModel = gson.fromJson(salvaDadosGenericoMsg.getJson(), AssociadoModel.class);
			//depois só salvar dado associadoModel na tabela, separando somente os dados desejados ou salvando toda a tabela
		}
		
		if(salvaDadosGenericoMsg.getCodigoTabela() == 2) {
			PlanoModel planoModel = gson.fromJson(salvaDadosGenericoMsg.getJson(), PlanoModel.class);
			
			//depois só salvar dado associadoModel na tabela, separando somente os dados desejados ou salvando toda a tabela
		}
		
	}
}
