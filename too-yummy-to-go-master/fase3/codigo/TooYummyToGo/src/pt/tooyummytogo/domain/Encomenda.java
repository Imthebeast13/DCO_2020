package pt.tooyummytogo.domain;

import java.text.NumberFormat;

/**
 * Classe Encomenda
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Encomenda {
	
	//atributos
	private String nome;
	private int quantidade;
	private double preco;
	
	/**
	 * Construtor
	 * @param preco 
	 * @param nome - o nome do produto em stock a por na encomenda
	 * @param qtd - a qtd de stock a por na encomenda
	 */
	public Encomenda (String nomeProdutos, int qtdProdutos, double preco) {
		this.nome = nomeProdutos;
		this.quantidade = qtdProdutos;
		this.preco = preco;
	}
	
	//metodos
	/**
	 * Buscar uma encomenda com qtd, nome e preco
	 * @return encomenda com qtd, nome e preco
	 */
	public String getEncomenda() {
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setMaximumFractionDigits(2);
		nFormat.setGroupingUsed(false);
		return this.nome +":\nQuantidade:" + this.quantidade + "\tPreço: " + nFormat.format(this.preco) + " €\n";
	}

	/**
	 * Quantidade do produto a encomendar
	 * @return quantidade do produto a encomendar
	 */
	public int getQtd() {
		return this.quantidade;
	}

	/**
	 * Nome do produto a encomendar
	 * @return nome do produto a encomendar
	 */
	public String getNome() {
		return this.nome;
	}
}