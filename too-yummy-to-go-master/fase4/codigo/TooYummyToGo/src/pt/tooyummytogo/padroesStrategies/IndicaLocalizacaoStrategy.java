package pt.tooyummytogo.padroesStrategies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.facade.dto.ComercianteInfo;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class IndicaLocalizacaoStrategy implements PesquisaComerciantes{

	//atributos
	//listaComerciantes.clear(); //limpa para criar uma lista de "raiz"
	private List<ComercianteInfo> ls = new ArrayList<>();


	//metodos
	@Override
	public List<ComercianteInfo> getListaComerciantes(CatPesquisa catPesquisa) {

		for(Map.Entry<String, Comerciante> entry : catPesquisa.getMapComerciantes().entrySet()) {

			if (entry.getValue().getCoordenadas().distanciaEmMetros(catPesquisa.getCoord()) <= catPesquisa.getRaioPadrao() &&
					(entry.getValue().getHoraInicioVenda().isBefore(LocalDateTime.now().plusHours(1))) &&
					(entry.getValue().getHoraFimVenda().isAfter(LocalDateTime.now()))) { //1.2 UC6 getListaComerciantesRaio5kms

				ls.add(new ComercianteInfo(entry.getValue().getNameUtilizador(), //1.3 UC6 getListaComerciantesRaio5kms
						entry.getValue().getCoordenadas(), 
						entry.getValue().toListaProdutosInfo(entry.getValue().getVenda().getStocks())));

			}

		}
		return ls; //raio de 5kms //1. UC6 getListaComerciantesRaio5kms
	}
}
