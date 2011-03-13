package pkGesproject.informes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import javax.swing.JPanel;


import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.test.TestRepositoryProducts;




import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PnlInfProyecto extends JPanel{
	JLabel jlbagrupa, jlbordenar, alerta;
	GpComboBox gbagrupa, gbordenar;
	JList lista1,lista2;
	JButton jbtngenerar;
	GesIdioma rec = GesIdioma.obtener_instancia();
	DefaultListModel modelo,modelo2;
	String[][] datos = {{"Cod Project","Name","Description","State","Budget","Start Date", "End Date","Contract Number"},
						{"id_pro","nombre","descripcion","estado","presupuesto","f_ini","f_fin","num_contrato"}};
	String[] agrupar = {"Ninguno","Partner"};
	
	int dimension[] = {7,15,30,10,7,8,8,15};
	ActionListener generar;
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	ResultSet rs;
	ConexionDb conexion= new ConexionDb();
	JPanel panel, mensaje;
	int ancho = 0 ;
	private final int PORTRAIT_PAGE_HEIGHT = 595;  
	private final int PORTRAIT_PAGE_WIDTH = 842; 

	public PnlInfProyecto(){
		panel = new JPanel();
		mensaje = new JPanel();
		
		//cargamos la interfaz del panel lo primero
		cargar_panel();
		crear_mensaje();
		// Evento doble click primer JLIST
		MouseListener mouseListener = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					agregar();
				}
			}
		};
	
		// Evento doble click segundo  JLIST (lsitaP2)
		MouseListener mouseListener2 = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					quitar();
				}
			}
		};
		
		//accion generar 
		generar = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getActionCommand().equals("generar")){
					if(gbordenar.getSelectedItem()== null){
					mensaje(rec.idioma[rec.eleidioma][245],false);
					}else{
						mensaje.setVisible(false);
						generar_reporte();
					}
				}

				if(arg0.getActionCommand().equals("agrupar")){
					if(gbagrupa.getSelectedItem().toString().equals("Ninguno")){
						ancho = ancho-20;
						mensaje("Ahora puede introducir mas columnas",true);
					}else{
						limpiar_campos();
						ancho =20;
						mensaje("Introduzca las columnas",true);
						//se genera el reporte

					}
				}
			}
		};
		
		//añadimos los eventos a los componentes correspondientes
		gbagrupa.addActionListener(generar);
		gbagrupa.setActionCommand("agrupar");
		jbtngenerar.addActionListener(generar);
		jbtngenerar.setActionCommand("generar");
		lista1.addMouseListener(mouseListener);
		lista2.addMouseListener(mouseListener2);
		
		modelo.toArray();
		this.setLayout(new BorderLayout());
		this.add(mensaje,BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);

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
		panel.setLayout(new GridBagLayout());
		
		//Label Agrupar
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0,0,0,5);
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila 
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		panel.add(jlbagrupa = new JLabel(rec.idioma[rec.eleidioma][131]),gbc);
		
		//combo Agrupar
		gbc.gridx = 1; // El área de texto empieza en la columna
		panel.add(gbagrupa = new GpComboBox(),gbc);
		gbagrupa.setPreferredSize(new Dimension(165,30));
		
		//Label ordenar
		gbc.insets = new Insets(20,30,15,5);
		gbc.gridx = 2; // El área de texto empieza en la columna
		panel.add(jlbordenar = new JLabel(rec.idioma[rec.eleidioma][132]),gbc);
		
		//Combo ordenar
		gbc.insets = new Insets(20,0,15,5);
		gbc.gridx = 3; // El área de texto empieza en la columna
		panel.add(gbordenar = new GpComboBox(),gbc);
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
		sp1.setColumnHeaderView(new JLabel()); 
		gbc.gridx = 1; // El área de texto empieza en la columna
		gbc.gridy = 1; 
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		gbc.insets = new Insets(25,40,25,100);
		//gbc.anchor = GridBagConstraints.WEST;
		panel.add(sp1,gbc);
		
		
		// Segundo JLIST
		lista2 = new JList(modelo2);
		JScrollPane sp2 = new JScrollPane(lista2);
		lista2.setFixedCellWidth(145);
		lista2.setFixedCellHeight(23);
		sp2.setColumnHeaderView(new JLabel()); 
		gbc.gridx = 2; 
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		//gbc.anchor = GridBagConstraints.WEST;
		panel.add(sp2,gbc);
		

		
		//Boton generar
		gbc.gridy = 2; // El área de texto empieza en la fila
		//gbc.gridx = 0;
		gbc.gridwidth = 4; // El área de texto ocupa x columnas.
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(jbtngenerar = new JButton(rec.idioma[rec.eleidioma][133]),gbc);
		
		
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
			
			if(datos[1][pos].equals("estado")){
				orden = "e."+datos[1][pos];
			}else{
				orden = "p."+datos[1][pos];
			}
			
			//hacemos la consulta con los campos correspondientes
			String consulta = "SELECT p.id_pro,p.nombre,p.descripcion,e.estado,p.presupuesto,p.f_ini,p.f_fin,p.num_contrato FROM PROYECTOS as p " +
							"INNER JOIN ESTADOS_PROYECTO as e on p.estado = e.id_est " +
							"ORDER BY "+orden;
			
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
				drb.setTitle("PROYECTOS");
				//.setSubtitle("panel report was generated at " + new Date())
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
			if(datos[1][pos].equals("estado")){
				orden = "e."+datos[1][pos];
			}else{
				orden = "p."+datos[1][pos];
			}
			String consulta = "SELECT pa.nombre AS PARTNER, p.id_pro,p.nombre,p.descripcion,e.espana,p.presupuesto,p.f_ini,p.f_fin,p.num_contrato FROM PROYECTOS as p " +
			"INNER JOIN PARTNER_PROYECTOS as pp on pp.id_pro = p.id_pro INNER JOIN PARTNER as pa ON pp.cod_part = pa.cod_part INNER JOIN ESTADOS_PROYECTO "+
			" as e on p.estado = e.id_est ORDER BY PARTNER , p."+datos[1][pos];
			
			conexion.Conectardb();
			rs = conexion.ConsultaSQL(consulta);
			final JRResultSetDataSource resulset = new JRResultSetDataSource(rs);
			
			FastReportBuilder drb = new FastReportBuilder();
			DynamicReport dr = null;
			try {
				dr = drb.build();
				drb.addColumn("PARTNER", "PARTNER", String.class.getName(),20);
				pos=0;
				for(int i = 0; i<lista2.getModel().getSize();i++){
					//System.out.println(lista2.getModel().getElementAt(i));
					pos = buscar(datos,(String) lista2.getModel().getElementAt(i));
					drb.addColumn((String) lista2.getModel().getElementAt(i), datos[1][pos], String.class.getName(),dimension[pos]);
				}
				
				drb.addGroups(1);
				drb.setPrintBackgroundOnOddRows(false);
				drb.setTitle("PROYETOS POR PARTNERS");
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
	
	
	/**
	 * Metodo para agregar al  Jlist 2
	 */
	public void agregar(){
		int pos = buscar(datos,(String)lista1.getModel().getElementAt(lista1.getSelectedIndex()));
		
		if((ancho + dimension[pos])<=75){
			if(lista1.getSelectedValue().equals("")){}else{
				modelo2.addElement(lista1.getSelectedValue());
				gbordenar.addItem(lista1.getSelectedValue());
				ancho = ancho + dimension[pos];
				modelo.removeElement(lista1.getSelectedValue());
				
			}
		}else{
			mensaje("No caben mas columnas en el informe", false);
		}
	}
	/**
	 * Metodo para quitar de la lista 2
	 */
	
	public void quitar(){
		int pos = buscar(datos,(String)lista2.getModel().getElementAt(lista2.getSelectedIndex()));
		if(lista2.getSelectedValue().equals("")){}else{
			mensaje.setVisible(false);
			modelo.addElement(lista2.getSelectedValue());
			ancho = ancho - dimension[pos];
			gbordenar.removeItem(lista2.getSelectedValue());	
			modelo2.removeElement(lista2.getSelectedValue());
		}
	}
	
	
	/**
	 * Metodo para  crear  el mensaje.
	 */
	public void crear_mensaje(){
		alerta=new JLabel();
		alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
		mensaje.add(alerta);
		mensaje.setBackground(Color.decode("#D0E495"));
		mensaje.setVisible(false);
	}
		
	
	/**
	 * Metodo para introducir que queremos en el mensaje.
	 */
	public void mensaje(String msg, boolean color){
		alerta.setText(msg);
		if(color){
		mensaje.setBackground(Color.decode("#D0E495"));
		}else{
		mensaje.setBackground(Color.decode("#ec8989"));
		}
		mensaje.setVisible(true);
	}
	public void limpiar_campos(){
		modelo2.removeAllElements();
		modelo.removeAllElements();
		gbordenar.removeAllItems();
		for(int j=0; j<dimension.length ; j++){
			modelo.addElement(datos[0][j]);  
		}
		gbordenar.setSelectedItem(null);
	}
	
}
