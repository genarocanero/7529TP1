package conteiner;

import conteiner.Priorizable;

public class Jugador implements Priorizable {
	
	private String nombre;
	private int prioridad;

	public Jugador(String nuevaPreferencia, int unaPrioridad) {
		
		this.nombre = nuevaPreferencia;
		this.prioridad = unaPrioridad;
	}

	//@Override
	public int getPrioridad() {
		
		return prioridad;
	}
	
	public String getNombre() {
		
		return nombre;
	}

}