package microservice.associados.DTOs;

import java.math.BigDecimal;

public class PlanoDisponivelDTO {

	Integer codigoPlanoInteger;
	String nomePlano;
	String descricaoPlano;
	BigDecimal valorPlano;
	
	public Integer getCodigoPlanoInteger() {
		return codigoPlanoInteger;
	}
	public void setCodigoPlanoInteger(Integer codigoPlanoInteger) {
		this.codigoPlanoInteger = codigoPlanoInteger;
	}
	public String getNomePlano() {
		return nomePlano;
	}
	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}
	public String getDescricaoPlano() {
		return descricaoPlano;
	}
	public void setDescricaoPlano(String descricaoPlano) {
		this.descricaoPlano = descricaoPlano;
	}
	public BigDecimal getValorPlano() {
		return valorPlano;
	}
	public void setValorPlano(BigDecimal valorPlano) {
		this.valorPlano = valorPlano;
	}
}
