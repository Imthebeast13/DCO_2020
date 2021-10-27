package pt.tooyummytogo.domain.utilizadores;

import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

/**
 * Classe Utilizador
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Utilizador {

	//atributos
	private String username;
	private PosicaoCoordenadas localizacao;
	private String password;

	//contrutor
	/**
	 * Contrutor de um utilizador da app
	 * @param nomeUtilizador - o nome do utilizador
	 * @param passUtilizador - a pass do utilizador
	 */
	protected Utilizador(String nomeUtilizador, String passUtilizador) {
		this.username = nomeUtilizador;
		this.password = passUtilizador;
	}
	
	//metodos
	/**
	 * Metodo que devolve a pass de um utilizador
	 * @return a pass do utilizador
	 */
	protected String getPassword() {
		return this.password;
	}

	/**
	 * Metodo que retorna o nome do utilizador
	 * @return nome do utilizador
	 */
	public String getNameUtilizador() {
		return this.username;
	}

	/**
	 * @param pw password
	 * @return true se forem iguais false cc
	 */
	public boolean hasPassword(String pw) {
		return this.password.equals(pw);
	}

	/**
	 * Buscar as coordenadas
	 * @return coordenadas
	 */
	public PosicaoCoordenadas getCoordenadas() {
		return this.localizacao;
	}
	
	/**
	 * Adicionar as coordenadas
	 * @param coordenadas coordenadas
	 */
	public void addCoordenadas(PosicaoCoordenadas coordenadas) {
		this.localizacao = coordenadas;
	}
}
