package utils.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public abstract class Observable <E extends Event> {

	List<Observer<E>> observers = new ArrayList<>();
	
	//metodos
	public void addObserver(Observer<E> o) {
		observers.add(o);
	}
	
	protected void dispatchEvent(E e) {
		for (Observer<E> obs : observers) {
			obs.handleNewEvent(e);
		}
	}
}
