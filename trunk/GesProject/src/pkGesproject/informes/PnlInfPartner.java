package pkGesproject.informes;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.test.TestRepositoryProducts;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;

public class PnlInfPartner extends JPanel{
	
	JLabel jlbagrupa, jlbordenar;
	GpComboBox gbagrupa, gbordenar;
	JList lista1,lista2;
	JButton jbtngenerar;
	GesIdioma rec = GesIdioma.obtener_instancia();
	DefaultListModel modelo,modelo2;
	String[][] datos = {{"Partner Code","Name","Sector","Country", "Address", "Postal Code", "Phone 1", "Phone 2", "Fax", "Email 1", "Email 2"},
						{"cod_part","nombre","sector","pais","direccion","codpostal","telefono","telefono2","fax","email","email2"}};
	String[] agrupar = {"Ninguno","Proyecto"};
	
	int dimension[] = {7,30,30,15,25,6,13,13,13,30,30};
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	
	public PnlInfPartner(){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0,0,0,5);
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila 
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		this.add(jlbagrupa = new JLabel(rec.idioma[rec.eleidioma][131]),gbc);
		gbc.gridx = 1; // El área de texto empieza en la columna
		this.add(gbagrupa = new GpComboBox(),gbc);
		gbagrupa.setPreferredSize(new Dimension(165,30));
		gbc.insets = new Insets(20,30,15,5);
		gbc.gridx = 2; // El área de texto empieza en la columna
		this.add(jlbordenar = new JLabel(rec.idioma[rec.eleidioma][132]),gbc);
		gbc.insets = new Insets(20,0,15,5);
		gbc.gridx = 3; // El área de texto empieza en la columna
		this.add(gbordenar = new GpComboBox(),gbc);
		gbordenar.setPreferredSize(new Dimension(165,30));
		

		
		
		// JLIST
		modelo = new DefaultListModel(); // modelos JLIST
		modelo2 = new DefaultListModel(); 

		lista1 = new JList(modelo); 
		int cuenta;
		// for para pasar los datos al modelo
		for(int j=0; j<dimension.length ; j++){
			modelo.addElement(datos[0][j]);  
		}

