package conteiner;

import java.util.HashMap;

public class Presentador {

	private HashMap<Integer,Cola<String>> preferencias;

	private int calificacionActual;

	
	public Presentador() {
		
		this.preferencias = new HashMap<Integer,Cola<String>>();

		this.calificacionActual = 20;
	}
	
	public void agregarPosiblePareja(String nombre, int calificacion) {
		
		if(!(hayOpcionesConTalCalificacion(calificacion))) this.preferencias.put(calificacion, new Cola<String>());

		this.preferencias.get(calificacion).acolar(nombre);
	}
	
	private boolean hayOpcionesConTalCalificacion(int calificacion) {
		
		Cola<String> opciones = this.preferencias.get(calificacion);
		
		return (opciones != null && !(opciones.vacia()) );
	}
	
//	en el peor de los casos todos tienen la minima calificacion por lo que solo iterara 19 veces
//	buscando una calificacion disponible, hasta llegar a la minima (de 20 a 1)
//	lo que seria O(20) -> O(1). Mas bien O(maxima puntuacion). Luego -> desacolar O(1)
	public String presentarPosiblePareja() {
		
		while(!(hayOpcionesConTalCalificacion(calificacionActual))) this.calificacionActual--;
	
		String nombre = this.preferencias.get(calificacionActual).desacolar();
		
		return nombre;
	}

	

	
	
			
}
