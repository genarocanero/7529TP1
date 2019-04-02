package conteiner;

import java.util.ArrayList;

public class Cola<T> {

	private ArrayList<T> cola;
	
	public Cola() {
		
		this.cola = new ArrayList<T>();
	}
	
	public void acolar(T elemento) {
		
		this.cola.add(elemento);
	}
	
	public boolean vacia() {
		
		return (this.cola.isEmpty());
	}
	
	public int size() {
		
		return (this.cola.size());
	}
	
	public T desacolar() {
		
		T primerElemento = this.cola.get(0);
		this.cola.remove(0);
		
		return (primerElemento);
	}
}
