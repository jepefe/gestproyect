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

import javax.swing.BorderFactory;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
	
	JPanel uspanel = new JPanel();
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	JPanel total = new JPanel();
	JPanel jpnopae = new JPanel();
	JPanel jpn = new JPanel();
	Border blackline;
	
	public PnlPracticas (){
		
		blackline = BorderFactory.createLineBorder(Color.black);
		uspanel.setBorder(blackline);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Datos Personales");
		uspanel.setBorder(title);
		
		panel.setBorder(blackline);
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Datos Becas");
		panel.setBorder(title2);
		
	
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		uspanel.setLayout(new GridBagLayout());
		total.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
				rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][28],rec.idioma[rec.eleidioma][197],
				rec.idioma[rec.eleidioma][159],rec.idioma[rec.eleidioma][160],rec.idioma[rec.eleidioma][161],
		   rec.idioma[rec.eleidioma][208],rec.idioma[rec.eleidioma][216],rec.idioma[rec.eleidioma][215],rec.idioma[rec.eleidioma][163],rec.idioma[rec.eleidioma][214],
		   rec.idioma[rec.eleidioma][164],
		   rec.idioma[rec.eleidioma][165],rec.idioma[rec.eleidioma][166],rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][198], 
		   rec.idioma[rec.eleidioma][198], rec.idioma[rec.eleidioma][199],rec.idioma[rec.eleidioma][200],rec.idioma[rec.eleidioma][201],
		   rec.idioma[rec.eleidioma][202],rec.idioma[rec.eleidioma][203],rec.idioma[rec.eleidioma][204],rec.idioma[rec.eleidioma][205],
		   rec.idioma[rec.eleidioma][206],rec.idioma[rec.eleidioma][207],rec.idioma[rec.eleidioma][209],rec.idioma[rec.eleidioma][210],
		   rec.idioma[rec.eleidioma][211],rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][212],rec.idioma[rec.eleidioma][213]
		   };
		int[] fieldWidths = {20,30,20,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		
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
			   uspanel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
  			gbc.gridwidth = GridBagConstraints.REMAINDER;
  			uspanel.add(jtxt[i] = new JTextField(20),gbc); 
  			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			 //panel.add(uspanel); 
			   break;
		   case (1)://apellidos
			   uspanel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
 			gbc.gridwidth = GridBagConstraints.REMAINDER;
 			uspanel.add(jtxt[i] = new JTextField(30),gbc);  
 			
 			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			 //panel.add(uspanel); 
			   break;
		   case (2)://tipo de estudios
			   uspanel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
 			gbc.gridwidth = GridBagConstraints.REMAINDER;
 			uspanel.add(jtxt[i] = new JTextField(30),gbc);  
 			
 			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			 //panel.add(uspanel); 
			   break;
		   case (3)://pago recivido
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
		   case (4)://documento de solicitud
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
		   case (5)://registrar nota examen idioma
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(jtxt[i] = new JTextField(2),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (6)://entrega D.N.I
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
		   case (7)://entrega listado preferencias universidad
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
		   case (8)://nota proceso de seleccion
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(jtxt[i] = new JTextField(2),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (9)://es becario
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(Relleno[i] = new JCheckBox(""));
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (10)://recibe financiaion
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(Relleno[i] = new JCheckBox(""));
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (11)://becario carta aceptacion beca
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
		   case (12)://becario entrega DNI
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
		   case (13)://becario entrega fotoopia libreeta bancria
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
		   case (14)://becario copia tarjeta sanitaria europea
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
		   case (15)://becario entrega expediente academico
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
		   case (16)://becario datos universidad
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
		   case (17)://becario convenio
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
		   case (18)://becario acuerdo de formacion
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
		   case (19)://becario contratacion seguro
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
		   case (20)://becario justificacion
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
		   case (21)://pagos realizados al alumno
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
		   case (22)://informe intermedio al oapee
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
		   case (23)://informe cualitatvo
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
		   case (24)://informe cumplimiento de la CUE
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
		   case (25)://excel
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
		   case (26)://entrega curriculum
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
		   case (27)://carta de motivacion
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
		   case (28)://listado de empresas
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
		   case (29)://expediente academico
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
		   case (30)://carta aceptacion empresa
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
		   case (31)://datos empresa
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
		   case (32)://acuerdo de practicas
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
		

		
		uspanel.setVisible(true);
		panel.setVisible(true);
		total.add(uspanel,gbc);
		total.add(panel,gbc);
		total.setVisible(true);
		this.setViewportView(total);
		//this.setViewportView(panel);
		
	}
	
}