		// Primer JLIST
		JScrollPane sp1 = new JScrollPane(lista1);
		lista1.setFixedCellWidth(145);
		lista1.setFixedCellHeight(23);
		sp1.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][84])); 
		gbc.gridx = 1; // El área de texto empieza en la columna
		gbc.gridy = 1; 
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		gbc.insets = new Insets(25,40,25,100);
		//gbc.anchor = GridBagConstraints.WEST;
		this.add(sp1,gbc);
		// Segundo JLIST
		lista2 = new JList(modelo2);
		JScrollPane sp2 = new JScrollPane(lista2);
		lista2.setFixedCellWidth(145);
		lista2.setFixedCellHeight(23);
		sp2.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][82])); 
		gbc.gridx = 2; 
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		//gbc.anchor = GridBagConstraints.WEST;
		this.add(sp2,gbc);
		

		
		
		gbc.gridy = 2; // El área de texto empieza en la fila
		//gbc.gridx = 0;
		gbc.gridwidth = 4; // El área de texto ocupa x columnas.
		gbc.anchor = GridBagConstraints.WEST;
		this.add(jbtngenerar = new JButton(rec.idioma[rec.eleidioma][133]),gbc);
		
		
		/*
		CbCoordinador = new GpComboBox() ; //ComboBox (Coordinador)
		CbCoordinador.setPreferredSize(new Dimension(165,30));
		jpnl.add(CbCoordinador,gbc);
		 */
		// Evento doble click primer JLIST
		MouseListener mouseListener = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modelo2.addElement(lista1.getSelectedValue());
					gbordenar.addItem(lista1.getSelectedValue());		    
					modelo.removeElement(lista1.getSelectedValue());
				}
			}
		};
		lista1.addMouseListener(mouseListener);

		modelo.toArray();
		
		
		// Evento doble click segundo  JLIST (lsitaP2)
		MouseListener mouseListener2 = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modelo.addElement(lista2.getSelectedValue());
					gbordenar.removeItem(lista2.getSelectedValue());	
					modelo2.removeElement(lista2.getSelectedValue());
				}
			}
		};
		lista2.addMouseListener(mouseListener2);
		
		//Cargamos los combobox
		for(int i = 0; i<agrupar.length;i++){
			gbagrupa.addItem(agrupar[i]);
		}
		
		
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(gbagrupa.getSelectedItem().equals("Ninguno")){
					int pos=0;
					String orden;
					pos = buscar(datos,(String) gbordenar.getSelectedItem());
					if(datos[1][pos].equals("sector")){
						orden = "s."+datos[1][pos];
					}else{
						if(datos[1][pos].equals("pais")){
							orden = "pais."+datos[1][pos];
						}else{
							orden = "pa."+datos[1][pos];
						}
					}
					String consulta = "SELECT p.nombre AS proyecto,pa.cod_part,pa.nombre,pa.direccion,pa.codpostal,pa.telefono,pa.telefono2," +
							"pa.fax,pa.email,pa.email2,s.sector,pais.pais FROM PARTNER pa INNER JOIN "+
					"PARTNER_PROYECTOS pp ON pa.cod_part= pp.cod_part INNER JOIN PROYECTOS p ON pp.id_pro = p.id_pro " +
					"INNER JOIN SECTORES s ON pa.sector = s.id_sector INNER JOIN PAIS pais ON pa.pais = pais.id_pais ORDER BY "+orden;
					
					conexion.Conectardb();
					rs = conexion.ConsultaSQL(consulta);
					final JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
					
					FastReportBuilder drb = new FastReportBuilder();
					DynamicReport dr = null;
					try {
						dr = drb.build();
						pos=0;
						for(int i = 0; i<lista2.getModel().getSize();i++){
							//System.out.println(lista2.getModel().getElementAt(i));
							pos = buscar(datos,(String) lista2.getModel().getElementAt(i));
							drb.addColumn((String) lista2.getModel().getElementAt(i), datos[1][pos], String.class.getName(),dimension[pos]);
						}
						
						drb.setPrintBackgroundOnOddRows(true);
						drb.setTitle("PARTNERS");
						//.setSubtitle("This report was generated at " + new Date())
						//.setPrintBackgroundOnOddRows(true)
						drb.setUseFullPageWidth(true);
						//.build();
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
						jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), resulset);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JasperViewer.viewReport(jp,false);    //finally display the report report
				
				}else{
					int pos=0;
					String orden;
					pos = buscar(datos,(String) gbordenar.getSelectedItem());
					if(datos[1][pos].equals("sector")){
						orden = "s."+datos[1][pos];
					}else{
						if(datos[1][pos].equals("pais")){
							orden = "pais."+datos[1][pos];
						}else{
							orden = "pa."+datos[1][pos];
						}
					}
					String consulta = "SELECT p.nombre AS proyecto,pa.cod_part,pa.nombre,pa.direccion,pa.codpostal,pa.telefono,pa.telefono2," +
							"pa.fax,pa.email,pa.email2,s.sector,pais.pais FROM PARTNER pa INNER JOIN "+
					"PARTNER_PROYECTOS pp ON pa.cod_part= pp.cod_part INNER JOIN PROYECTOS p ON pp.id_pro = p.id_pro " +
					"INNER JOIN SECTORES s ON pa.sector = s.id_sector INNER JOIN PAIS pais ON pa.pais = pais.id_pais ORDER BY proyecto,"+orden;
					
					conexion.Conectardb();
					rs = conexion.ConsultaSQL(consulta);
					final JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
					
					FastReportBuilder drb = new FastReportBuilder();
					DynamicReport dr = null;
					try {
						dr = drb.build();
						drb.addColumn("Proyecto", "proyecto", String.class.getName(),30);
						pos=0;
						for(int i = 0; i<lista2.getModel().getSize();i++){
							//System.out.println(lista2.getModel().getElementAt(i));
							pos = buscar(datos,(String) lista2.getModel().getElementAt(i));
							drb.addColumn((String) lista2.getModel().getElementAt(i), datos[1][pos], String.class.getName(),dimension[pos]);
						}
						
						drb.addGroups(1);
						drb.setPrintBackgroundOnOddRows(true);
						drb.setTitle("PARTNERS POR PROYECTO");
						//.setSubtitle("This report was generated at " + new Date())
						//.setPrintBackgroundOnOddRows(true)
						drb.setUseFullPageWidth(true);
						//.build();
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
						jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), resulset);
					} catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JasperViewer.viewReport(jp,false);    //finally display the report report
				}
				
			}
			
		};
		
		jbtngenerar.addActionListener(generar);
		
		
	}
	
	/**
	 * Método que busca una palabra dentro de un array y devuelve su posicion dentro del array
	 * @param array
	 * @param cadena
	 * @return
	 */
	public int buscar(String[][] array, String cadena){
		int cuenta=0;
		for(int i=0;i<array[0].length;i++ ){
			if(array[0][i].equals(cadena)){
				cuenta = i;
			}
		}
	
		return cuenta;
	}

}
