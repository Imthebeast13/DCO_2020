package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.catalogos.CatProdutos;
import pt.tooyummytogo.domain.catalogos.CatUtilizadores;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.exception.UtilizadorExistenteException;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class RegistarComercianteHandler {
	
	//atributos
	private CatUtilizadores cat;
	
	//construtor
	public RegistarComercianteHandler(CatUtilizadores cat) {
		this.cat = cat;
	}

	//metodos
	/**
	 * Regista um Comerciante.
	 * @param Username
	 * @param Password
	 * @throws UtilizadorExistenteException 
	 * @ensures existe um comerciante com esse username
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas p) {
		CatProdutos catP = new CatProdutos();
		try {
			cat.novoComerciante(username, password, catP, p);
		} catch (UtilizadorExistenteException e) {
			// Do nothing 
		}
		
	}
}
