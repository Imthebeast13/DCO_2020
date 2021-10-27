package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.catalogos.CatUtilizadores;
import pt.tooyummytogo.facade.exception.UtilizadorExistenteException;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class RegistarUtilizadorHandler {
	
	//metodos
	private CatUtilizadores cat;
	
	//construtor
	public RegistarUtilizadorHandler(CatUtilizadores cat) {
		this.cat = cat;
	}
	
	//metodos
	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @throws UtilizadorExistenteException 
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
		try {
			cat.novoConsumidor(username, password);
		} catch (UtilizadorExistenteException e) {
			// Do nothing
		}
	}
}
