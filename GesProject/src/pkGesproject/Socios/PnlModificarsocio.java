package pkGesproject.Socios;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;
import pkGesproject.Staff.pnlAlta_staff;

/**
 * Esta clase esta creada para listar todos los partners, poder modificar los datos de cualquiera de ellos
 * y tambien la opción de elimiar alguno.
 **/

public class PnlModificarsocio extends JPanel{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs,rs2;
	JPanel panel = new JPanel();
	int cuenta = 0;
	int columnas;
	String datos[][];
	String auxdatos[][];
	String colu[] = {rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][69],rec.idioma[rec.eleidioma][76]};
	String consulta = "SELECT p.nombre,p.telefono,p.telefono2,p.cod_part FROM PARTNER p ORDER BY p.nombre"; //Esta consulta cargara los datos en la tabla
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar,jbtnaceptar,jbtncancelar,jbtnactualizar;
	Boolean llena = new Boolean(false);
	Boolean existe= new Boolean(false);
	String[] fila;
	JTable jtblLateral;
	JScrollPane jspntabla;
	PnlAltasocio mod;
	FocusListener comp;
	public static JDialog modificar;
	static PnlModificarsocio instancia = new PnlModificarsocio(); 
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    FocusListener foco;
    KeyListener accion,suprimir;
    ActionListener event;
    MouseListener mouse;
    Object[] dat;
    
    public PnlModificarsocio(){
    	
    	
    	//utilizamos la funcion crear tabla para crear la tabla
    	crear_tabla();
    	
    	//creamos la interfaz
		crear_interfaz();
        	
    	//evento para borrar el texto del textfield buscar
		foco = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				jtxt.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtxt.getText().equals("")){
					jtxt.setText(rec.idioma[rec.eleidioma][235]);
				}
				
			}
			
		};
        
            
        // keylistener para cuando escribe en el campo texto busqueda que buque en la tabla
        accion = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Has pulsado una tecla");
				
				
			}

			@Override
			public void keyReleased(KeyEvent act) {
				// TODO Auto-generated method stub
				//llamamos al método para buscar coincidencias dentrod e la tabla
				algoritmo_busqueda();
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        };
       
            
        event = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("modificar")){
					//llamamos al metodo modificar
					modificar();
				}
				
				if(e.getActionCommand().equals("eliminar")){
					//llamamos al metodo eliminar
					eliminar();
				}
				
				
			}
        	
        };
        
        mouse = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modificar();
				}
			}
        	
        };
            
        
        suprimir = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==127)
					eliminar();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        };
        //agregamos las acciones necesarias
        jbtnmodificar.setActionCommand("modificar");
        jbtnmodificar.addActionListener(event);
        jbtneliminar.setActionCommand("eliminar");
        jbtneliminar.addActionListener(event);
        jtxt.addKeyListener(accion);
        jtxt.addFocusListener(foco);
        jtblLateral.addMouseListener(mouse);
        jtblLateral.addKeyListener(suprimir);
    }
   
    /**
     * con este método se crean y se añaden todos los elementos a la interfaz
     */
    public void crear_interfaz(){
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
    	
    	/*
    	 * Creamos el jtextfield para la busquera y le añadimos un focus listener para que se borre su contenido
    	 */
		jtxt=new JTextField(20);
		jtxt.setText(rec.idioma[rec.eleidioma][235]);
		jtxt.putClientProperty("JTextField.variant", "search");
		jtxt.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
		
		
		GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // El área de texto empieza en la columna
        constraints.gridy = 0; // El área de texto empieza en la fila
        constraints.gridwidth = 2; // El área de texto ocupa x columnas.
        constraints.gridheight = 1; // El área de texto ocupa x filas.
        constraints.insets = new Insets(20,30,0,10);
        this.add(jtxt,constraints);
        
        
        final JScrollPane jspntabla = new JScrollPane(jtblLateral); //Añadimos la tabla al jscrollpane
        constraints.gridx = 0; // El área de texto empieza en la columna
        constraints.gridy = 1; // El área de texto empieza en la fila
        constraints.weighty = 1.0; // Con esto la fila 1 se estira al estirar la ventana
        constraints.fill = GridBagConstraints.BOTH;
        this.add(jspntabla,constraints);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.0; // Volvemos a dejarla como antes para el resto
        
        jbtnmodificar = new JButton(rec.idioma[rec.eleidioma][38]);
        constraints.gridy = 2; // El área de texto empieza en la fila
        constraints.gridwidth = 1; // El área de texto ocupa x columnas.
        constraints.insets = new Insets(20,30,20,10);
        //constraints.anchor = GridBagConstraints.CENTER;
        this.add(jbtnmodificar,constraints);
        
        jbtneliminar = new JButton(rec.idioma[rec.eleidioma][39]);
        constraints.gridx = 1; // El área de texto empieza en la columna
        //constraints.anchor = GridBagConstraints.EAST;
        this.add(jbtneliminar,constraints);
    }
    
    /**
     * Este metodo devuelve true o false dependiendo si el registro actual de auxdatos indicado por la variable i existe o no 
     * en la tabla datos.
     * @param datos
     * @param auxdatos
     * @param i
     * @param cuenta
     * @param columnas
     * @return
     **/
    public Boolean buscar_reg(String datos[][],String auxdatos[][],int i,int cuenta,int columnas){
		
    	Boolean existe = false;
    	int cont=0;
    	for(int f =0;f<cuenta;f++){
    		for(int c = 0;c<columnas;c++){
    			if(auxdatos[i][c].equals(datos[f][c])){
    				cont++;
    			}
    		}
    		if(cont==columnas){
    			existe=true;
    		}
    		cont=0;
    	}
    	
    	return existe;
    }
    
    /**
     * este método se utiliza para crear el JTable al inicio del panel
     */
    public void crear_tabla(){
    	conexion.Conectardb();
    	rs = conexion.ConsultaSQL(consulta);
    	cuenta = 0;
    	try {
			while(rs.next()){
				cuenta++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//creamos los array y matrices necesarios
		fila = new String[colu.length+1];
    	columnas =colu.length+1;
    	datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
	
		
		
		//obtenemos los datos de la base de datos y cargamos el table model
    	rs = conexion.ConsultaSQL(consulta);
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<columnas+1;j++){
					datos[i][j-1] = rs.getString(j);
					fila[j-1]=datos[i][j-1];
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tablemodel= new DefaultTableModel(datos,colu);
    	
    	
    	//creamos el JTable pasandole nuestro tablemodel
    	jtblLateral  = new JTable(tablemodel){
	   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	              }
	    };
	    
	    jtblLateral.setPreferredScrollableViewportSize(new Dimension(700,160));
    }
    
    /**
     * este método es el algoritmo que busca coincidencias dentro de las tablas
     */
    public void algoritmo_busqueda(){
    	dat = new Object[columnas];
    	if(llena==false){
			for(int i=0;i<cuenta;i++){
				for(int j = 0;j<columnas;j++){
					auxdatos[i][j]= datos[i][j];
					datos[i][j]=null;								
				}
			}
			llena = true;
		}
		
		if(llena==true){
			for(int i=0;i<cuenta;i++){
				for(int j = 0;j<columnas;j++){
					datos[i][j]=null;
				}
			}
			if(jtxt.getText().equals("")){
				for(int i=0;i<cuenta;i++){
					for(int j = 0;j<columnas;j++){
						datos[i][j]=auxdatos[i][j];
					}
				}
		    	conexion.cerrarConexion();
		    	tablemodel.setDataVector(datos, colu);
			}else{
				int tam = jtxt.getText().length();
				int i=0;
				int j=0;
				int fila=0;
				String[] res = new String[columnas];//almacenamos los resultados
				if(tablemodel.getRowCount() != 0){//si la tabla no esta vacia la vaciamos
					for( int a = tablemodel.getRowCount() - 1; a >= 0; a-- ){
				tablemodel.removeRow(a);
					}
				}
				try{
					for(j=0;j<columnas;j++){
						for(i=0;i<cuenta;i++){
							if(auxdatos[i][j]!= null){
								if(auxdatos[i][j].regionMatches( true, 0, jtxt.getText(), 0, tam )){
									existe=buscar_reg(datos,auxdatos,i,cuenta,columnas);
									if(existe==false){
    									for(int col =0;col<columnas;col++){
    										datos[fila][col]= auxdatos[i][col];
    										dat[col] = datos[fila][col];
    									}
    									fila++;
    									
    									tablemodel.addRow(dat);
									}
									
								}
							}
						}
					}
					
				}catch(ArrayIndexOutOfBoundsException act1) {
				}
			}
				
			
		}
    }
    
    /**
     * Sirve para la modificación de los datos al presional el boton
     */
    public void modificar(){
    	modificar = new JDialog();
		mod = new PnlAltasocio();
		modificar.add(mod);
		modificar.setBounds(0, 0, 600, 650);
		modificar.setLocationRelativeTo(null);
		modificar.setModal(true);
		mod.jtxt[0].removeFocusListener(mod.foco);
		
		conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT p.nombre,p.direccion,p.codpostal,p.email,p.email2,p.telefono,p.telefono2,p.fax,p.observaciones FROM PARTNER p WHERE p.cod_part = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
    	int i=1;
    	
			try {
					
				rs.next();
				for(i=1;i<9;i++){
					mod.jtxt[i-1].setText(rs.getString(i));
				}
				mod.textarea.setText(rs.getString(i));
				rs=conexion.ConsultaSQL("SELECT p.nombre FROM TASAS_PAIS p INNER JOIN PARTNER pa ON p.id_pais = pa.pais WHERE pa.cod_part = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
				rs.next();
				mod.cbpais.setSelectedItem(rs.getString(1));
			
				rs=conexion.ConsultaSQL("SELECT s.sector FROM SECTORES s INNER JOIN PARTNER p ON s.id_sector = p.sector WHERE p.cod_part = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
				rs.next();
				mod.cbsector.setSelectedItem(rs.getString(1));
				mod.jbtnaceptar.setVisible(false);
				mod.jbtncancelar.setVisible(false);
				mod.jbtnaceptarmod.setVisible(true);
				mod.jbtncancelarmod.setVisible(true);
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
    	conexion.cerrarConexion();
		
		modificar.setVisible(true);
    }
    
    /**
     * este método elimina el partner seleccionado en la lista
     */
    public void eliminar(){
    	Component aviso = null;
		int s = JOptionPane.showOptionDialog(aviso, rec.idioma[rec.eleidioma][194],null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		
		if(s==0){
			conexion.Conectardb();
			conexion.executeUpdate("DELETE FROM PARTNER WHERE cod_part = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
			
			actualizar_tabla();
			
			JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][63]);
		
			conexion.cerrarConexion();
		}
    }
    /**
     * con este método podemos actualizar la tabla siempre que lo llamemos
     */
    public void actualizar_tabla(){
    	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL(consulta);
    	cuenta = 0;
    	try {
			while(rs.next()){
				cuenta++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//ponemos nuevos tamaños a las matrices
		datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
		
		
		//obtenemos los datos de la base de datos y cargamos el table model
    	rs = conexion.ConsultaSQL(consulta);
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<columnas+1;j++){
					datos[i][j-1] = rs.getString(j);
					fila[j-1]=datos[i][j-1];
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tablemodel= new DefaultTableModel(datos,colu);
    	
    	//le añadimos el modelo a nuestro JTable
		jtblLateral.setModel(tablemodel);
		llena = false;
    }
    
    
    /**
     *Este metodo sirve para instanciar esta clase y poder llamar a sus objetos desde otra clase. 
     * @return
     */
    public static PnlModificarsocio Obtener_Instancia(){
		return instancia;
	}
}
