package pt.tooyummytogo.domain;

import pt.tooyummytogo.facade.dto.ProdutoInfo;

/**
 * Classe Stock
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Stock {

	//atributos
	private int quantidade;
	private String nome;
	private Venda v;
	private int counter;
	private double preco;

	//construtor
	/**
	 * Construtor
	 * @param nome nome do stock de produto
	 * @param nrQuantidade quantidade do stock de produto
	 * @param preco preco do stock de stock de produto
	 */
	public Stock(String nome, int nrQuantidade, double preco) {
		this.quantidade = nrQuantidade;
		this.nome = nome;
		this.preco = preco;
	}

	//metodos
	/**
	 * Metodo que devolve o nome do produto em stock
	 * @return nome do prouto em stock
	 */
	public String getNameProdutos() {
		return this.nome;
	}

	/**
	 * Metodo que retorna a quantidade em stock de um produto
	 * @return quantidade em stock
	 */
	public int getQtd() {
		return this.quantidade;
	}


	/**
	 * Criar uma encomenda
	 * @param p - o produtoInfo sobre o qual se cria a encomenda
	 * @param i - a qtd pretendida desse produtoInfo
	 */
	public Encomenda criaEncomenda(ProdutoInfo p, int i) {
		Encomenda e = new Encomenda(p.getNome(), i, p.getPreco());
		this.quantidade -= i;
		p.setQtd(quantidade);
		counter++;
		return e;
	}

	/**
	 * adiciona o stock corrente a uma venda
	 */
	public void adiciona() {
		v.addStock(this);

	}

	/**
	 * Buscar o preco do stock de um produto
	 * @return preco do stock de um produto
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * Buscar a quantidade do stock de um produto
	 * @param qtd quantidade do stock de um produto
	 */
	public void setQtd(int qtd) {
		this.quantidade = qtd;
	}
}
