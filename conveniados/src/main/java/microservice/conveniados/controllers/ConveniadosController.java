package microservice.conveniados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.conveniados.DTOs.AutorizaExameDTO;
import microservice.conveniados.servicos.ConveniadosService;


@RestController
@RequestMapping("/conveniado")
public class ConveniadosController {


	@Autowired
	ConveniadosService conveniadosService;
	
	
	@CrossOrigin
	@PostMapping("/autorizarExame")
    public String mudarPlanos(@RequestBody AutorizaExameDTO autorizaExameDTO, 
			@RequestHeader("Authorization") String token) throws InterruptedException {
		return conveniadosService.solicitaAutorizacaoExame(token, autorizaExameDTO.getCodigoAssociado(), autorizaExameDTO.getCodigoExame());
	}
	
	
}
