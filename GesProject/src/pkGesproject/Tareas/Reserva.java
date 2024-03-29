package pkGesproject.Tareas;
/**
 * Clase que modifica el documento excel, para presentar los informes de los avances de los proyectos,
 * con toda la información disponibles en la BD, partners,prosupuestos,gastos,avances.
 * @author Freyder Espinosa V y js.
 */
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

public class Reserva extends JPanel{


	String ruta;
	ResultSet rs,rp;
	ActionListener exportar;
	ActionListener importar;
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	JButton btnExportar = new JButton("Exportar");
	JButton btnImportar = new JButton("Importar");
	JFileChooser fileChooser = new JFileChooser();
	
	
	
	public Reserva(){
		
		
		this.setLayout(new GridBagLayout());
		this.add(btnExportar);
	
		btnImportar.setActionCommand("importar");
		btnExportar.setActionCommand("exportar");
		
		exportar = new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent arg0) {
				//HSSFWorkbook libro = null;
		    	//FileInputStream tuFlujoDeDatos = null;
				//InputStream tuFlujoDeDatos = cftp.Descargar("135"); //Descarga el fichero en memoria,no contemplado fallo al descargar
				
				exportar_partners();
				exportar_summary();
			}
		
		};
		btnExportar.addActionListener(exportar);
		btnImportar.addActionListener(importar);
		this.setVisible(true);
	}
	
	
	public void exportar_partners(){
		
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
    	rp = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE id_pro = 39");
    	String id_p= null ;
    	try {
    		rp.next();
			id_p = rp.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        // Se crea el contenido de la celda y se mete en ella.
       // HSSFRichTextString texto = new HSSFRichTextString("aqui");
        rs = conexion.ConsultaSQL("SELECT p.nombre, tp.nombre FROM PARTNER p INNER JOIN TASAS_PAIS tp ON p.pais=tp.id_pais INNER JOIN PARTNER_PROYECTOS pp ON p.cod_part=pp.cod_part WHERE pp.id_pro ="+id_p);
        /*
         * 
         */
        String nombre = null;
        String pais = null;
        int i;
        int j;
        	try {
				while(rs.next()){
					
					nombre = rs.getString(1);
					pais = rs.getString(2);
					
					tuCell.setCellValue(nombre);
					tuCell1.setCellValue(pais);
					tuCell1 = tuRow.getCell(column2);
					tuRow= tuSheet.getRow(row);
					row+=1;
			
					tuCell = tuRow.getCell(column);
					//System.out.println("Qué pasa?");
					//System.out.println(tuSheet.getRow(row));
					//obtenemos filas hasta encontrar la fula null
					if (tuSheet.getRow(row) != null){
						tuRow= tuSheet.getRow(row);
					} else {
						tuRow = tuSheet.createRow(row);
						tuCell = tuRow.createCell(column);
						}
					
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
        
     
		System.out.println("Después del while");
		
		/*try {
			while(rs.next()){
				pais = rs.getString(2);
				tuCell1.setCellValue(pais);
				row+=1;
				tuCell1 = tuRow.getCell(column2);
				tuRow= tuSheet.getRow(row);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
         
            
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
	
//--------------------EXPORTAR TABLA SUMMARY---------------------//
	                                        
	public void exportar_summary(){
		
			//1 Abrir la plantilla como un libro de excel
	    	// Si todo ha ido bien
	    	HSSFWorkbook tuWorkBook = null;
			//2 Se accede a la hoja y a la casilla
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(1); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
	    		short row = (short) 14; // Tercera fila
		    	short column = (short) 1; // Cuarta columna
		    	short column2 = (short) 2; // Cuarta columna
	    	
	    	
	    	HSSFRow tuRow= tuSheet.getRow(row);
	    	HSSFCell tuCell = tuRow.getCell(column);
	    	
	    	HSSFCell tuCell1 = tuRow.getCell(column2);
	    	//3 Ahora que tienes la celda ya puedes extraerle el contenido 
	    	rp = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE id_pro = 39");
	    	String id_p = null;
	    	try {
	    		rp.next();
				id_p = rp.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   
	     rs = conexion.ConsultaSQL("select p.id_pro, p.nombre, p.f_ini, p.f_fin, p.num_contrato, a.nombre,pa.nombre,pa.direccion from proyectos p inner join partner_proyectos pp on p.id_pro=pp.id_pro inner join partner pa on pp.cod_part=pa.cod_part inner join actions a on p.action=a.id_action where pp.coordinador=”1” and p.id_pro="+id_p);
	      
	        String nombre = null;
	        String pais = null;
	        int i;
	        int j;
	        	try {
					while(rs.next()){
						
						nombre = rs.getString(1);
						pais = rs.getString(2);
						
						tuCell.setCellValue(nombre);
						tuCell1.setCellValue(pais);
						tuCell1 = tuRow.getCell(column2);
						tuRow= tuSheet.getRow(row);
						row+=1;
				
						tuCell = tuRow.getCell(column);
					
						if (tuSheet.getRow(row) != null){
							tuRow= tuSheet.getRow(row);
						} else {
							tuRow = tuSheet.createRow(row);
							tuCell = tuRow.createCell(column);
							}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	   }
}