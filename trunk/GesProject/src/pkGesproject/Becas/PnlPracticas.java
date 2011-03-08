/** Esta clase se encarga de realizar el alta todos los tipos
 * de becas. 
 * 
 * @author Félix Perona G
 */
package pkGesproject.Becas;

import java.awt.BorderLayout;
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
	
	JPanel jpsoloapee = new JPanel();
	JPanel jpsolalumnos = new JPanel();
	JPanel jpbecario = new JPanel();
	JPanel jpinfinter = new JPanel();
	JPanel jpinffinal = new JPanel();
	
	Border blackline;
	
	public PnlPracticas (){
		panel.setSize(new Dimension(300,400));
		//jpsoloapee.setSize(new Dimension(250,70));
		jpsolalumnos.setSize(new Dimension(250,200));
		jpbecario.setSize(new Dimension(100,200));
		jpinfinter.setSize(new Dimension(250,200));
		jpinffinal.setSize(new Dimension(250,200));
		
		blackline = BorderFactory.createLineBorder(Color.black);
		uspanel.setBorder(blackline);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Datos Personales");
		uspanel.setBorder(title);
		
		panel.setBorder(blackline);
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Datos Becas");
		panel.setBorder(title2);
		
		TitledBorder title3;
		jpsoloapee.setBorder(blackline);
		title3 = BorderFactory.createTitledBorder("SOLICITUD AL OAPEE");
		jpsoloapee.setBorder(title3);
		
		TitledBorder title4;
		jpsolalumnos.setBorder(blackline);
		title4 = BorderFactory.createTitledBorder("SOLICITUD ALUMNOS");
		jpsolalumnos.setBorder(title4);
		
		TitledBorder title5;
		jpbecario.setBorder(blackline);
		title5 = BorderFactory.createTitledBorder("BECARIO");
		jpbecario.setBorder(title5);
		
		TitledBorder title6;
		jpinfinter.setBorder(blackline);
		title6 = BorderFactory.createTitledBorder("INFORME INTERMEDIO AL OAPEE");
		jpinfinter.setBorder(title6);
		
		TitledBorder title7;
		jpinffinal.setBorder(blackline);
		title7 = BorderFactory.createTitledBorder("INFORME FINAL AL OAPEE");
		jpinffinal.setBorder(title7);
		
		 
	 
	
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		uspanel.setLayout(new GridBagLayout());
		total.setLayout(new BorderLayout());
		jpsoloapee.setLayout(new GridBagLayout());
		jpsolalumnos.setLayout(new GridBagLayout());
		jpbecario.setLayout(new GridBagLayout());
		jpinfinter.setLayout(new GridBagLayout());
		jpinffinal.setLayout(new GridBagLayout());
		  
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
		//gbc.anchor = GridBagConstraints.CENTER;
		//gbc.insets = new Insets(20,0,15,0);
		
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
		  if (i>2){
			   gbc.gridwidth = 5;
		   }

		   
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
		   case (3)://pago recibido
			   //gbc.gridwidth = 5;
			   jpsoloapee.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsoloapee.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsoloapee.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsoloapee.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			break;
		   case (4)://documento de solicitud
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
		   		Relleno[i+20].setEnabled(false);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (5)://registrar nota examen idioma
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (6)://entrega D.N.I
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (7)://entrega listado preferencias universidad
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
		   		Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (8)://nota proceso de seleccion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (9)://es becario
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (10)://recibe financiaion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (11)://becario carta aceptacion beca
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (12)://becario entrega DNI
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (13)://becario entrega fotoopia libreeta bancria
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (14)://becario copia tarjeta sanitaria europea
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (15)://becario entrega expediente academico
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (16)://becario datos universidad
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (17)://becario convenio
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (18)://becario acuerdo de formacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (19)://becario contratacion seguro
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (20)://becario justificacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (21)://becario pagos realizados al alumno
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (22)://informe intermedio al oapee
			   jpinfinter.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinfinter.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinfinter.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinfinter.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (23)://informe cualitatvo
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (24)://informe cumplimiento de la CUE
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (25)://excel
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (26)://entrega curriculum
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (27)://carta de motivacion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (28)://listado de empresas
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (29)://expediente academico
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (30)://becario carta aceptacion empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (31)://datos empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (32)://acuerdo de practicas
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   		
		   }


		}//fin for

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel.add(jpsoloapee,gbc);
		panel.add(jpsolalumnos,gbc);
		jpsolalumnos.add(jpbecario,gbc);
		panel.add(jpinfinter,gbc);
		panel.add(jpinffinal,gbc);
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
		
		
		
		total.setVisible(true);
		this.setViewportView(total);
		jpsolalumnos.setVisible(true);
		jpbecario.setVisible(true);
		jpinfinter.setVisible(true);
		jpinffinal.setVisible(true);
		total.add(uspanel,BorderLayout.NORTH);
		total.add(panel,BorderLayout.CENTER);
		uspanel.setVisible(true);
		panel.setVisible(true);
		jpsoloapee.setVisible(true);
		
		
		//this.setViewportView(panel);
		
	}
	
}
