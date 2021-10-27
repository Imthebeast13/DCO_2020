package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.domain.utilizadores.Utilizador;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class AdicionarTipoDeProdutoHandler {
	
	//atributos
	private Comerciante c;

	//construtor
	public AdicionarTipoDeProdutoHandler(Utilizador currentUser) {
		this.c = (Comerciante) currentUser;
	}

	//metodos
	/**
	 * 
	 * @param tp
	 */
	public void registaTipoDeProduto(String tp) {
		c.registarNovoProduto(tp);
	}
}

