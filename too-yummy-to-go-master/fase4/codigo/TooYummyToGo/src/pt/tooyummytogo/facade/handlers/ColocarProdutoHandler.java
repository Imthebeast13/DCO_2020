package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Produto;
import pt.tooyummytogo.domain.Stock;
import pt.tooyummytogo.domain.catalogos.CatProdutos;
import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.domain.utilizadores.Utilizador;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class ColocarProdutoHandler {
	
	//atributos
	private Comerciante c;
	private List<Stock> stocks = new ArrayList<>();  //2. UC5 iniciaVenda 	

	//construtor
	/**
	 * Construtor do colocar produto handler
	 * @param currentUser user corrente
	 */
	public ColocarProdutoHandler(Utilizador currentUser) {
		this.c = (Comerciante) currentUser;
	}

	/**
	 * UC5 -> iniciaVenda
	 * @return lista de tipos de produto
	 */
	public List<String> inicioDeProdutosHoje() {
		return c.getListaProdutos();
	}

	/**
	 * UC5 -> disponibilizaProduto
	 * @param string nome do produto em questao
	 * @param i quantidade do produto em questao
	 */
	public void indicaProduto(String string, int i) { //nome e qtd
		Produto p = c.getProduto(string);
		Stock s = p.addStock(string, i, p.getPreco()); //1.2 UC5 disponibilizaProduto 
		stocks.add(s); //2. UC5 disponibilizaVenda e //1.2.1 UC5 disponibilizaProduto 
	}

	/**
	 * UC5 -> confirmaDisponibilidade
	 * @param now hora inicial da venda
	 * @param plusHours hora final da venda
	 */
	public void confirma(LocalDateTime now, LocalDateTime plusHours) {
		c.criaVenda(now, plusHours, stocks); //1.1 UC5 confirmaDisponibilidade e //2. UC5 confirmaDisponibilidade
	}
}
