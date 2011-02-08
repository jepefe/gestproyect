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
import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.test.TestRepositoryProducts;

public class PnlInfStaff extends JPanel{

	
	JLabel jlbagrupa, jlbordenar;
	GpComboBox gbagrupa, gbordenar;
	JList lista1,lista2;
	JButton jbtngenerar;
	GesIdioma rec = GesIdioma.obtener_instancia();
	DefaultListModel modelo,modelo2;
	String[][] datos = {{"Code Staff","Dni","Name","Surname","Birth Date", "Country", "Province","Region","City","Address","PostalCode","Phone1","Phone2","Fax","Email"},
						{"sid_staff","dni","nombre","apellidos","f_nac","pais_en_ingles","estado","region","ciudad","direccion","codpostal","telefono","telefono2","fax","email"}};
	String[] agrupar = {"Ninguno","Partner"};
	
	int dimension[] = {7,8,20,20,8,8,8,8,8,15,5,9,9,9,12};
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	
	public PnlInfStaff(){
		
		//cargamos la interfaz del panel lo primero
		cargar_panel();
		
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
		
		//accion generar 
		generar = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//se genera el reporte
				generar_reporte();
				
			}
			
		};
		
		//añadimos los eventos a los componentes correspondientes
		jbtngenerar.addActionListener(generar);
		lista1.addMouseListener(mouseListener);
		lista2.addMouseListener(mouseListener2);
		
		modelo.toArray();
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
	
	/**
	 * Método que crea los componentes del panel y los añade
	 */
	public void cargar_panel(){
		this.setLayout(new GridBagLayout());
		
		//Label Agrupar
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0,0,0,5);
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila 
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		this.add(jlbagrupa = new JLabel(rec.idioma[rec.eleidioma][131]),gbc);
		
		//combo Agrupar
		gbc.gridx = 1; // El área de texto empieza en la columna
		this.add(gbagrupa = new GpComboBox(),gbc);
		gbagrupa.setPreferredSize(new Dimension(165,30));
		
		//Label ordenar
		gbc.insets = new Insets(20,30,15,5);
		gbc.gridx = 2; // El área de texto empieza en la columna
		this.add(jlbordenar = new JLabel(rec.idioma[rec.eleidioma][132]),gbc);
		
		//Combo ordenar
		gbc.insets = new Insets(20,0,15,5);
		gbc.gridx = 3; // El área de texto empieza en la columna
		this.add(gbordenar = new GpComboBox(),gbc);
		gbordenar.setPreferredSize(new Dimension(165,30));
		

		
		
		// modelo para el jlist
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
		

		
		//Boton generar
		gbc.gridy = 2; // El área de texto empieza en la fila
		//gbc.gridx = 0;
		gbc.gridwidth = 4; // El área de texto ocupa x columnas.
		gbc.anchor = GridBagConstraints.WEST;
		this.add(jbtngenerar = new JButton(rec.idioma[rec.eleidioma][133]),gbc);
		
		
		//Cargamos el combobox agrupar
		for(int i = 0; i<agrupar.length;i++){
			gbagrupa.addItem(agrupar[i]);
		}
		
		
	}

	/**
	 * Método que se encarga de generar el reporte
	 */
	
	public void generar_reporte(){
		
		//Lo primero comprobamos si se va a agrupar o no para hacer de una forma u otra
		if(gbagrupa.getSelectedItem().equals("Ninguno")){
			int pos=0;
			String orden;
			pos = buscar(datos,(String) gbordenar.getSelectedItem());
			
			/*if(datos[1][pos].equals("sector")){
				orden = "s."+datos[1][pos];
			}else{
				if(datos[1][pos].equals("pais")){
					orden = "pais."+datos[1][pos];
				}else{
					orden = "pa."+datos[1][pos];
				}
			}
			*/
			//hacemos la consulta con los campos correspondientes
			String consulta = "SELECT s.id_staff, s.dni, s.nombre,s.apellidos,s.f_nac,e.pais_en_ingles," +
					" pro.estado,s.region,s.ciudad,s.direccion,s.codpostal,s.telefono, s.telefono2,s.fax,s.email " +
					" FROM STAFF as s inner join PAIS  as e on s.pais = e.id_pais INNER JOIN PROVINCIAS as pro on s.provincia = pro.id_provincias " +
					" ORDER BY s."+datos[1][pos];
			
			conexion.Conectardb();
			rs = conexion.ConsultaSQL(consulta);
			JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
			FastReportBuilder drb = new FastReportBuilder();
			DynamicReport dr = null;
			
			//Con este for creamos las columnas que se han elegido
			try {
				dr = drb.build();
				pos=0;
				for(int i = 0; i<lista2.getModel().getSize();i++){
					//System.out.println(lista2.getModel().getElementAt(i));
					pos = buscar(datos,(String) lista2.getModel().getElementAt(i));
					drb.addColumn((String) lista2.getModel().getElementAt(i), datos[1][pos], String.class.getName(),dimension[pos]);
				}
				
				drb.setPrintBackgroundOnOddRows(false);
				drb.setTitle("STAFF");
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

			//generamos el jasperprint pasandole el resulset con los datos
			JRDataSource ds = new JRBeanCollectionDataSource(TestRepositoryProducts.getDummyCollection());
			JasperPrint jp = null;
			try {
				jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), resulset);
			} catch (JRException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Por último mostramos el reporte por pantalla 
			JasperViewer.viewReport(jp,false); 
		
		}else{
			int pos=0;
			String orden;
			pos = buscar(datos,(String) gbordenar.getSelectedItem());
			if(datos[1][pos].equals("pais_en_ingles")){
				orden = "e."+datos[1][pos];
			}else{
				if(datos[1][pos].equals("estado")){
					orden = "pro."+datos[1][pos];
				}else{
				if(datos[1][pos].equals("partner")){
					orden = "p."+datos[1][pos];
				}
					orden = "s."+datos[1][pos];
				}
			}
			String consulta = "SELECT p.nombre as Partner ,s.id_staff, s.dni, s.nombre,s.apellidos,s.f_nac,e.pais_en_ingles," +
			" pro.estado,s.region,s.ciudad,s.direccion,s.codpostal,s.telefono, s.telefono2,s.fax,s.email " +
			" FROM STAFF as s inner join PAIS  as e on s.pais = e.id_pais INNER JOIN PROVINCIAS as pro on s.provincia = pro.id_provincias INNER JOIN PARTNER as p on p.cod_part = s.cod_part " +
			" ORDER BY p.nombre,"+orden;
			
			conexion.Conectardb();
			rs = conexion.ConsultaSQL(consulta);
			final JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
			
			FastReportBuilder drb = new FastReportBuilder();
			DynamicReport dr = null;
			try {
				dr = drb.build();
				
				
				drb.addColumn("Partner","Partner", String.class.getName(),20);
				pos=0;
				for(int i = 0; i<lista2.getModel().getSize();i++){
					//System.out.println(lista2.getModel().getElementAt(i));
					pos = buscar(datos,(String) lista2.getModel().getElementAt(i));
					drb.addColumn((String) lista2.getModel().getElementAt(i), datos[1][pos], String.class.getName(),dimension[pos]);
				}
				
				drb.addGroups(1);
			
				drb.setPrintBackgroundOnOddRows(false);
				drb.setTitle("STAFF FROM PARTNER");
				drb.setPrintColumnNames(true);
				drb.setUseFullPageWidth(true);
			


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
	
	
}