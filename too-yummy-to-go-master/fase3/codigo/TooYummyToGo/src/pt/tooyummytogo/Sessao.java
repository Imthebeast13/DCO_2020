package pt.tooyummytogo;

import pt.tooyummytogo.domain.catalogos.CatUtilizadores;
import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.domain.utilizadores.Consumidor;
import pt.tooyummytogo.domain.utilizadores.Utilizador;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class Sessao {
	
	private Consumidor consumidor;
	private Comerciante comerciante;
	private CatUtilizadores cat;
	
	
	public Sessao (Utilizador u, CatUtilizadores cat) {
		this.cat = cat;
		if(u.getCoordenadas() != null) {
			this.comerciante = (Comerciante) u; 
		} else {
			this.consumidor = (Consumidor) u;
			
		}
	}
	
	// UC4
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() {
		return new AdicionarTipoDeProdutoHandler(comerciante);
	}

	// UC5
	public ColocarProdutoHandler getColocarProdutoHandler() {
		return new ColocarProdutoHandler(comerciante);
	}

	//UC6 e UC7
	public EncomendarHandler getEncomendarComerciantesHandler() {
		return new EncomendarHandler(consumidor, cat);
	}
}
//TODOOOOO - testar git 
