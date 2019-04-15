package conteiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Presentador {

	private HashMap<Integer,Cola<String>> preferencias;

	private int calificacionActual;
	
	private int cantidadParejas;

	
	public Presentador() {
		
		this.preferencias = new HashMap<Integer,Cola<String>>();

		this.calificacionActual = 20;
		
		this.cantidadParejas = 0;
	}
	
	public void agregarPosiblePareja(String nombre, int calificacion) {
		
		if(!(hayOpcionesConTalCalificacion(calificacion))) this.preferencias.put(calificacion, new Cola<String>());

		this.preferencias.get(calificacion).acolar(nombre);
		
		this.cantidadParejas++;
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
		
		this.cantidadParejas--;
		
		return nombre;
	}
	
	private boolean quedanPosiblesParejas() {
		
		return (this.cantidadParejas > 0);
	}
	
	public List<String> obtenerMejoresQue(String nombre){
		
		List<String> mejores = new ArrayList<String>();
		
		boolean nombreEncontrado = false;
		
		while(!nombreEncontrado && this.quedanPosiblesParejas()) {
			
			String nombreActual = this.presentarPosiblePareja();
			
			if(!nombre.equals(nombreActual)) mejores.add(nombreActual);
			else nombreEncontrado = true;
		}
		
		return mejores;
	}

	

	
	
			
}
