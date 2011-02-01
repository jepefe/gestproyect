package pkGesproject.Tareas;

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

public class PnlImportarExportar extends JPanel{


	JButton btnExportar = new JButton("Exportar");
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	String fileName;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	JFileChooser filechooser;
	String Path;
	
	public PnlImportarExportar(){
		
		this.setLayout(new GridBagLayout());
		this.add(btnExportar);
		fileName = System.getProperty("user.dir")+"//src//pkGesproject//reports//prueba.jrxml";
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
					
					//Hacemos las consulta y la guardamos en el resulset
					String consulta = "SELECT *,(SELECT COUNT(*) FROM IDIOMA) AS total FROM IDIOMA";
					conexion.Conectardb();
					rs = conexion.ConsultaSQL(consulta);
					JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
					
						

					conexion.cerrarConexion();
					filechooser = new JFileChooser();
					int returnVal = filechooser.showSaveDialog(null);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = filechooser.getSelectedFile();
						
						/*
						 * sacamos la Path del archivo y su extension
						 */
						Path = file.getPath();
				    } 	
					JasperExportManager.exportReportToPdfFile(
							jasperPrint, Path+".pdf");
					System.out.println("SE A CREADO CORRECTAMENTE EL PDF");
				}
				catch (JRException e)
				{
					e.printStackTrace();
					System.out.println("ERROR EL PDF NO SE A CREADO!!!");
				}
			}

		};

		btnExportar.addActionListener(generar);
		this.setVisible(true);
	}
}
