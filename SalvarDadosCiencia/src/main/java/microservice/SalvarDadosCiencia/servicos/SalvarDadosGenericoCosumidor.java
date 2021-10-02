package microservice.SalvarDadosCiencia.servicos;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import microservice.SalvarDadosCiencia.rabbitmq.EndPoint;
import microservice.SalvarDadosCiencia.rabbitmq.mensagens.SalvaDadosGenericoMsg;

@Component
@Scope("prototype")
public class SalvarDadosGenericoCosumidor implements Runnable, Consumer {
	
	@Autowired
	private EndPoint endPoint;

	private Channel channel;

	private Gson gson;
	
	@Autowired
	LogErro logErro;
	
	@Autowired
	SalvaDadosGenericoService salvaDadosGenericoService;


	@Value("${rabbitmq.salvarDadosGenerico.nomeFila}")
	private String nomeFila;
	@Value("${rabbitmq.salvarDadosGenerico.nomeRota}")
	private String nomeRota;
	@Value("${rabbitmq.salvarDadosGenerico.nomeExchange}")
	private String nomeExchange;

	public SalvarDadosGenericoCosumidor() throws IOException {

		this.gson = new Gson();
	}
	
	public void criarFila() throws Exception {
		this.channel = endPoint.criarFila(nomeExchange, "direct", nomeFila, nomeRota, null);
	}

	public void run() {
		try {
			channel.basicConsume(nomeFila, false, this);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	

	// Chama esse método quando existe uma mensagem não lida na fila.
	@Override
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body)
			throws IOException {
		try {
			String message = new String(body, "UTF-8");
			
			
			//criada uma classe genérica, para que não seja necessário criar vários consumidores diferentes
			SalvaDadosGenericoMsg salvaDadosGenericoMsg = gson.fromJson(message, SalvaDadosGenericoMsg.class);	
			
			this.salvaDadosGenericoService.salvarDados(salvaDadosGenericoMsg);
			
			// se tudo correr conforme esperado, marca a mensagem como lida no final
			channel.basicAck(env.getDeliveryTag(), false);

		}catch (Exception e) {
			logErro.logErro("Erro no consumidor RetornoSituacaoAssociadoConsumidor: ", e.getMessage());
		}
	}

	
	@Override
	public void handleConsumeOk(String consumerTag) {
		
	}

	
	@Override
	public void handleCancelOk(String consumerTag) {
	

	}

	
	@Override
	public void handleCancel(String consumerTag) throws IOException {
	

	}

	
	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
	

	}

	
	@Override
	public void handleRecoverOk(String consumerTag) {
		
	}

	
	
}
