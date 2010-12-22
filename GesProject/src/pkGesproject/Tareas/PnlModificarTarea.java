package pkGesproject.Tareas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import pkGesproject.Proyectos.PnlNuevoProyecto;
import pkGesproject.Staff.pnlAlta_staff;


public class PnlModificarTarea extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	
	String datos[][] = new String[50000][5];
	String auxdatos[][] = new String[50000][5];
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
	
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
    public PnlModificarTarea(){
    	
    	conexion.Conectardb();
    	
    	rs = conexion.ConsultaSQL("SELECT nombre,presupuesto,descripcion,f_ini,f_fin FROM TAREAS");
    	int i=0;
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
    		
    		GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // El área de texto empieza en la columna
            constraints.gridy = 0; // El área de texto empieza en la fila
            constraints.gridwidth = 2; // El área de texto ocupa x columnas.
            constraints.gridheight = 1; // El área de texto ocupa x filas.
            constraints.insets = new Insets(20,30,0,10);
            this.add(jtext,constraints);
            
            
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
         
            this.add(jbtnmodificar,constraints);
            
            jbtneliminar = new JButton(rec.idioma[rec.eleidioma][39]);
            constraints.gridx = 1; // El área de texto empieza en la columna
            //constraints.anchor = GridBagConstraints.EAST;
            this.add(jbtneliminar,constraints);
            //constraints.anchor = GridBagConstraints.CENTER;
     

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
    						for(int j = 0;j<5;j++){
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
    						for(int j = 0;j<5;j++){
    							datos[i][j]=null;
    						}
    					}
    					//System.out.print("LIMPIADO");
    					
    					if(jtext.getText().equals("")){
    						
    						for(int i=0;i<100;i++){
        						for(int j = 0;j<5;j++){
        							datos[i][j]=auxdatos[i][j];
        						}
        					}
    				    	conexion.cerrarConexion();
    				    	tablemodel.setDataVector(datos, colu);
    				    	//System.out.print("BLANCO");
    					}else{
    						//System.out.print("MOVIDA");
    						
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
    							//System.out.print("\n");
								//System.out.print("\n");
	    						while(auxdatos[i][j]!=null){
	    							while(auxdatos[i][j]!=null){
	    								//System.out.print(i);
	    								
	    								if(auxdatos[i][j].regionMatches( true, 0, jtext.getText(), 0, tam )){
	    									//System.out.print("ENTRÓO");
	    									
	    									for(int col =0;col<5;col++){
	    										
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
            jtext.addKeyListener(accion); 
            
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
						modificar.setVisible(true);
					}
					
					if(e.getActionCommand().equals("eliminar")){
						ConexionDb conexdb = new ConexionDb();
						ResultSet rs;
						conexdb.Conectardb();
						// cambiar fecha a sql
						
						//jtblLateral.getSelectedRow();
						conexdb.executeUpdate("DELETE FROM TAREAS WHERE id_task=11");

						//JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
						conexdb.cerrarConexion();
					}
						JFrame modificar = new JFrame();
						pnlAlta_staff mod;
						modificar.add(mod = new pnlAlta_staff());
						modificar.setVisible(true);
					}
					
				
            	
            };
            jbtnmodificar.setActionCommand("modificar");
            jbtnmodificar.addActionListener(event);
            
            jbtneliminar.setActionCommand("eliminar");
            jbtneliminar.addActionListener(event);
            
            
    }
}
