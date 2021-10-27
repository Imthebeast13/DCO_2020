package pt.tooyummytogo.pagamento;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class MetodoPagamento {
	
	//atributos
	private String nr = "365782312312";
	private String data ="02/21";
	private String ccv= "765";
	private MonsterCardAPI monster;
	private Card c;
	
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
		String [] datas = data.split("/");
		c = new Card(nr, ccv, datas[0], "20" + datas[1]);
		monster = new MonsterCardAPI();
	}

	/**
	 * Verificar se eh valido
	 * @param num_cartao numero do cartao
	 * @param validade_cartao validade do cartao
	 * @param ccv2 ccv2
	 * @param total total
	 * @return se for valido valid true cc false
	 */
	public boolean isValid(String num_cartao, String validade_cartao, String ccv2, double total) {
		monster.block(c, total); //verificar se o plafon eh maior que o preco a pagar
		if(monster.isValid(c)) { //ver se os dados do uti estao validos
			monster.charge(c, total); //retirar o dinheiro do cartao para fazer o pagamento
			return true;
		} else {
			return false;
		}
	}
}
