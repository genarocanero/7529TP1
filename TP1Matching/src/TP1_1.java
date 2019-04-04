import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import conteiner.Cola;
import conteiner.Preferencia;
import lecturaDeArchivos.LectorCsv;

public class TP1_1 {
	

    public static void main(String[] args){
    	
//    	String direccionArchivo = args[2];
    	
    	//prueba
    	String direccionArchivo = "jugadores.txt";
    	
    	Cola<String[]> lineasDelArchivo = LectorCsv.leerArchivoCsv(direccionArchivo);
    	
    	ArrayList<String> oferentes;
    	Map<String,Preferencia<String,Integer>> preferencias = new TreeMap<String,Preferencia<String,Integer>>();
    	
    	oferentes = cargarDatosJugadores(lineasDelArchivo, preferencias);
    	
        Map<String, String> matches = match(oferentes,preferencias);
        
        for(Map.Entry<String, String> couple:matches.entrySet()){
            System.out.println(
                    couple.getKey() + " is engaged to " + couple.getValue());
        }
//        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
//            System.out.println("Marriages are stable");
//        }else{
//            System.out.println("Marriages are unstable");
//        }
//        String tmp = matches.get(girls.get(0));
//        matches.put(girls.get(0), matches.get(girls.get(1)));
//        matches.put(girls.get(1), tmp);
//        System.out.println(
//                girls.get(0) +" and " + girls.get(1) + " have switched partners");
//        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
//            System.out.println("Marriages are stable");
//        }else{
//            System.out.println("Marriages are unstable");
//        }
    }
 
    private static ArrayList<String> cargarDatosJugadores(Cola<String[]> lineasDelArchivo,
			Map<String, Preferencia<String, Integer>> preferencias) {
    	
    	ArrayList<String> oferentes = new ArrayList<String>();
		
    	int totalDeJugadores = lineasDelArchivo.size();
    	
    	while(!(lineasDelArchivo.vacia())) {
    		
    		String[] lineaActual = lineasDelArchivo.desacolar();
    		
    		String nombreJugadorActual = lineaActual[1];
    		
    		Preferencia<String,Integer> preferenciasDelJugador = setPreferencias(lineaActual[2]);
    		
    		preferencias.put(nombreJugadorActual, preferenciasDelJugador);
    		
    		if(Integer.parseInt(lineaActual[0]) <= totalDeJugadores/2) oferentes.add(nombreJugadorActual);
    	}
    	
		return oferentes;
	}

	private static Preferencia<String, Integer> setPreferencias(String archivoPreferencias) {
		
		Cola<String[]> lineasDelArchivo = LectorCsv.leerArchivoCsv(archivoPreferencias);
		
		Preferencia<String,Integer> preferencias = new Preferencia<String,Integer>();
		
		preferencias.cargarPreferencias(lineasDelArchivo);
		
		return preferencias;
	}

	private static Map<String, String> match(ArrayList<String> oferentes,Map<String,Preferencia<String,Integer>> preferencias){
      
		Map<String, String> comprometidos = new TreeMap<String, String>();
		ArrayList<String> oferentesLibres = new ArrayList<String>();
        oferentesLibres.addAll(oferentes);
      
        while(!oferentesLibres.isEmpty()){
            
        	boolean seFormoPareja = false;
        	
        	String oferenteActual = oferentesLibres.remove(0); 
            Preferencia<String,Integer> preferenciasOferente = preferencias.get(oferenteActual);
           
            while(!seFormoPareja && preferenciasOferente.quedanPreferencias()){
            	
            	String posiblePareja = preferenciasOferente.mayorPreferenciaDisponible();
            	String suPareja = comprometidos.get(posiblePareja);
            	
                if(suPareja == null){
                    
                	comprometidos.put(posiblePareja, oferenteActual); 
                	seFormoPareja = true; 	
                }
                else{
                	Preferencia<String,Integer> preferenciasPosiblePareja = preferencias.get(posiblePareja);
                	
                	if(preferenciasPosiblePareja.getPreferencia(suPareja) < preferenciasPosiblePareja.getPreferencia(oferenteActual)){
                       
                        comprometidos.replace(posiblePareja, oferenteActual);
                        oferentesLibres.add(suPareja);
                        seFormoPareja = true;
                    }
                }
            }
        }
        return comprometidos;
    }
 
//    private static boolean checkMatches(List<String> guys, List<String> girls,
//            Map<String, String> matches, Map<String, List<String>> guyPrefers,
//            Map<String, List<String>> girlPrefers) {
//        if(!matches.keySet().containsAll(girls)){
//            return false;
//        }
// 
//        if(!matches.values().containsAll(guys)){
//            return false;
//        }
// 
//        Map<String, String> invertedMatches = new TreeMap<String, String>();
//        for(Map.Entry<String, String> couple:matches.entrySet()){
//            invertedMatches.put(couple.getValue(), couple.getKey());
//        }
// 
//        for(Map.Entry<String, String> couple:matches.entrySet()){
//            List<String> shePrefers = girlPrefers.get(couple.getKey());
//            List<String> sheLikesBetter = new LinkedList<String>();
//            sheLikesBetter.addAll(shePrefers.subList(0, shePrefers.indexOf(couple.getValue())));
//            List<String> hePrefers = guyPrefers.get(couple.getValue());
//            List<String> heLikesBetter = new LinkedList<String>();
//            heLikesBetter.addAll(hePrefers.subList(0, hePrefers.indexOf(couple.getKey())));
// 
//            for(String guy : sheLikesBetter){
//                String guysFinace = invertedMatches.get(guy);
//                List<String> thisGuyPrefers = guyPrefers.get(guy);
//                if(thisGuyPrefers.indexOf(guysFinace) >
//                        thisGuyPrefers.indexOf(couple.getKey())){
//                    System.out.printf("%s likes %s better than %s and %s"
//                            + " likes %s better than their current partner\n",
//                       couple.getKey(), guy, couple.getValue(),
//                       guy, couple.getKey());
//                    return false;
//                }
//            }
// 
//            for(String girl : heLikesBetter){
//                String girlsFinace = matches.get(girl);
//                List<String> thisGirlPrefers = girlPrefers.get(girl);
//                if(thisGirlPrefers.indexOf(girlsFinace) >
//                        thisGirlPrefers.indexOf(couple.getValue())){
//                    System.out.printf("%s likes %s better than %s and %s"
//                            + " likes %s better than their current partner\n",
//                       couple.getValue(), girl, couple.getKey(),
//                       girl, couple.getValue());
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}

