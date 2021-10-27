package pt.tooyummytogo.padroesStrategies;

import java.util.List;
import java.util.Map;

import pt.tooyummytogo.facade.dto.ComercianteInfo;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public interface PesquisaComerciantes {
	
	/**
	 * Metodo getListaComerciantes
	 * @param cat catalogo de pesquisa
	 * @return lista de comerciantes
	 */
	public List<ComercianteInfo> getListaComerciantes (CatPesquisa cat);
	
}
