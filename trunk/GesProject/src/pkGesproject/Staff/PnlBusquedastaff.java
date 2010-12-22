package pkGesproject.Staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;
import pkGesproject.Socios.PnlAltasocio;

public class PnlBusquedastaff extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	
	String datos[][] = new String[50000][3];
	String auxdatos[][] = new String[50000][3];
	String colu[] = {"Nombre","Apellidos","Partner"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[3];
	JTable jtblLateral;
	JScrollPane jspntabla;
    
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
    public PnlBusquedastaff(){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT s.nombre,s.apellidos,p.nombre FROM STAFF s LEFT JOIN PARTNER p ON s.cod_part = p.cod_part");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<4;j++){
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
    		//jtxt.setText("Buscar...");
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
            //constraints.anchor = GridBagConstraints.CENTER;
            
            /*
            constraints.gridx = 2; // El área de texto empieza en la columna
            constraints.gridy = 1; // El área de texto empieza en la fila
            constraints.gridwidth = 1; // El área de texto ocupa x columnas.
            constraints.gridheight = 1; // El área de texto ocupa x filas.
            constraints.weightx = 1.0;	// con esto estiramos segunda columna al maximo que de la ventana
            constraints.insets = new Insets(20,30,0,10);
            this.add(pnlaltasocio,constraints);
            constraints.weightx = 0.0; // La dejamos igual
            */
            
            
            KeyListener accion = new KeyListener(){

    			@Override
    			public void keyPressed(KeyEvent arg0) {
    				// TODO Auto-generated method stub
    				//System.out.println("Has pulsado una tecla");
    				
    				
    			}

    			@Override
    			public void keyReleased(KeyEvent act) {
    				// TODO Auto-generated method stub
    				
    				
    				if(llena==false){
    					for(int i=0;i<100;i++){
    						for(int j = 0;j<3;j++){
    							auxdatos[i][j]= datos[i][j];
    							datos[i][j]=null;
    							//System.out.print(auxdatos[i][j]);
    							
    						}
    						
    						//System.out.print("\n");
    					}
    					llena = true;
    					//System.out.print("LLENO");
    				}
    				
    				if(llena==true){
    					for(int i=0;i<100;i++){
    						for(int j = 0;j<3;j++){
    							datos[i][j]=null;
    						}
    					}
    					//System.out.print("LIMPIADO");
    					
    					if(jtxt.getText().equals("")){
    						
    						for(int i=0;i<100;i++){
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
    							//System.out.print("\n");
								//System.out.print("\n");
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
    						         //System.out.println("This program takes 3 parameters: ");
    						         //System.out.println("  month day year.");
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
						/*
						for(int i=0;i<3;i++){
							System.out.print(datos[jtblLateral.getSelectedRow()][i]+";");
						}
						*/
						
						JFrame modificar = new JFrame();
						pnlAlta_staff mod;
						modificar.add(mod = new pnlAlta_staff());
						modificar.setBounds(0, 0, 600, 650);
						modificar.setLocationRelativeTo(null);
						mod.jbtnaceptar.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								System.out.print("te tengo");
							}
							
						});
							
						conexion.Conectardb();
				    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
				    	rs = conexion.ConsultaSQL("SELECT s.nombre,s.apellidos,s.region,s.ciudad,s.direccion,s.codpostal,s.telefono,s.nick_usuario,s.foto FROM STAFF s WHERE s.nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"' AND s.apellidos LIKE '"+ datos[jtblLateral.getSelectedRow()][1]+"'");
				    	int i=1;
				    	
							try {
									
										rs.next();
										for(i=1;i<10;i++){										
											mod.jtxt[i-1].setText(rs.getString(i));
										}
									
							
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
				    	conexion.cerrarConexion();
						
						modificar.setVisible(true);
					}
					
					if(e.getActionCommand().equals("eliminar")){
						conexion.Conectardb();
						conexion.executeUpdate("DELETE FROM STAFF WHERE nombre LIKE '"+datos[jtblLateral.getSelectedRow()][0]+"' AND apellidos LIKE '"+ datos[jtblLateral.getSelectedRow()][1]+"'");
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
		   
    
    }
}
