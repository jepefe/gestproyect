package pkGesproject.Tareas;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import pkGesproject.ConexionFTP;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PnlImportarExportar extends JPanel{


	String ruta;
	ResultSet rs;
	ActionListener exportar;
	ActionListener importar;
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	JButton btnExportar = new JButton("Exportar");
	JButton btnImportar = new JButton("Importar");
	JFileChooser fileChooser = new JFileChooser();
	
	
	
	public PnlImportarExportar(){
		
		
		this.setLayout(new GridBagLayout());
		this.add(btnExportar);
	
		btnImportar.setActionCommand("importar");
		btnExportar.setActionCommand("exportar");
		
		exportar = new ActionListener(){
			
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				exportar();
			}
		
		};
		
		
		btnExportar.addActionListener(exportar);
		btnImportar.addActionListener(importar);
		this.setVisible(true);
	}
	
	
	public void exportar(){
		
		//1 Abrir la plantilla como un libro de excel
    	//String ruta = "src/PkModelosExcel/ReportIn.xls";
    	HSSFWorkbook libro = null;
    	//FileInputStream tuFlujoDeDatos = null;
		InputStream tuFlujoDeDatos = cftp.Descargar("135"); //Descarga el fichero en memoria,no contemplado fallo al descargar
    	/*try {
		tuFlujoDeDatos = new FileInputStream(ruta );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    */    	
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
    	short column2 = (short) 2; // Cuarta columna
    	
    	
    	HSSFRow tuRow= tuSheet.getRow(row);
    	HSSFCell tuCell = tuRow.getCell(column);
    	
    	HSSFCell tuCell1 = tuRow.getCell(column2);
    	//3 Ahora que tienes la celda ya puedes extraerle el contenido 

        // Se crea el contenido de la celda y se mete en ella.
       // HSSFRichTextString texto = new HSSFRichTextString("aqui");
        rs = conexion.ConsultaSQL("SELECT nombre,pais FROM PARTNER");
        String nombre = null;
        int i;
        
        
        for(i=0;i<=2;i++){
	    	try {
				while(rs.next()){
					
					nombre = rs.getString(i);
					
					System.out.println(nombre);
					tuCell.setCellValue(nombre);
					row+=1;
					//System.out.println("Columna = " + column + " / Fila = " + row);
					tuCell = tuRow.getCell(column);
					//System.out.println("Qué pasa?");
					//System.out.println(tuSheet.getRow(row));
					//obtenemos filas hasta encontrar la fula null
					if (tuSheet.getRow(row) != null){
						tuRow= tuSheet.getRow(row);
						System.out.println("Dentro del if");
					} else {
						tuRow = tuSheet.createRow(row);
						System.out.println("Dentro del else");
						tuCell = tuRow.createCell(column);
						}
					
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
        }
        column++;
		System.out.println("Después del while");
		/*try {
			while(rs.next()){
				nombre = rs.getString(2);
				tuCell1.setCellValue(nombre);
				row+=1;
				tuCell1 = tuRow.getCell(column2);
				tuRow= tuSheet.getRow(row);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
		
       //System.out.println("Contenido del RS:"+rs);
        
            
        try
		{
        	// Se salva el libro.          
            // Volcamos la informaci�n a un archivo.
        	int seleccion = fileChooser.showSaveDialog(null);
        	
        	/*if (seleccion == JFileChooser.APPROVE_OPTION)
        	{
        	   File fichero = fileChooser.getSelectedFile();
        	   ruta = fichero.getPath();
        	   // Aquí debemos abrir el fichero para escritura
        	   // y salvar nuestros datos.
        	    * 
        	    */
        	    
        	    
        	   FileOutputStream stream = new FileOutputStream("c:/users/freyder espinosa v/desktop/excel.xls");
   			
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

	

