package pt.tooyummytogo.facade.dto;

public class ProdutoInfo {
	
	private String nome;
	private int qtd;
	private double preco;
	
	/**
	 * 
	 * @param nome
	 * @param qtd
	 */
	public ProdutoInfo(String nome, int qtd, double preco) {
		this.nome = nome;
		this.qtd = qtd;
		this.preco = preco;
	}
	
	/**
	 * Metodo que retorna o nome do stock
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo que retorna a quantidade disponivel em stock de um produto
	 * @return qtd quantidade disponivel de um stock de produto
	 */
	public int getQtd() {
		return this.qtd;
	}

	/**
	 * Metodo que atualiza a quantidade de ProdutoInfo apos ser criada uma nova encomenda
	 * @param qtdAtualizada quantidade disponivel em ProdutoInfo apos uma nova encomenda
	 */
	public void setQtd(int qtdAtualizada) {
		this.qtd = qtdAtualizada;
		
	}
	
	public double getPreco() {
		return this.preco;
	}

}
