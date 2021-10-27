package pt.tooyummytogo.pagamento;

import java.util.List;

import pt.tooyummytogo.plugins.pagamento.PagamentoPlugin;
import pt.tooyummytogo.plugins.pagamento.PluginFactory;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class MetodoPagamento {

	//atributos
	private String nr;
	private String data;
	private String ccv;
	//private MonsterCardAPI monster;
	//private Card c;
	

	/**
	 * Construtor do Metodo de Pagamento
	 * @param nr numero do cartao
	 * @param data data do cartao
	 * @param ccv ccv
	 */
	public MetodoPagamento(String nr, String data, String ccv) {

		this.nr = nr;
		this.data = data;
		this.ccv = ccv;
	}

	/**
	 * Verificar se eh valido
	 * @param num_cartao numero do cartao
	 * @param validade_cartao validade do cartao
	 * @param ccv2 ccv2
	 * @param total total
	 * @return se for valido valid true cc false
	 */
	public boolean isValid(double total) {
		
		//novo metodo de pagar/criar cartoes
		List<PagamentoPlugin> plugins = PluginFactory.getInstance().getPaymentPlugins(); 
		for(PagamentoPlugin p : plugins) {
			p.criaCartao(nr, data, ccv);
			
			if (p.verificaPagamento(total)) {
				return true;
			}
			
		}
		return false;
	}

}
