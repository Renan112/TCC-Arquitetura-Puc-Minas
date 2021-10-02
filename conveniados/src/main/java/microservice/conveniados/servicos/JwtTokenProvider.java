package microservice.conveniados.servicos;

import java.util.Base64;

import org.springframework.stereotype.Component;

 
@Component
public class JwtTokenProvider {

    
    public String buscarLogin(String token) {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return login[0].substring(7).replaceAll("\"", "");
    }
   
}
