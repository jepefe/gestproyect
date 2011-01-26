package pkGesproject.Proyectos;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
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
					//1-Compilamos el archivo XML y lo cargamos en memoria
					jasperReport = JasperCompileManager.compileReport(fileName);

					//2. Se llena el reporte con la informacion necesaria
					/*Map pars = new HashMap();
				pars.put("P_INSTITUCION", "Universidad de Montemorelos");
				pars.put("P_FACULTAD", "Facultad de Ingeniería Tecnología");
				pars.put("P_CARRERA", "Ingeniería en Sistemas Computacionales");

				jasperPrint = JasperFillManager.fillReport(jasperReport, pars, new JREmptyDataSource());
					 */
					jasperPrint = JasperFillManager.fillReport(
							jasperReport, new HashMap(), new JREmptyDataSource());



					//3-Exportamos el reporte a pdf y lo guardamos en disco
					JasperExportManager.exportReportToPdfFile(
							jasperPrint, "/Users/ruben_albi/Desktop/pruebas/reporte.pdf");
				}
				catch (JRException e)
				{
					e.printStackTrace();
				}
			}

		};

		btngenerar.addActionListener(generar);
		this.setVisible(true);
	}
}
