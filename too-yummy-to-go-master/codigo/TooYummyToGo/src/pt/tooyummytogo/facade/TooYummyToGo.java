package pt.tooyummytogo.facade;



import java.util.Optional;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.domain.catalogos.CatUtilizadores;
import pt.tooyummytogo.domain.utilizadores.Utilizador;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;
import pt.tooyummytogo.plugins.Sensor;


/**
 * Esta eh a classe do sistema.
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class TooYummyToGo {

	//atributos
	private CatUtilizadores cat = new CatUtilizadores();


	// UC1
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(cat);
	}

	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 * 
	 * UC2
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<Utilizador> currentUser = cat.isUser(username, password);
		return currentUser.map(u -> new Sessao(u, cat));
	}

	// UC3
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler(cat);
	}

	public void print() {
		for(String u : cat.getNames()) {
			System.out.println(u);
		}
	}




}
