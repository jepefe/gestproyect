package Tablas;

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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import pkGesproject.Proyectos.PnlModificarProyecto;
import pkGesproject.Proyectos.PnlNuevoProyecto;

public class PnlMod_equipamientos extends JPanel {
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	java.util.Date fecha;
	String datos [][];		
	String auxdatos[][] ;
	String colu[] = {rec.idioma[rec.eleidioma][57],rec.idioma[rec.eleidioma][41],rec.idioma[rec.eleidioma][123],rec.idioma[rec.eleidioma][40],rec.idioma[rec.eleidioma][95]};
	JLabel jlbl;
	PnlAlta_equipamientos pnlAltaEquipamientoso = new PnlAlta_equipamientos();
	JTextField jtext;
	JButton jbtn,jbtnmodificar,jbtneliminar, jbtnactualizar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[6];
	JTable jtblLateral;
	JScrollPane jspntabla;
	public static int id_pro,ref  ;
	public static JDialog modificar;
	static PnlMod_equipamientos instancia = new PnlMod_equipamientos();
	int cuenta;
	int columnas;
	Boolean existe = new Boolean(false);
	
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
	void cerrarpanel(){
		modificar.dispose();
	}
	
	public PnlMod_equipamientos(){
		
		/**
    	 * Cargamos los array y la tabla con los datos de la bd
    	 */
    	cuenta=contar_reg();
    	columnas =colu.length+1;
    	datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
    	tablemodel=cargar_tabla(datos,columnas);
    	jtblLateral  = new JTable(tablemodel){
   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
             return false;
           }
    	};
    	
    	jtblLateral.setPreferredScrollableViewportSize(new Dimension(700,160));
		
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
    	
