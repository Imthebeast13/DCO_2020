package pt.tooyummytogo.plugins;

import utils.observer.Event;
import utils.observer.Observable;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */

public abstract class Sensor <E extends Event> extends Observable<E>{
	public abstract void novaReserva();

}
