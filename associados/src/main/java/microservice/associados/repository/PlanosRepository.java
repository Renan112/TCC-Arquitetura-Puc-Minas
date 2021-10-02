package microservice.associados.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import microservice.associados.models.PlanoModel;

@Repository
public class PlanosRepository {

	public List<PlanoModel> listaPlanos(){
		
		PlanoModel planosModel = null;
		List<PlanoModel> listaPlanosModel = new ArrayList<PlanoModel>(); 
		
		planosModel = new PlanoModel();
		planosModel.setCodigo(1);
		planosModel.setNome("enfermaria");
		planosModel.setDescricao("plano mais básico com co-participação");
		planosModel.setValorBase(BigDecimal.valueOf(200.00));
		listaPlanosModel.add(planosModel);
		
		planosModel = new PlanoModel();
		planosModel.setCodigo(2);
		planosModel.setNome("apartamento");
		planosModel.setDescricao("plano intermediário sem co-participação");
		planosModel.setValorBase(BigDecimal.valueOf(400.00));
		listaPlanosModel.add(planosModel);
		
		planosModel = new PlanoModel();
		planosModel.setCodigo(3);
		planosModel.setNome("vip");
		planosModel.setDescricao("plano com mais serviços e atendimentos incluídos");
		planosModel.setValorBase(BigDecimal.valueOf(1000.00));
		listaPlanosModel.add(planosModel);
		
		return listaPlanosModel;
	}
	
}
