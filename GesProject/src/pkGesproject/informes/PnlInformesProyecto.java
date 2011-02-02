package pkGesproject.informes;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.JPanel;


import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.test.TestRepositoryProducts;




import pkGesproject.ConexionDb;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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
	DynamicReport dr = null;
	public PnlInformesProyecto(){
		
		this.setLayout(new GridBagLayout());
		this.add(btngenerar);
		//fileName = System.getProperty("user.dir")+"//src//pkGesproject//reports//prueba.jrxml";
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				FastReportBuilder drb = new FastReportBuilder();
				
				try {
					dr = drb.addColumn("State", "state", String.class.getName(),30)
					.addColumn("Branch", "branch", String.class.getName(),30)
					.addColumn("Product Line", "productLine", String.class.getName(),50)
					.addColumn("Item", "item", String.class.getName(),50)
					.addColumn("Item Code", "id", Long.class.getName(),30,true)
					.addColumn("Quantity", "quantity", Long.class.getName(),60,true)
					.addColumn("Amount", "amount", Float.class.getName(),70,true)
					.addGroups(2)
					.setTitle("November 2006 sales report")
					.setSubtitle("This report was generated at " + new Date())
					.setPrintBackgroundOnOddRows(true)
					.setUseFullPageWidth(true)
					.build();
				} catch (ColumnBuilderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JRDataSource ds = new JRBeanCollectionDataSource(TestRepositoryProducts.getDummyCollection());
				JasperPrint jp = null;
				
				try {
				//	jp = JasperFillManager.fillReport(dr, new ClassicLayoutManager(), ds);
					jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JasperViewer.viewReport(jp);    //finally display the report report
				
				
				//test.testReport();
				//test.exportToJRXML();
				//JRDataSource ds = new JRBeanCollectionDataSource(TestRepositoryProducts.getDummyCollection());
				//JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
				//JasperViewer.viewReport(jp);    //finally display the report report
		  		
				/*try
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
							
							if(filechooser.getFileFilter().getDescription().equals("Documento PDF")){
								if(filechooser.getSelectedFile().getName().contains(".pdf")){
									ruta = file.getPath();
								}else{
									ruta = file.getPath()+".pdf";
								}
							}else{
								ruta = file.getPath();
							}
							JasperExportManager.exportReportToPdfFile(jasperPrint, ruta);
							JasperViewer.viewReport(jasperPrint);
							System.out.println("SE A CREADO CORRECTAMENTE EL PDF");
					    } 		
					
					
				}
				catch (JRException e)
				{
					e.printStackTrace();
					System.out.println("ERROR EL PDF NO SE A CREADO!!!");
				}*/
			}

		};

		btngenerar.addActionListener(generar);
		this.setVisible(true);
	}
	
public DynamicReport buildReport() throws Exception{
		
	FastReportBuilder drb = new FastReportBuilder();
	drb.addColumn("State", "state", String.class.getName(),30)
		.addColumn("Branch", "branch", String.class.getName(),30)
		.addColumn("Product Line", "productLine", String.class.getName(),50)
		.addColumn("Item", "item", String.class.getName(),50)
		.addColumn("Item Code", "id", Long.class.getName(),30,true)
		.addColumn("Quantity", "quantity", Long.class.getName(),60,true)
		.addColumn("Amount", "amount", Float.class.getName(),70,true)
		.setTitle("November \"2006\" sales report")
		.setSubtitle("This report was generated at " + new Date())
  			.setPrintBackgroundOnOddRows(true)			
  			.setUseFullPageWidth(true);
  
	DynamicReport dr = drb.build();
	  
	  		return dr;
	}
}
