package pt.tooyummytogo.domain.utilizadores;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.pagamento.MetodoPagamento;

/**
 * Classe Consumidor
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class Consumidor extends Utilizador {

	//atributos
	private List<Reserva> listaDeReservas = new ArrayList<>();

	//Construtor
	/**
	 * Construtor do consumidor
	 * @param nomeConsumidor - nome de utilizador do consumidor
	 * @param passConsumidor - pass do consumidor
	 */
	public Consumidor(String nomeConsumidor, String passConsumidor) {
		super(nomeConsumidor, passConsumidor);
	}

	//metodos
	/**
	 * Adicionar uma reserva
	 * @param r reserva
	 */
	public void addReserva(Reserva r) { //4.1d UC7 criaReserva
		listaDeReservas.add(r);
	}

	/**
	 * Metodo de pagamento valido com os dados respetivos
	 * @param num_cartao numero do cartao
	 * @param validade_cartao validade do cartao
	 * @param ccv2 ccv2
	 * @return se o cartao de pagamento eh valido
	 */
	public boolean metodoPagValido(String num_cartao, String validade_cartao, String ccv2, double total) {
		MetodoPagamento cartaoPagamento = new MetodoPagamento(num_cartao, validade_cartao, ccv2);
		return cartaoPagamento.isValid(num_cartao, validade_cartao, ccv2, total);
	}
}