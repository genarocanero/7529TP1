package testEstabilidad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aplicacion.TP1_1;
import conteiner.Preferencias;
import conteiner.Presentador;
import lecturaDeArchivos.LectorCsv;

public class testerPareo {

	
	public static void main(String args[]) {
		
		
		Map<String,String> match = setMatch(args[1]);
		
		ArrayList<ArrayList<String>> datosDeLosParticipantes = LectorCsv.cargarDatosDelArchivo(args[0]);
			
		List<String> participantes = datosDeLosParticipantes.get(1);
		List<String> archivosDePreferencias = datosDeLosParticipantes.get(2);
		
		List<String> oferentes = participantes.subList(0,participantes.size()/2);
		List<String> candidatos = participantes.subList(participantes.size()/2,participantes.size());
		
		List<String> archivosPrefOferentes = archivosDePreferencias.subList(0,participantes.size()/2);
		List<String> archivosPrefCandidatos = archivosDePreferencias.subList(participantes.size()/2,participantes.size());
		
		Map<String,Presentador> presentadoresOferentes = new HashMap<String,Presentador>();
		Map<String,Preferencias> preferenciasCandidatos = new HashMap<String,Preferencias>();
		
		int i = 0;
		
		while(i < oferentes.size() && i < candidatos.size()) {
			
			String oferente = oferentes.get(i);
			String archivoOferente = archivosPrefOferentes.get(i);
			
			String candidato = candidatos.get(i);
			String archivoCandidato = archivosPrefCandidatos.get(i);
			
			presentadoresOferentes.put(oferente, TP1_1.cargarPresentador(archivoOferente));
			preferenciasCandidatos.put(candidato,TP1_1.cargarPreferencias(archivoCandidato));
			
			i++;
			
		}
		
		boolean pareoEstable = chequearEstabilidad(oferentes,match,presentadoresOferentes,preferenciasCandidatos);
		
		String resultado;
		
		if(pareoEstable) resultado = "El pareo es estable";
		else resultado = "El pareo no es estable";
		
		System.out.println(resultado);
	}
	

	private static Map<String, String> setMatch(String archivo) {
		
		Map<String, String> match = new HashMap<String, String>();
		
		List<ArrayList<String>> parejas = LectorCsv.cargarDatosDelArchivo(archivo);
		
		List<String> oferentes = parejas.get(0);
		List<String> candidatos = parejas.get(1);
		
		int parejasContadas = 0;
		
		while(parejasContadas < oferentes.size() && parejasContadas < candidatos.size()) {
			
			match.put(oferentes.get(parejasContadas), candidatos.get(parejasContadas));
			match.put(candidatos.get(parejasContadas), oferentes.get(parejasContadas));
			
			parejasContadas++;
		}
		
		return match;	
	}

	public static boolean chequearEstabilidad(List<String> oferentes, Map<String, String> match, Map<String, Presentador> presentadoresOferentes, Map<String, Preferencias> preferenciasCandidatos) {
		
		boolean esEstable = true;
		
		while(esEstable && !oferentes.isEmpty()) {
			
			String oferenteActual = oferentes.remove(0);
			
			String parejaActual = match.get(oferenteActual);
				
			List<String> masDeseados = presentadoresOferentes.get(oferenteActual).obtenerMejoresQue(parejaActual);
				
			while(!masDeseados.isEmpty() && esEstable) {
					
				String deseado = masDeseados.remove(0);
					
				String suPareja = match.get(deseado);
					
				Preferencias preferencias = (preferenciasCandidatos.get(deseado));
					
				if(preferencias.getCalificacionDe(suPareja) < preferencias.getCalificacionDe(oferenteActual)) {
					
					System.out.print("\n" + oferenteActual + " prefiere mas a " + deseado + " antes que a su pareja " + parejaActual);
					System.out.print(" y " + deseado + " prefiere mas a " + oferenteActual + " antes que a su pareja " + suPareja + "\n\n");				
					esEstable = false;
				}
			}
				
		}
		
		return esEstable;
	}
	
	
	


}
