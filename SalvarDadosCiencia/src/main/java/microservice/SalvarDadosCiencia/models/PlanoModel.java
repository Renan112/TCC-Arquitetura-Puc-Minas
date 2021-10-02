package microservice.SalvarDadosCiencia.models;

import java.math.BigDecimal;

public class PlanoModel {

	Integer codigo;
	String nome;
	String descricao;
	BigDecimal valorBase;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorBase() {
		return valorBase;
	}
	public void setValorBase(BigDecimal valorBase) {
		this.valorBase = valorBase;
	}
}
