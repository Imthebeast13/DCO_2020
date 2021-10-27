package pt.tooyummytogo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Reserva
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Reserva {
	
	//atributos
	private int codigo;
	private List<Encomenda> prodEncomendados = new ArrayList<>();

	//construtor
	/**
	 * Construtor
	 * @param listaDeEncomendas lista de encomendas
	 */
	public Reserva(List<Encomenda> listaDeEncomendas) {
		this.prodEncomendados = listaDeEncomendas;
	}

	//metodos
	/**
	 * Definir o codigo da reserva
	 * @param codigo codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Metodo que vai buscar o codigo da reserva
	 * @return codigo da reserva
	 */
	public String getCodigoReserva() {
		return String.valueOf(this.codigo);
	}
	
	/**
	 * Buscar a lista produtos encomendados
	 * @return lista produtos encomendados
	 */
	public List<Encomenda> getprodutos() {
		return prodEncomendados;
	}
}
