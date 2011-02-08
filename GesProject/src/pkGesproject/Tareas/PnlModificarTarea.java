package pkGesproject.Tareas;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;
import pkGesproject.Socios.PnlModificarsocio;
import pkGesproject.Staff.pnlAlta_staff;

public class PnlModificarTarea extends JPanel{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs,rs2;
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	int cuenta = 0;
	int columnas;
	String datos[][];
	Boolean existe = new Boolean(false);
	String auxdatos[][];
	static PnlModificarTarea instancia = new PnlModificarTarea(); 
	String colu[] = {"Nombre","Presupuesto","Worpackages","Proyecto"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	PnlAltatarea mod = new PnlAltatarea();
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar,jbtnaceptar,jbtncancelar;
	Boolean llena = new Boolean(false);
	String[] fila = new String[4];
	JTable jtblLateral;
	JScrollPane jspntabla;
	public static JDialog modta;
	
	//PnlModificarTarea tableOther = new PnlModificarTarea();
    
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
    
    public PnlModificarTarea(){
    	
    	/**
    	 * Metodo para cargar ppor primera vez el tableModel
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
    	String resul[]= new String[3];
    	conexion.Conectardb();
    
    	rs = conexion.ConsultaSQL("");
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
    	*/
    		
    	this.setLayout(new GridBagLayout()); //Ponemos el Layout al panel
		
    	//jtblLateral  = new JTable(tablemodel= new DefaultTableModel(datos,colu));
       
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
			    										res[col] = datos[fila][col];
			    									}
			    									fila++;
			    									
			    									Object[] dat = {res[0],res[1],res[2]};
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
            
            conexion.cerrarConexion();
            
            ActionListener event = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("modificar")){
						
						if(jtblLateral.getSelectedRow()==-1){
							Component aviso = null;
							JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][103]);
							
						}else{
						
						modta = new JDialog();
						
						modta.add(mod);
						modta.setBounds(0, 0, 600, 650);
						modta.setLocationRelativeTo(null);
						modta.setModal(true);
						mod.jbtncancelar.setVisible(false);
						mod.jbtnaceptar.setVisible(false);
						mod.jbtnaceptarM.setVisible(true);
						mod.jbtncancelarM.setVisible(true);
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
								
									} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
				    	conexion.cerrarConexion();
						
						modta.setVisible(true);
						}
					}
					
					if(e.getActionCommand().equals("eliminar")){
						
							JOptionPane.showConfirmDialog(aviso,"Va a borrar un registro, ¿Desea continuar?");
							for(int i=0;i<2;i++){
								//jtxt[i].setText("");
							}
						
						conexion.Conectardb();
						conexion.executeUpdate("DELETE FROM TAREAS WHERE nombre = '"+datos[jtblLateral.getSelectedRow()][0]+"'");
						Component aviso = null;
						
						
						/**
						 * Código para actualizar el table model
						 */
						cuenta=contar_reg();
						datos = new String[cuenta][columnas];
						auxdatos = new String[cuenta][columnas];
						tablemodel =cargar_tabla(datos,columnas);
						jtblLateral.setModel(tablemodel);
						llena = false;
						
						conexion.cerrarConexion();
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][135]);
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
    
    public DefaultTableModel cargar_tabla(String datos[][],int columnas){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	
		//datos = new String[cuenta][3];
		//auxdatos = new String[cuenta][3];
		
    	
    	rs = conexion.ConsultaSQL("SELECT  t.nombre, t.presupuesto, w.nombre, p.nombre FROM TAREAS t LEFT JOIN " +
    	"WORKPAQUETS w ON t.id_wp = w.id_wp LEFT JOIN PROYECTOS p ON w.id_pro = p.id_pro");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<columnas+1;j++){
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
    
    public static PnlModificarTarea Obtener_Instancia(){
		return instancia;
	}
}
