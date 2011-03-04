/** Esta clase se encarga de realizar el alta todos los tipos
 * de becas. 
 * 
 * @author Félix Perona G
 */
package pkGesproject.Becas;

import java.awt.Checkbox;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pkGesproject.ConexionDb;
import pkGesproject.ConexionFTP;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;
import pkGesproject.Workpakages.PnlBusquedawp;

import com.toedter.calendar.JDateChooser;

public class PnlPracticas extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar, jbtnlimpiar;
	JButton[] jbtnsubir;
	JDateChooser jdc1,jdc2;
	ConexionDb conexion = new ConexionDb();
	int id_wp = 0;
	ResultSet rs;
	String nomwp;
	int indexwp;
	char caracter;
	Border empty = new EmptyBorder(0,0,0,0);
	PnlBusquedawp modwp = PnlBusquedawp.Obtener_Instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	DefaultListModel modelo;  // listas Partners (lista2)
	DefaultListModel modelo2;
	JPanel jpnl = new JPanel();
	String Npartners[] ;	// array con partners
	int cuenta =0; // cuenta para array dinamica.
	ConexionFTP coneftp = new ConexionFTP();
	JCheckBox[] Relleno;
	JCheckBox Relleno2 = new JCheckBox();
	JCheckBox Relleno3 = new JCheckBox();
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	
	JPanel jpnopae = new JPanel();
	JPanel jpn = new JPanel();
	
	public PnlPracticas (){
	
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][159],rec.idioma[rec.eleidioma][160], rec.idioma[rec.eleidioma][161], rec.idioma[rec.eleidioma][162],
		   rec.idioma[rec.eleidioma][162],rec.idioma[rec.eleidioma][163],rec.idioma[rec.eleidioma][164], rec.idioma[rec.eleidioma][165], 
		   rec.idioma[rec.eleidioma][166], rec.idioma[rec.eleidioma][167], rec.idioma[rec.eleidioma][168], rec.idioma[rec.eleidioma][169],
		   rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],
		   rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],
		   rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168],
		   rec.idioma[rec.eleidioma][168],rec.idioma[rec.eleidioma][168]
		   };
		int[] fieldWidths = {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
	    
	      
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
	      
		jbtnsubir=new JButton[fieldNames.length];
		Relleno = new JCheckBox[(fieldNames.length)+20];      
	    	
	     
		
		for(int i=0;i<fieldNames.length;++i) {
		
			//System.out.println("Fieldnames = " + fieldNames.length + " / i = " + i);
			
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   

		   
		   switch(i){
		   
		   case (0)://nombre
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			//panel.add(Relleno2,gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			//panel.add(Relleno3,gbc);
	   			panel.add(Relleno[i] = new JCheckBox());
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			break;
		   case (1)://presupuesto
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i+20] = new JCheckBox(""));
		   		Relleno[i+20].setEnabled(false);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (2)://combo de proyectos
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (3):
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (4)://fecha de inicio
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i+20] = new JCheckBox(""));
		   		Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (5)://fecha de fin
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (6)://descripcion
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (7)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (8)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (9)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (10)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (11)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (12)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (13)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (14)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (15)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (16)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (17)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (18)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (19)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (20)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (21)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (22)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (23)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (24)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (25)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(Relleno[i] = new JCheckBox(""));
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			panel.add(Relleno[i+20] = new JCheckBox(""));
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			panel.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   		
		   }


		}//fin for


	  /*  
		**
		 * Creamos los cuatro botones para este panel 
		 */
		//primeros dos botones del panel
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		

		
		
		panel.setVisible(true);
		this.setViewportView(panel);
		
	}
	
}
