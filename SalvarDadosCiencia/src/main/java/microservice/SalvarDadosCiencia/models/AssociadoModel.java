package microservice.SalvarDadosCiencia.models;

import java.math.BigDecimal;

public class AssociadoModel {

	Integer codigo;
	String nome;
	String login;
	Integer idade;
	Integer codigoPlano;
	BigDecimal valorPlano;
	Integer odontologico;
	String situacao;
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Integer getCodigoPlano() {
		return codigoPlano;
	}
	public void setCodigoPlano(Integer codigoPlano) {
		this.codigoPlano = codigoPlano;
	}
	public BigDecimal getValorPlano() {
		return valorPlano;
	}
	public void setValorPlano(BigDecimal valorPlano) {
		this.valorPlano = valorPlano;
	}
	public Integer getOdontologico() {
		return odontologico;
	}
	public void setOdontologico(Integer odontologico) {
		this.odontologico = odontologico;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
