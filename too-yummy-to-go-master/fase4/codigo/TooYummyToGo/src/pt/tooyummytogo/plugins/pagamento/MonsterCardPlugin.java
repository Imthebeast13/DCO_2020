package pt.tooyummytogo.plugins.pagamento;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class MonsterCardPlugin implements PagamentoPlugin {
	
	//atributos
	private Card c;
	private MonsterCardAPI monster;

	//matodos
	
	@Override
	public void criaCartao(String numero, String validade, String ccv) {
		String [] datas = validade.split("/");
		c = new Card(numero, ccv, datas[0], "20" + datas[1]);
		monster = new MonsterCardAPI();
	}

	@Override
	public boolean verificaPagamento(double montante) {
		boolean verificaPagamento = false;
		if(monster.isValid(c)) {
			if(monster.block(c,	montante)) {
				if(monster.charge(c, montante))
					verificaPagamento = true;
					return verificaPagamento;
			}

		}
		verificaPagamento = false;
		return verificaPagamento;
	}

}
