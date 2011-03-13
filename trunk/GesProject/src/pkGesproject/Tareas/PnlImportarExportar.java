package pkGesproject.Tareas;
/**
 * Clase que modifica el documento excel, para presentar los informes de los avances de los proyectos,
 * con toda la información disponibles en la BD, partners,prosupuestos,gastos,avances.
 * @author Freyder Espinosa V y js.
 */
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import pkGesproject.ConexionDb;
import pkGesproject.ConexionFTP;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PnlImportarExportar extends JPanel{
	GesIdioma rec = GesIdioma.obtener_instancia();
//9637

	String ruta;
	JLabel[] jlbl;
	String id_p = null;
	ResultSet rs,rp;
	String[] projects = new String[200];
	ActionListener exportar;
	ActionListener importar;
	JFrame aviso = new JFrame();
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	HSSFWorkbook tuWorkBook = new HSSFWorkbook();
	JButton btnExportar = new JButton("Exportar");
	JButton btnImportar = new JButton("Importar");
	JFileChooser fileChooser = new JFileChooser();
	GpComboBox CmbPro = new GpComboBox();
	
	
	
	public PnlImportarExportar(){
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
	
		
		exportar = new ActionListener(){
		@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Abrir_Libro();
				exportar_summary();
				exportar_progres();
			  	exportar_partners();
			  	exportar_equipment();

			  	guardar();
			  JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
				
			}
		
		};//fin event
		String[] fieldNames = {rec.idioma[rec.eleidioma][178]};
		jlbl = new JLabel[fieldNames.length];
		int i=0;
		this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);

		this.add(CmbPro,gbc);
		   CmbPro.setPreferredSize(new Dimension(140,30));
		   
			/**
			 * Creacion del JComboBox y a�adir los items.
			 *Se conecta a la BD para realizar la consulta
			 **/
			conexion.Conectardb();
			rp = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS ORDER BY nombre");
			
			int j=0;
			try {
				
			while(rp.next()){
				
				projects[j]= Integer.toString(rp.getInt(2));
				
				CmbPro.addItem(rp.getString(1));
				j++;
				CmbPro.setSelectedItem(null);
				
			}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				
//--------------------------------------------------------
				this.setLayout(new GridBagLayout());
				this.add(btnExportar);
			
				btnImportar.setActionCommand("importar");
				btnExportar.setActionCommand("exportar");
				
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		btnExportar.addActionListener(exportar);
		btnImportar.addActionListener(importar);
		this.setVisible(true);
	}
	

	//--------------------EXPORTAR TABLA SUMMARY---------------------//
    
	public void exportar_summary(){
		
		HSSFSheet tuSheet = tuWorkBook.getSheetAt(0); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
	    		int row = 11; // Fila once
		    	int column = 2; // Segunda Columna
		    	
	    	HSSFRow tuRow= tuSheet.getRow(row);
	    	HSSFCell tuCell = tuRow.getCell(column);
	    
	     rs = conexion.ConsultaSQL("SELECT p.id_pro,p.f_ini,p.f_fin,p.num_contrato,p.nombre,act.nombre,pa.nombre,pa.direccion FROM PROYECTOS p INNER JOIN PARTNER_PROYECTOS pp ON p.id_pro=pp.id_pro INNER JOIN ACTIONS act ON act.id_accion=p.action INNER JOIN PARTNER pa ON pp.cod_part=pa.cod_part WHERE p.id_pro="+projects[CmbPro.getSelectedIndex()]);
	  // rp = conexion.ConsultaSQL("SELECT p.f_ini,p.f_fin FROM PROYECTOS p INNER JOIN PARTNER_PROYECTOS pp ON p.id_pro=pp.id_pro INNER JOIN ACTIONS act ON act.id_accion=p.action INNER JOIN PARTNER pa ON pp.cod_part=pa.cod_part WHERE p.id_pro="+projects[CmbPro.getSelectedIndex()]);
	   int i=1;
		try {
				while(rs.next()){
					try {
						rs.getString(i);
						//rs.getString(i+1);
						
						tuCell.setCellValue(rs.getString(i));
						//tuCell1.setCellValue(pais);
						//tuCell1 = tuRow.getCell(column2);
						tuCell = tuRow.getCell(column);
						
						tuRow= tuSheet.getRow(row);
						row+=1;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					i++;
				
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	      //SELECT p.id_pro, p.num_contrato,p.nombre,  "+id_p);
	       /* String nombre = null;
	            int i=1;
	        	try {
					while(rs.next()){
						nombre = rs.getString(i);
						
						tuRow= tuSheet.getRow(row);
						tuCell.setCellValue(nombre);
						row+=1;
						i++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
		}
	
	//--------------------EXPORTAR TABLA PROGRES---------------------//
    
	public void exportar_progres(){
		
		
	    	HSSFSheet tuSheet = tuWorkBook.getSheetAt(1); // Segunda hoja del libro excel (debe existir en tu plantilla porque de lo contrario te dar� una excepci�n al estar fuera del �ndice.
		    	
	    		short row = (short) 8; // Tercera fila
		    	short column = (short) 7; // Cuarta columna
		    	short column2 = (short) 10; // Cuarta columna
	    	
	    	
	    	HSSFRow tuRow= tuSheet.getRow(row);
	    	HSSFCell tuCell = tuRow.getCell(column);
	    	
	    	HSSFCell tuCell1 = tuRow.getCell(column2);
	    	//3 Ahora que tienes la celda ya puedes extraerle el contenido 
	    	
	    	
	       
	     rs = conexion.ConsultaSQL("SELECT f_ini, f_fin FROM PROYECTOS WHERE id_pro="+projects[CmbPro.getSelectedIndex()]);
	      
	        String nombre = null;
	        String pais = null;
	        	try {
					while(rs.next()){
						
						nombre = rs.getString(1);
						pais = rs.getString(2);
						
						tuCell.setCellValue(nombre);
						tuCell1.setCellValue(pais);
						tuCell1 = tuRow.getCell(column2);
						tuRow= tuSheet.getRow(row);
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
    	short column2 = (short) 2; // Cuarta columna
    	
    	
    	HSSFRow tuRow= tuSheet.getRow(row);
    	HSSFCell tuCell = tuRow.getCell(column);
    	HSSFCell tuCell1 = tuRow.getCell(column2);
       	
        rs = conexion.ConsultaSQL("SELECT p.nombre, tp.nombre FROM PARTNER p INNER JOIN TASAS_PAIS tp ON p.pais=tp.id_pais INNER JOIN PARTNER_PROYECTOS pp ON p.cod_part=pp.cod_part WHERE pp.id_pro ="+ projects[CmbPro.getSelectedIndex()]);
     
        String value;
        String pais;
        int i=1;
        
          	try {
				while(rs.next()){
					
					value = rs.getString(i);
					pais = rs.getString(i+1);
					
					tuCell.setCellValue(value);
					tuCell1.setCellValue(pais);
					tuCell1 = tuRow.getCell(column2);
					tuCell = tuRow.getCell(column);
					
					tuRow= tuSheet.getRow(row);
					row+=1;
					
				
					//obtenemos filas hasta encontrar la fila null
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
	     //rs = conexion.ConsultaSQL("SELECT e.partner,e.descripcion,e.justificacion,e.coste_total,e.fecha,e.compra_alquiler,e.grado_depreciacion, e.meses_usara, e.grado_utilizacion FROM EQUIPAMIENTOS e INNER JOIN workpaquets wp ON e.wp=wp.id_wp INNER JOIN proyectos p ON wp.id_pro=p.id_pro WHERE p.id_pro="+projects[CmbPro.getSelectedIndex()]);
	    	rs = conexion.ConsultaSQL("SELECT * FROM EQUIPAMIENTOS" );
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
	

	public void guardar(){
			
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
		        	  FileOutputStream stream = new FileOutputStream(ruta.getAbsoluteFile()+"_Reporting_tool"+".xls");
		   			
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