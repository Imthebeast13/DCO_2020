package pt.tooyummytogo.padroesStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class DefinePeriodoStrategy implements PesquisaComerciantes {
	
	//atributos
	private List<ComercianteInfo> ls = new ArrayList<>();

	//metodos
	@Override
	public List<ComercianteInfo> getListaComerciantes(CatPesquisa cat) {
		for(Map.Entry<String, Comerciante> entry : cat.getMapComerciantes().entrySet()) {
			
			if(entry.getValue().temVenda()) {
				if(entry.getValue().getHoraInicioVenda().isBefore(cat.getHoras()[1]) && //1.2.1 UC6 definePeriodo
						cat.getHoras()[0].isBefore(entry.getValue().getHoraFimVenda())) { 
					ls.add(new ComercianteInfo(entry.getValue().getNameUtilizador(), //1.3 UC6 definePeriodo
							entry.getValue().getCoordenadas(),
							entry.getValue().toListaProdutosInfo(entry.getValue().getVenda().getStocks())));
				}
			}
		}
		return ls; //1. UC6 definePeriodo
	}

}
