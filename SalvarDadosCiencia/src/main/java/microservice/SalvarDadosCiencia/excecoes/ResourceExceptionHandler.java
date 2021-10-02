package microservice.SalvarDadosCiencia.excecoes;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import microservice.SalvarDadosCiencia.DTOs.ExcessoesRetornoApiDTO;
import microservice.SalvarDadosCiencia.servicos.LogErro;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Autowired
	LogErro logErro;


	@ExceptionHandler(ExcecaoRegraNegocio.class)
	public ResponseEntity<ExcessoesRetornoApiDTO> handleExcecaoRegraNegocio(ExcecaoRegraNegocio e,
			HttpServletRequest request, HandlerMethod handlerMethod) {	
		ExcessoesRetornoApiDTO excessoesMensagemRetorno = new ExcessoesRetornoApiDTO(409,  ApiStatus.ERRO_REQUISICAO.httpStatus.toString(), e.getMessage());
		return new  ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno, ApiStatus.ERRO_REQUISICAO.httpStatus);
	}

	
	@ExceptionHandler(Exception.class)	
	public ResponseEntity<ExcessoesRetornoApiDTO> handleGenericException(Exception e, HttpServletRequest request, HandlerMethod handlerMethod) {
		ExcessoesRetornoApiDTO excessoesMensagemRetorno = 
				new ExcessoesRetornoApiDTO(400, ApiStatus.DADO_INVALIDO.httpStatus.toString(), e.getMessage());
		
		logErro.logErroGeral(e, "Exception.class");
		return new ResponseEntity<ExcessoesRetornoApiDTO>(excessoesMensagemRetorno, ApiStatus.DADO_INVALIDO.httpStatus);
	}


}