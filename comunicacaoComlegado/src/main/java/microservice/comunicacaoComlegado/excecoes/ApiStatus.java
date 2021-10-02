package microservice.comunicacaoComlegado.excecoes;

import org.springframework.http.HttpStatus;
 
public enum ApiStatus {
	
	SUCESSO(HttpStatus.OK, "Operação concluída com exito", ""),
	ERRO_INESPERADO(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", ""),
	SEM_RESULTADO(HttpStatus.NO_CONTENT, "Não existe resultado para os parametros informados.", ""),
	DADO_INVALIDO(HttpStatus.BAD_REQUEST, "Dado inválido", ""),
	ERRO_REQUISICAO(HttpStatus.CONFLICT, "Problema na requisição", ""),
	USUARIO_BLOQUEADO(HttpStatus.I_AM_A_TEAPOT, "Usuario Bloqueado", "");
	
	public final HttpStatus httpStatus;
	public final String message;
	public final String description;

	private ApiStatus(HttpStatus httpStatus, String message, String description) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.description = description;
	}

	public int toInt() {
		return httpStatus.value();
	}

}
