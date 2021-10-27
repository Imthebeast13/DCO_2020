package pt.tooyummytogo;

import utils.observer.Event;

public class ReservaEvent implements Event{

	String codigo;

	public ReservaEvent (String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoReserva() {
		return this.codigo;
	}


}
