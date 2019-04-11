package lecturaDeArchivos;

import java.io.IOException;
import java.util.ArrayList;

import com.csvreader.CsvReader;

public class LectorCsv {

	public static ArrayList<ArrayList<String>> cargarDatosDelArchivo(String direccionArchivo){
		
		ArrayList<ArrayList<String>> campos = new ArrayList<ArrayList<String>>();
		
		try {
		
			String[ ]linea = null;
			
			CsvReader lectorCSV = new CsvReader(direccionArchivo);
			
			if(lectorCSV.readRecord()) linea = lectorCSV.getValues();
			
			for(int i = 0 ; i < linea.length ; i++) campos.add(new ArrayList<String>());
				
			while(lectorCSV.readRecord() || linea.length != 0) {
					
				for(int i = 0 ; i < linea.length ; i++) campos.get(i).add(linea[i]);
				
				linea = lectorCSV.getValues();
					
			}
			
		}catch (NumberFormatException e) {
		
					e.printStackTrace();
					
		}catch (IOException e) {
					
					e.printStackTrace();
		}
			
		return campos;	
	}

		
}


