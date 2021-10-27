package pt.tooyummytogo.domain.utilizadores;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.ReservaEvent;
import pt.tooyummytogo.domain.Encomenda;
import pt.tooyummytogo.domain.Produto;
import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.domain.Stock;
import pt.tooyummytogo.domain.Venda;
import pt.tooyummytogo.domain.catalogos.CatProdutos;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.TooMuchQuantityException;
import utils.observer.Observer;

/**
 * Classe Comerciante
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Comerciante extends Utilizador implements Observer<ReservaEvent> {
	
	@Override
	public void handleNewEvent(ReservaEvent e) {
		for(Reserva r : lr) {
			if(r.getCodigoReserva().contentEquals(e.getCodigoReserva())) {
				System.out.println("Reserva nº " + r.getCodigoReserva() + "\n");
				printReserva(r.getprodutos());
			}
		}
	}
	
	public void printReserva(List<Encomenda> le) {
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setMaximumFractionDigits(2);
		nFormat.setGroupingUsed(false);
		double total = 0;
		for(Encomenda e : le) {
			total += e.getPreco()*e.getQtd();
			System.out.println(e.getNome() + "\nQuantidade: " + e.getQtd() + "\nPreco: " + e.getPreco() + "€\n");
		}
		System.out.println("Total a pagar: " + nFormat.format(total) + "€");
	}

	//atributos
	private Venda v;
	private CatProdutos catProdutos;
	private Reserva r;
	private List<Reserva> lr = new ArrayList<>();

	//construtor
	/**
	 * Construtor de um comerciante
	 * @param nomeComerciante - nome de utilizador do comerciante
	 * @param passComerciante - pass do comerciante
	 * @param coordenadas 
	 */
	public Comerciante(String nomeUtilizador, String passUtilizador) {
		super(nomeUtilizador, passUtilizador);
	}

	//metodos
	/**
	 * Metodo que retorna a lista de produtos do comerciante
	 * @return lista de produtos do comerciante
	 */
	public List<String> getListaProdutos() {
		return catProdutos.getListaNomeProdutos();	//1. UC5 iniciaVenda
	}

	/**
	 * Regista um novo produto
	 * @param tp - nome do novo produto
	 */
	public void registarNovoProduto(String tp, double r) {
		catProdutos.createProduto(tp, r);

	}

	/**
	 * Devolve um produto atraves do seu nome 
	 * @param string - nome do produto a retornar
	 * @return produto com o nome pedido
	 */
	public Produto getProduto(String string) {
		return catProdutos.getProduto(string);
	}

	/**
	 * Cria uma nova venda
	 * @param now - hora de inicio da venda
	 * @param plusHours - hora do fim da venda 
	 * @param stocks 
	 */
	public void criaVenda(LocalDateTime now, LocalDateTime plusHours, List<Stock> stocks) {
		this.v = new Venda(now, plusHours, stocks); //1. UC5 confirmaDisponibilidade
	}

	/**
	 * Metodo que retorna a hora de inicio de uma venda
	 * @return hora de inicio da venda
	 */
	public LocalDateTime getHoraInicioVenda() {
		return v.getHoraInicio();
	}

	/**
	 * Metodo que retorna a hora de fim de uma venda
	 * @return hora de fim da venda
	 */
	public ChronoLocalDateTime<?> getHoraFimVenda() {
		return v.getHoraFim();
	}

	/**
	 * Metodo que converte a lista de stocks do comerciante numa
	 * lista de ProdutoInfo
	 * @param stocks - lista de stocks do comerciante
	 * @return lista dos ProdutoInfo que o comerciante tem para venda
	 */
	public List<ProdutoInfo> toListaProdutosInfo(List<Stock> stocks) {
		List<ProdutoInfo> lista = new ArrayList<>();
		for(Stock s : stocks) {
			lista.add(new ProdutoInfo(s.getNameProdutos(), s.getQtd(), s.getPreco())); 
		}
		return lista;
	}

	/**
	 * 
	 * @param p produto a adicionar
	 * @param qtd quantidade do produto em questao
	 * @throws TooMuchQuantityException Excecao que eh lancada quando se indica 
	 * mais quantidade do que a que se tem em stock
	 */
	public void addProduto(ProdutoInfo p, int qtd) throws TooMuchQuantityException {
		v.indicaProduto(p, qtd); //1. UC5 disponibilizaProduto
	}

	/**
	 * Metodo que cria uma reserva, adiciona-a ah lista de reservas do
	 * comerciante e retorna o codigo
	 * @param listaDeEncomendas 
	 * @return codigo da reserva criada
	 */
	public Reserva fazReserva(List<Encomenda> listaDeEncomendas) {
		r = new Reserva(listaDeEncomendas); //3.1 UC7 criaReserva
		lr.add(r); //3.2 UC7 criaReserva
		r.setCodigo(lr.size());
		return r;
	}

	/**
	 * Metodo que define o catalogo de produtos do comerciante atual
	 * @param catP
	 */
	public void addCatProdutos(CatProdutos catP) {
		this.catProdutos = catP;
	}

	/**
	 * Verifica se o comerciante tem uma venda 
	 * @return true se tem venda
	 */
	public boolean temVenda() {
		if(v == null) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo que retorna a quantidade de um stock
	 * @param nome
	 * @return
	 */
	public int getQtd(String nome) {
		return catProdutos.getProduto(nome).getStockProduto().getQtd();
	}

	/**
	 * Metodo que atualiza a quantidade de stock disponivel de um produto
	 * @param nome do stock
	 * @param qtd nova quantidade disponivel em stock
	 */
	public void setQtd(String nome, int qtd) {
		v.setQtd(nome, qtd);
	}

	/**
	 * Metodo que retorna a venda do comerciante
	 * @return v venda
	 */
	public Venda getVenda() {
		return this.v;
	}



}
