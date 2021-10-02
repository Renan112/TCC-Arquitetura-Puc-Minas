package microservice.SalvarDadosCiencia.rabbitmq.mensagens;

public class SalvaDadosGenericoMsg {

	Integer codigoTabela;
	String json;
	
	public Integer getCodigoTabela() {
		return codigoTabela;
	}
	public void setCodigoTabela(Integer codigoTabela) {
		this.codigoTabela = codigoTabela;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
}
