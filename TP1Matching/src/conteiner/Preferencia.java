package conteiner;

import conteiner.Priorizable;

public class Preferencia<T> implements Priorizable {
	
	private T preferencia;
	private int prioridad;

	public Preferencia(T nuevaPreferencia, int unaPrioridad) {
		
		this.preferencia = nuevaPreferencia;
		this.prioridad = unaPrioridad;
	}

	//@Override
	public int getPrioridad() {
		
		return prioridad;
	}
	
	public T getPreferencia() {
		
		return preferencia;
	}

}
