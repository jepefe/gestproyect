package pkGesproject.informes;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.test.TestRepositoryProducts;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;





public class prueba extends JPanel{

	
	JButton btngenerar = new JButton("Generar");
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	
	
	public prueba(){
	this.setLayout(new GridBagLayout());
	this.add(btngenerar);
	//fileName = System.getProperty("user.dir")+"//src//pkGesproject//reports//prueba.jrxml";
	generar = new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	
	
	
	FastReportBuilder drb = new FastReportBuilder();
	DynamicReport dr = null;
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
		//.setSubtitle("This report was generated at " + new Date())
		.setPrintBackgroundOnOddRows(true)
		.setUseFullPageWidth(true)
		.build();
	} catch (ColumnBuilderException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	JasperPrint jp = null;
	JRDataSource ds = new JRBeanCollectionDataSource(TestRepositoryProducts.getDummyCollection());
	try {
		System.out.println("Antes");
		jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
		System.out.println("Despues");
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JasperViewer.viewReport(jp);
	//finally display the report report
	   
		}
	};
	
	btngenerar.addActionListener(generar);
	
	}
}
