package microservice.associados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservice.associados.DTOs.AlteraPlanoDTO;
import microservice.associados.DTOs.PlanoDisponivelDTO;
import microservice.associados.models.AssociadoModel;
import microservice.associados.servicos.AssociadosService;



@RestController
@RequestMapping("/associado")
public class AssociadosController {

	@Autowired
	AssociadosService associadosService;

	@CrossOrigin
	@GetMapping("/listaPlanos")
    public List<PlanoDisponivelDTO> consultaPlanos(@RequestHeader("Authorization") String token) {
		return associadosService.listarPlanosDisponiveis(token);
	}
	
	@CrossOrigin
	@PostMapping("/alterarPlano")
    public AssociadoModel mudarPlanos(@RequestBody AlteraPlanoDTO alteraPlanoDTO, 
			@RequestHeader("Authorization") String token) {
		return associadosService.salvarPlanoAssociado(token, alteraPlanoDTO.getCodigoPlano(), alteraPlanoDTO.getOdontologico());
	}
	
}
