package microservice.comunicacaoComlegado.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import microservice.comunicacaoComlegado.rabbitmq.Produtor;
import microservice.comunicacaoComlegado.rabbitmq.mensagens.SituacaoAssociadoMsg;

@Service
public class Sgps {

	@Autowired
	Produtor produtor;
	
	
	@Value("${rabbitmq.retornoSituacaoAssociado.nomeExchange}")
	private String exchange;
	
	@Value("${rabbitmq.retornoSituacaoAssociado.nomeRota}")
	private String rota;
	
	public void verificaExame(SituacaoAssociadoMsg situacaoAssociadoMsg) {
		
		//Para esse teste os exames sempre são autorizados, na situação real o sistema legado seria consultado com base na interface disponível no sistema legado (comunicação via API REST, SOAP ou até mesmo acesso direto ai banco de dados se necessário)
		situacaoAssociadoMsg.setAutorizacaoSgps("Autorizado");
		produtor.envioMensagem(exchange, "direct", rota, situacaoAssociadoMsg);
		
	}

	
	
}
