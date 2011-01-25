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
	
	public PnlInformesProyecto(){
		this.setLayout(new GridBagLayout());
		this.add(btngenerar);
		
		
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try
				{
				//1-Compilamos el archivo XML y lo cargamos en memoria
				jasperReport = JasperCompileManager.compileReport("H:/JAVA/GesProject/src/pkGesproject/reports/mireport.jrxml");
				
				//2. Se llena el reporte con la informacion necesaria
				Map pars = new HashMap();
				pars.put("P_INSTITUCION", "Universidad de Montemorelos");
				
				//2-Llenamos el reporte con la información y parámetros necesarios (En este caso nada)
				jasperPrint = JasperFillManager.fillReport(jasperReport, pars, new JREmptyDataSource());
			
					

				
				
				//3-Exportamos el reporte a pdf y lo guardamos en disco
				JasperExportManager.exportReportToPdfFile(
				jasperPrint, "H:/Java/holaMundo.docx");
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
