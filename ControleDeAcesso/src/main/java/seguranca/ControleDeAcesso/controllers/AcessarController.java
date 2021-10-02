package seguranca.ControleDeAcesso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seguranca.ControleDeAcesso.DTOs.LoginDTO;
import seguranca.ControleDeAcesso.DTOs.RetornoLoginDTO;
import seguranca.ControleDeAcesso.servicos.LoginService;


@RestController
@RequestMapping("/acessar")
public class AcessarController {
	
	@Autowired
	LoginService loginService;

	//Token para testes: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvQXNzb2NpYWRvIiwiaXNzIjoiV1A4emhycXF2REhmdkVZMGE0cWtIb091OUJqWkRTNXkiLCJpYXQiOjE2Mjg4ODcxMzQsImV4cCI6MTE2Mjg4ODcxMzR9.KFMmFm_VfmbM1wCx8YEaIJ-EQFALvn7Zrtxp27tn454

	@CrossOrigin
	@PostMapping
    public RetornoLoginDTO autentication(@RequestBody LoginDTO login) {
		
		RetornoLoginDTO retornoLoginDTO = loginService.verificaLogin(login);
		return retornoLoginDTO;
	}
	
	@CrossOrigin
	@GetMapping
    public String teste() {
		return "teste";
	}
	
	
}
