package microservice.associados.repository;

import org.springframework.stereotype.Repository;

import microservice.associados.models.AssociadoModel;

@Repository
public class AssociadoRepository {

	public AssociadoModel buscaAssociadoPorLogin(String login) {
		
		AssociadoModel associadoModel = new AssociadoModel();
	
		if(login.equals("usuarioAssociado")) {
			associadoModel = this.associado1(associadoModel);
		}
		
		return associadoModel;
	}
	
	public String salvaPlanoAssociado(AssociadoModel associadoModel) {
		
		return "Atualizados dados do associado " + associadoModel.getNome();
	}

	public AssociadoModel buscaAssociadoPorCodigo(Integer codigoAssociado) {
		
		AssociadoModel associadoModel = new AssociadoModel();
		associadoModel = this.associado1(associadoModel);
		if(associadoModel.getCodigo() == codigoAssociado) {
			return associadoModel;
		}

		return null;
	}
	
	AssociadoModel associado1(AssociadoModel associadoModel) {
		
		associadoModel.setCodigo(1);
		associadoModel.setIdade(35);
		associadoModel.setLogin("usuarioAssociado");
		associadoModel.setNome("Nome do usuário associado");
		associadoModel.setSituacao("Ativo");
		
		return associadoModel;
		
	}
	
}
