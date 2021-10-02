package microservice.associados.DTOs;

import java.time.Instant;

public class ExcessoesRetornoApiDTO extends MensagemRetornoApiDTO {
	
	private String descricaoExcessao;
	
	public ExcessoesRetornoApiDTO(int codeStatus, String tituloStatus, String descricaoExcessao) {
		super();
		setCodeStatus(codeStatus);
		setTituloStatus(tituloStatus);
		setDataHora(Instant.now());
		this.descricaoExcessao = descricaoExcessao;
	}

	public ExcessoesRetornoApiDTO() {
		super();
	}
	
	public String getDescricaoExcessao() {
		return descricaoExcessao;
	}

	public void setDescricaoExcessao(String descricaoExcessao) {
		this.descricaoExcessao = descricaoExcessao;
	}
	
}
