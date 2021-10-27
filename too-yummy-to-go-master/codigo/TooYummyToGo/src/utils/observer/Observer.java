package utils.observer;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public interface Observer <E extends Event> {
	public void handleNewEvent(E e);

}
