package conteiner;

import java.util.ArrayList;


import conteiner.Priorizable;

public class MaxHeap<T extends Priorizable> {
	
	private int size;
	
	private ArrayList<T> monticulo;
	
	public MaxHeap() {
		
		this.size = 0;
		this.monticulo = new ArrayList<T>();
		this.monticulo.add(null);
		
	}

	public void insertar(T elemento) {
		
		this.monticulo.add(elemento);
		
		flotar(this.size+1);

		this.size++;
	}
	
	private void hundir(int posicion) {
		
		boolean sePuedePercolar = true;
		
		while(sePuedePercolar && !(esUnaHoja(posicion))) {
			
			T elemento = this.monticulo.get(posicion);
			
			int posicionHijoConMasPrioridad = 0;
		
			posicionHijoConMasPrioridad = posHijoConMayorPrioridad(posicion);
			
			if(elemento.getPrioridad() < this.monticulo.get(posicionHijoConMasPrioridad).getPrioridad()){
				
				swap(posicion,posicionHijoConMasPrioridad);
				posicion = posicionHijoConMasPrioridad;
			}
			
			else sePuedePercolar = false;
		}
	}
	
	private void flotar(int pos) {
		
		boolean sePuedePercolar = true;
		
		T elemento = this.monticulo.get(pos);
		
		while(sePuedePercolar && !(esLaRaiz(pos))) {
		
		T padre = this.padre(pos);
		
		if( (elemento.getPrioridad() > padre.getPrioridad()) ) {
			
			swap(posicionPadre(pos),pos);
			pos = posicionPadre(pos);
		}
		
		else sePuedePercolar = false;		
		}
	}
	
	private boolean esLaRaiz(int pos) {
		
		return (pos == 1);
	}

	private void swap(int unaPos, int otraPos) {
		
		T unElemento = this.monticulo.get(unaPos);
		T otroElemento = this.monticulo.get(otraPos);
		
		this.monticulo.set(unaPos, otroElemento);
		this.monticulo.set(otraPos, unElemento);
	}
	
	private T padre(int pos) {
		
		int posPadre = pos/2;
		
		return this.monticulo.get(posPadre);
	}
	
	private int posicionPadre(int pos) {
		
		return (pos/2);
	}
	
	private int prioridadHijoIzquierdo(int pos) {
		
		int prioridad = 0;
		
		if(posicionHijoIzquierdo(pos) <= this.size) prioridad = this.monticulo.get(posicionHijoIzquierdo(pos)).getPrioridad();
		
		return (prioridad);
	}
	
	private int posicionHijoIzquierdo(int pos) {
		
		return (pos*2);
	}
	
	private int prioridadHijoDerecho(int pos) {
		
		int prioridad = 0;
		
		if(posicionHijoDerecho(pos) <= this.size) prioridad = this.monticulo.get(posicionHijoDerecho(pos)).getPrioridad();
		
		return (prioridad);
	}
	
	private int posicionHijoDerecho(int pos) {
		
		return (pos*2 + 1);
	}
	
	public T getElemento() {
		
		T elementoQuitado = null;
		
		if(this.size > 0) {
			
			T nuevaRaiz = this.monticulo.get(size);
			elementoQuitado = this.monticulo.get(1);
			
			this.monticulo.set(1, nuevaRaiz);
			this.monticulo.remove(size);
			
			this.size--;
			
			hundir(1);
			
			
		}
		
		return elementoQuitado;
		
	}

	private boolean esUnaHoja(int posicion) {
		
		return ((posicionHijoIzquierdo(posicion) > this.size) && (posicionHijoDerecho(posicion) >  this.size));
	}

// hijo izq siempre tiene si entra aca, pues el elemento no es una hoja. En el caso de no tener hijo derecho, la prioridad es 0
// por lo que la comparacion es valida
	private int posHijoConMayorPrioridad(int pos) {
		
		int posicionMayorPrioridad;
		
		int prioridadHijoDerecho = this.prioridadHijoDerecho(pos);
		int prioridadHijoIzquierdo = this.prioridadHijoIzquierdo(pos);
		
		
			if(prioridadHijoIzquierdo >= prioridadHijoDerecho) posicionMayorPrioridad = this.posicionHijoIzquierdo(pos);	
			else posicionMayorPrioridad = this.posicionHijoDerecho(pos);
				
		return posicionMayorPrioridad;
	}
	
	public boolean estaVacio() {
		
		return (this.size == 0);
	}
	
}
