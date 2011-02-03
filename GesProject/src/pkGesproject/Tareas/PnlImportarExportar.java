package pkGesproject.Tareas;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import pkGesproject.ConexionDb;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PnlImportarExportar extends JPanel{


	ActionListener exportar;
	ActionListener importar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	String fileName;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	JFileChooser filechooser;
	JButton btnExportar = new JButton("Exportar");
	//JButton btnImportar = new JButton("Importar");
	String ruta;
	
	public PnlImportarExportar(){
		
		
		this.setLayout(new GridBagLayout());
		this.add(btnExportar);
	
		//btnImportar.setActionCommand("importar");
		btnExportar.setActionCommand("exportar");
		
		exportar = new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
	        	HSSFWorkbook tuWorkBook = null;
				try {
					tuWorkBook = new HSSFWorkbook(tuFlujoDeDatos );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	//2 Se accede a la hoja y a la casilla
	        	HSSFSheet tuSheet = tuWorkBook.getSheetAt(2); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
	        	short row = (short) 8; // Tercera fila
	        	short column = (short) 1; // Cuarta columna
	        	HSSFRow tuRow= tuSheet.getRow(row);
	        	HSSFCell tuCell = tuRow.getCell(column);
	        	//3 Ahora que tienes la celda ya puedes extraerle el contenido 

	            // Se crea el contenido de la celda y se mete en ella.
	           // HSSFRichTextString texto = new HSSFRichTextString("aqui");
	            rs = conexion.ConsultaSQL("SELECT nombre FROM PARTNER");
	            String nombre = null;
	        	try {
					while(rs.next()){
						nombre = rs.getString(1);
						tuCell.setCellValue(nombre);
						row+=1;
						tuCell = tuRow.getCell(column);
						tuRow= tuSheet.getRow(row);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
				
	            System.out.println("Contenido del RS:"+rs);
	            
	           
	            
	            HSSFCell celda2 = tuRow.createCell((short) 4);
	            
	            HSSFRichTextString texto2 = new HSSFRichTextString("alla");
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
		
		};
		
		
		btnExportar.addActionListener(exportar);
		//btnImportar.addActionListener(importar);
		this.setVisible(true);
	}
}
