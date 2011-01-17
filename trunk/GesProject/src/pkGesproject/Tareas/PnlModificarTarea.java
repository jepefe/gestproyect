package pkGesproject.Tareas;

import java.awt.Component;
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

public class PnlModificarTarea extends JPanel{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs,rs2;
	JPanel panel = new JPanel();
	int cuenta = 0;
	String datos[][];
	String auxdatos[][];
	String colu[] = {"Nombre","Presupuesto","Worpackages","Proyecto"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltatarea pnlaltasocio = new PnlAltatarea();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar,jbtnaceptar,jbtncancelar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[4];
	JTable jtblLateral;
	JScrollPane jspntabla;
    
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
    public PnlModificarTarea(){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
    
    	rs = conexion.ConsultaSQL("SELECT  t.nombre, t.presupuesto, w.nombre, p.nombre FROM TAREAS t LEFT JOIN " +
    	"WORKPAQUETS w ON t.id_wp = w.id_wp LEFT JOIN PROYECTOS p ON w.id_pro = p.id_pro");
    	cuenta = 0;
    	try {
			while(rs.next()){
				cuenta++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	datos = new String[cuenta][4];
		auxdatos = new String[cuenta][4];
    	
		rs = conexion.ConsultaSQL("SELECT  t.nombre, t.presupuesto, w.nombre, p.nombre FROM TAREAS t LEFT JOIN " +
    	"WORKPAQUETS w ON t.id_wp = w.id_wp LEFT JOIN PROYECTOS p ON w.id_pro = p.id_pro");
    	int i=0;
    	
    	try {
			while(rs.next()){
				for(int j = 1;j<5;j++){
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
       
    		jtxt=new JTextField(20);
    		jtxt.setText("Buscar...");
    		jtxt.putClientProperty("JTextField.variant", "search");
    		jtxt.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
    		jtxt.addFocusListener(new FocusListener(){

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
    			
    		});
    		
    		
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
            //constraints.anchor = GridBagConstraints.CENTER;
            
              final KeyListener accion = new KeyListener(){

    			@Override
    			public void keyPressed(KeyEvent arg0) {
    				// TODO Auto-generated method stub
    				//System.out.println("Has pulsado una tecla");
    				
    				
    			}

    			@Override
    			public void keyReleased(KeyEvent act) {
    				// TODO Auto-generated method stub
    				
    				
    				if(llena==false){
    					for(int i=0;i<cuenta;i++){
    						for(int j = 0;j<3;j++){
    							auxdatos[i][j]= datos[i][j];
    							datos[i][j]=null;
    							
    							
    						}
    						
    						//System.out.print("\n");
    					}
    					llena = true;
    					//System.out.print("LLENO");
    				}
    				
    				if(llena==true){
    					for(int i=0;i<cuenta;i++){
    						for(int j = 0;j<3;j++){
    							datos[i][j]=null;
    						}
    					}
    					//System.out.print("LIMPIADO");
    					
    					if(jtxt.getText().equals("")){
    						
    						for(int i=0;i<cuenta;i++){
        						for(int j = 0;j<3;j++){
        							datos[i][j]=auxdatos[i][j];
        						}
        					}
    				    	conexion.cerrarConexion();
    				    	tablemodel.setDataVector(datos, colu);
    				    	//System.out.print("BLANCO");
    					}else{
    						//System.out.print("MOVIDA");
    						
    						int tam = jtxt.getText().length();
    						int i=0;
    						int j=0;
    						int fila=0;
    						String[] res = new String[3];//almacenamos los resultados
    						if(tablemodel.getRowCount() != 0){//si la tabla no esta vacia la vaciamos
    							for( int a = tablemodel.getRowCount() - 1; a >= 0; a-- ){
    						tablemodel.removeRow(a);
    							}
    						}
    						
    						
    						try{
    							
	    						while(auxdatos[i][j]!=null){
	    							while(auxdatos[i][j]!=null){
	    								//System.out.print("llega");
	    								
	    								if(auxdatos[i][j]!= null){
		    								if(auxdatos[i][j].regionMatches( true, 0, jtxt.getText(), 0, tam )){
		    									//System.out.print("ENTRÓO");
		    									
		    									for(int col =0;col<3;col++){
		    										
		    										datos[fila][col]= auxdatos[i][col];
		    										
		    										res[col] = datos[fila][col];
		    									}
		    									fila++;
		    									
		    									Object[] dat = {res[0],res[1],res[2]};
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
            
            conexion.cerrarConexion();
            
            ActionListener event = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("modificar")){
						
						if(jtblLateral.getSelectedRow()==-1){
							Component aviso = null;
							JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
							
						}else{
						
						JFrame modificar = new JFrame();
						final PnlAltatarea mod = new PnlAltatarea();
						modificar.add(mod);
						modificar.setBounds(0, 0, 600, 650);
						modificar.setLocationRelativeTo(null);
					
						conexion.Conectardb();
				    	
						/**
						 * Con esto cojes los datos que necesitas mostrar d la tarea que esta seleccionada, osea cuando nombre es eso
						 **/
						rs = conexion.ConsultaSQL("SELECT t.nombre,t.presupuesto,t.f_ini, t.f_fin, t.descripcion, t.observaciones"+
				    	" FROM TAREAS t WHERE t.nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
						int i=1;
				    	
							try {
									
								//aki lo que te dixo antes, cojes el 1 y el 2 y los metes en el txt 0 y 1
								rs.next();
								for(i=1;i<3;i++){
									mod.jtxt[i-1].setText(rs.getString(i));
								}
								mod.jdc1.setDate(rs.getDate(3));
								mod.jdc2.setDate(rs.getDate(4));
								mod.textarea.setText(rs.getString(5));
								mod.textarea2.setText(rs.getString(6));
								
								rs=conexion.ConsultaSQL("SELECT w.nombre FROM WORKPAQUETS w INNER JOIN TAREAS t ON w.id_wp = t.id_wp WHERE t.nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
								rs.next();
								mod.CmbWp.setSelectedItem(rs.getString(1));
								
								
								
								/*jbtnaceptar.addActionListener(new ActionListener(){

									@Override
									
																		
									 public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										
										rs=conexion.ConsultaSQL("SELECT id_sector FROM SECTORES WHERE sector like '"+mod.cbsector.getSelectedItem().toString()+"'");
										
										
										
										String sector = null;
										try {
											rs.next();
											sector = rs.getString(1);
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										//todo esto no te petaba antes o k¿? no por que no lo tenia
										// te dije que esto es la parte del final tuya hay que cambiarla pero no se como cargar la info aqui
										//ya se lo kkieres, vamos a arregalr todo eso entoences
										//estas cosasa eran para el combobox para cargar la posicion que toca, si eligio españa que se carque esoe
										//xo sigo sin entender xk esto esta dentro del boton aceptar? asi k voy a
										//aqui lo que tengo que cargar es el CmbWp que es que tiene los WPs yaya los
										//estavamos en la otra
										
										conexion.executeUpdate("UPDATE PARTNER SET nombre='"+mod.jtxt[0].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET direccion='"+mod.jtxt[1].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET sector='"+sector+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET pais='"+pais+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET codpostal='"+mod.jtxt[2].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET email='"+mod.jtxt[3].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET email2='"+mod.jtxt[4].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET telefono='"+mod.jtxt[5].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET telefono2='"+mod.jtxt[6].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET fax='"+mod.jtxt[7].getText()+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
										conexion.executeUpdate("UPDATE PARTNER SET observaciones='"+mod.textarea+"' WHERE nombre LIKE '"+mod.jtxt[0].getText()+"'");
									}
									
								});*/
								
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
						conexion.executeUpdate("DELETE FROM STAFF WHERE nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"'");
						Component aviso = null;
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][63]);
						conexion.cerrarConexion();
					}
				}
            	
            };
            
            jbtnmodificar.setActionCommand("modificar");
            jbtnmodificar.addActionListener(event);
            jbtneliminar.setActionCommand("eliminar");
            jbtneliminar.addActionListener(event);
            
            /*
            jtblLateral.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

    			@Override
    			public void valueChanged(ListSelectionEvent e) {
    				if(e.getValueIsAdjusting()){
    					//setRightComponent(panlsStaff[jtblLateral.getSelectedRow()]);
    					System.out.println(jtblLateral.getSelectedRow());
    				}
    			}});
            */
            jtxt.addKeyListener(accion);
            System.out.println("entrada 1");
		   
    
    }
}
