package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.TooMuchQuantityException;

/**
 * Classe Venda
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Venda {
	
	//atributos
	private List<Stock> stocks = new ArrayList<>();
	private LocalDateTime h_inicio;
	private LocalDateTime h_fim;
	
	//construtor
	/**
	 * Contrutor de uma venda
	 * @param now - hora de inicio da venda
	 * @param plusHours - hora do final da venda
	 * @param stocks 
	 */
	public Venda (LocalDateTime now, LocalDateTime plusHours, List<Stock> stocks) {
		this.h_inicio = now;
		this.h_fim = plusHours;
		this.stocks = stocks;
	}

	//metodos
	/**
	 * Adiciona um novo stock aos stocks do comerciante
	 * @param stock
	 */
	public void addStock(Stock stock) {
		stocks.add(stock);	
	}
		
	/**
	 * Esta-se a ir buscar o stock de um produto
	 * @return stock de um produto
	 */
	public List<Stock> getStocks() {
		return this.stocks;
	}
	
	/**
	 * Metodo que retorna a hora de inicio da venda
	 * @return hora de inicio da venda
	 */
	public LocalDateTime getHoraInicio() {
		return this.h_inicio;
	}
	
	/**
	 * Metodo que retorna a hora de fim da venda
	 * @return hora de fim da venda
	 */
	public ChronoLocalDateTime<?> getHoraFim() {
		return this.h_fim;
	}
	
	/**
	 * Indicar o produto
	 * @param p produto
	 * @param qtd quantidade
	 * @throws TooMuchQuantityException eh lancada quando a quantidade inserida eh maior que a quantidade disponivel
	 */
	public void indicaProduto(ProdutoInfo p, int qtd) throws TooMuchQuantityException {
		for(int i = 0; i < stocks.size(); i++ ) {
			if(p.getNome().equals(stocks.get(i).getNameProdutos())) {
				if(p.getQtd() > qtd) {
					stocks.get(i).criaEncomenda(p, i);		
				} else {
					System.out.println("Quantidade de produto excedida"+ p.getNome() );
					throw new TooMuchQuantityException();
				}
			} 
		} 
	}

	/**
	 * Metodo que atualiza a quantidade disponivel em stock
	 * @param nome do stock
	 * @param qtd actualizada do stock
	 */
	public void setQtd(String nome, int qtd) {
		for(Stock s : stocks) {
			if(s.getNameProdutos().equals(nome)) {
				s.setQtd(qtd);
			}
		}
	}
}
