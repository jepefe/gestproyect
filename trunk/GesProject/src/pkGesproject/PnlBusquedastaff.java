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
	
	String datos[][] = new String[100][3];
	String colu[] = {"id_staff","dni","Nombre"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar;
	Boolean llena = new Boolean(false);
	String auxdatos[][] = new String[100][3];
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    public PnlBusquedastaff(){
    	
 
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
		   
		   
    	final JTable jtblLateral  = new JTable(new DefaultTableModel(datos, colu)) {};
       
        
    	
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0; // El área de texto empieza en la columna
            constraints.gridy = 0; // El área de texto empieza en la fila
            constraints.gridwidth = 2; // El área de texto ocupa x columnas.
            constraints.gridheight = 1; // El área de texto ocupa x filas.
            constraints.insets = new Insets(20,30,0,10);
            this.add(jtxt=new JTextField(20),constraints);
            
            
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
				public void keyReleased(KeyEvent arg0) {
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
						System.out.print("LLENO");
					}
					
					if(llena==true){
						for(int i=0;i<100;i++){
							for(int j = 0;j<3;j++){
								datos[i][j]=null;
							}
						}
						System.out.print("LIMPIADO");
						
						if(jtxt.getText().equals("")){
							System.out.println("Has pulsado una tecla");
	
							
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
					    	System.out.print("BLANCO");
						}else{
							System.out.print("MOVIDA");
							int tam = jtxt.getText().length();
							for(int j=0;j<3;j++){
								for(int i=0;i<100;i++){
									System.out.print(i);
									if(auxdatos[i][j].regionMatches( true, 0, jtxt.getText(), 0, tam )){
										System.out.print("ENTRÓO");
										for(int col =0;col<3;col++){
											datos[i][j]= auxdatos[i][j];
											jtblLateral.repaint();
											jtblLateral.validate();
											jspntabla.repaint();
											jspntabla.validate();
											repaint();
											validate();
										}
									}
								}
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