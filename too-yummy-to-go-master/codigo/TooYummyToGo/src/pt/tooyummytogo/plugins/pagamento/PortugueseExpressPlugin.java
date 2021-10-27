package pt.tooyummytogo.plugins.pagamento;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class PortugueseExpressPlugin implements PagamentoPlugin {

	//atributos
	private PortugueseExpress c = new PortugueseExpress();

	//metodos
	@Override
	public void criaCartao(String numero, String validade, String ccv) {
		String [] datas = validade.split("/");
		c.setNumber(numero);
		c.setMonth(Integer.parseInt(datas[0]));
		c.setYear(Integer.parseInt("20" + datas[1]));
		c.setCcv(Integer.parseInt(ccv));

	}

	@Override
	public boolean verificaPagamento(double montante) {
		boolean verificaPagamento = false;
		if(c.validate()) {
			try {
				c.block(montante);
				c.charge(montante);
			} catch (InvalidCardException e) {
				return verificaPagamento;
			}
		verificaPagamento = true;
		}
		return verificaPagamento;
	}

}
