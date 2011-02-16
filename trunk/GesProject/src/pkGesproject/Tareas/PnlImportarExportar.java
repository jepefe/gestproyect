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

public class PnlImportarExportar extends JPanel{


	String ruta;
	ResultSet rs,rp;
	ActionListener exportar;
	ActionListener importar;
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	HSSFWorkbook tuWorkBook = new HSSFWorkbook();
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
				
				Abrir_Libro();
				exportar_summary();
			  	exportar_partners();
			  	exportar_equipment();
			  	guardar();
				
			}
		
		};
		btnExportar.addActionListener(exportar);
		btnImportar.addActionListener(importar);
		this.setVisible(true);
	}
	

	//--------------------EXPORTAR TABLA SUMMARY---------------------//
    
	public void exportar_summary(){
		
		
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(0); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
	    		short row = (short) 11; // Tercera fila
		    	short column = (short) 2; // Cuarta columna
		    	
	    	
	    	
	    	HSSFRow tuRow= tuSheet.getRow(row);
	    	HSSFCell tuCell = tuRow.getCell(column);
	    	
	    	HSSFCell tuCell1 = tuRow.getCell(column);
	    	//3 Ahora que tienes la celda ya puedes extraerle el contenido 
	    	rp = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE id_pro = 3");
	    	String id_p = null;
	    	try {
	    		rp.next();
				id_p = rp.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   //p.f_ini, p.f_fin,
	     rs = conexion.ConsultaSQL("SELECT p.id_pro, p.num_contrato,p.nombre FROM PROYECTOS p WHERE p.id_pro="+id_p);
	      //SELECT p.id_pro, p.num_contrato,p.nombre, a.nombre,pa.nombre,pa.direccion FROM PROYECTOS p INNER JOIN PARTNER_PROYECTOS pp on p.id_pro=pp.id_pro INNER JOIN PARNERT pa ON pp.cod_part=pa.cod_part INNER JOIN ACTIONS a ON p.action=a.id_action WHERE pp.coordinador=”1” AND p.id_pro="+id_p);
	        String nombre = null;
	            
	        	try {
					while(rs.next()){
						
						nombre = rs.getString(1);
					
						tuCell.setCellValue(nombre);
						tuCell = tuRow.getCell(column);
						tuRow= tuSheet.getRow(row);
						row+=1;
				
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
	
	//--------------------EXPORTAR TABLA PROGRES---------------------//
    
	public void exportar_progres(){
		
		
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
	
//----------------Hoja 2 PARTNERS--------------------//
	public void exportar_partners(){
		
		
    	//2 Se accede a la hoja y a la casilla
    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(2); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
    	short row = (short) 8; // Tercera fila
    	short column = (short) 1; // Cuarta columna
    	//short column2 = (short) 2; // Cuarta columna
    	
    	
    	HSSFRow tuRow= tuSheet.getRow(row);
    	HSSFCell tuCell = tuRow.getCell(column);
    	
    	//HSSFCell tuCell1 = tuRow.getCell(column2);
    	//3 Ahora que tienes la celda ya puedes extraerle el contenido 
    	rp = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE id_pro = 3");
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
       // String pais = null;
        int i=1;
          	try {
				while(rs.next()){
					
					nombre = rs.getString(i);
					//pais = rs.getString(2);
					
					tuCell.setCellValue(nombre);
					column+=1;
					tuCell = tuRow.getCell(column);
					//tuCell1.setCellValue(pais);
					//tuCell1 = tuRow.getCell(column2);
					tuRow= tuSheet.getRow(row);
					i+=1;
				
					//obtenemos filas hasta encontrar la fula null
					if (tuSheet.getRow(row) != null){
						tuRow= tuSheet.getRow(row);
					} else {
						tuRow = tuSheet.createRow(row);
						tuCell = tuRow.createCell(column);
						}
					
				}
				
				row+=1;
			
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
         
            
       
}
	
	//--------------------EXPORTAR TABLA staff---------------------//
    
	public void exportar_staff(){
		
		
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(3); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
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
	//--------------------EXPORTAR TABLA travel---------------------//
    
	public void exportar_travel(){
		
		
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(4); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
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
	//--------------------EXPORTAR TABLA equipment---------------------//
    
	public void exportar_equipment(){
		
		
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(5); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
	    		short row = (short) 5; // Tercera fila
		    	short column = (short) 1; // Cuarta columna
		        	
	    	
	    	HSSFRow tuRow= tuSheet.getRow(row);
	    	@SuppressWarnings("deprecation")
			HSSFCell tuCell = tuRow.getCell(column);
	    	
	      	
	   //rs = conexion.ConsultaSQL("SELECT partner,descripcion,justificacion,wp,coste_total,fecha,compra_alguiler,grado_depreciacion,meses_usara,grado_utilizacion,costes_subvencionados FROM EQUIPAMIENTOS WHERE partner = 142");
	     rs = conexion.ConsultaSQL("SELECT e.partner,e.descripcion,e.justificacion,e.coste_total,e.fecha,e.compra_alquiler,e.grado_depreciacion, e.meses_usara, e.grado_utilizacion FROM EQUIPAMIENTOS e"); //INNER JOIN workpaquets wp ON e.wp=wp.id_wp INNER JOIN proyectos p ON wp.id_pro=p.id_pro WHERE p.id_pro=142");
	     	
	     String value;
	    	
	       //String nom;
	        	try {
					while(rs.next()){
						
						 value= rs.getString(2);
						//nom = rs.getString(2);
												
						tuCell.setCellValue(value);
						
						tuCell = tuRow.getCell(column);
						column+=1;
											
						//toRow= tuSheet.getColumn(colum);
						tuRow= tuSheet.getRow(row);
						//row+=1;
						
						//System.out.println("contenido de columna despues:"+column );
				
						
					
						if (tuSheet.getRow(row) != null){
							tuRow= tuSheet.getRow(row);
						} else {
							tuRow = tuSheet.createRow(row);
							tuCell = tuRow.createCell(column);
							}
					}
					row+=1;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
	   }
	

	public void guardarr(){
			
			 try
				{
		        	// Se salva el libro.          
		            // Volcamos la informaci�n a un archivo.
		        	//int seleccion = fileChooser.showSaveDialog(null);
		        	
		        
		        	int seleccion = fileChooser.showSaveDialog(null);
		        	if (seleccion == JFileChooser.APPROVE_OPTION)
		        	{
		        	   File ruta = fileChooser.getSelectedFile();
		        	   // Aquí debemos abrir el fichero para escritura
		        	   // y salvar nuestros datos.
		        	  FileOutputStream stream = new FileOutputStream(ruta);
		   			
		   			tuWorkBook.write(stream);
		   			stream.close();
		        	}

		        	  
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
	
	public void guardar(){
		 try
			{
	        	// Se salva el libro.          
	            // Volcamos la informaci�n a un archivo.
	        	int seleccion = fileChooser.showSaveDialog(null);
	        	
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
//---------------------------------------...............---------------------------------------//
	/**
	 * Método que abre el libro excel para poder introducir la información del proyecto activo.
	 * @author Freyder Espinosa V
	 * 
	 */
	public void Abrir_Libro(){
		//1 Abrir la plantilla como un libro de excel
       	HSSFWorkbook libro = null;
    	InputStream tuFlujoDeDatos = cftp.Descargar("135"); //Descarga el fichero en memoria,no contemplado fallo al descargar
       	
    	// Si todo ha ido bien
    	try {
			tuWorkBook = new HSSFWorkbook(tuFlujoDeDatos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}