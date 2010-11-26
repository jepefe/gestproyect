package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class PnlBusquedastaff extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	
	String datos[][] = new String[50000][3];
	String colu[] = {"id_staff","dni","Nombre"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar;
	Boolean llena = new Boolean(false);
	String auxdatos[][] = new String[50000][3];
	JTable jtblLateral;
	JScrollPane jspntabla;
    
	DefaultTableModel tablemodel;// = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
    public PnlBusquedastaff(){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
    	rs = conexion.ConsultaSQL("SELECT id_staff,dni,nombre FROM STAFF");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<4;j++){
					datos[i][j-1] = rs.getString(j);
					
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
            
            jbtnmodificar = new JButton(rec.idioma[rec.eleidioma][37]);
            constraints.gridy = 2; // El área de texto empieza en la fila
            constraints.gridwidth = 1; // El área de texto ocupa x columnas.
            constraints.insets = new Insets(20,30,20,10);
            //constraints.anchor = GridBagConstraints.CENTER;
            this.add(jbtnmodificar,constraints);
            
            jbtneliminar = new JButton(rec.idioma[rec.eleidioma][38]);
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
            this.add(pnlaltasocio,constraints);
            constraints.weightx = 0.0; // La dejamos igual
            
            
            
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
    						//System.out.println("Has pulsado una tecla");

    						/*
    						conexion.Conectardb();
    				    	rs = conexion.ConsultaSQL("SELECT id_staff,dni,nombre FROM STAFF");
    				    	int i=0;
    				    	try {
    							while(rs.next()){
    								for(int j = 1;j<4;j++){
    									datos[i][j-1] = rs.getString(j);
    								}
    								i++;
    							}
    						} catch (SQLException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    						*/
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
	    								//System.out.print(i);
	    								
	    								if(auxdatos[i][j].regionMatches( true, 0, jtxt.getText(), 0, tam )){
	    									//System.out.print("ENTRÓO");
	    									
	    									for(int col =0;col<3;col++){
	    										
	    										datos[i][col]= auxdatos[i][col];
	    										/*jtblLateral.repaint();
	    										jtblLateral.validate();
	    										jspntabla.repaint();
	    										jspntabla.validate();
	    										repaint();
	    										validate();*/
	    										res[col] = datos[i][col];
	    									}
	    									//Si el resultado no esta vacio que pasa por algun error de codigo a�adimos linea al jtable
	    									if (!res[0].equals("") && !res[1].equals("") && !res[2].equals("")){
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
            
            
            
            jtxt.addKeyListener(accion);
		   
    
    }
}
