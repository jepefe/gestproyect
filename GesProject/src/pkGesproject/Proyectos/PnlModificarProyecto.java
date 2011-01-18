 package pkGesproject.Proyectos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;
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
	JButton jbtn,jbtnmodificar,jbtneliminar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[5];
	JTable jtblLateral;
	JScrollPane jspntabla;
	public static int id_pro  ;
	public static JFrame modificar;

	int cuenta;
	
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
	void cerrarpanel(){
		modificar.dispose();
	}
	
	public PnlModificarProyecto(){
    	/*
    	 * Solucion para que la Matriz de Busqueda sea Dinamica.
    	 */
    	conexion.Conectardb();
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
    	auxdatos = new String[cuenta][5];
    /*
     *  Sacar Datos de la base de datos y ponerlas en la matriz
     */
    	conexion.Conectardb();
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
    	   		
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
		   
        	jtblLateral  = new JTable(tablemodel= new DefaultTableModel(datos,colu));
       
    		jtext=new JTextField(20);
    		jtext.setText("Buscar...");
    		jtext.putClientProperty("JTextField.variant", "search");
    		jtext.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
    		// BARRA BUSCAR
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // El área de texto empieza en la columna
            constraints.gridy = 0; // El área de texto empieza en la fila
            constraints.gridwidth = 2; // El área de texto ocupa x columnas.
            constraints.gridheight = 1; // El área de texto ocupa x filas.
            constraints.anchor = GridBagConstraints.WEST;
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
    				if(llena==false){
    					for(int i=0;i<cuenta;i++){
    						for(int j = 0;j<5;j++){
    							auxdatos[i][j]= datos[i][j];
    							datos[i][j]=null;
    												
    						}
    					
    					}
    					llena = true;
    				}		
    				if(llena==true){
    					for(int i=0;i<cuenta;i++){
    						for(int j = 0;j<5;j++){
    							datos[i][j]=null;
    						}
    					}
    					//System.out.print("LIMPIADO");
    					
    					if(jtext.getText().equals("")){
    						
    						for(int i=0;i<cuenta;i++){
        						for(int j = 0;j<5;j++){
        							datos[i][j]=auxdatos[i][j];
        						}
        					}
    				    	conexion.cerrarConexion();
    				    	tablemodel.setDataVector(datos, colu);
    					}else{

    						int tam = jtext.getText().length();
    						int i=0;
    						int j=0;
    						String[] res = new String[5];//almacenamos los resultados
    						if(tablemodel.getRowCount() != 0){//si la tabla no esta vacia la vaciamos
    							for( int a = tablemodel.getRowCount() - 1; a >= 0; a-- ){
    						tablemodel.removeRow(a);
    							}
    						}
    						try{
	    						while(auxdatos[i][j]!=null){
	    							while(auxdatos[i][j]!=null){
	    								if(auxdatos[i][j].regionMatches( true, 0, jtext.getText(), 0, tam )){								
	    									for(int col =0;col<5;col++){
	    										datos[i][col]= auxdatos[i][col];
	    										res[col] = datos[i][col];
	    									}
	    									//Si el resultado no esta vacio que pasa por algun error de codigo a�adimos linea al jtable
	    									if (!res[0].equals("") && !res[1].equals("") && !res[2].equals("")&& !res[3].equals("") && !res[4].equals("")){
	    									Object[] dat = {res[0],res[1],res[2],res[3],res[4]};
	    									tablemodel.addRow(dat);	    									
	    									}	
	    								}	    								
	    								i++;
	    							}
	    							i=0;
	    							j++;
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
					
						PnlNuevoProyecto mod;
						modificar = new JFrame();
						modificar.add(mod = new PnlNuevoProyecto());
						modificar.setBounds(0, 0, 500, 600);
						mod.jbtnaceptar.setVisible(false);
						mod.jbtncancelar.setVisible(false);
						mod.jbtncancelar2.setVisible(true);
						mod.jbtnaceptar2.setVisible(true);
						modificar.setLocationRelativeTo(null);
						
						
						
	/*
	 *
	 * Introducir datos al panel para poder modificar
	 * 
	 */
						conexion.Conectardb();
				    	rs = conexion.ConsultaSQL("SELECT id_pro, p.nombre, p.descripcion, p.f_ini, p.f_fin FROM PROYECTOS as p WHERE p.nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
							try {									
										rs.next();
											id_pro = rs.getInt(1);
											mod.jtxt[0].setText(rs.getString(2));
											mod.textarea.setText(rs.getString(3));
											mod.jdc1.setDate(rs.getDate(4));
											mod.jdc2.setDate(rs.getDate(5));				
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
									PnlNuevoProyecto.CbCoordinador.addItem(rs.getString(1));
									mod.modelo2.removeElement(rs.getString(1));
									
									
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
				
            };
            
            jbtnmodificar.setActionCommand("modificar");
            jbtnmodificar.addActionListener(event);
            
            jtext.addKeyListener(accion);  
    }
}
