	/**
	 /**
	 * Esta clase se encarga de realizar el alta de nuevas Becas 
	 * 
	 * @author Felix Perona V
	 */
package pkGesproject.Workpakages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import pkGesproject.Socios.PnlModificarsocio;

public class PnlBusquedawp extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs,rs2;
	JPanel panel = new JPanel();
	int cuenta=0;
	String datos[][];
	String auxdatos[][];
	String colu[] = {"Nombre","Partner","Proyecto"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar;
	Boolean llena = new Boolean(false);
	Boolean existe = false;
	String[] fila = new String[3];
	JTable jtblLateral;
	JScrollPane jspntabla;
    String nompartner[];
    String nomproyecto[];
	public static JFrame modificar ;
	public static int id_wp;
	int columnas = 3;
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    static PnlBusquedawp instancia = new PnlBusquedawp();
    
	
	
    public PnlBusquedawp(){
    	conexion.Conectardb();
    	/*String resul[]= new String[3];
    	conexion.Conectardb();
    	
    	rs = conexion.ConsultaSQL("SELECT w.nombre, p.nombre, po.nombre FROM WORKPAQUETS w LEFT JOIN PARTNER_WORKPAQUETS pa ON w.id_wp = pa.id_wp LEFT JOIN PROYECTOS po ON w.id_pro = po.id_pro LEFT JOIN PARTNER p ON pa.cod_part = p.cod_part ORDER BY w.nombre");
    	cuenta = 0;
    	try {
			while(rs.next()){
				cuenta++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
		datos = new String[cuenta][3];
		auxdatos = new String[cuenta][3];
    	
    	
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT w.nombre, p.nombre, po.nombre FROM WORKPAQUETS w LEFT JOIN PARTNER_WORKPAQUETS pa ON w.id_wp = pa.id_wp LEFT JOIN PROYECTOS po ON w.id_pro = po.id_pro LEFT JOIN PARTNER p ON pa.cod_part = p.cod_part ORDER BY w.nombre");
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
    	*/
    		
    	
    	
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
    	
    	cuenta=contar_reg();
    	columnas =colu.length;
    	datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
    	tablemodel=cargar_tabla(datos);
    	jtblLateral  = new JTable(tablemodel){
   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
             return false;
           }
    	};
    	
    	jtblLateral.setPreferredScrollableViewportSize(new Dimension(700,160));
        	//jtblLateral  = new JTable(tablemodel= new DefaultTableModel(datos,colu));
       
    		jtxt=new JTextField(20);
    		jtxt.setText("Buscar...");
    		jtxt.putClientProperty("JTextField.variant", "search");
    		jtxt.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
    		jtxt.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					jtxt.setText("");
				}

				@Override
				public void focusLost(FocusEvent e) {
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
	    						//while(auxdatos[i][j]!=null){
	    						for(j=0;j<3;j++){
	    							//while(auxdatos[i][j]!=null){
	    							for(i=0;i<cuenta;i++){
	    								if(auxdatos[i][j]!= null){
		    								if(auxdatos[i][j].regionMatches( true, 0, jtxt.getText(), 0, tam )){
		    									existe=buscar_reg(datos,auxdatos,i,cuenta,columnas);
		    									if(existe==false){
			    									for(int col =0;col<columnas;col++){
			    										datos[fila][col]= auxdatos[i][col];
			    										res[col] = datos[fila][col];
			    									}
			    									fila++;
			    									
			    									Object[] dat = {res[0],res[1],res[2]};
			    									tablemodel.addRow(dat);
		    									}
		    									
		    								}
	    								}
	    								
	    								//i++;
	    							}
	    							//i=0;
	    							//j++;
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
						/*
						for(int i=0;i<3;i++){
							System.out.print(datos[jtblLateral.getSelectedRow()][i]+";");
						}
						*/
						conexion.Conectardb();
						
						modificar = new JFrame();
						PnlAltawp mod;
						modificar.add(mod = new PnlAltawp());
						modificar.setBounds(0, 0, 600, 650);
						modificar.setLocationRelativeTo(null);
						modificar.setVisible(true);
						mod.jbtnaceptar.setVisible(false);
						mod.jbtnaceptar2.setVisible(true);
						mod.jbtncancelar.setVisible(false);
						mod.jbtncancelar2.setVisible(true);
						mod.jtxt[0].setEditable(false);
						
				    	//rs = conexion.ConsultaSQL("SELECT w.nombre, w.presupuesto FROM WORKPAQUETS");
						
				    	rs = conexion.ConsultaSQL("SELECT w.nombre, w.presupuesto, w.id_pro, w.id_wp, w.f_ini, w.f_fin, w.descripcion, w.observaciones, p.cod_part " +
				    			"FROM WORKPAQUETS w LEFT JOIN PARTNER_WORKPAQUETS p ON w.id_wp = p.id_wp WHERE nombre = '"+datos[jtblLateral.getSelectedRow()][0]+"'");
				    	int i=1;
				    	int id_pro = 0;
				    	int id_par = 0;
							try {
									rs.next();
									id_wp = rs.getInt(4);
									for(i=1;i<3;i++){
										
										mod.jtxt[i-1].setText(rs.getString(i));
									}
									mod.jdc1.setDate(rs.getDate(5));
									mod.jdc2.setDate(rs.getDate(6));
									mod.textarea.setText(rs.getString(7));
									mod.textarea2.setText(rs.getString(8));
									id_pro = rs.getInt(3);
									id_par = rs.getInt(9);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							rs2=conexion.ConsultaSQL("SELECT p.nombre FROM PROYECTOS p WHERE p.id_pro = '"+id_pro+"'");
							try {
								rs2.next();
								mod.CmbPro.setSelectedItem(rs2.getString(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexion.cerrarConexion();
						conexion.Conectardb();
						
						/*
						 * Consulta y añadre a los JLIST los datos.
						 */
						rs2 = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER_WORKPAQUETS wp INNER JOIN PARTNER p ON wp.id_part = p.id_part INNER JOIN WORKPAQUETS w ON wp.id_wp = w.id_wp WHERE w.nombre = '"+datos[jtblLateral.getSelectedRow()][1]+"'");
						try {									
							while(rs2.next()){	
								mod.modelo.addElement(rs2.getString(1));
								mod.modelo2.removeElement(rs2.getString(1));
							}					
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							

				    	conexion.cerrarConexion();
						
						modificar.setVisible(true);
					}
						if(e.getActionCommand().equals("eliminar")){
							Component aviso = null;
							int s = JOptionPane.showConfirmDialog(aviso,rec.idioma[rec.eleidioma][59]);
							if(s == 0){
								conexion.Conectardb();
								conexion.executeUpdate("DELETE FROM WORKPAQUETS WHERE nombre = '"+datos[jtblLateral.getSelectedRow()][0]+"'");
								
								
								cuenta=contar_reg();
								datos = new String[cuenta][columnas];
								auxdatos = new String[cuenta][columnas];
								tablemodel =cargar_tabla(datos);
								jtblLateral.setModel(tablemodel);
								llena = false;
								
								JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][63]);
								conexion.cerrarConexion();
							}
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
    /**
     * Este metodo coje la matriz datos, descarga de la BD los datos de los partners y genera un tablemodel, el cual es devuelto por
     * este método.
     * @param datos
     * @return
     **/
    
    public DefaultTableModel cargar_tabla(String datos[][]){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	
		//datos = new String[cuenta][3];
		//auxdatos = new String[cuenta][3];
		
    	
    	rs = conexion.ConsultaSQL("SELECT w.nombre, p.nombre, po.nombre FROM WORKPAQUETS w LEFT JOIN PARTNER_WORKPAQUETS pa ON w.id_wp = pa.id_wp LEFT JOIN PROYECTOS po ON w.id_pro = po.id_pro LEFT JOIN PARTNER p ON pa.cod_part = p.cod_part ORDER BY w.nombre");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<4;j++){
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
    	rs = conexion.ConsultaSQL("SELECT w.nombre, p.nombre, po.nombre FROM WORKPAQUETS w LEFT JOIN PARTNER_WORKPAQUETS pa ON w.id_wp = pa.id_wp LEFT JOIN PROYECTOS po ON w.id_pro = po.id_pro LEFT JOIN PARTNER p ON pa.cod_part = p.cod_part ORDER BY w.nombre");
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
    			if(auxdatos[i][c]==datos[f][c]){
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
    public static PnlBusquedawp Obtener_Instancia(){
		return instancia;
	}

}
