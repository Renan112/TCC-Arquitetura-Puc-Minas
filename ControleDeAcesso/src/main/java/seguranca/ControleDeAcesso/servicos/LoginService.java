package seguranca.ControleDeAcesso.servicos;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seguranca.ControleDeAcesso.DTOs.LoginDTO;
import seguranca.ControleDeAcesso.DTOs.RetornoLoginDTO;
import seguranca.ControleDeAcesso.apiGateway.JwtTokenProvider;

@Service
public class LoginService {

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	public RetornoLoginDTO verificaLogin(LoginDTO login) {
		
		String loginUsuario = login.getLogin();
		String senhaUsuario = login.getSenha();
		RetornoLoginDTO retornoLoginDTO = new RetornoLoginDTO();
		
		if((loginUsuario.equals("usuarioAssociado") || loginUsuario.equals("usuarioPrestadorServico") || loginUsuario.equals("usuarioConveniado")) && senhaUsuario.equals("12345")) {
			try {
				retornoLoginDTO.setTokenAcessoString(jwtTokenProvider.createToken(login.getLogin()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		return retornoLoginDTO;
	}

}
