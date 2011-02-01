package pkGesproject.Proyectos;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


import pkGesproject.ConexionDb;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PnlInformesProyecto extends JPanel{


	JButton btngenerar = new JButton("Generar");
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	String fileName;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	JFileChooser filechooser;
	String ruta;
	
	public PnlInformesProyecto(){
		
		this.setLayout(new GridBagLayout());
		this.add(btngenerar);
		fileName = System.getProperty("user.dir")+"//src//pkGesproject//reports//prueba.jrxml";
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
					
					//Hacemos las consulta y la guardamos en el resulset
					//String consulta = "SELECT *,(SELECT COUNT(*) FROM IDIOMA) AS total FROM IDIOMA";
					String consulta = "SELECT p.nombre AS proyecto,pa.nombre,pa.sector,pa.pais FROM PARTNER pa INNER JOIN "+
										"PARTNER_PROYECTOS pp ON pa.cod_part= pp.cod_part INNER JOIN PROYECTOS p ON pp.id_pro = p.id_pro ORDER BY p.nombre";
					conexion.Conectardb();
					rs = conexion.ConsultaSQL(consulta);
					JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
					
					//1-Compilamos el archivo XML y lo cargamos en memoria
					jasperReport = JasperCompileManager.compileReport(fileName);
					

					//2. Se llena el reporte con la informacion necesaria
					jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), resulset);
					
					conexion.cerrarConexion();
					//3-Exportamos el reporte a pdf y lo guardamos en disco
					
					JFileChooser filechooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Documento PDF", "pdf");//Añadimos el filtro para que nos muestre sólo las extensiones que queremos
					filechooser.setFileFilter(filter);
					
					int returnVal = filechooser.showSaveDialog(null);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = filechooser.getSelectedFile();
							/*
							 * sacamos la ruta del archivo y su extension
							 */
							if(filechooser.getFileFilter().getDescription().equals("Documento PDF")){
								if(filechooser.getSelectedFile().getName().contains(".pdf")){
									ruta = file.getPath();
								}else{
									ruta = file.getPath()+".pdf";
								}
							}else{
								ruta = file.getPath();
							}
					    } 		
					
					JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
					System.out.println("SE A CREADO CORRECTAMENTE EL PDF");
				}
				catch (JRException e)
				{
					e.printStackTrace();
					System.out.println("ERROR EL PDF NO SE A CREADO!!!");
				}
			}

		};

		btngenerar.addActionListener(generar);
		this.setVisible(true);
	}
}
