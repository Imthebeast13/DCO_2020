package pt.tooyummytogo.domain.catalogos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random; 

/**
 * Classe Catalogo de Produtos
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 André Firmino
 * fc53683 João Godinho
 */
import pt.tooyummytogo.domain.Produto;

/**
 * Classe Catalogo de Produtos
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class CatProdutos {

	//atributos
	private List<Produto> catProdutos = new ArrayList<>();
	private List<String> listaProdutos = new ArrayList<>();

	//metodos
	/**
	 * Metodo que retorna uma lista com os nomes dos produtos de um comerciante
	 * @return lista de tipos produtos de um comerciante
	 */
	public List<String> getListaNomeProdutos() {

		for(Produto prod : catProdutos) {
			if(!listaProdutos.contains(prod.getNomeProduto()))
				listaProdutos.add(prod.getNomeProduto());
		}

		return listaProdutos; //1.1 UC5 iniciaVenda
	}
	
	/**
	 * Metodo que retorna o produto com o nome indicado
	 * @param nome - o nome do produto a devolver
	 * @return p produto a retornar
	 */
	public Produto getProduto(String nome) {
		Random r = new Random();
		double preco = r.nextDouble();
		for( Produto p : catProdutos) {
			if(p.getNomeProduto().equals(nome)) {
				return p;
			}
		}
		return createProduto(nome, preco); //1.1 UC5 disponibilizaProduto e //1.1.1 UC5 disponibilizaProduto -> nome em vez de cod
	}
	
	/**
	 * Metodo que adiciona o produto p ao catalogo de produtos
	 * @param p produto a adicionar ah lista
	 */
	public void add(Produto p) {
		catProdutos.add(p);	//1.1.3 UC5 iniciaVenda
	}

	/**
	 * Metodo que regista um novo produto
	 * @param tp - nome do produto a registar
	 * @return p novo produto criado pelo comerciante
	 */
	public Produto createProduto(String tp, double r) {	
		//Random r = new Random();
		//double preco = r.nextInt(1000)/100.0;
		double preco = r;
		Produto p = new Produto(tp, preco); //1.1.1 UC5 iniciaVenda
		catProdutos.add(p); //1.1.3 UC5 iniciaVenda 
		return p;
	}
}