    	jtext=new JTextField(20);
		jtext.setText("Buscar...");
		jtext.putClientProperty("JTextField.variant", "search");
		jtext.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
		jtext.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				jtext.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtext.getText().equals("")){
					jtext.setText("Buscar...");
				}
				
			}
			
		});
		
		// BARRA BUSCAR
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // El área de texto empieza en la columna
        constraints.gridy = 0; // El área de texto empieza en la fila
        constraints.gridwidth = 2; // El área de texto ocupa x columnas.
        constraints.gridheight = 1; // El área de texto ocupa x filas.
        constraints.insets = new Insets(20,30,0,10);
        this.add(jtext,constraints);
        
        // TABLA BUSCADOR
        final JScrollPane jspntabla = new JScrollPane(jtblLateral); //Añadimos la tabla al jscrollpane
        constraints.gridx = 0; // El área de texto empieza en la columna
        constraints.gridy = 1; // El área de texto empieza en la fila
        constraints.weighty = 1.0; // Con esto la fila 1 se estira al estirar la ventana
        constraints.fill = GridBagConstraints.BOTH;
        this.add(jspntabla,constraints);
        constraints.fill = GridBagConstraints.NONE;
        constraints.weighty = 0.0; // Volvemos a dejarla como antes para el resto
        
        jbtnmodificar = new JButton(rec.idioma[rec.eleidioma][38]);
        constraints.gridy = 3; // El área de texto empieza en la fila
        constraints.gridwidth = 1; // El área de texto ocupa x columnas.
        constraints.insets = new Insets(20,30,20,10);
     
        this.add(jbtnmodificar,constraints);
        
        jbtneliminar = new JButton(rec.idioma[rec.eleidioma][39]);
        constraints.gridx = 1; // El área de texto empieza en la columna
        //constraints.anchor = GridBagConstraints.EAST;
        this.add(jbtneliminar,constraints);
        //constraints.anchor = GridBagConstraints.CENTER;
        
        jbtnactualizar = new JButton(rec.idioma[rec.eleidioma][39]);
        
        this.add(jbtnactualizar,constraints);
        constraints.gridx = 2; // El área de texto empieza en la columna
        constraints.gridy = 1; // El área de texto empieza en la fila
        constraints.gridwidth = 1; // El área de texto ocupa x columnas.
        constraints.gridheight = 1; // El área de texto ocupa x filas.
        constraints.weightx = 1.0;	// con esto estiramos segunda columna al maximo que de la ventana
        constraints.insets = new Insets(20,30,0,10);
		
		
        
        KeyListener accion = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub			
			}
			@Override
			public void keyReleased(KeyEvent act) {
				// TODO Auto-generated method stub
		     Object[] dat = new Object[columnas];	
				if(llena==false){
					for(int i=0;i<cuenta;i++){
						for(int j = 0;j<columnas;j++){
							auxdatos[i][j]= datos[i][j];
							datos[i][j]= null ;								
						}
					}
					llena = true;
				}
				
				if(llena==true){
					for(int i=0;i<cuenta;i++){
						for(int j = 0;j<columnas;j++){
							datos[i][j]= null;
						}
					}
					if(jtext.getText().equals("")){
						for(int i=0;i<cuenta;i++){
    						for(int j = 0;j<columnas;j++){
    							datos[i][j]=auxdatos[i][j];
    						}
    					}
				    	conexion.cerrarConexion();
				    	tablemodel.setDataVector(datos, colu);
					}else{
						int tam = jtext.getText().length();
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
	    								if(auxdatos[i][j].regionMatches( true, 0, jtext.getText(), 0, tam )){
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

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub	
			}
        	
        };

        
        ActionListener event = new ActionListener(){

			@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("modificar")){
				// Frame de modificar con boton presupuesto visible
				if(jtblLateral.getSelectedRow()==-1){
					Component aviso = null;
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][103]);
				}else{
					PnlAlta_equipamientos mod;
					modificar = new JDialog();
					modificar.add(mod = new PnlAlta_equipamientos());
					modificar.setBounds(0, 0, 500, 600);
					mod.jbtnaceptar.setVisible(true); mod.jbtncancelar.setVisible(true);
					mod.jbtncancelar.setVisible(true); mod.jbtncancelar.setVisible(true);
					modificar.setModal(true);
					modificar.setLocationRelativeTo(null);
					
					
					
					/*
					 * Introducir datos al panel para poder modificar
					 */
					conexion.Conectardb();
			    	rs = conexion.ConsultaSQL("SELECT p.ref,p.partner,p.descripcion,p.justificacion,p.wp,p.coste_total,p.fecha,p.compra_alquiler,p.grado_depreciacion,p.meses_usara,p.grado_utilizacion FROM EQUIPAMIENTOS as p WHERE p.ref LIKE '"+datos[jtblLateral.getSelectedRow()][5]+"'");
						try {									
									rs.next();
										ref = rs.getInt(1);
										mod.textdescripcion.setText(rs.getString(3));
										mod.textjustificacion.setText(rs.getString(4));
										mod.jtxt[0].setText(rs.getString(6));
										mod.jtxt[1].setText(rs.getString(9));
										mod.jdc1.setDate(rs.getDate(7));
										mod.CmbComp.setSelectedIndex(rs.getInt(8));
										mod.jtxt[2].setText(rs.getString(10));
										mod.jtxt[3].setText(rs.getString(11));
										
				
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER as p inner join EQUIPAMIENTOS as pp on p.cod_part = pp.partner WHERE pp.ref = '"+ref+"'");
						try {									
							while(rs.next()){
								mod.CmbPar.setSelectedItem(rs.getString(1));
							}					
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexion.cerrarConexion();
						
						rs = conexion.ConsultaSQL("SELECT p.nombre FROM WORKPAQUETS as p inner join EQUIPAMIENTOS as pp on p.id_wp = pp.wp WHERE pp.ref = '"+ref+"'");
						try {									
							while(rs.next()){
								mod.Cmbwp.setSelectedItem(rs.getString(1));
							}					
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexion.cerrarConexion();
						
						
					
					modificar.setVisible(true);
				}	
			}
			if(e.getActionCommand().equals("eliminar")){
				conexion.Conectardb();
				conexion.executeUpdate("DELETE FROM EQUIPAMIENTOS WHERE ref LIKE '"+datos[jtblLateral.getSelectedRow()][5]+"'");
				Component aviso = null;
				JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][176]);
				
				cuenta=contar_reg();
				datos = new String[cuenta][columnas];
				auxdatos = new String[cuenta][columnas];
				tablemodel = cargar_tabla(datos,columnas);
				jtblLateral.setModel(tablemodel);
				jtblLateral.repaint();
				llena = false;
				conexion.cerrarConexion();
			}

			
		}	
        };
        
        jbtnmodificar.setActionCommand("modificar");
        jbtnmodificar.addActionListener(event);
        jbtneliminar.setActionCommand("eliminar");
        jbtneliminar.addActionListener(event);
        
        jtext.addKeyListener(accion);  
        this.setVisible(true);
        panel.setVisible(true);
        
		
	}
	
	
	/**
     * Este metodo coje la matriz datos, descarga de la BD los datos de los partners y genera un tablemodel, el cual es devuelto por
     * este método.
     * @param datos
     * @return
     **/
    
    public DefaultTableModel cargar_tabla(String datos[][], int columna){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
		
    	
    	rs = conexion.ConsultaSQL("SELECT partner,descripcion,justificacion,wp,fecha,ref FROM EQUIPAMIENTOS ORDER BY ref");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<columna+1;j++){
					datos[i][j-1] = rs.getString(j);
					fila[j-1]=datos[i][j-1];
				}

				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	conexion.cerrarConexion();
    	
    	DefaultTableModel tablemodel= new DefaultTableModel(datos,colu);
    	return tablemodel;
    }
	
	
	
	/**
     * Este método lo que hace es devolvernos los registros que existen actualmente en la tabla partner
     **/
	
	public int contar_reg(){
		
    	conexion.Conectardb();

    	rs = conexion.ConsultaSQL("SELECT partner,descripcion,justificacion,wp,fecha,ref FROM EQUIPAMIENTOS");
    	cuenta = 0;
    	try {
			while(rs.next()){
				cuenta++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		conexion.cerrarConexion();
    	return cuenta;
    	
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
    			System.out.println("Datos Pro:"+ datos[f][c] );
    			System.out.println("AuxDatos Pro:"+ datos[i][c]);
    			if(auxdatos[i][c] == datos[f][c]){
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
	

}
