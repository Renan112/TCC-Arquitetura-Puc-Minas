package microservice.SalvarDadosCiencia.rabbitmq;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.Channel;

import microservice.SalvarDadosCiencia.excecoes.ExcecaoRegraNegocio;



@Service
public class Produtor {

	@Autowired
	private EndPoint endPoint;
		
	public Produtor() {
		super();
	}
	/**
	 * @param nomeExchange segNomeComponente.exchange
	 * @param nomeTipoExchange fanout
	 * @param nomeRota ""
	 * */
	public String envioMensagem(String nomeExchange, String nomeTipoExchange, String nomeRota, Object objetoProdutor) throws ExcecaoRegraNegocio {
		
		Gson gson = new Gson();
		Channel channel = endPoint.criarExchange(nomeExchange, nomeTipoExchange);
		
		try {
		    Builder builderPropsJson = new BasicProperties()
						.builder()
						.timestamp(new Date())
						.contentType("application/json")
						//.deliveryMode(2) é para manter a messagem gravada mesmo quando o rabbitmq é reiniciado.
						.deliveryMode(2);
			
			BasicProperties basicProps = builderPropsJson.build();
			String message = gson.toJson(objetoProdutor);
			channel.basicPublish(nomeExchange, nomeRota, basicProps, message.getBytes());
			channel.getConnection().close();

		} catch (IOException ex) {
 			throw new ExcecaoRegraNegocio("Erro ao enviar mensagem assíncrona.");
		}	
		return "Sucesso";
	}
	
}
