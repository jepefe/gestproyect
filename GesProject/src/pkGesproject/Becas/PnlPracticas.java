/** Esta clase se encarga de realizar el alta todos los tipos
 * de becas. 
 * 
 * @author FÃ©lix Perona G
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
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
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
	
	String ruta,extension;
	int tam;
	String rutfich = "";
	
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
	JRadioButton rbnada = new JRadioButton();
	ButtonGroup group = new ButtonGroup();
	
	ConexionFTP subfich = new ConexionFTP();
	
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
		rbnada.setMnemonic('i');
		rbnada.setSelected(true);
		
		/**
		 * se aÃ±aden los radio button a un grupo 
		 */
		group.add(rbnada);
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
		   rec.idioma[rec.eleidioma][208],rec.idioma[rec.eleidioma][165],rec.idioma[rec.eleidioma][166],rec.idioma[rec.eleidioma][167], 
		   rec.idioma[rec.eleidioma][198], rec.idioma[rec.eleidioma][199],rec.idioma[rec.eleidioma][200],rec.idioma[rec.eleidioma][201],
		   rec.idioma[rec.eleidioma][202],rec.idioma[rec.eleidioma][203],rec.idioma[rec.eleidioma][204],rec.idioma[rec.eleidioma][205],
		   rec.idioma[rec.eleidioma][206],rec.idioma[rec.eleidioma][207],rec.idioma[rec.eleidioma][209],rec.idioma[rec.eleidioma][210],
		   rec.idioma[rec.eleidioma][211],rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][212],rec.idioma[rec.eleidioma][213],
		   rec.idioma[rec.eleidioma][217],
		   };
		int[] fieldWidths = {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		jtxtra = new JLabel[8];
		
		jpradio.add(rbnada);
		jpradio.add(jtxtra[4]=new JLabel(rec.idioma[rec.eleidioma][233]));
		jpradio.add(rbeeres);
		jpradio.add(jtxtra[0]=new JLabel(rec.idioma[rec.eleidioma][152]));
	    jpradio.add(rbeerpr);
	    jpradio.add(jtxtra[1]=new JLabel(rec.idioma[rec.eleidioma][150]));
	    jpradio.add(rbeermodo);
	    jpradio.add(jtxtra[2]=new JLabel(rec.idioma[rec.eleidioma][153]));
	    jpradio.add(rbeermonodo);
	    jpradio.add(jtxtra[3]=new JLabel(rec.idioma[rec.eleidioma][154]));
	    /*jpradio.add(rbeerco);
	    jpradio.add(jtxtra[4]=new JLabel(rec.idioma[rec.eleidioma][158]));
	    jpradio.add(rbemi);
	    jpradio.add(jtxtra[5]=new JLabel(rec.idioma[rec.eleidioma][155]));
	    jpradio.add(rbecofc);
	    jpradio.add(jtxtra[6]=new JLabel(rec.idioma[rec.eleidioma][156]));
	    jpradio.add(rotmo);
	    jpradio.add(jtxtra[7]=new JLabel(rec.idioma[rec.eleidioma][157]));*/
		
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
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
	   			break;
		   case (4)://documento de solicitud
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+60] = new JCheckBox(),gbc);
		   		Relleno[i+60].setEnabled(false);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (5)://registrar nota examen idioma
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jlbl[i].setVisible(false);
		   		jtxt[i].setVisible(false);
			   	break;
		   case (6)://entrega D.N.I
			   //gbc.gridwidth = 5;
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+60] = new JCheckBox(),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
	   			//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
			   	break;
		   case (7)://entrega listado preferencias universidad
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i+60] = new JCheckBox(),gbc);
		   		Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (8)://nota proceso de seleccion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(jtxt[i] = new JTextField(2),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jlbl[i].setVisible(false);
		   		jtxt[i].setVisible(false);
		   		break;
		   case (9)://es becario
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			
			   	break;
		   case (10)://recibe financiaion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
		   		break;
		   case (11)://becario carta aceptacion beca
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+60] = new JCheckBox(),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (12)://becario entrega DNI
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+60] = new JCheckBox(),gbc);
	   			Relleno[i+60].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+60].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (13)://becario entrega fotoopia libreeta bancria
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (14)://becario copia tarjeta sanitaria europea
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (15)://becario entrega expediente academico
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (16)://becario datos universidad
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (17)://becario convenio
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (18)://becario acuerdo de formacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (19)://becario contratacion seguro
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (20)://becario justificacion
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (21)://becario pagos realizados al alumno
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (22)://informe intermedio al oapee
			   jpinfinter.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinfinter.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinfinter.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinfinter.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (23)://informe cualitatvo
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (24)://informe cumplimiento de la CUE
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (25)://excel
			   jpinffinal.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpinffinal.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpinffinal.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpinffinal.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (26)://entrega curriculum
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (27)://carta de motivacion
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (28)://listado de empresas
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (29)://expediente academico
			   jpsolalumnos.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpsolalumnos.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpsolalumnos.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpsolalumnos.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (30)://becario carta aceptacion empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (31)://datos empresa
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
		   		break;
		   case (32)://acuerdo de practicas
			   jpbecario.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		jpbecario.add(Relleno[i] = new JCheckBox(),gbc);
	   			//gbc.gridwidth = GridBagConstraints.RELATIVE;
	   			jpbecario.add(Relleno[i+20] = new JCheckBox(),gbc);
	   			Relleno[i+20].setEnabled(false);
	   			gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jpbecario.add(jbtnsubir[i]=new JButton(rec.idioma[rec.eleidioma][195]),gbc);
		   		//gbc.gridwidth = GridBagConstraints.REMAINDER;
	   			jlbl[i].setVisible(false);
	   			Relleno[i].setVisible(false);
	   			Relleno[i+20].setVisible(false);
	   			jbtnsubir[i].setVisible(false);
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
				panel.setVisible(true);
				//erasmus estudios
				if (rbeeres.isSelected() == true){
					System.out.println("action era estudios");
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
					
					panel.repaint();
				}
				//erasmus practicas
				if (rbeerpr.isSelected() == true){
					System.out.println("action era practicas");
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
					
					panel.repaint();
				}
				
				//erasmus mobvilidad docente
				if (rbeermodo.isSelected() == true){
					System.out.println("action era movilidad docente");
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
					
					jlbl[9].setVisible(true);
					Relleno[9].setVisible(true);
					
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
					
					panel.repaint();
				}
				
				//erasmus movilidad no docente
				
				if (rbeermonodo.isSelected() == true){
					System.out.println("action era movilidad no docente");
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
					
					jlbl[9].setVisible(true);
					Relleno[9].setVisible(true);
					
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
					
					panel.repaint();
				}
				if (rbeerco.isSelected() == true){
					repaint();
				}
				if (rbemi.isSelected() == true){
					repaint();
				}
				if (rbecofc.isSelected() == true){
					repaint();
				}
				if (rotmo.isSelected() == true){
					repaint();
				}
				if (rbnada.isSelected() == true){
					panel.setVisible(false);
				}
				
				
			}
			
		};
		
		/**
		 * se crean los action listener para los botones subir
		 * se sube  el archivo
		 */
		ActionListener acsubir = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, PNG, PDF", "jpg", "jpeg", "gif", "png", "pdf");//Añadimos el filtro para que nos muestre sólo las extensiones que queremos
				filechooser.setFileFilter(filter);
				int returnVal = filechooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					/*
					 * sacamos la ruta del archivo y su extension
					 */
					ruta = file.getPath();
					System.out.println(ruta);
					tam = ruta.length();
					extension = ruta.substring(tam-3,tam);
					if(extension.equalsIgnoreCase ("jpg") || extension.equalsIgnoreCase ("jpeg") || extension.equalsIgnoreCase ("gif") || extension.equalsIgnoreCase ("png")|| extension.equalsIgnoreCase ("pdf")){
						rutfich = ruta;
					}
					
			    }
				
				if (e.getActionCommand().equals("3")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[63].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("4")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[64].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("6")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[66].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("7")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[67].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("11")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[71].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("12")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[72].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("13")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[33].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("14")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[34].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("15")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[35].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("16")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[36].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("17")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[37].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("18")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[38].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("19")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[39].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("20")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[40].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("21")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[41].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("22")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[42].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("23")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[43].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("24")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[44].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("25")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[45].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("26")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[46].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("27")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[47].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("28")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[48].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("29")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[49].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("30")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[50].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("31")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[51].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if (e.getActionCommand().equals("32")){
					try {
						subfich.connectar();
						subfich.SubirFichero(rutfich, Integer.toString(recursos.getIdusuario()), null, "becas", 0);
						Relleno[52].setSelected(true);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
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
					panel.repaint();
				}else{
					jpbecario.setVisible(false);
					panel.repaint();
				}
			}
			
		};
		
		/**
		 * se crean los action para los botnoes aceptar y limpiar campos
		 */
		ActionListener acfinal = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e4) {
				// TODO Auto-generated method stub
				if (e4.getActionCommand().equals("aceptar")){
					
				}
				if (e4.getActionCommand().equals("limpiar")){
					Relleno[3].setSelected(false);
					Relleno[63].setSelected(false);

					Relleno[4].setSelected(false);
					Relleno[64].setSelected(false);

					jtxt[5].setText("");
					
					Relleno[6].setSelected(false);
					Relleno[66].setSelected(false);
					
					Relleno[7].setSelected(false);
					Relleno[67].setSelected(false);
					
					jtxt[8].setText("");
					
					Relleno[9].setSelected(false);
					
					Relleno[10].setSelected(false);
				
					Relleno[11].setSelected(false);
					Relleno[71].setSelected(false);
					
					Relleno[12].setSelected(false);
					Relleno[72].setSelected(false);
					
					Relleno[13].setSelected(false);
					Relleno[33].setSelected(false);
					
					Relleno[14].setSelected(false);
					Relleno[34].setSelected(false);
					
					Relleno[15].setSelected(false);
					Relleno[35].setSelected(false);
					
					Relleno[16].setSelected(false);
					Relleno[36].setSelected(false);
					
					Relleno[17].setSelected(false);
					Relleno[37].setSelected(false);
					
					Relleno[18].setSelected(false);
					Relleno[38].setSelected(false);
					
					Relleno[19].setSelected(false);
					Relleno[39].setSelected(false);
					
					Relleno[20].setSelected(false);
					Relleno[40].setSelected(false);
					
					Relleno[21].setSelected(false);
					Relleno[41].setSelected(false);
					
					Relleno[22].setSelected(false);
					Relleno[42].setSelected(false);
					
					Relleno[23].setSelected(false);
					Relleno[43].setSelected(false);
					
					Relleno[24].setSelected(false);
					Relleno[44].setSelected(false);
					
					Relleno[25].setSelected(false);
					Relleno[45].setSelected(false);
					
					Relleno[26].setSelected(false);
					Relleno[46].setSelected(false);
					
					Relleno[27].setSelected(false);
					Relleno[47].setSelected(false);
					
					Relleno[28].setSelected(false);
					Relleno[48].setSelected(false);
					
					Relleno[29].setSelected(false);
					Relleno[49].setSelected(false);
					
					Relleno[30].setSelected(false);
					Relleno[50].setSelected(false);
					
					Relleno[31].setSelected(false);
					Relleno[51].setSelected(false);
					
					Relleno[32].setSelected(false);
					Relleno[52].setSelected(false);
					
					jpbecario.setVisible(false);
					jpbecario.repaint();
					panel.repaint();
				}
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
		jpsoloapee.setVisible(true);
		
		total.add(jpradio,BorderLayout.PAGE_START);
		total.add(uspanel,BorderLayout.NORTH);
		total.add(panel,BorderLayout.CENTER);
		total.add(jpbotones,BorderLayout.SOUTH);
		uspanel.setVisible(true);
		panel.setVisible(false);
		
		
		/**
		 * aÃ±adimos los actionlistener a los objetos
		 */
		jbtncancelar.setActionCommand("limpiar");
		jbtncancelar.addActionListener(acfinal);
		jbtnaceptar.addActionListener(acfinal);
		jbtnaceptar.setActionCommand("aceptar");
		
		Relleno[9].addActionListener(acbecario);
		rbeeres.addActionListener(acradio);
		rbeerpr.addActionListener(acradio);
		rbeermodo.addActionListener(acradio);
		rbeermonodo.addActionListener(acradio);
		rbeerco.addActionListener(acradio);
		rbemi.addActionListener(acradio);
		rbecofc.addActionListener(acradio);
		rotmo.addActionListener(acradio);
		rbnada.addActionListener(acradio);
		
		jbtnsubir[3].setActionCommand("3");
		jbtnsubir[3].addActionListener(acsubir);
		
		jbtnsubir[4].setActionCommand("4");
		jbtnsubir[4].addActionListener(acsubir);
		
		jbtnsubir[6].setActionCommand("6");
		jbtnsubir[6].addActionListener(acsubir);
		
		jbtnsubir[7].setActionCommand("7");
		jbtnsubir[7].addActionListener(acsubir);
		
		jbtnsubir[11].setActionCommand("11");
		jbtnsubir[11].addActionListener(acsubir);
		
		jbtnsubir[12].setActionCommand("12");
		jbtnsubir[12].addActionListener(acsubir);
		
		jbtnsubir[13].setActionCommand("13");
		jbtnsubir[13].addActionListener(acsubir);
		
		jbtnsubir[14].setActionCommand("14");
		jbtnsubir[14].addActionListener(acsubir);
		
		jbtnsubir[15].setActionCommand("15");
		jbtnsubir[15].addActionListener(acsubir);
		
		jbtnsubir[16].setActionCommand("16");
		jbtnsubir[16].addActionListener(acsubir);
		
		jbtnsubir[17].setActionCommand("17");
		jbtnsubir[17].addActionListener(acsubir);
		
		jbtnsubir[18].setActionCommand("18");
		jbtnsubir[18].addActionListener(acsubir);
		
		jbtnsubir[19].setActionCommand("19");
		jbtnsubir[19].addActionListener(acsubir);
		
		jbtnsubir[20].setActionCommand("20");
		jbtnsubir[20].addActionListener(acsubir);
		
		jbtnsubir[21].setActionCommand("21");
		jbtnsubir[21].addActionListener(acsubir);
		
		jbtnsubir[22].setActionCommand("22");
		jbtnsubir[22].addActionListener(acsubir);
		
		jbtnsubir[23].setActionCommand("23");
		jbtnsubir[23].addActionListener(acsubir);
		
		jbtnsubir[24].setActionCommand("24");
		jbtnsubir[24].addActionListener(acsubir);
		
		jbtnsubir[25].setActionCommand("25");
		jbtnsubir[25].addActionListener(acsubir);
		
		jbtnsubir[26].setActionCommand("26");
		jbtnsubir[26].addActionListener(acsubir);
		
		jbtnsubir[27].setActionCommand("27");
		jbtnsubir[27].addActionListener(acsubir);
		
		jbtnsubir[28].setActionCommand("28");
		jbtnsubir[28].addActionListener(acsubir);
		
		jbtnsubir[29].setActionCommand("29");
		jbtnsubir[29].addActionListener(acsubir);
		
		jbtnsubir[30].setActionCommand("30");
		jbtnsubir[30].addActionListener(acsubir);
		
		jbtnsubir[31].setActionCommand("31");
		jbtnsubir[31].addActionListener(acsubir);
		
		jbtnsubir[32].setActionCommand("32");
		jbtnsubir[32].addActionListener(acsubir);
		
		
	}
	
}