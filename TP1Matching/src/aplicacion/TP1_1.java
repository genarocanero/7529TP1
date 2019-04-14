package aplicacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import conteiner.Preferencias;
import conteiner.Presentador;
import escrituraDeArchivos.EscritorCsv;
import lecturaDeArchivos.LectorCsv;

public class TP1_1 {
	

    public static void main(String[] args){
    	
    	String direccionArchivo = args[1];
    	int cantidadDeJugadores = Integer.parseInt(args[0]);
    	
    	ArrayList<ArrayList<String>> camposDelArchivoCSV = LectorCsv.cargarDatosDelArchivo(direccionArchivo);
    	
    	List<String> oferentes = camposDelArchivoCSV.get(1).subList(0,(cantidadDeJugadores/2));
    	List<String> candidatos = camposDelArchivoCSV.get(1).subList((cantidadDeJugadores/2),cantidadDeJugadores);
    	
    	Map<String,Presentador> presentadoresDeParejas = new HashMap<String,Presentador>();
    	Map<String,Preferencias> preferenciasCandidatos = new HashMap<String,Preferencias>();
    	
    	
    	for(int i = 0 ; i < cantidadDeJugadores/2 ; i++) {
    		
    		presentadoresDeParejas.put(oferentes.get(i),cargarPresentador((camposDelArchivoCSV.get(2).get(i))));
    		preferenciasCandidatos.put(candidatos.get(i), cargarPreferencias(camposDelArchivoCSV.get(2).get((cantidadDeJugadores/2)+i)));
    	}
    	
        Map<String, String> matches = match(oferentes,presentadoresDeParejas,preferenciasCandidatos);
        
        EscritorCsv.crearArchivoCsv(matches, candidatos, "parejas.txt");
        
    }
    
    public static Presentador cargarPresentador(String archivoPreferencias) {
    	
    	Presentador presentadorDelOferente = new Presentador();
    	
    	ArrayList<ArrayList<String>> datosPreferencias = LectorCsv.cargarDatosDelArchivo(archivoPreferencias);
    	
    	ArrayList<String> nombres = datosPreferencias.remove(0);
    	ArrayList<String> calificaciones = datosPreferencias.remove(0);
    	
    	while(!(nombres.isEmpty()) && !(calificaciones.isEmpty())) {
    		
    		presentadorDelOferente.agregarPosiblePareja(nombres.remove(0), Integer.parseInt(calificaciones.remove(0)));
    	}
    	
    	return presentadorDelOferente;
    }
 

	public static Preferencias cargarPreferencias(String archivoPreferencias) {
		
		ArrayList<ArrayList<String>> datosPreferencias = LectorCsv.cargarDatosDelArchivo(archivoPreferencias);
		
		Preferencias preferencias = new Preferencias();
		
		ArrayList<String> nombres = datosPreferencias.remove(0);
    	ArrayList<String> calificaciones = datosPreferencias.remove(0);
    	
    	while(!(nombres.isEmpty()) && !(calificaciones.isEmpty())) {
    		
    		preferencias.guardarCalificacion(nombres.remove(0), Integer.parseInt(calificaciones.remove(0)));
    	}
		
		return preferencias;
	}
	
	
	private static Map<String, String> match(List<String> oferentes,Map<String,Presentador> presentadores,Map<String,Preferencias> preferencias){
      
		Map<String, String> comprometidos = new HashMap<String, String>();
		List<String> oferentesLibres = new ArrayList<String>();
        oferentesLibres.addAll(oferentes);
      
        while(!oferentesLibres.isEmpty()){
            
        	boolean seFormoPareja = false;
        	
        	String oferenteActual = oferentesLibres.remove(0); 
            Presentador presentador = presentadores.get(oferenteActual);
            
            while(!seFormoPareja){
            	
            	String posiblePareja = presentador.presentarPosiblePareja();
            	String suPareja = comprometidos.get(posiblePareja);
            	
                if(suPareja == null){
                    
                	comprometidos.put(posiblePareja, oferenteActual); 
                	seFormoPareja = true; 	
                }
                else{
                	Preferencias preferenciasPosiblePareja = preferencias.get(posiblePareja);
                	
                	if(preferenciasPosiblePareja.getCalificacionDe(suPareja) < preferenciasPosiblePareja.getCalificacionDe(oferenteActual)){
                       
                        comprometidos.replace(posiblePareja, oferenteActual);
                        oferentesLibres.add(suPareja);
                        seFormoPareja = true;
                    }
                }
            }
        }
        return comprometidos;
    }
 
}

