package pt.tooyummytogo.domain.utilizadores;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.ReservaEvent;
import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.pagamento.MetodoPagamento;
import pt.tooyummytogo.plugins.Sensor;

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



	/**
	 * 
	 * @author 
	 *
	 */
	public class EncomendaSensor extends Sensor<ReservaEvent> {

		private Reserva r;
		private Consumidor cons;
		
		public EncomendaSensor(Consumidor consumidor, Reserva res) {
			this.cons = consumidor;
			this.r = res;	
		}
		
		@Override
		public void novaReserva() {
			dispatchEvent(new ReservaEvent(r.getCodigoReserva()));
		}
		
		public String toString () {
			return this.cons.getNameUtilizador();
		}

	}



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
		return cartaoPagamento.isValid(total);
	}
}