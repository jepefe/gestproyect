 package pkGesproject.Proyectos;

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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import pkGesproject.Socios.PnlModificarsocio;
import pkGesproject.Staff.pnlAlta_staff;


public class PnlModificarProyecto extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	java.util.Date f_ini , f_fin = new java.util.Date();
	String datos [][];		
	String auxdatos[][] ;
	String colu[] = {"Nombre","Presupuesto","Descripcion","Fecha Inicio","Fecha Fin"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlNuevoProyecto pnlnuevoproyecto = new PnlNuevoProyecto();
	JTextField jtext;
	JButton jbtn,jbtnmodificar,jbtneliminar, jbtnactualizar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[5];
	JTable jtblLateral;
	JScrollPane jspntabla;
	public static int id_pro  ;
	public static JDialog modificar;
	static PnlModificarProyecto instancia = new PnlModificarProyecto();
	int cuenta;
	int columnas;
	Boolean existe = new Boolean(false);
	
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
	void cerrarpanel(){
		modificar.dispose();
	}
	
	public PnlModificarProyecto(){
    	
		/**
    	 * Cargamos los array y la tabla con los datos de la bd
    	 */
    	cuenta=contar_reg();
    	columnas =colu.length;
    	datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
    	tablemodel=cargar_tabla(datos,columnas);
    	jtblLateral  = new JTable(tablemodel){
   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
             return false;
           }
    	};
    	jtblLateral.setPreferredScrollableViewportSize(new Dimension(700,160));
		
		/*
    	 * Solucion para que la Matriz de Busqueda sea Dinamica.
    	 */
    	/*conexion.Conectardb();
    	rs = conexion.ConsultaSQL("SELECT nombre,presupuesto,descripcion,f_ini,f_fin FROM PROYECTOS");
    	cuenta = 0 ; // Cuenta para hacer la matriz dinamica
    	try {
			while(rs.next()){
			cuenta = cuenta + 1;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	conexion.cerrarConexion();
    	datos = new String[cuenta][5];// Matriz Dinamica
    	auxdatos = new String[cuenta][5];*/
    /*
     *  Sacar Datos de la base de datos y ponerlas en la matriz
     */
    /*	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT nombre,presupuesto,descripcion,f_ini,f_fin FROM PROYECTOS");
    	int i = 0; 
    	try {
			while(rs.next()){
				for(int j = 1;j<6;j++){	
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
    	   		*/
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
		   
        //	jtblLateral  = new JTable(tablemodel= new DefaultTableModel(datos,colu));
       
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
						PnlNuevoProyecto mod;
						modificar = new JDialog();
						modificar.add(mod = new PnlNuevoProyecto());
						modificar.setBounds(0, 0, 500, 600);
						mod.jbtnaceptar.setVisible(false); mod.jbtncancelar.setVisible(false);
						mod.jbtnaceptar2.setVisible(true); mod.jbtncancelar2.setVisible(true);
						mod.txtprecio.setVisible(true);
						mod.pres.setVisible(true);
						modificar.setModal(true);
						modificar.setLocationRelativeTo(null);
						mod.jtxt[0].removeFocusListener(mod.foco);
						/*
						 * Introducir datos al panel para poder modificar
						 */
						conexion.Conectardb();
				    	rs = conexion.ConsultaSQL("SELECT id_pro, p.nombre,p.num_contrato, p.descripcion,p.presupuesto, p.f_ini, p.f_fin FROM PROYECTOS as p WHERE p.nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
							try {									
										rs.next();
											id_pro = rs.getInt(1);
											mod.jtxt[0].setText(rs.getString(2));
											mod.jtxt[1].setText(rs.getString(3));
											mod.textarea.setText(rs.getString(4));
											mod.txtprecio.setText(String.valueOf(rs.getInt(5))) ;
											mod.jdc1.setDate(rs.getDate(6));
											mod.jdc2.setDate(rs.getDate(7));				
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							/*
							 * Consulta y añadre a los JLIST los datos.
							 */
							rs = conexion.ConsultaSQL("SELECT p.nombre,coordinador FROM PARTNER as p inner join PARTNER_PROYECTOS as pp on p.cod_part = pp.cod_part WHERE pp.id_pro = '"+id_pro+"'");
							try {									
								while(rs.next()){	
									mod.modelo.addElement(rs.getString(1));
									mod.CbCoordinador.addItem(rs.getString(1));
									mod.modelo2.removeElement(rs.getString(1));
								}					
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER as p inner join PARTNER_PROYECTOS as pp on p.cod_part = pp.cod_part WHERE pp.id_pro = '"+id_pro+"'AND pp.coordinador ="+1+"");
							try {									
								while(rs.next()){
									mod.CbCoordinador.setSelectedItem(rs.getString(1));
								}					
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							conexion.cerrarConexion();
							
							rs = conexion.ConsultaSQL("SELECT a.nombre FROM PROYECTOS as p inner join ACTIONS as a  on a.id_accion = p.action WHERE p.id_pro = '"+id_pro+"'");
							try {									
								while(rs.next()){
									mod.CbAccion.setSelectedItem(rs.getString(1));
								}					
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							conexion.cerrarConexion();
							
					/*
					 * llamada del metodo nuevoitem de pnlnuevoproyecto
					 */
						
						modificar.setVisible(true);
					}	
				}
				if(e.getActionCommand().equals("eliminar")){
					conexion.Conectardb();
					conexion.executeUpdate("DELETE FROM PROYECTOS WHERE nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
					Component aviso = null;
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][108]);
					
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
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	
		//datos = new String[cuenta][3];
		//auxdatos = new String[cuenta][3];
		
    	
    	rs = conexion.ConsultaSQL("SELECT nombre,presupuesto,descripcion,f_ini,f_fin FROM PROYECTOS ORDER BY nombre");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<columna+1;j++){
					datos[i][j-1] = rs.getString(j);
					fila[j-1]=datos[i][j-1];
					//System.out.print(fila[j-1]+";");
				}
				//System.out.print("\n");
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
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT nombre,presupuesto,descripcion,f_ini,f_fin FROM PROYECTOS");
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
    
    /**
     *Este metodo sirve para instanciar esta clase y poder llamar a sus objetos desde otra clase. 
     * @return
     */
    public static PnlModificarProyecto Obtener_Instancia(){
		return instancia;
	}
}
