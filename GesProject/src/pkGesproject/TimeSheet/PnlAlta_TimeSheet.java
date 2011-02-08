/**
 /**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Félix Perona G
 */

package pkGesproject.TimeSheet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GesStaff;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{
	
	private static final String BorderLayout = null;
	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JTextField[] jtxtpro,jtxtta ;
	JLabel[] jlblpro, jlblta, jlblhe;
	JDateChooser jdc1;
	JButton jbtnaceptar, jbtnlimpiar,jbtneliminar;
	GpComboBox CmbTareas = new GpComboBox();
	GpComboBox CmbProyecto = new GpComboBox();
	GpComboBox CmbPart = new GpComboBox();
	GpComboBox CmbWorkpaquets = new GpComboBox();
	GpComboBox CmbStaff = new GpComboBox();
	ResultSet rs,rs2;
	ConexionDb conexion = new ConexionDb();
	JFrame aviso = new JFrame();
	JPanel Jproyecto = new JPanel();
	JPanel Jtarea = new JPanel();
	JPanel Jtabla = new JPanel();
	JScrollPane JScroll; 
	int cuenta = 0;
	int columnas;
	String auxdatos[][];
	String[] fila;
	final String colu[] = {rec.idioma[rec.eleidioma][95],rec.idioma[rec.eleidioma][129],rec.idioma[rec.eleidioma][40],rec.idioma[rec.eleidioma][97]};
	String nomsta = "";
	DefaultTableModel tablemodel = new DefaultTableModel(null,colu); //Creamos el tablemodel global y le pasamos las columnas
	
	/*final Object[][] info ={
			{"15/05/2010","tarea manolo", "wp2", "25"},
			{"18/12/2010","tarea frey", "wp1", "10"},
			{"01/01/2011","tarea ruben", "wp2", "5"},
			{"05/01/2011","tarea berna", "wp3", "40"},
			{"07/01/2011","tarea felix", "wp1", "2"},
			{"07/01/2011","tarea juan", "wp1", "2"},
			{"10/01/2011","tarea vicente", "wp1", "23"},
			{"10/01/2011","tarea esteban", "wp3", "4"},
			{"03/02/2011","tarea josodo", "wp1", "45"},
			{"03/02/2011","tarea vlad", "wp3", "2"}
	};*/

	JTable jtblTime = new JTable(null, colu);	
	
	int id_wp = 0;
	String datos [][];
	String nombrepro = null;
	int Tarea, workpaquet, vacio = 0;
	
	public PnlAlta_TimeSheet(){
		
    	/**
    	 * Cargamos los array y la tabla con los datos de la bd
    	 */
    	cuenta=contar_reg();
    	fila = new String[colu.length+1];
    	columnas =4;
    	datos = new String[cuenta][columnas];
		auxdatos = new String[cuenta][columnas];
    	tablemodel=cargar_tabla(datos,columnas);
    	jtblTime  = new JTable(tablemodel){
    		 public boolean isCellEditable(int rowIndex, int mColIndex) {
                 return false;
               }
    	};
		
		
		for (int cont=0; cont < 3; cont++){
		System.out.println(colu[cont]);
		}
		System.out.println("HOLAAAAAAAAA");
		this.setLayout(new GridBagLayout());
		Jproyecto.setLayout(new GridBagLayout());
		Jtarea.setLayout(new GridBagLayout());
		Jtabla.setLayout(new GridBagLayout());
		
		datos = new String[Tarea][workpaquet];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		

		jtblTime.setPreferredScrollableViewportSize(new Dimension(816,200));
		final JScrollPane jspntabla = new JScrollPane(jtblTime);
	    JScrollPane scrollpanel = new JScrollPane(jtblTime);
		
		String[] fieldNamesproyecto = {
		   rec.idioma[rec.eleidioma][111],rec.idioma[rec.eleidioma][101], rec.idioma[rec.eleidioma][102], rec.idioma[rec.eleidioma][125], rec.idioma[rec.eleidioma][105]
		   };
		String[] fieldNamestarea = {
				   rec.idioma[rec.eleidioma][95],rec.idioma[rec.eleidioma][129], rec.idioma[rec.eleidioma][40], rec.idioma[rec.eleidioma][97]
				   };
		int[] fieldWidths = {20,20,20,9,9};
		//jtxt de tareas
		jtxtta = new JTextField[fieldNamestarea.length];
		jlblta = new JLabel[fieldNamestarea.length];
		//jtxt de proyecto
		jtxtpro = new JTextField[fieldNamesproyecto.length];
		jlblpro = new JLabel[fieldNamesproyecto.length];
		
		
		
		
		//declaramos el campo que vamos a utilizar para a�adir las fechas
	      jdc1 = new JDateChooser();
	      jdc1.setDateFormatString("DD/MM/YYYY");
	    

		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
	      
	      
	      GridBagConstraints gbt = new GridBagConstraints();
          
	      
	      for(int i=0;i<fieldNamesproyecto.length;++i) {
				
				//System.out.println("Fieldnames = " + fieldNamesproyecto.length + " / i = " + i);
				
				//gbc.gridwidth = GridBagConstraints.RELATIVE;
				//Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbc);

			   /**
			    * Cargamos los campos para proyecto mediante el swith
			    */
			   switch(i){
			   
			   case (0)://nombre combo
				gbt.insets = new Insets(10,0,10,5);
				gbt.gridx = 0; // El área de texto empieza en la columna
				gbt.gridy = 0; // El área de texto empieza en la fila
				//gbc.gridwidth = GridBagConstraints.REMAINDER;
				Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbt);
				gbt.insets = new Insets(10,0,10,0);
				gbt.gridx = 1; // El área de texto empieza en la columna
			   	Jproyecto.add(CmbProyecto,gbt);
			   CmbProyecto.setPreferredSize(new Dimension(140,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS ORDER BY nombre");
				try {
				while(rs.next()){
					CmbProyecto.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				CmbProyecto.setSelectedItem(null);
				conexion.cerrarConexion();
				break;
			   case (1)://contract number
				   gbt.insets = new Insets(10,17,10,5);
					gbt.gridx = 2; // El área de texto empieza en la columna
					gbt.gridy = 0; // El área de texto empieza en la fila
				   Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbt);
				   //gbc.gridwidth = GridBagConstraints.REMAINDER;
				   System.out.println("Entra contract numbre");
				   gbt.insets = new Insets(10,0,10,0);
				   gbt.gridx = 3; // El área de texto empieza en la fila
				   Jproyecto.add(jtxtpro[i]=new JTextField(fieldWidths[i]),gbt);
				   jtxtpro[i].disable();
			   		break;
			   case (2)://institución
				   gbt.insets = new Insets(10,0,10,5);
					gbt.gridx = 0; // El área de texto empieza en la columna
					gbt.gridy = 1; // El área de texto empieza en la fila
				   Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbt);
				   //gbc.gridwidth = GridBagConstraints.REMAINDER;
				   System.out.println("Entra combo institucion");
				   gbt.insets = new Insets(10,0,10,0);
				   gbt.gridx = 1; // El área de texto empieza en la columna
				   Jproyecto.add(CmbPart,gbt);
			   CmbPart.setPreferredSize(new Dimension(140,30));
	   
	   				conexion.Conectardb();
	   					rs = conexion.ConsultaSQL("SELECT nombre,cod_part FROM PARTNER ORDER BY nombre");
	   						try {
	   								while(rs.next()){
	   									CmbPart.addItem(rs.getString(1));	
			
	   								}
	   							} catch (SQLException e1) {
	   										// TODO Auto-generated catch block
	   								e1.printStackTrace();
	   							}
	   							CmbPart.setSelectedItem(null);
	   								conexion.cerrarConexion();
				   break;
			   case (3):
				gbt.insets = new Insets(10,17,10,5);
				gbt.gridx = 2; // El área de texto empieza en la columna
				gbt.gridy = 1; // El área de texto empieza en la fila
				Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbt);
				//gbc.gridwidth = GridBagConstraints.REMAINDER;
				System.out.println("Entra combo staff");
				gbt.insets = new Insets(10,0,10,0);
				gbt.gridx = 3; // El área de texto empieza en la columna
			   	Jproyecto.add(CmbStaff,gbt);
			   	CmbStaff.setPreferredSize(new Dimension(230,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_staff FROM STAFF ORDER BY nombre");
				try {
				while(rs.next()){
					CmbStaff.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					CmbStaff.setSelectedItem(null);
					if (!GesStaff.esRepresentante()){
						
					rs = conexion.ConsultaSQL("SELECT nombre FROM STAFF WHERE id_staff='"+Integer.toString(recursos.getIdusuario())+"'");
					try {
						rs.next();
						nomsta = rs.getString(1);
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					CmbStaff.setSelectedItem(nomsta);
					CmbStaff.setEnabled(false);
					}
					rs = conexion.ConsultaSQL("SELECT nombre FROM STAFF WHERE id_staff='"+Integer.toString(recursos.getIdusuario())+"'");
					try {
						rs.next();
						nomsta = rs.getString(1);
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					CmbStaff.setSelectedItem(nomsta);
					
				conexion.cerrarConexion();
				break;
			   case (4)://rol en el proyecto
				gbt.insets = new Insets(10,17,10,5);
				gbt.gridx = 4; // El área de texto empieza en la columna
				gbt.gridy = 1; // El área de texto empieza en la fila
				Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbt);
				//gbc.gridwidth = GridBagConstraints.REMAINDER;
				//System.out.println("Entra rol proyecto");
				gbt.insets = new Insets(10,0,10,0);
				gbt.gridx = 5; // El área de texto empieza en la columna
				Jproyecto.add(jtxtpro[i]=new JTextField(fieldWidths[i]),gbt);
				JTextFieldLimit ljtxt2 = new JTextFieldLimit(20);
				jtxtpro[i].setDocument(ljtxt2);
				  	break;
			   		
			   }//fin switch proyecto
			   
			   /**
			    * campos del panel de tareas
			    */
		switch (i){
				case (0)://fecha
					gbt.insets = new Insets(10,0,10,5);
					gbt.gridx = 0; // El área de texto empieza en la columna
					gbt.gridy = 0; // El área de texto empieza en la fila
					//gbc.gridwidth = GridBagConstraints.RELATIVE;
					Jtarea.add(jlblta[i]=new JLabel(fieldNamestarea[i]),gbt);
					gbt.insets = new Insets(10,0,10,0);
					gbt.gridx = 1; // El área de texto empieza en la columna
		   			Jtarea.add(jdc1,gbt);
					break;
				case (1)://tarea
					gbt.insets = new Insets(10,0,10,5);
					gbt.gridx = 0; // El área de texto empieza en la columna
					gbt.gridy = 1; // El área de texto empieza en la fila
					//gbc.gridwidth = GridBagConstraints.RELATIVE;
					Jtarea.add(jlblta[i]=new JLabel(fieldNamestarea[i]),gbt);
					System.out.println("Entra combo nombre tareas");
					//gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbt.insets = new Insets(10,0,10,0);
					gbt.gridx = 1; // El área de texto empieza en la columna
			   		Jtarea.add(CmbTareas,gbt);
			   		CmbTareas.setPreferredSize(new Dimension(140,30));
			   
			   		conexion.Conectardb();
			   		rs = conexion.ConsultaSQL("SELECT nombre,id_task FROM TAREAS ORDER BY nombre");
			   		try {
			   			while(rs.next()){
			   				CmbTareas.addItem(rs.getString(1));	
					
			   			}
			   			} catch (SQLException e1) {
						// TODO Auto-generated catch block
			   				e1.printStackTrace();
						}
			   		CmbTareas.setSelectedItem(null);
			   		conexion.cerrarConexion();
					break;
			   	case (2)://workpaquet
			   	gbt.insets = new Insets(10,17,10,5);
			   	gbt.gridx =2; // El área de texto empieza en la columna
				gbt.gridy = 1; // El área de texto empieza en la fila
			   	//gbc.gridwidth = GridBagConstraints.RELATIVE;
			   	Jtarea.add(jlblta[i]=new JLabel(fieldNamestarea[i]),gbt);
				System.out.println("Entra combo nombre workpaquets");
				//gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbt.insets = new Insets(10,0,10,0);
				gbt.gridx =3; // El área de texto empieza en la columna
		   		Jtarea.add(CmbWorkpaquets,gbt);
		   		CmbWorkpaquets.setPreferredSize(new Dimension(140,30));
		   
		   		conexion.Conectardb();
		   		rs = conexion.ConsultaSQL("SELECT nombre,id_wp FROM WORKPAQUETS ORDER BY nombre");
		   		try {
		   			while(rs.next()){
		   				CmbWorkpaquets.addItem(rs.getString(1));	
				
		   			}
		   			} catch (SQLException e1) {
					// TODO Auto-generated catch block
		   				e1.printStackTrace();
					}
		   		CmbWorkpaquets.setSelectedItem(null);
		   		conexion.cerrarConexion();
			   		break;
			   	case (3)://horas
			   		gbt.insets = new Insets(10,17,10,5);
				   	gbt.gridx = 4; // El área de texto empieza en la columna
			   		gbt.gridy = 1; // El área de texto empieza en la fila
			   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		Jtarea.add(jlblta[i]=new JLabel(fieldNamestarea[i]),gbt);
			   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		gbt.insets = new Insets(10,0,10,0);
			   		gbt.gridx = 5; // El área de texto empieza en la columna
			   		Jtarea.add(jtxtta[i]=new JTextField(fieldWidths[i]),gbt);
			   		JTextFieldLimit ljtxt0 = new JTextFieldLimit(4);
			   		jtxtta[i].setDocument(ljtxt0);
			   		break;
			   }//fin switch tareas
			   
			}//fin for de proyectos

	      
	      
	      
	      /**
	       * agregamos la tabla
	       */
	      
	      Jtabla.add(JScroll = new JScrollPane(jtblTime));
	      //accion tarea
	      ActionListener accionta = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (CmbTareas.getSelectedItem() != null){
					vacio = 2;
					String nomwp = null;
					//para las tareas
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("SELECT id_wp FROM TAREAS WHERE nombre LIKE '"+CmbTareas.getSelectedItem()+"'");
					
					try {
						rs.next();
						id_wp = rs.getInt(1);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//para workpaquets
					rs = conexion.ConsultaSQL("SELECT nombre FROM WORKPAQUETS WHERE id_wp = '"+id_wp+"'");
					
					try {
						rs.next();
						nomwp = rs.getString(1);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					CmbWorkpaquets.setSelectedItem(nomwp);
					conexion.cerrarConexion();
				}
			}
	    	  
	      };
	      
	      
	      /**
	       * action listener para combo proyectos
	       */
	      ActionListener accionpro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO acction listener combo proyectos
				if (CmbProyecto.getSelectedItem() != null){
					int idpro = 0;
					int idwp = 0;
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("SELECT num_contrato, id_pro FROM PROYECTOS WHERE nombre LIKE '"+CmbProyecto.getSelectedItem()+"'");
					
					try {
						rs.next();
						nombrepro = rs.getString(1);
						idpro = rs.getInt(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(nombrepro);
					jtxtpro[1].setText(nombrepro);
					conexion.cerrarConexion();
					
			   		/*conexion.Conectardb();
			   		rs = conexion.ConsultaSQL("SELECT nombre,id_wp FROM WORKPAQUETS WHERE id_pro = '"+idpro+"' ORDER BY nombre");
			   		try {
			   			while(rs.next()){
			   				CmbWorkpaquets.addItem(rs.getString(1));	
			   				idwp = rs.getInt(2);
			   			}
			   			} catch (SQLException e1) {
						// TODO Auto-generated catch block
			   				e1.printStackTrace();
						}
			   		CmbWorkpaquets.setSelectedItem(null);
			   		conexion.cerrarConexion();*/
			   		
			   		/*conexion.Conectardb();
			   		rs = conexion.ConsultaSQL("SELECT nombre,id_task FROM TAREAS WHERE i_wp = '"+idwp+"' ORDER BY nombre");
			   		try {
			   			while(rs.next()){
			   				CmbTareas.addItem(rs.getString(1));	
					
			   			}
			   			} catch (SQLException e1) {
						// TODO Auto-generated catch block
			   				e1.printStackTrace();
						}
			   		CmbTareas.setSelectedItem(null);
			   		conexion.cerrarConexion();*/
			   		
				}
			}
	    	  
	      };
	      	//gbc.gridwidth = GridBagConstraints.RELATIVE;
	      	gbt.gridx = 8; // El área de texto empieza en la columna
	   		gbt.gridy = 1; // El área de texto empieza en la fila
	   		
	   		
	   		/**
	   		 * Se agregan los botones al panel
	   		 */
	   		gbt.insets = new Insets(10,20,10,9);
	   		Jtarea.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][134]),gbt);
	   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   		gbt.gridx = 9; // El área de texto empieza en la columna
	   		gbt.insets = new Insets(10,0,10,9);
	   		Jtarea.add(jbtnlimpiar=new JButton(rec.idioma[rec.eleidioma][74]),gbt);
	   		//gbt.gridx = 6; // El área de texto empieza en la columna
	   		//gbt.gridy = 1; // El área de texto empieza en la fila
	   		//gbt.insets = new Insets(10,0,10,0);
	   		gbc.gridwidth = GridBagConstraints.REMAINDER;
	   		Jtabla.add(scrollpanel,gbc);
	   		gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   		//gbt.gridy = 2; // El área de texto empieza en la fila
	   		Jtabla.add(jbtneliminar = new JButton(rec.idioma[rec.eleidioma][39]),gbc);
	   		
	   		
	    /**
	     * Accion para el boton eliminar
	     */
	   		ActionListener event = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("eliminar")){
						Component aviso = null;
						int s = JOptionPane.showConfirmDialog(aviso, "Esta seguro??");
				
						if(s==0){
						
							conexion.Conectardb();
							conexion.executeUpdate("DELETE FROM TIMESHEET WHERE id_tarea = '"+datos[jtblTime.getSelectedRow()][1]+"'");
					
							cuenta=contar_reg();
							datos = new String[cuenta][columnas];
							auxdatos = new String[cuenta][columnas];
							tablemodel =cargar_tabla(datos,columnas);
							jtblTime.setModel(tablemodel);
							//jtblTime.repaint();
							//jtblTime.revalidate();
					
							JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][63]);
				
							conexion.cerrarConexion();
							
					    	/**
					    	 * Cargamos los array y la tabla con los datos de la bd
					    	 */
					    	cuenta=contar_reg();
					    	fila = new String[colu.length+1];
					    	columnas =4;
					    	datos = new String[cuenta][columnas];
							auxdatos = new String[cuenta][columnas];
					    	tablemodel=cargar_tabla(datos,columnas);
					    	jtblTime.setModel(tablemodel);
						}
					}
				}
	   		};
	   		
	    /**
	     * action listener para combo workpaquets
	     */
	      ActionListener accionwp = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO metodo acction para combo wp
				System.out.println("compruebo campo nulo");
				if (CmbTareas.getSelectedItem()!= null){
					System.out.println("el campo no es nulo");
					if (vacio == 1){
						System.out.println("pongo tares a null");
						CmbTareas.setSelectedItem(null);
					}else{
						System.out.println("pongo el campo a cero");
						vacio = 1;
					}
				}
				
				}
			};
			
			
		    /**
		     * action listener para boton aceptar
		     */
		      ActionListener accionba = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (CmbTareas.getSelectedItem()!=null || CmbProyecto.getSelectedItem()!=null){
						// cambiar fecha a sql
						java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						  
						conexion.Conectardb();
						rs = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre = '"+CmbProyecto.getSelectedItem()+"' ");
						int idpro = 0;
						try {
							rs.next();
							idpro = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rs = conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre = '"+CmbPart.getSelectedItem()+"' ");
						int idpart = 0;
						try {
							rs.next();
							idpart = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rs = conexion.ConsultaSQL("SELECT id_staff FROM STAFF WHERE nombre = '"+CmbStaff.getSelectedItem()+"' ");
						int idstaff = 0;
						try {
							rs.next();
							idstaff = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rs = conexion.ConsultaSQL("SELECT id_task FROM TAREAS WHERE nombre = '"+CmbTareas.getSelectedItem()+"' ");
						int idtar = 0;
						try {
							rs.next();
							idtar = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS WHERE nombre = '"+CmbWorkpaquets.getSelectedItem()+"' ");
						int idwpp = 0;
						try {
							rs.next();
							idwpp = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexion.executeUpdate("INSERT INTO TIMESHEET (id_pro, id_part, id_staff, rol, fecha, id_tarea, id_wp, horas) VALUES ('"+idpro+"','"+idpart+"','"+idstaff+"','"+jtxtpro[4].getText()+"','"+sqlDate1+"','"+idtar+"','"+idwpp+"','"+jtxtta[3].getText()+"')");
						
						conexion.cerrarConexion();
						
				    	/**
				    	 * Cargamos los array y la tabla con los datos de la bd despues de crear el alta nueva
				    	 */
				    	cuenta=contar_reg();
				    	fila = new String[colu.length+1];
				    	columnas =4;
				    	datos = new String[cuenta][columnas];
						auxdatos = new String[cuenta][columnas];
				    	tablemodel=cargar_tabla(datos,columnas);
				    	jtblTime.setModel(tablemodel);
				    	
						jdc1.setDate(null);
						CmbTareas.setSelectedItem(null);
						CmbWorkpaquets.setSelectedItem(null);
						jtxtta[3].setText("");
				    	
					}else{
						JOptionPane.showMessageDialog( null, "Se ha de seleccionar tarea o workpaquet"); 
					}
					
					
					
				}
		    	  
		      };
		     
		      
			/**
			 * action listener para boton limpiar
			 */
		      ActionListener accionbl = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					vacio = 2;
					//vaciamos proyecto
					CmbProyecto.setSelectedItem(null);
					jtxtpro[1].setText("");
					CmbStaff.setSelectedItem(null);
					jtxtpro[4].setText("");
					CmbPart.setSelectedItem(null);
					
					//vaciamos tarea
					jdc1.setDate(null);
					CmbTareas.setSelectedItem(null);
					CmbWorkpaquets.setSelectedItem(null);
					jtxtta[3].setText("");
				}
				
		    	  
		      };
		      
		      
		      
	      /**
	       * Se agregan los action listener a los objeto
	       */
		      
	      
		      CmbProyecto.addActionListener(accionpro);
	      	CmbTareas.addActionListener(accionta);
	      	CmbWorkpaquets.addActionListener(accionwp);
	      	jbtnaceptar.addActionListener(accionba);
	      	jbtnlimpiar.addActionListener(accionbl);
	      	jbtneliminar.setActionCommand("eliminar");
            jbtneliminar.addActionListener(event);
          	GridBagConstraints gbbc = new GridBagConstraints();
          //gbbc.gridx = 0; // El área de texto empieza en la columna
          	gbbc.gridy = 0; // El área de texto empieza en la fila
          	gbbc.insets = new Insets(15,0,100,0);
			this.add(Jproyecto,gbbc);
			gbbc.gridy = 1; // El área de texto empieza en la fila
			gbbc.insets = new Insets(0,0,100,0);
			this.add(Jtarea,gbbc);
			gbbc.gridy = 2; // El área de texto empieza en la fila
			this.add(Jtabla,gbbc);
			this.setVisible(true);
		}//fin constructor
	
	/**
     * Este metodo coje la matriz datos, descarga de la BD los datos de los partners y genera un tablemodel, el cual es devuelto por
     * este método.
     * @param datos
     * @return
     **/
    
    public DefaultTableModel cargar_tabla(String datos[][],int columna){
    	
    	String resul[]= new String[3];
    	conexion.Conectardb();
		
    	
    	rs = conexion.ConsultaSQL("SELECT fecha,id_tarea,id_wp,horas FROM TIMESHEET ORDER BY fecha");
    	int i=0;
    	int idta = 0;
    	int idwp = 0;
    	String nomta;
    	String nomwp;
    	/*try {
    		rs.next();
			idta = rs.getInt(2);
			idwp = rs.getInt(3);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	rs2 = conexion.ConsultaSQL("SELECT nombre FROM TAREAS WHERE id_task = '"+idta+"'");
    	try {
    		rs2.next();
			nomta = rs2.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
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
     * Este método lo que hace es devolvernos los registros que existen actualmente en la tabla timesheet
     **/
    public int contar_reg(){
		
    	conexion.Conectardb();
    	//rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF");
    	rs = conexion.ConsultaSQL("SELECT fecha,id_tarea,id_wp,horas FROM TIMESHEET");
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
		
	}//fin clase

	

