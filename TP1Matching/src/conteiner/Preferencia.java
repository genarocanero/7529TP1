package conteiner;

import java.util.Map;
import java.util.TreeMap;

public class Preferencia<K,V>{
	
	private Map<String,Integer> calificaciones;
	private Cola<String>  jugadoresPreferidos;
	
	public Preferencia(){
		
		this.calificaciones = new TreeMap<String,Integer>();
		this.jugadoresPreferidos = new Cola<String>();
	}
	
	public Integer getPreferencia(String jugadoPreferido) {
		
		return (this.calificaciones.get(jugadoPreferido));
	}
	
	public boolean quedanPreferencias() {
		
		return (!this.jugadoresPreferidos.vacia());
	}
	
	public String mayorPreferenciaDisponible() {
		
		return (this.jugadoresPreferidos.desacolar());
	}

	public void cargarPreferencias(Cola<String[]> todasLasPreferencias) {
		
		MaxHeap<Jugador> maxHeap = new MaxHeap<Jugador>();
		
		while(!(todasLasPreferencias.vacia())) {
			
			String[] unaPreferencia = todasLasPreferencias.desacolar();
			String nombre = unaPreferencia[0];
			int prioridad = Integer.parseInt(unaPreferencia[1]);
			
			this.calificaciones.put(nombre,prioridad);
			maxHeap.insertar(new Jugador(nombre, prioridad));
		}
		
		this.jugadoresPreferidos.acolar(maxHeap.getElemento().getNombre());
	}
	


}
