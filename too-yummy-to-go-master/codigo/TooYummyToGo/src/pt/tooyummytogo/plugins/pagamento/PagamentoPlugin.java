package pt.tooyummytogo.plugins.pagamento;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public interface PagamentoPlugin {
	
	/**
	 * Metodo para criar cartao
	 * @param numero numero do cartao
	 * @param validade validade do cartao
	 * @param ccv ccv
	 */
	public void criaCartao(String numero, String validade, String ccv);
	
	/**
	 * Metodo para verificar o pagamento
	 * @param montante montante
	 * @return se o pagamento se verifica ou nao
	 */
	public boolean verificaPagamento(double montante);

}
