package pt.tooyummytogo.facade.handlers;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Encomenda;
import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.domain.catalogos.CatUtilizadores;
import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.domain.utilizadores.Consumidor;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.TooMuchQuantityException;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class EncomendarHandler {

	//atributos
	private CatUtilizadores catUtilizadores;
	private Consumidor consumidor;
	private Reserva r;
	private int raio5kms = 5000;
	private List<Encomenda> listaDeEncomendas = new ArrayList<>();
	private Comerciante c_corrente;
	private ComercianteInfo cInfoCorrente;
	private double total;

	//construtor
	/**
	 * Construtor do encomendar handler
	 * @param consumidor consumidor
	 * @param cat catalogo de utilizadores
	 */
	public EncomendarHandler(Consumidor consumidor, CatUtilizadores cat) {
		this.consumidor = consumidor;
		this.catUtilizadores = cat;
	}

	//metodos
	/**
	 * UC6 -> indicaLocalizacao
	 * @param coordinate
	 * @return
	 */
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas coordinate) {
		return catUtilizadores.indicaLocalizacao(coordinate, raio5kms);
	}

	/**
	 * Redefinir o raop
	 * @param i raio
	 * @return
	 */
	public List<ComercianteInfo> redefineRaio(int i) {
		return catUtilizadores.defineRaio(i);
	}

	/**
	 * Redefinir Periodo
	 * @param now hora atual
	 * @param plusHours hora final
	 * @return lista de comerciantes info
	 */
	public List<ComercianteInfo> redefinePeriodo(LocalDateTime now, LocalDateTime plusHours) {
		return catUtilizadores.definePeriodo(now, plusHours);
	}

	/**
	 * UC7 - indicaComerciante
	 * @param comercianteInfo
	 * @return
	 */
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) { //1.1 UC7 indicaComerciante
		cInfoCorrente = comercianteInfo; //1. UC7 indicaComerciante
		return catUtilizadores.indicaComerciante(comercianteInfo);
	}

	/**
	 * Indica Produto
	 * @param p produto
	 * @param i indice
	 * @throws TooMuchQuantityException 
	 */
	public void indicaProduto(ProdutoInfo p, int i) throws TooMuchQuantityException {
		c_corrente = catUtilizadores.getComercianteCorrente(cInfoCorrente.toString()); //1. UC7 disponibilizaProduto
		int qtd = c_corrente.getQtd(p.getNome()); //2. UC7 disponibilizaProduto
		if(qtd >= i && i > 0) {
			Encomenda eCorrente = new Encomenda(p.getNome(), i, p.getPreco());
			total += p.getPreco()*i;
			listaDeEncomendas.add(eCorrente); //3. ; 3.1 e 3.2 UC7 disponibilizaProduto
			c_corrente.setQtd(p.getNome(), qtd - i); //stock interno
			cInfoCorrente.setQtd(p.getNome(), qtd - i); //stock mostrado ao consumidor 
		} else {
			throw new TooMuchQuantityException();
		}

	}

	/**
	 * UC7 - criaReserva
	 * @param num_cartao
	 * @param validade_cartao
	 * @param ccv2
	 * @return
	 * @throws TooMuchQuantityException
	 */
	public String indicaPagamento(String num_cartao, String validade_cartao, String ccv2) throws TooMuchQuantityException {
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setMaximumFractionDigits(2);
		nFormat.setGroupingUsed(false);
		if (!consumidor.metodoPagValido(num_cartao, validade_cartao, ccv2, total)) { //2. UC7 criaReserva
			for(Encomenda e : listaDeEncomendas) {
				c_corrente.setQtd(e.getNome(), c_corrente.getQtd(e.getNome()) + e.getQtd());
				cInfoCorrente.setQtd(e.getNome(), c_corrente.getQtd(e.getNome()) + e.getQtd());
				throw new TooMuchQuantityException();
			}
		} else { 
			r = c_corrente.fazReserva(listaDeEncomendas); //3. UC7 criaReserva
			for(Encomenda e : r.getprodutos()) {
				System.out.println(e.getEncomenda());

			}
			System.out.println("O total a pagar e: " + nFormat.format(total) + " ?");
			consumidor.addReserva(r); //4. UC7 criaReserva
		}
		return r.getCodigoReserva(); //5. UC7 criaReserva
	}
}
