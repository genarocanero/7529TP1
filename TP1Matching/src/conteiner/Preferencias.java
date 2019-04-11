package conteiner;

import java.util.HashMap;

public class Preferencias {

	private HashMap<String, Integer> calificaciones;
	
	public Preferencias() {
		
		this.calificaciones = new HashMap<String,Integer>();
	}
	
	public void guardarCalificacion(String nombre, int calificacion) {
		
		this.calificaciones.put(nombre, calificacion);
	}
	
	public int getCalificacionDe(String nombre) {
		
		return (this.calificaciones.get(nombre));
	}
}
