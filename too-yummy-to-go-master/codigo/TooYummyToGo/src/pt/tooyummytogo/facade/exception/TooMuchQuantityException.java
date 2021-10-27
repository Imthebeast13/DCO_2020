package pt.tooyummytogo.facade.exception;

/**
 * @authors:
 * 
 * fc53512 Ana Albuquerque
 * fc44999 Andre Firmino
 * fc53683 Joao Godinho
 */
public class TooMuchQuantityException extends Exception {
	public static void main(String[] args) {
		System.out.println("A quantidade que inseriu excede a quantidade"
				+ " disponivel em stock!");
	}
}
