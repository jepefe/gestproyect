package pkGesproject;import java.awt.Container;import java.awt.Dimension;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Insets;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.io.File;import java.sql.ResultSet;import java.sql.SQLException;import javax.swing.JButton;import javax.swing.JCheckBox;import javax.swing.JComboBox;import javax.swing.JFileChooser;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JOptionPane;import javax.swing.JPanel;import javax.swing.JScrollPane;import javax.swing.JTextField;public class pnlAlta_staff extends JScrollPane{	GesIdioma rec = GesIdioma.obtener_instancia();	JTextField[] jtxt;	JLabel[] jlbl;	JButton jbtnaceptar, jbtncancelar, jbtnexaminar;	JPanel panel = new JPanel();	JFrame aviso = new JFrame();	JComboBox cbPais = new JComboBox();	JCheckBox cbxRepresentante = new JCheckBox();		public pnlAlta_staff (){		RsGesproject recursos = RsGesproject.Obtener_Instancia();				panel.setLayout(new GridBagLayout());		  		String[] fieldNames = {		   rec.idioma[rec.eleidioma][2],		   rec.idioma[rec.eleidioma][44],rec.idioma[rec.eleidioma][45],rec.idioma[rec.eleidioma][46],		   rec.idioma[rec.eleidioma][48],rec.idioma[rec.eleidioma][6],		   rec.idioma[rec.eleidioma][7],rec.idioma[rec.eleidioma][49],		   rec.idioma[rec.eleidioma][9],rec.idioma[rec.eleidioma][10],		   rec.idioma[rec.eleidioma][51],rec.idioma[rec.eleidioma][52],rec.idioma[rec.eleidioma][42]		};		int[] fieldWidths = {11,9,20,20,10,20,20,20,10,10,20,10,10,10,10};		jtxt = new JTextField[fieldNames.length];		jlbl = new JLabel[fieldNames.length];				GridBagConstraints gbc = new GridBagConstraints();				gbc.gridwidth = GridBagConstraints.REMAINDER;		gbc.anchor = GridBagConstraints.CENTER;		gbc.insets = new Insets(20,0,15,0);		//panel.add(new JLabel("Alta Partner"),gbc);				gbc.anchor = GridBagConstraints.WEST;		gbc.insets = new Insets(5,10,5,5);				/*		 * Con el bucle for vamos creando tantos labels y textfields como 		 * nombres de campos hayamos metido en fieldNames.		 */		for(int i=0;i<fieldNames.length;++i) {									if(i==0){				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][43]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jlbl[i]=new JLabel("10"),gbc);			}						/*			 * Creamos un checkbox para decir si el usuario es representante o no			 */						if(i==3){				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][53]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(cbxRepresentante,gbc);							}						/*			 *Creamos un combobox para eleguir el pa�s 			 */						if (i==6){										cbPais.addItem("Espa�a");					cbPais.addItem("Alemania");					cbPais.addItem("Inglaterra");					cbPais.addItem("Francia");					cbPais.addItem("Ucrania");				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel("Pa�s:"),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				cbPais.setPreferredSize(new Dimension(233,30));				panel.add(cbPais,gbc);															}						if(i==8){				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][4]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);								/*				 * Creamos un Keylistener para admitir la entrada de s�lo n�meros				 */								jtxt[i].addKeyListener(new KeyAdapter()				{				   public void keyTyped(KeyEvent e)				   {				      char caracter = e.getKeyChar();				      if(((caracter < '0') ||				         (caracter > '9')) &&				         (caracter != KeyEvent.VK_BACK_SPACE))				      {				         e.consume();  				      }				   }				});							}						/*			 * Creamos un campo de texto y un bot�n para guardar la ruta de la imagen elegida			 */			if(i==10){				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][50]),gbc);				final JTextField jtxtFoto;				panel.add(jtxtFoto=new JTextField(fieldWidths[i]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jbtnexaminar=new JButton(rec.idioma[rec.eleidioma][54]),gbc);				jbtnexaminar.addActionListener(new ActionListener(){					@Override					public void actionPerformed(ActionEvent arg0) {						// TODO Auto-generated method stub						JFileChooser filechooser = new JFileChooser();						int returnVal = filechooser.showSaveDialog(null);											if (returnVal == JFileChooser.APPROVE_OPTION) {						File file = filechooser.getSelectedFile();						jtxtFoto.setText(file.getPath());					    } 												}									});							}								   gbc.gridwidth = GridBagConstraints.RELATIVE;		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);		   gbc.gridwidth = GridBagConstraints.REMAINDER;		   panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);		}				/*		 * Creamos los dos botones para este panel 		 */		gbc.anchor = GridBagConstraints.EAST;		gbc.insets = new Insets(30,10,5,5);		gbc.gridwidth = GridBagConstraints.RELATIVE;		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][0]),gbc);		gbc.anchor = GridBagConstraints.WEST;		gbc.gridwidth = GridBagConstraints.REMAINDER;		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);		//				ActionListener accion = new ActionListener(){			@Override			public void actionPerformed(ActionEvent e) {				// TODO Auto-generated method stub				if(e.getActionCommand().equals("aceptar")){					ConexionDb conexdb = new ConexionDb();					ResultSet rs;					conexdb.Conectardb();					conexdb.executeUpdate("INSERT INTO PARTNER (nombre, sector, direccion, codpostal, telefono) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"')");					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][23]);					conexdb.cerrarConexion();				}			}					};		jbtnaceptar.setActionCommand("aceptar");		jbtnaceptar.addActionListener(accion);		jbtncancelar.setActionCommand("cancelar");						panel.setOpaque(true);		this.setViewportView(panel);			}}		/*	ActionListener alta = new ActionListener(){		@Override		public void actionPerformed(ActionEvent e) {			// TODO Auto-generated method stub			if(e.getActionCommand().equals("crear")){				ConexionDb conexdb = new ConexionDb();				ResultSet rs;				conexdb.Conectardb();				conexdb.executeUpdate("INSERT INTO STAFF (,dni,nombre, categoria, representante, f_nac, pais,region,ciudad,direccion,codpostal, telefono) VALUES ('"+ jtxt1.getText()+"','"+jtxt2.getText()+"','"+jtxt3.getText()+"','"+jtxt4.getText()+"','"+jtxt5.getText()+"')");				JFrame aviso = new JFrame();				JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][23]);				conexdb.cerrarConexion();			}		}			};		jbtnCrear.setActionCommand("crear");	jbtnCrear.addActionListener(alta);	jbtnSalir.setActionCommand("Salir");			panel.setOpaque(true);	this.setViewportView(panel);}}*/