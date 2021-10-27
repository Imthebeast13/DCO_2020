package pt.tooyummytogo.domain;

/**
 * Classe Produto
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Produto {
	
	//atributos
	private String nome;
	private Produto codigo;
	private double preco;
	private Stock s;
	
	//construtor
	/**
	 * Contrutor de um novo produto
	 * @param tp - nome do novo tipo produto
	 * @param preco 
	 */
	public Produto(String tp, double preco) {
		this.nome = tp;
		this.preco = preco;
	}
	
	//metodos
	/**
	 * Metodo que devolve o nome do produto
	 * @return nome do produto
	 */
	public String getNomeProduto() {
		return this.nome;
	}
	
	/**
	 * Codigo de um determinado produto
	 * @return codigo de um determinado produto
	 */
	public Produto getCodProduto() {
		return this.codigo; //1.1.2 UC5 iniciaVenda
	}
	
	//Vamos buscar o stock nao por este produto, mas sim pelo
	//produtoInfo de acordo com o handler dado pelo prof
	/**
	 * Buscar um stock de um produto
	 * @return stock de um produto
	 */
	public Stock getStockProduto() {	
		return this.s;
	}

	/**
	 * Buscar o preço de um produto
	 * @return preco do produto
	 */
	public double getPreco() {
		return this.preco;
	}
	
	/**
	 * Adicionar um produto ao stock
	 * @param nomeProduto nome do produto
	 * @param quantidade quantidade de produto
	 * @param preco preco do produto a add
	 * @return produto add ao stock
	 */
	public Stock addStock(String nomeProduto, int quantidade, double preco) {
		s = new Stock(this.nome, quantidade, this.preco); //1.2.1 UC5 confirmaDisponibilidade
		return s;
	}
}
