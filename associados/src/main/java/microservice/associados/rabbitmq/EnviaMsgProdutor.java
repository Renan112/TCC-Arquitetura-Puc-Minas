package microservice.associados.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnviaMsgProdutor {

	@Value("${rabbitmq.associados.nomeExchange}")
	private String exchange;
	
	@Autowired
	Produtor produtor;
	
	public void enviarMsg() {
		

//			classeX msg = new classeX();
//			msg.setDadoX("teste");

//			produtor.envioMensagem(exchange, "fanout", "", msg);
			
		
		
	}
	
}
