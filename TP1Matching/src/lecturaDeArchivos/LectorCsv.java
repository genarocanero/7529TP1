package lecturaDeArchivos;

import java.io.IOException;

import com.csvreader.CsvReader;
import conteiner.Cola;

public class LectorCsv {

	public static Cola<String[]> leerArchivoCsv(String direccionArchivo){
		
		Cola<String[]> texto = new Cola<String[]>();
		
		try {
		
			String[ ]linea;
			CsvReader lectorCSV = new CsvReader(direccionArchivo);
				
			while(lectorCSV.readRecord()) {
						
				linea = lectorCSV.getValues();
				texto.acolar(linea);
			}
			
		}catch (NumberFormatException e) {
		
					e.printStackTrace();
					
		}catch (IOException e) {
					
					e.printStackTrace();
		}
			
		return texto;	
	}

		
}


