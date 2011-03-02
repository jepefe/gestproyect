package Tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Socios.PnlModificarsocio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PnlMod_viajes extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs,rs2;
	JPanel panel = new JPanel();
	int cuenta = 0;
	int columnas;
	String datos[][];
	String auxdatos[][];
	String colu[] = {"Nombre","Direccion","Telefono"};
	String consulta = "SELECT nombre,direccion,telf,cod_sub FROM SUBCONTRATA ORDER BY nombre"; //Esta consulta cargara los datos en la tabla
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAlta_viajes pnlalta_viajes = new PnlAlta_viajes();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar,jbtnaceptar,jbtncancelar,jbtnactualizar;
	Boolean llena = new Boolean(false);
	Boolean existe= new Boolean(false);
	String[] fila;
	JTable jtblLateral;
	JScrollPane jspntabla;
	FocusListener comp;
	public static JDialog modificar;
	static PnlMod_Sub instancia = new PnlMod_Sub(); 
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    FocusListener foco;
    KeyListener accion,suprimir;
    ActionListener event;
    MouseListener mouse;
    Object[] dat;
    PnlAlta_viajes mod;
    
    
	public PnlMod_viajes(){
		
		//creamos la tabla
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
					jtxt.setText("Buscar...");
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
				if(e.getActionCommand().equals("actualizar")){
					//llamamos al metodo eliminar
					actualizar();
					
					//conexion.executeUpdate("UPDATE SUBCONTRATA SET nombre = '"+mod.jtxt[0].getText()+"', sector = '"+idsector+"', pais = '"+idpais+"', provincia = '"+idprovincia+"', direccion = '"+jtxt[1].getText()+"',	cod_postal = '"+jtxt[2].getText()+"', telf = '"+jtxt[3].getText()+"', observaciones = '"+textarea.getText()+"' WHERE SUBCONTRATA.cod_sub =1 ");
					
				}
				if(e.getActionCommand().equals("cerrar")){
					modificar.dispose();
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
	
	//creamos la interfaz
	public void crear_interfaz(){
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
    	
    	/*
    	 * Creamos el jtextfield para la busquera y le añadimos un focus listener para que se borre su contenido
    	 */
		jtxt=new JTextField(20);
		jtxt.setText("Buscar...");
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
		mod = new PnlAlta_viajes();
		modificar.add(mod);
		modificar.setBounds(0, 0, 600, 650);
		modificar.setLocationRelativeTo(null);
		modificar.setModal(true);
		mod.jbtnaceptar.removeActionListener(mod.accion);
		mod.jbtncancelar.removeActionListener(mod.accion);
		mod.jbtncancelar.setText(rec.idioma[rec.eleidioma][99]);
		
		conexion.Conectardb();
    	rs = conexion.ConsultaSQL("SELECT nombre,direccion,cod_postal,telf,observaciones FROM SUBCONTRATA WHERE cod_sub = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
    	int i=1;
    	
			try {
					
				rs.next();
				for(i=1;i<5;i++){
					mod.jtxt[i-1].setText(rs.getString(i));
				}
				mod.textarea.setText(rs.getString(i));
				
				rs=conexion.ConsultaSQL("SELECT SECTORES.sector FROM SECTORES INNER JOIN SUBCONTRATA ON SECTORES.id_sector = SUBCONTRATA.sector WHERE SUBCONTRATA.cod_sub = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
				rs.next();
				mod.CmbSector.setSelectedItem(rs.getString(1));
				
				rs=conexion.ConsultaSQL("SELECT PAIS.pais FROM PAIS INNER JOIN SUBCONTRATA ON PAIS.id_pais = SUBCONTRATA.pais WHERE SUBCONTRATA.cod_sub = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
				rs.next();
				mod.CmbPais.setSelectedItem(rs.getString(1));
				
				rs=conexion.ConsultaSQL("SELECT estado FROM PROVINCIAS INNER JOIN SUBCONTRATA ON PROVINCIAS.id_provincias = SUBCONTRATA.provincia WHERE SUBCONTRATA.cod_sub = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
				rs.next();
				mod.CmbProvincia.setSelectedItem(rs.getString(1));
			    
				
			
				
				
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
		int s = JOptionPane.showConfirmDialog(aviso, rec.idioma[rec.eleidioma][194]);
		
		if(s==0){
			conexion.Conectardb();
			conexion.executeUpdate("DELETE FROM TRAVEL_SUBSISTENCE WHERE ref = '"+datos[jtblLateral.getSelectedRow()][3]+"'");
			
			actualizar_tabla();
			
			JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][182]);
		
			conexion.cerrarConexion();
		}
    }
    public void actualizar(){
    	int proyecto;
    	String paissalida = null;
    	String provinciasalida = null;
    	String paisdestino = null;
    	String provinciadestino= null;
    	
    	conexion.Conectardb();
    	rs=conexion.ConsultaSQL("SELECT ref FROM TRAVEL_SUBSISTENCE WHERE ref like '"+mod.cbproyecto.getSelectedItem().toString()+"'");
		try {
			rs.next();
			proyecto = rs.getInt(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rs=conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+mod.cbpsalida.getSelectedItem().toString()+"'");
		try {
			rs.next();
			paissalida = rs.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rs=conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+mod.cbcsalida.getSelectedItem().toString()+"'");
		try {
			rs.next();
			provinciasalida = rs.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rs=conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+mod.cbpdestino.getSelectedItem().toString()+"'");
		try {
			rs.next();
			paisdestino = rs.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rs=conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+mod.cbcdestino.getSelectedItem().toString()+"'");
		try {
			rs.next();
			provinciadestino = rs.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conexion.executeUpdate("UPDATE SUBCONTRATA SET nombre = '"+mod.jtxt[0].getText()+"', sector = '"+sector+"', pais = '"+pais+"', provincia = '"+provincia+"', direccion = '"+mod.jtxt[1].getText()+"', cod_postal = '"+mod.jtxt[2].getText()+"', telf = '"+mod.jtxt[3].getText()+"', observaciones = '"+mod.textarea.getText()+"' WHERE SUBCONTRATA.cod_sub =1 ");

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
    public static PnlMod_Sub Obtener_Instancia(){
		return instancia;
	}
}
  
	

