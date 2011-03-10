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
import java.awt.FlowLayout;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	JLabel[] jtxtra;
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
	
	JPanel jpradio = new JPanel();
	JPanel jpbotones = new JPanel();
	JPanel jpsoloapee = new JPanel();
	JPanel jpsolalumnos = new JPanel();
	JPanel jpbecario = new JPanel();
	JPanel jpinfinter = new JPanel();
	JPanel jpinffinal = new JPanel();
	
	
	Border blackline;
	
	JRadioButton rbeeres = new JRadioButton();
	JRadioButton rbeerpr = new JRadioButton();
	JRadioButton rbeermodo = new JRadioButton();
	JRadioButton rbeermonodo = new JRadioButton();
	JRadioButton rbeerco = new JRadioButton();
	JRadioButton rbemi = new JRadioButton();
	JRadioButton rbecofc = new JRadioButton();
	JRadioButton rotmo = new JRadioButton();
	ButtonGroup group = new ButtonGroup();
	
	public PnlPracticas (){
		panel.setSize(new Dimension(300,400));
		jpsoloapee.setSize(new Dimension(300,70));
		jpsolalumnos.setSize(new Dimension(300,200));
		jpbecario.setSize(new Dimension(100,200));
		jpinfinter.setSize(new Dimension(300,200));
		jpinffinal.setSize(new Dimension(300,200));
		
		blackline = BorderFactory.createLineBorder(Color.black);
		uspanel.setBorder(blackline);
		TitledBorder title;
		title = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][227]);
		uspanel.setBorder(title);
		
		panel.setBorder(blackline);
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][222]);
		panel.setBorder(title2);
		
		TitledBorder title3;
		jpsoloapee.setBorder(blackline);
		title3 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][223]);
		jpsoloapee.setBorder(title3);
		
		TitledBorder title4;
		jpsolalumnos.setBorder(blackline);
		title4 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][224]);
		jpsolalumnos.setBorder(title4);
		
		TitledBorder title5;
		jpbecario.setBorder(blackline);
		title5 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][163]);
		jpbecario.setBorder(title5);
		
		TitledBorder title6;
		jpinfinter.setBorder(blackline);
		title6 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][225]);
		jpinfinter.setBorder(title6);
		
		TitledBorder title7;
		jpinffinal.setBorder(blackline);
		title7 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][226]);
		jpinffinal.setBorder(title7);
		
		TitledBorder title8;
		jpradio.setBorder(blackline);
		title8 = BorderFactory.createTitledBorder(rec.idioma[rec.eleidioma][221]);
		jpradio.setBorder(title8);
		
		/**
		 * se ponen las propiedades a los radio button
		 */
		
		rbeeres.setMnemonic('a');
		rbeerpr.setMnemonic('b');
		rbeermodo.setMnemonic('c');
		rbeermonodo.setMnemonic('d');
		rbeerco.setMnemonic('e');
		rbemi.setMnemonic('f');
		rbecofc.setMnemonic('g');
		rotmo.setMnemonic('h');
		rbeeres.setSelected(true);
		
		/**
		 * se añaden los radio button a un grupo 
		 */
	    group.add(rbeeres);
	    group.add(rbeerpr);
	    group.add(rbeermodo);
	    group.add(rbeermonodo);
	    group.add(rbeerco);
	    group.add(rbemi);
	    group.add(rbecofc);
	    group.add(rotmo);
	    	 
	
		this.setBorder(empty);
		
		jpradio.setLayout(new FlowLayout());
		jpbotones.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		uspanel.setLayout(new GridBagLayout());
		total.setLayout(new BorderLayout());
		jpsoloapee.setLayout(new GridBagLayout());
		jpsolalumnos.setLayout(new GridBagLayout());
		jpbecario.setLayout(new GridBagLayout());
		jpinfinter.setLayout(new GridBagLayout());
		jpinffinal.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
			rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][28],rec.idioma[rec.eleidioma][197],rec.idioma[rec.eleidioma][159],
			rec.idioma[rec.eleidioma][160],rec.idioma[rec.eleidioma][161],rec.idioma[rec.eleidioma][208],rec.idioma[rec.eleidioma][216],
			rec.idioma[rec.eleidioma][215],rec.idioma[rec.eleidioma][163],rec.idioma[rec.eleidioma][214],rec.idioma[rec.eleidioma][164],
		   rec.idioma[rec.eleidioma][165],rec.idioma[rec.eleidioma][166],rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][198], 
		   rec.idioma[rec.eleidioma][198], rec.idioma[rec.eleidioma][199],rec.idioma[rec.eleidioma][200],rec.idioma[rec.eleidioma][201],
		   rec.idioma[rec.eleidioma][202],rec.idioma[rec.eleidioma][203],rec.idioma[rec.eleidioma][204],rec.idioma[rec.eleidioma][205],
		   rec.idioma[rec.eleidioma][206],rec.idioma[rec.eleidioma][207],rec.idioma[rec.eleidioma][209],rec.idioma[rec.eleidioma][210],
		   rec.idioma[rec.eleidioma][211],rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][212],rec.idioma[rec.eleidioma][213]
		   };
		int[] fieldWidths = {20,30,20,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		jtxtra = new JLabel[8];
		
		jpradio.add(rbeeres);
		jpradio.add(jtxtra[0]=new JLabel(rec.idioma[rec.eleidioma][152]));
	    jpradio.add(rbeerpr);
	    jpradio.add(jtxtra[1]=new JLabel(rec.idioma[rec.eleidioma][153]));
	    jpradio.add(rbeermodo);
	    jpradio.add(jtxtra[2]=new JLabel(rec.idioma[rec.eleidioma][153]));
	    jpradio.add(rbeermonodo);
	    jpradio.add(jtxtra[3]=new JLabel(rec.idioma[rec.eleidioma][154]));
	    jpradio.add(rbeerco);
	    jpradio.add(jtxtra[4]=new JLabel(rec.idioma[rec.eleidioma][158]));
	    jpradio.add(rbemi);
	    jpradio.add(jtxtra[5]=new JLabel(rec.idioma[rec.eleidioma][155]));
	    jpradio.add(rbecofc);
	    jpradio.add(jtxtra[6]=new JLabel(rec.idioma[rec.eleidioma][156]));
	    jpradio.add(rotmo);
	    jpradio.add(jtxtra[7]=new JLabel(rec.idioma[rec.eleidioma][157]));
		
		GridBagConstraints gbc = new GridBagConstraints();
		//gbc.anchor = GridBagConstraints.CENTER;
		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		
		
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
		Relleno = new JCheckBox[(fieldNames.length)+60];      
	    	
	     
		
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
	   			jpsoloapee.add(Relleno[i+60] = new JCheckBox(),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsoloapee.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			break;
		   case (4)://documento de solicitud
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+60] = new JCheckBox(""),gbc);
		   		Relleno[i+60].setEnabled(false);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (5)://registrar nota examen idioma
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (6)://entrega D.N.I
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+60] = new JCheckBox(""),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	break;
		   case (7)://entrega listado preferencias universidad
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+60] = new JCheckBox(""),gbc);
		   		Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (8)://nota proceso de seleccion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
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
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+60] = new JCheckBox(""),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (12)://becario entrega DNI
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+60] = new JCheckBox(""),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (13)://becario entrega fotoopia libreeta bancria
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (14)://becario copia tarjeta sanitaria europea
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (15)://becario entrega expediente academico
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (16)://becario datos universidad
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (17)://becario convenio
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (18)://becario acuerdo de formacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (19)://becario contratacion seguro
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (20)://becario justificacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (21)://becario pagos realizados al alumno
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (22)://informe intermedio al oapee
			   jpinfinter.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinfinter.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinfinter.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinfinter.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (23)://informe cualitatvo
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (24)://informe cumplimiento de la CUE
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (25)://excel
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (26)://entrega curriculum
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (27)://carta de motivacion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (28)://listado de empresas
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (29)://expediente academico
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (30)://becario carta aceptacion empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (31)://datos empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   case (32)://acuerdo de practicas
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(""),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(""),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		break;
		   		
		   }


		}//fin for
		
		/**
		 * se crean los action listener para los radio button
		 * se selecciona el tipo de beca  introducir
		 */
		ActionListener acradio = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				//erasmus estudios
				if (rbeeres.isSelected() == false){
										
					jlbl[3].setVisible(true);
					Relleno[3].setVisible(true);
					Relleno[63].setVisible(true);
					jbtnsubir[3].setVisible(true);
					
					jlbl[4].setVisible(true);
					Relleno[4].setVisible(true);
					Relleno[64].setVisible(true);
					jbtnsubir[4].setVisible(true);
					
					jlbl[5].setVisible(true);
					jtxt[5].setVisible(true);
					
					
					jlbl[6].setVisible(true);
					Relleno[6].setVisible(true);
					Relleno[66].setVisible(true);
					jbtnsubir[6].setVisible(true);
					
					jlbl[7].setVisible(true);
					Relleno[7].setVisible(true);
					Relleno[67].setVisible(true);
					jbtnsubir[7].setVisible(true);
					
					jlbl[8].setVisible(true);
					jtxt[8].setVisible(true);
					
					jlbl[9].setVisible(true);
					Relleno[9].setVisible(true);
					
					jlbl[10].setVisible(true);
					Relleno[10].setVisible(true);
				
					jlbl[11].setVisible(true);
					Relleno[11].setVisible(true);
					Relleno[71].setVisible(true);
					jbtnsubir[11].setVisible(true);
					
					jlbl[12].setVisible(true);
					Relleno[12].setVisible(true);
					Relleno[72].setVisible(true);
					jbtnsubir[12].setVisible(true);
					
					jlbl[13].setVisible(true);
					Relleno[13].setVisible(true);
					Relleno[33].setVisible(true);
					jbtnsubir[13].setVisible(true);
					
					jlbl[14].setVisible(true);
					Relleno[14].setVisible(true);
					Relleno[34].setVisible(true);
					jbtnsubir[14].setVisible(true);
					
					jlbl[15].setVisible(true);
					Relleno[15].setVisible(true);
					Relleno[35].setVisible(true);
					jbtnsubir[15].setVisible(true);
					
					jlbl[16].setVisible(true);
					Relleno[16].setVisible(true);
					Relleno[36].setVisible(true);
					jbtnsubir[16].setVisible(true);
					
					jlbl[17].setVisible(true);
					Relleno[17].setVisible(true);
					Relleno[37].setVisible(true);
					jbtnsubir[17].setVisible(true);
					
					jlbl[18].setVisible(true);
					Relleno[18].setVisible(true);
					Relleno[38].setVisible(true);
					jbtnsubir[18].setVisible(true);
					
					jlbl[19].setVisible(true);
					Relleno[19].setVisible(true);
					Relleno[39].setVisible(true);
					jbtnsubir[19].setVisible(true);
					
					jlbl[20].setVisible(true);
					Relleno[20].setVisible(true);
					Relleno[40].setVisible(true);
					jbtnsubir[20].setVisible(true);
					
					jlbl[21].setVisible(true);
					Relleno[21].setVisible(true);
					Relleno[41].setVisible(true);
					jbtnsubir[21].setVisible(true);
					
					jlbl[22].setVisible(true);
					Relleno[22].setVisible(true);
					Relleno[42].setVisible(true);
					jbtnsubir[22].setVisible(true);
					
					jlbl[23].setVisible(true);
					Relleno[23].setVisible(true);
					Relleno[43].setVisible(true);
					jbtnsubir[23].setVisible(true);
					
					jlbl[24].setVisible(true);
					Relleno[24].setVisible(true);
					Relleno[44].setVisible(true);
					jbtnsubir[24].setVisible(true);
					
					jlbl[25].setVisible(true);
					Relleno[25].setVisible(true);
					Relleno[45].setVisible(true);
					jbtnsubir[25].setVisible(true);
					
					jlbl[26].setVisible(false);
					Relleno[26].setVisible(false);
					Relleno[46].setVisible(false);
					jbtnsubir[26].setVisible(false);
					
					jlbl[27].setVisible(false);
					Relleno[27].setVisible(false);
					Relleno[47].setVisible(false);
					jbtnsubir[27].setVisible(false);
					
					jlbl[28].setVisible(false);
					Relleno[28].setVisible(false);
					Relleno[48].setVisible(false);
					jbtnsubir[28].setVisible(false);
					
					jlbl[29].setVisible(false);
					Relleno[29].setVisible(false);
					Relleno[49].setVisible(false);
					jbtnsubir[29].setVisible(false);
					
					jlbl[30].setVisible(false);
					Relleno[30].setVisible(false);
					Relleno[50].setVisible(false);
					jbtnsubir[30].setVisible(false);
					
					jlbl[31].setVisible(false);
					Relleno[31].setVisible(false);
					Relleno[51].setVisible(false);
					jbtnsubir[31].setVisible(false);
					
					jlbl[32].setVisible(false);
					Relleno[32].setVisible(false);
					Relleno[52].setVisible(false);
					jbtnsubir[32].setVisible(false);
				}
				//erasmus practicas
				if (rbeerpr.isSelected() == false){
					jlbl[3].setVisible(true);
					Relleno[3].setVisible(true);
					Relleno[63].setVisible(true);
					jbtnsubir[3].setVisible(true);
					
					jlbl[4].setVisible(true);
					Relleno[4].setVisible(true);
					Relleno[64].setVisible(true);
					jbtnsubir[4].setVisible(true);
					
					jlbl[5].setVisible(true);
					jtxt[5].setVisible(true);
					
					jlbl[6].setVisible(false);
					Relleno[6].setVisible(false);
					Relleno[66].setVisible(false);
					jbtnsubir[6].setVisible(false);
					
					jlbl[7].setVisible(false);
					Relleno[7].setVisible(false);
					Relleno[67].setVisible(false);
					jbtnsubir[7].setVisible(false);
					
					jlbl[8].setVisible(true);
					jtxt[8].setVisible(true);
					
					jlbl[9].setVisible(true);
					Relleno[9].setVisible(true);
					
					jlbl[10].setVisible(true);
					Relleno[10].setVisible(true);
				
					jlbl[11].setVisible(true);
					Relleno[11].setVisible(true);
					Relleno[71].setVisible(true);
					jbtnsubir[11].setVisible(true);
					
					jlbl[12].setVisible(true);
					Relleno[12].setVisible(true);
					Relleno[72].setVisible(true);
					jbtnsubir[12].setVisible(true);
					
					jlbl[13].setVisible(true);
					Relleno[13].setVisible(true);
					Relleno[33].setVisible(true);
					jbtnsubir[13].setVisible(true);
					
					jlbl[14].setVisible(true);
					Relleno[14].setVisible(true);
					Relleno[34].setVisible(true);
					jbtnsubir[14].setVisible(true);
					
					jlbl[15].setVisible(false);
					Relleno[15].setVisible(false);
					Relleno[35].setVisible(false);
					jbtnsubir[15].setVisible(false);
					
					jlbl[16].setVisible(false);
					Relleno[16].setVisible(false);
					Relleno[36].setVisible(false);
					jbtnsubir[16].setVisible(false);
					
					jlbl[17].setVisible(false);
					Relleno[17].setVisible(false);
					Relleno[37].setVisible(false);
					jbtnsubir[17].setVisible(false);
					
					jlbl[18].setVisible(false);
					Relleno[18].setVisible(false);
					Relleno[38].setVisible(false);
					jbtnsubir[18].setVisible(false);
					
					jlbl[19].setVisible(true);
					Relleno[19].setVisible(true);
					Relleno[39].setVisible(true);
					jbtnsubir[19].setVisible(true);
					
					jlbl[20].setVisible(true);
					Relleno[20].setVisible(true);
					Relleno[40].setVisible(true);
					jbtnsubir[20].setVisible(true);
					
					jlbl[21].setVisible(true);
					Relleno[21].setVisible(true);
					Relleno[41].setVisible(true);
					jbtnsubir[21].setVisible(true);
					
					jlbl[22].setVisible(true);
					Relleno[22].setVisible(true);
					Relleno[42].setVisible(true);
					jbtnsubir[22].setVisible(true);
					
					jlbl[23].setVisible(true);
					Relleno[23].setVisible(true);
					Relleno[43].setVisible(true);
					jbtnsubir[23].setVisible(true);
					
					jlbl[24].setVisible(true);
					Relleno[24].setVisible(true);
					Relleno[44].setVisible(true);
					jbtnsubir[24].setVisible(true);
					
					jlbl[25].setVisible(true);
					Relleno[25].setVisible(true);
					Relleno[45].setVisible(true);
					jbtnsubir[25].setVisible(true);
					
					jlbl[26].setVisible(true);
					Relleno[26].setVisible(true);
					Relleno[46].setVisible(true);
					jbtnsubir[26].setVisible(true);
					
					jlbl[27].setVisible(true);
					Relleno[27].setVisible(true);
					Relleno[47].setVisible(true);
					jbtnsubir[27].setVisible(true);
					
					jlbl[28].setVisible(true);
					Relleno[28].setVisible(true);
					Relleno[48].setVisible(true);
					jbtnsubir[28].setVisible(true);
					
					jlbl[29].setVisible(true);
					Relleno[29].setVisible(true);
					Relleno[49].setVisible(true);
					jbtnsubir[29].setVisible(true);
					
					jlbl[30].setVisible(true);
					Relleno[30].setVisible(true);
					Relleno[50].setVisible(true);
					jbtnsubir[30].setVisible(true);
					
					jlbl[31].setVisible(true);
					Relleno[31].setVisible(true);
					Relleno[51].setVisible(true);
					jbtnsubir[31].setVisible(true);
					
					jlbl[32].setVisible(true);
					Relleno[32].setVisible(true);
					Relleno[52].setVisible(true);
					jbtnsubir[32].setVisible(true);
				}
				
				//erasmus mobvilidad docente
				if (rbeermodo.isSelected() == false){
					jlbl[3].setVisible(true);
					Relleno[3].setVisible(true);
					Relleno[63].setVisible(true);
					jbtnsubir[3].setVisible(true);
					
					jlbl[4].setVisible(true);
					Relleno[4].setVisible(true);
					Relleno[64].setVisible(true);
					jbtnsubir[4].setVisible(true);
					
					jlbl[5].setVisible(false);
					jtxt[5].setVisible(false);
					
					
					jlbl[6].setVisible(false);
					Relleno[6].setVisible(false);
					Relleno[66].setVisible(false);
					jbtnsubir[6].setVisible(false);
					
					jlbl[7].setVisible(false);
					Relleno[7].setVisible(false);
					Relleno[67].setVisible(false);
					jbtnsubir[7].setVisible(false);
					
					jlbl[8].setVisible(false);
					jtxt[8].setVisible(false);
					
					jlbl[9].setVisible(false);
					Relleno[9].setVisible(false);
					
					jlbl[10].setVisible(false);
					Relleno[10].setVisible(false);
				
					jlbl[11].setVisible(true);
					Relleno[11].setVisible(true);
					Relleno[71].setVisible(true);
					jbtnsubir[11].setVisible(true);
					
					jlbl[12].setVisible(false);
					Relleno[12].setVisible(false);
					Relleno[72].setVisible(false);
					jbtnsubir[12].setVisible(false);
					
					jlbl[13].setVisible(false);
					Relleno[13].setVisible(false);
					Relleno[33].setVisible(false);
					jbtnsubir[13].setVisible(false);
					
					jlbl[14].setVisible(true);
					Relleno[14].setVisible(true);
					Relleno[34].setVisible(true);
					jbtnsubir[14].setVisible(true);
					
					jlbl[15].setVisible(false);
					Relleno[15].setVisible(false);
					Relleno[35].setVisible(false);
					jbtnsubir[15].setVisible(false);
					
					jlbl[16].setVisible(true);
					Relleno[16].setVisible(true);
					Relleno[36].setVisible(true);
					jbtnsubir[16].setVisible(true);
					
					jlbl[17].setVisible(true);
					Relleno[17].setVisible(true);
					Relleno[37].setVisible(true);
					jbtnsubir[17].setVisible(true);
					
					jlbl[18].setVisible(false);
					Relleno[18].setVisible(false);
					Relleno[38].setVisible(false);
					jbtnsubir[18].setVisible(false);
					
					jlbl[19].setVisible(true);
					Relleno[19].setVisible(true);
					Relleno[39].setVisible(true);
					jbtnsubir[19].setVisible(true);
					
					jlbl[20].setVisible(true);
					Relleno[20].setVisible(true);
					Relleno[40].setVisible(true);
					jbtnsubir[20].setVisible(true);
					
					jlbl[21].setVisible(true);
					Relleno[21].setVisible(true);
					Relleno[41].setVisible(true);
					jbtnsubir[21].setVisible(true);
					
					jlbl[22].setVisible(true);
					Relleno[22].setVisible(true);
					Relleno[42].setVisible(true);
					jbtnsubir[22].setVisible(true);
					
					jlbl[23].setVisible(true);
					Relleno[23].setVisible(true);
					Relleno[43].setVisible(true);
					jbtnsubir[23].setVisible(true);
					
					jlbl[24].setVisible(true);
					Relleno[24].setVisible(true);
					Relleno[44].setVisible(true);
					jbtnsubir[24].setVisible(true);
					
					jlbl[25].setVisible(true);
					Relleno[25].setVisible(true);
					Relleno[45].setVisible(true);
					jbtnsubir[25].setVisible(true);
					
					jlbl[26].setVisible(true);
					Relleno[26].setVisible(true);
					Relleno[46].setVisible(true);
					jbtnsubir[26].setVisible(true);
					
					jlbl[27].setVisible(true);
					Relleno[27].setVisible(true);
					Relleno[47].setVisible(true);
					jbtnsubir[27].setVisible(true);
					
					jlbl[28].setVisible(true);
					Relleno[28].setVisible(true);
					Relleno[48].setVisible(true);
					jbtnsubir[28].setVisible(true);
					
					jlbl[29].setVisible(false);
					Relleno[29].setVisible(false);
					Relleno[49].setVisible(false);
					jbtnsubir[29].setVisible(false);
					
					jlbl[30].setVisible(true);
					Relleno[30].setVisible(true);
					Relleno[50].setVisible(true);
					jbtnsubir[30].setVisible(true);
					
					jlbl[31].setVisible(false);
					Relleno[31].setVisible(false);
					Relleno[51].setVisible(false);
					jbtnsubir[31].setVisible(false);
					
					jlbl[32].setVisible(false);
					Relleno[32].setVisible(false);
					Relleno[52].setVisible(false);
					jbtnsubir[32].setVisible(false);
				}
				
				//erasmus movilidad no docente
				if (rbeermonodo.isSelected() == false){
					jlbl[3].setVisible(true);
					Relleno[3].setVisible(true);
					Relleno[63].setVisible(true);
					jbtnsubir[3].setVisible(true);
					
					jlbl[4].setVisible(true);
					Relleno[4].setVisible(true);
					Relleno[64].setVisible(true);
					jbtnsubir[4].setVisible(true);
					
					jlbl[5].setVisible(false);
					jtxt[5].setVisible(false);
					
					
					jlbl[6].setVisible(false);
					Relleno[6].setVisible(false);
					Relleno[66].setVisible(false);
					jbtnsubir[6].setVisible(false);
					
					jlbl[7].setVisible(false);
					Relleno[7].setVisible(false);
					Relleno[67].setVisible(false);
					jbtnsubir[7].setVisible(false);
					
					jlbl[8].setVisible(false);
					jtxt[8].setVisible(false);
					
					jlbl[9].setVisible(false);
					Relleno[9].setVisible(false);
					
					jlbl[10].setVisible(false);
					Relleno[10].setVisible(false);
				
					jlbl[11].setVisible(true);
					Relleno[11].setVisible(true);
					Relleno[71].setVisible(true);
					jbtnsubir[11].setVisible(true);
					
					jlbl[12].setVisible(false);
					Relleno[12].setVisible(false);
					Relleno[72].setVisible(false);
					jbtnsubir[12].setVisible(false);
					
					jlbl[13].setVisible(false);
					Relleno[13].setVisible(false);
					Relleno[33].setVisible(false);
					jbtnsubir[13].setVisible(false);
					
					jlbl[14].setVisible(true);
					Relleno[14].setVisible(true);
					Relleno[34].setVisible(true);
					jbtnsubir[14].setVisible(true);
					
					jlbl[15].setVisible(false);
					Relleno[15].setVisible(false);
					Relleno[35].setVisible(false);
					jbtnsubir[15].setVisible(false);
					
					jlbl[16].setVisible(false);
					Relleno[16].setVisible(false);
					Relleno[36].setVisible(false);
					jbtnsubir[16].setVisible(false);
					
					jlbl[17].setVisible(true);
					Relleno[17].setVisible(true);
					Relleno[37].setVisible(true);
					jbtnsubir[17].setVisible(true);
					
					jlbl[18].setVisible(false);
					Relleno[18].setVisible(false);
					Relleno[38].setVisible(false);
					jbtnsubir[18].setVisible(false);
					
					jlbl[19].setVisible(true);
					Relleno[19].setVisible(true);
					Relleno[39].setVisible(true);
					jbtnsubir[19].setVisible(true);
					
					jlbl[20].setVisible(true);
					Relleno[20].setVisible(true);
					Relleno[40].setVisible(true);
					jbtnsubir[20].setVisible(true);
					
					jlbl[21].setVisible(true);
					Relleno[21].setVisible(true);
					Relleno[41].setVisible(true);
					jbtnsubir[21].setVisible(true);
					
					jlbl[22].setVisible(true);
					Relleno[22].setVisible(true);
					Relleno[42].setVisible(true);
					jbtnsubir[22].setVisible(true);
					
					jlbl[23].setVisible(true);
					Relleno[23].setVisible(true);
					Relleno[43].setVisible(true);
					jbtnsubir[23].setVisible(true);
					
					jlbl[24].setVisible(true);
					Relleno[24].setVisible(true);
					Relleno[44].setVisible(true);
					jbtnsubir[24].setVisible(true);
					
					jlbl[25].setVisible(true);
					Relleno[25].setVisible(true);
					Relleno[45].setVisible(true);
					jbtnsubir[25].setVisible(true);
					
					jlbl[26].setVisible(true);
					Relleno[26].setVisible(true);
					Relleno[46].setVisible(true);
					jbtnsubir[26].setVisible(true);
					
					jlbl[27].setVisible(true);
					Relleno[27].setVisible(true);
					Relleno[47].setVisible(true);
					jbtnsubir[27].setVisible(true);
					
					jlbl[28].setVisible(true);
					Relleno[28].setVisible(true);
					Relleno[48].setVisible(true);
					jbtnsubir[28].setVisible(true);
					
					jlbl[29].setVisible(false);
					Relleno[29].setVisible(false);
					Relleno[49].setVisible(false);
					jbtnsubir[29].setVisible(false);
					
					jlbl[30].setVisible(true);
					Relleno[30].setVisible(true);
					Relleno[50].setVisible(true);
					jbtnsubir[30].setVisible(true);
					
					jlbl[31].setVisible(true);
					Relleno[31].setVisible(true);
					Relleno[51].setVisible(true);
					jbtnsubir[31].setVisible(true);
					
					jlbl[32].setVisible(false);
					Relleno[32].setVisible(false);
					Relleno[52].setVisible(false);
					jbtnsubir[32].setVisible(false);
				}
				if (rbeerco.isSelected() == false){
					
				}
				if (rbemi.isSelected() == false){
					
				}
				if (rbecofc.isSelected() == false){
					
				}
				if (rotmo.isSelected() == false){
					
				}
				
			}
			
		};
		
		/**
		 * se crean los action listener para los botones subir
		 * se sube  el archivo
		 */
		ActionListener acsubir = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		/**
		 * se crean los action el check becario
		 */
		ActionListener acbecario = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Relleno[9].isSelected()){
					jpbecario.setVisible(true);
				}else{
					jpbecario.setVisible(false);
				}
			}
			
		};
		
		/**
		 * se crean los action para los botnoes aceptar y limpiar campos
		 */
		ActionListener acfinal = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		panel.add(jpsoloapee,gbc);
		panel.add(jpsolalumnos,gbc);
		jpsolalumnos.add(jpbecario,gbc);
		panel.add(jpinfinter,gbc);
		panel.add(jpinffinal,gbc);
	  /**
		 * Creamos los cuatro botones para este panel 
		 */
		//primeros dos botones del panel
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		jpbotones.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		jpbotones.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
		
		
		total.setVisible(true);
		this.setViewportView(total);
		jpsolalumnos.setVisible(true);
		jpbecario.setVisible(false);
		jpinfinter.setVisible(true);
		jpinffinal.setVisible(true);
		
		total.add(jpradio,BorderLayout.PAGE_START);
		total.add(uspanel,BorderLayout.NORTH);
		total.add(panel,BorderLayout.CENTER);
		total.add(jpbotones,BorderLayout.SOUTH);
		uspanel.setVisible(true);
		panel.setVisible(true);
		jpsoloapee.setVisible(true);
		
		/**
		 * añadimos los actionlistener a los objetos
		 */
		Relleno[9].addActionListener(acbecario);
		rbeeres.addActionListener(acradio);
		rbeerpr.addActionListener(acradio);
		rbeermodo.addActionListener(acradio);
		rbeermonodo.addActionListener(acradio);
		rbeerco.addActionListener(acradio);
		rbemi.addActionListener(acradio);
		rbecofc.addActionListener(acradio);
		rotmo.addActionListener(acradio);
		
		
	}
	
}
