/**
 /**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Félix Perona G
 */

package pkGesproject.TimeSheet;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JTextField[] jtxtpro, jtxtta;
	JLabel[] jlblpro, jlblta;
	JDateChooser jdc1;
	JButton jbtnaceptar, jbtncancelar;
	GpComboBox CmbTareas = new GpComboBox();
	GpComboBox CmbProyecto = new GpComboBox();
	GpComboBox CmbStaff = new GpComboBox();
	GpComboBox CmbPart = new GpComboBox();
	
	ResultSet rs;
	ConexionDb conexion = new ConexionDb();
	JFrame aviso = new JFrame();
	JPanel Jproyecto = new JPanel();
	JPanel Jtarea = new JPanel();
	JPanel Jtabla = new JPanel();
	
	
	public PnlAlta_TimeSheet(){
		System.out.println("HOLAAAAAAAAA");
		this.setLayout(new BorderLayout());
		Jproyecto.setLayout(new GridBagLayout());
		Jtarea.setLayout(new GridBagLayout());
		
		
		String[] fieldNamesproyecto = {
		   rec.idioma[rec.eleidioma][111],rec.idioma[rec.eleidioma][101], rec.idioma[rec.eleidioma][102], rec.idioma[rec.eleidioma][105]
		   };
		String[] fieldNamestarea = {
				   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][13], rec.idioma[rec.eleidioma][55], rec.idioma[rec.eleidioma][55]
				   };
		int[] fieldWidths = {20,20,20,9};
		//jtxt de tareas
		jtxtta = new JTextField[fieldNamestarea.length];
		jlblta = new JLabel[fieldNamestarea.length];
		//jtxt de proyecto
		jtxtpro = new JTextField[fieldNamesproyecto.length];
		jlblpro = new JLabel[fieldNamesproyecto.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		
		//declaramos el campo que vamos a utilizar para a�adir las fechas
	      jdc1 = new JDateChooser();
	      jdc1.setDateFormatString("DD/MM/YYYY");
	    

		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
	      
	      //campos para proyecto
		 
	      
	      for(int i=0;i<fieldNamesproyecto.length;++i) {
				
				System.out.println("Fieldnames = " + fieldNamesproyecto.length + " / i = " + i);
				
			   gbc.gridwidth = GridBagConstraints.RELATIVE;
			   Jproyecto.add(jlblpro[i]=new JLabel(fieldNamesproyecto[i]),gbc);
			   gbc.gridwidth = GridBagConstraints.REMAINDER;
			   
			   switch(i){
			   
			   case (0)://nombre combo
				   System.out.println("Entra combo nombre");
			   	Jproyecto.add(CmbProyecto,gbc);
			   CmbProyecto.setPreferredSize(new Dimension(140,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS");
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
				   System.out.println("Entra contract numbre");
				   Jproyecto.add(jtxtpro[i]=new JTextField(fieldWidths[i]),gbc);
			   		break;
			   case (2)://institución
				   System.out.println("Entra combo institucion");
				   Jproyecto.add(CmbPart,gbc);
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
			  
			   case (3)://rol en el proyecto
				   System.out.println("Entra rol proyecto");
				   Jproyecto.add(jtxtpro[i]=new JTextField(fieldWidths[i]),gbc);
				   break;
			   		
			   }//fin switch 
			   
			}//fin for de proyectos
	      
	      //action listener para combo proyectos
	      ActionListener accionpro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (CmbProyecto.getSelectedItem() != null){
					String nombrepro = null;
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("SELECT num_contrato FROM PROYECTOS WHERE nombre LIKE '"+CmbProyecto.getSelectedItem()+"'");
					
					try {
						rs.next();
						nombrepro = rs.getString(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jtxtpro[1].setText(nombrepro);
					conexion.cerrarConexion();
				}
			}
	    	  
	      };
	      CmbProyecto.addActionListener(accionpro);
			this.add(Jproyecto,BorderLayout.NORTH);
			this.add(Jtarea,BorderLayout.CENTER);
			this.setVisible(true);
		}//fin constructor
	}//fin clase

	
