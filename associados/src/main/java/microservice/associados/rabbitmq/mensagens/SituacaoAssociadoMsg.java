package microservice.associados.rabbitmq.mensagens;

public class SituacaoAssociadoMsg {
	
	Integer codigoExame;
	Integer codigoAssociado;
	String situacao;
	String autorizacaoSgps;
	
	public Integer getCodigoExame() {
		return codigoExame;
	}
	public void setCodigoExame(Integer codigoExame) {
		this.codigoExame = codigoExame;
	}
	public Integer getCodigoAssociado() {
		return codigoAssociado;
	}
	public void setCodigoAssociado(Integer codigoAssociado) {
		this.codigoAssociado = codigoAssociado;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getAutorizacaoSgps() {
		return autorizacaoSgps;
	}
	public void setAutorizacaoSgps(String autorizacaoSgps) {
		this.autorizacaoSgps = autorizacaoSgps;
	}
	
}
