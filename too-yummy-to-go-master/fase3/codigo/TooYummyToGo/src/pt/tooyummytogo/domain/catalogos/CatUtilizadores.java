package pt.tooyummytogo.domain.catalogos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pt.tooyummytogo.domain.Stock;
import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.domain.utilizadores.Consumidor;
import pt.tooyummytogo.domain.utilizadores.Utilizador;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.exception.UtilizadorExistenteException;

/**
 * Classe Catalogo de Utilizadores
 * 
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class CatUtilizadores {

	//atributos
	private Map<String, Utilizador> catalogoUtilizadores = new HashMap<>();
	private Map<String, Consumidor> catalogoConsumidores = new HashMap<>();
	private Map<String, Comerciante> catalogoComerciantes = new HashMap<>();

	private List<ComercianteInfo> listaComerciantes = new ArrayList<>(); //1.1 UC6 OPs A, B1 e B2

	private PosicaoCoordenadas localizacaoConsumidor;

	//construtor(es)
	/**
	 * Construtor de um consumidor
	 * @param username - nome do consumidor
	 * @param password - pass do consumidor
	 * @throws UtilizadorExistenteException
	 */
	public void novoConsumidor(String username, String password) throws UtilizadorExistenteException {

		for(Map.Entry<String, Consumidor> entry : catalogoConsumidores.entrySet()) {
			if(entry.getKey().equals(username)) 
				throw new UtilizadorExistenteException();	
		}
		Consumidor c = new Consumidor(username, password);
		catalogoConsumidores.put(username, c);
		catalogoUtilizadores.put(username, c);
	}

	/**
	 * Contrutor de um comerciante
	 * @param username - nome do comerciante
	 * @param password - pass do comerciante
	 * @param catP 
	 * @param coordenadas - coordenadas do comerciante
	 * @throws UtilizadorExistenteException
	 */
	public void novoComerciante(String username, String password, 
			CatProdutos catP, PosicaoCoordenadas coordenadas) throws UtilizadorExistenteException {

		for(Map.Entry<String, Comerciante> entry : catalogoComerciantes.entrySet()) {
			if(entry.getKey().equals(username)) 
				throw new UtilizadorExistenteException();	
		}
		Comerciante cCorrente = new Comerciante(username, password);
		cCorrente.addCoordenadas(coordenadas);
		cCorrente.addCatProdutos(catP);
		catalogoComerciantes.put(username, cCorrente);
		catalogoUtilizadores.put(username, cCorrente);	
	}

	/**
	 * Verifica o login de um utilizador da aplicacao
	 * @param username - o nome de utilizador
	 * @param password - a pass do utilizador
	 * @return true se o utilizador eh valido
	 * 				cc
	 * 					false
	 */
	public Optional<Utilizador> isUser(String username, String password) {
		return Optional.ofNullable(catalogoUtilizadores.get(username)).filter(u -> u.hasPassword(password));
	}

	/**
	 * UC6 - getListaComerciantesRaio5kms
	 * Retorna a lista de comerciantes refinada pelo raio padrao
	 * @param coordinate - as coordenadas do utilizador
	 * @param raioPadrao - o raio padrao usado na procura de comerciantes
	 * @param consumidor 
	 * @return lista de comerciantes no raio de 5 kms
	 */
	public List<ComercianteInfo> indicaLocalizacao(PosicaoCoordenadas coordinate, int raio5kms) {
		localizacaoConsumidor = coordinate;
		listaComerciantes.clear(); //limpa para criar uma lista de "raiz"

		for(Map.Entry<String, Comerciante> entry : catalogoComerciantes.entrySet()) {
			
			if (entry.getValue().getCoordenadas().distanciaEmMetros(coordinate) <= raio5kms &&
					(entry.getValue().getHoraInicioVenda().isBefore(LocalDateTime.now().plusHours(1))) &&
					(entry.getValue().getHoraFimVenda().isAfter(LocalDateTime.now()))) { //1.2 UC6 getListaComerciantesRaio5kms
				
				listaComerciantes.add(new ComercianteInfo(entry.getValue().getNameUtilizador(), //1.3 UC6 getListaComerciantesRaio5kms
						entry.getValue().getCoordenadas(), 
						entry.getValue().toListaProdutosInfo(entry.getValue().getVenda().getStocks())));
			
			}

		}
		return listaComerciantes; //raio de 5kms //1. UC6 getListaComerciantesRaio5kms
	}

	/**
	 * UC6 - getListaComerRaio
	 * Metodo que devolve a lista de comerciantes filtrada pelo novo raio
	 * @param i - novo raio a usar para filtrar a lista de comerciantes a retornar
	 * @return lista de comerciantes refinada
	 */
	public List<ComercianteInfo> defineRaio(int i) {
		listaComerciantes.clear();

		for(Map.Entry<String, Comerciante> entry : catalogoComerciantes.entrySet()) {
			
			if(entry.getValue().getCoordenadas().distanciaEmMetros(localizacaoConsumidor) <= i &&
					(entry.getValue().getHoraInicioVenda().isBefore(LocalDateTime.now().plusHours(1))) &&
					(entry.getValue().getHoraFimVenda().isAfter(LocalDateTime.now()))) { //1.2 UC6 getListaComerRaio
				
				listaComerciantes.add(new ComercianteInfo(entry.getValue().getNameUtilizador(), //1.3 UC6 getListaComerRaio
						entry.getValue().getCoordenadas(), 
						entry.getValue().toListaProdutosInfo(entry.getValue().getVenda().getStocks())));
			}
		}
		return listaComerciantes; //1. UC6 getListaComerRaio
	}

	/**
	 * UC6 - definePeriodo
	 * Metodo que devolve uma lista dos comerciantes que tem produtos a venda
	 * numa janela temporal coincidente com a janela passada como argumento
	 * 
	 * @param now - hora de inicio de venda pretendida pelo consumidor
	 * @param plusHours - hora de fim de venda pretendida pelo consumidor
	 * @return lista de comerciantes filtrada pela janela temporal do consumidor
	 */
	public List<ComercianteInfo> definePeriodo(LocalDateTime now, LocalDateTime plusHours) { //1.2 UC6 definePeriodo
		listaComerciantes.clear();
		
		for(Map.Entry<String, Comerciante> entry : catalogoComerciantes.entrySet()) {
			
			if(entry.getValue().temVenda()) {
				if(entry.getValue().getHoraInicioVenda().isBefore(plusHours) && //1.2.1 UC6 definePeriodo
						now.isBefore(entry.getValue().getHoraFimVenda())) { 
					listaComerciantes.add(new ComercianteInfo(entry.getValue().getNameUtilizador(), //1.3 UC6 definePeriodo
							entry.getValue().getCoordenadas(),
							entry.getValue().toListaProdutosInfo(entry.getValue().getVenda().getStocks())));
				}
			}
		}
		return listaComerciantes; //1. UC6 definePeriodo
	}

	/**
	 * Devolve a lista de produtos que um comerciante tem disponiveis para venda
	 * @param comercianteInfo o comerciante ao qual se quer retirar a lista
	 * @return lista de produtos que o comerciante vende
	 */
	public List<ProdutoInfo> indicaComerciante(ComercianteInfo comercianteInfo) { 
		List<ProdutoInfo> listaProdutos = new ArrayList<>(); //2. UC7 indicaComerciante e //2.1.1 UC7 indicaComerciante

		if(catalogoComerciantes.containsKey(comercianteInfo.toString())) { //2.1.2 UC7 indicaComerciante
			for(Stock s : catalogoComerciantes.get(comercianteInfo.toString()).getVenda().getStocks()) { //2.1 UC7 indicaComerciante
				listaProdutos.add(new ProdutoInfo(s.getNameProdutos(), s.getQtd(), s.getPreco())); //2.1.3 UC7 indicaComerciante
			}

		}
		return listaProdutos; 
	}

	/**
	 * Metodo que retorna o comerciante pedido
	 * @param nome - do comerciante a retornar
	 * @return comerciante 
	 */
	public Comerciante getComercianteCorrente(String nome) {
		return catalogoComerciantes.get(nome); //1.1 UC7 disponibilizaProduto
	}

	/**
	 * Apenas para imprimir os nomes dos utilizadores da aplicacao (nao necessario)
	 * @return lista de nomes dos utilizadores
	 */
	public List<String> getNames() { //1.1.1 UC7 disponibilizaProduto
		List<String> s = new ArrayList<>();
		for(Map.Entry<String, Utilizador> entry : catalogoUtilizadores.entrySet()) { //1.1 UC7 indicaComerciante
			s.add(entry.getKey());
		}
		return s;
	}
}
