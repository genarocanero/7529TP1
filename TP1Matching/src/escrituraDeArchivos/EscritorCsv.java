package escrituraDeArchivos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.csvreader.CsvWriter;

public class EscritorCsv {

	public static void crearArchivoCsv(Map<String, String> matches,ArrayList<String> candidatos,
			String archivo) {
		
		CsvWriter writer;
		
		try {
			
			writer = new CsvWriter(new FileWriter(archivo),',');
		
			while(!(candidatos.isEmpty())) {
			
				String candidato = candidatos.remove(0);
				String pareja = matches.get(candidato);
			
				writer.write(pareja);
				writer.write(candidato);	
				
				writer.endRecord();
			}
			
			writer.close();
		}
		catch (IOException e) {
		
		e.printStackTrace();
		
		}
	}
};
