package pt.tooyummytogo.facade.dto;

import java.util.List;

public class ComercianteInfo {
	
	private String nome;
	private PosicaoCoordenadas localizacao;
	private List<ProdutoInfo> produtos;

	/**
	 * Construtor do comerciante fachada
	 * @param nome - nome do comerciante
	 * @param coordenadas - coordenadas do comerciante
	 * @param listaProdutos - lista filtrada de produtos que o comerciante vende  
	 */
	public ComercianteInfo(String nome, PosicaoCoordenadas coordenadas, List<ProdutoInfo> listaProdutos) {
		this.nome = nome;
		this.localizacao = coordenadas;
		this.produtos = listaProdutos;
	}
	
	/**
	 * @return nome do comerciante
	 */
	public String toString() {
		return this.nome;
	}

	/**
	 * 
	 * @return lista dos ProdutoInfo do comerciante
	 */
	public List<ProdutoInfo> getProdutos() {
		return this.produtos;
	}

	public void setQtd(String string, int qtd) {
		for(ProdutoInfo p : produtos) {
			if(p.getNome().contentEquals(string)) {
				p.setQtd(qtd);
			}
		}
	}
}
