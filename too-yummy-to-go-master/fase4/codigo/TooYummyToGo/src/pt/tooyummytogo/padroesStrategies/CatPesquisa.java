package pt.tooyummytogo.padroesStrategies;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.tooyummytogo.domain.utilizadores.Comerciante;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class CatPesquisa {

	//atributos
	private PosicaoCoordenadas coord;
	private int novoRaio;
	private LocalDateTime now;
	private LocalDateTime plusHours;
	private List<ComercianteInfo> ls;
	private Map<String, Comerciante> mapaDeComerciantes = new HashMap<>();
	private static final double RAIO5KM = 5000;

	//metodos
	/**
	 * Metodo setMapa
	 * @param mapa mapa string comerciante
	 */
	public void setMapa(Map<String, Comerciante> mapa) {
		for(Map.Entry<String, Comerciante> entry : mapa.entrySet()) {
			this.mapaDeComerciantes.put(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * Metodo setCoordenadas
	 * @param coordenadasConsumidor coordenadas do consumidor
	 */
	public void setCoordenadasConsumidor(PosicaoCoordenadas coordenadasConsumidor) {
		this.coord = coordenadasConsumidor;
	}

	/**
	 * Metodo setHoras
	 * @param now hora atual
	 * @param plusHours hora final
	 */
	public void setHoras(LocalDateTime now, LocalDateTime plusHours) {
		this.now = now;
		this.plusHours = plusHours;
	}

	/**
	 * Metodo getCoord 
	 * @return coordenadas
	 */
	public PosicaoCoordenadas getCoord() {
		return this.coord;
	}

	/**
	 * Metodo getRaio
	 * @return novo raio
	 */
	public int getRaio() {
		return this.novoRaio;
	}

	/**
	 * Metodo getMapComerciantes
	 * @return mapa de comerciantes
	 */
	public Map<String, Comerciante> getMapComerciantes() {
		return this.mapaDeComerciantes;
	}

	/**
	 * Metodo getListaComerciantes
	 * @return lista
	 */
	public List<ComercianteInfo> getListaComerciantes() {
		return this.ls;
	}

	/**
	 * Metodo getRaioPadrao
	 * @return RAIO5KM
	 */
	public double getRaioPadrao() {
		return RAIO5KM;
	}

	/**
	 * Metodo setNovoRaio
	 * @param raio raio
	 */
	public void setNovoRaio(int raio) {
		this.novoRaio = raio;
	}

	/**
	 * Metodo getHoras
	 * @return horas
	 */
	public LocalDateTime[] getHoras() {
		LocalDateTime[] horas = new LocalDateTime[2];
		horas[0] = this.now;
		horas[1] = this.plusHours;
		return horas;
	}

	public List<ComercianteInfo> getListaComerciantes(PesquisaComerciantes st) {
		return st.getListaComerciantes(this);
	}
}
