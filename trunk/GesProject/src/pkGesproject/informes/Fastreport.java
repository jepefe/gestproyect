package pkGesproject.informes;

import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import pkGesproject.ConexionDb;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.test.BaseDjReportTest;





public class Fastreport{

	
	
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	
	
	
	public DynamicReport buildReportLayout() throws ColumnBuilderException, ClassNotFoundException {
		// TODO Auto-generated method stub
		FastReportBuilder drb = new FastReportBuilder();
		drb.addColumn("State", "state", String.class.getName(),30)
		.addColumn("Branch", "branch", String.class.getName(),30)
		.setTitle("November \"2006\" sales report")
		.setUseFullPageWidth(true)
		.setPrintColumnNames(true);
		
		DynamicReport dr = drb.build();
		
		String consulta = "SELECT p.nombre AS proyecto,pa.nombre,pa.sector,pa.pais FROM PARTNER pa INNER JOIN "+
		"PARTNER_PROYECTOS pp ON pa.cod_part= pp.cod_part INNER JOIN PROYECTOS p ON pp.id_pro = p.id_pro ORDER BY p.nombre";
conexion.Conectardb();
rs = conexion.ConsultaSQL(consulta);
JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
		
		
		try {
			JasperReport jr = DynamicJasperHelper.generateJasperReport(dr, new ClassicLayoutManager(), new HashMap());
			jasperPrint = JasperFillManager.fillReport(jr, new HashMap(), resulset);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documento PDF", "pdf");//Añadimos el filtro para que nos muestre sólo las extensiones que queremos
		filechooser.setFileFilter(filter);
		
		int returnVal = filechooser.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = filechooser.getSelectedFile();
				String ruta;
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
				try {
					JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("SE A CREADO CORRECTAMENTE EL PDF");
		    } 	
		return dr;
	}

}
