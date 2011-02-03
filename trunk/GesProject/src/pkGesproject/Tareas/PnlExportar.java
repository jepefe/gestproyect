package pkGesproject.Tareas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import pkGesproject.ConexionDb;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pkGesproject.ConexionDb;

public class PnlExportar extends PnlAltatarea{
		
		public static ConexionDb conexion = new ConexionDb();
		public static ResultSet rs;
		HSSFRichTextString texto = new HSSFRichTextString();
		//public static ConexionDb conexion = new ConexionDb();
		//ResultSet rs;
		
		
public PnlExportar() throws IOException{
				
				System.out.println("xxxxxxxxxxx");
	        	//1 Abrir la plantilla como un libro de excel
	        	String ruta = "src/Pruebas_POI/ReportIn.xls";
	        	HSSFWorkbook libro = null;
	        	FileInputStream tuFlujoDeDatos = null;
				try {
					tuFlujoDeDatos = new FileInputStream(ruta );
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	
	        	// Si todo ha ido bien
	        	HSSFWorkbook tuWorkBook = new HSSFWorkbook(tuFlujoDeDatos );
	        	//2 Se accede a la hoja y a la casilla
	        	HSSFSheet tuSheet = tuWorkBook.getSheetAt(0); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
	        	short row = (short) 2; // Tercera fila
	        	short column = (short) 3; // Cuarta columna
	        	HSSFRow tuRow= tuSheet.getRow(row);
	        	HSSFCell tuCell = tuRow.getCell(column);
	        	//3 Ahora que tienes la celda ya puedes extraerle el contenido 

	            // Se crea el contenido de la celda y se mete en ella.
	            texto = new HSSFRichTextString("uno");
	            rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS");
	            System.out.println("xxxxxxxxxxx");
	            tuCell.setCellValue(texto);
	            
	            HSSFCell celda2 = tuRow.createCell((short) 4);
	            
	            HSSFRichTextString texto2 = new HSSFRichTextString("dos");
	            celda2.setCellValue(texto2);
	            
	            try
	    		{
	            	// Se salva el libro.          
	                // Volcamos la informaci�n a un archivo.

	    			FileOutputStream stream = new FileOutputStream("src/Pruebas_POI/Salida.xls");
	    			
	    			tuWorkBook.write(stream);
	    			stream.close();
	            	
	            	
	    		}
	    		catch (FileNotFoundException fe)
	    		{
	    			fe.printStackTrace();
	    		}
	    		catch (IOException ioe)
	    		{
	    			ioe.printStackTrace();
	    		}
	        
	  }
	

}
