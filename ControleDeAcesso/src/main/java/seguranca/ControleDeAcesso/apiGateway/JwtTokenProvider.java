package seguranca.ControleDeAcesso.apiGateway;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
@Component
public class JwtTokenProvider {

	@Value("${token.validade}")
    private long validityInMilliseconds;

    @Autowired
    private Kong kong;
 
   
    public String createToken(String username) throws IOException {
        Map<String,String> hashMap = kong.criarConsumerJWT(username);
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("iss", hashMap.get("key"));
        //claims.put("grupoAcesso", grupoAcesso);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token =  Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(hashMap.get("secret").getBytes()))
                .compact();
     
        return token;
    }
    
    public String buscarLogin(String token) {
    	String payloads[] = token.split("\\.");	
    	byte[] decodedBytes = Base64.getDecoder().decode(payloads[1]);
    	String payload = new String(decodedBytes);
    	String login[] = payload.split(",");      
    	return login[0].substring(7).replaceAll("\"", "");
    }
   
}
