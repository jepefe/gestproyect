package pkGesproject.Formularios;

import java.util.HashMap;

import net.sf.jasperreports.engine.*;

public class reporte {
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	
	public reporte(){
		try
		{
		//1-Compilamos el archivo XML y lo cargamos en memoria
		jasperReport = JasperCompileManager.compileReport("H:/JAVA/GesProject/src/pkGesproject/Formularios/mireport.jrxml");
		
		//2-Llenamos el reporte con la información y parámetros necesarios (En este caso nada)
		jasperPrint = JasperFillManager.fillReport(
		jasperReport, new HashMap(), new JREmptyDataSource());
	
		//3-Exportamos el reporte a pdf y lo guardamos en disco
		JasperExportManager.exportReportToPdfFile(
		jasperPrint, "H:/Java/holaMundo.pdf");
		}
		catch (JRException e)
		{
		e.printStackTrace();
		}
	}
}
