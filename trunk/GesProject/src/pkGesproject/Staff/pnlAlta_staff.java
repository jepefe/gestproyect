package pkGesproject.Staff;import java.awt.Color;import java.awt.Container;import java.awt.Dimension;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Insets;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.io.File;import java.sql.ResultSet;import java.sql.SQLException;import javax.swing.JButton;import javax.swing.JCheckBox;import javax.swing.JComboBox;import javax.swing.JFileChooser;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JOptionPane;import javax.swing.JPanel;import javax.swing.JPasswordField;import javax.swing.JRadioButton;import javax.swing.JScrollPane;import javax.swing.JTextField;import com.toedter.calendar.JDateChooser;import pkGesproject.ConexionDb;import pkGesproject.GesIdioma;import pkGesproject.RsGesproject;public class pnlAlta_staff extends JScrollPane{	GesIdioma rec = GesIdioma.obtener_instancia();	JTextField[] jtxt;	JLabel[] jlbl;	JButton jbtnaceptar, jbtncancelar, jbtnexaminar;	JPanel panel = new JPanel();	JFrame aviso = new JFrame();	JComboBox cbPais =  new JComboBox();		JComboBox cbprov = new JComboBox ();	JCheckBox cbxRepresentante = new JCheckBox();	JPasswordField jpas1,jpas2 = new JPasswordField() ;	JCheckBox jrad = new JCheckBox();	ResultSet rs;	ConexionDb conexion = new ConexionDb();	String pais[] = new String[240];	int idprovpais[]= new int[4292];	String prov[] = new String[4292];	int indexpais;	JDateChooser jdcNacimiento;			public pnlAlta_staff (){		RsGesproject recursos = RsGesproject.Obtener_Instancia();				panel.setLayout(new GridBagLayout());		  		String[] fieldNames = {		   rec.idioma[rec.eleidioma][3],		   rec.idioma[rec.eleidioma][28],		   rec.idioma[rec.eleidioma][47],rec.idioma[rec.eleidioma][48],		   rec.idioma[rec.eleidioma][7], rec.idioma[rec.eleidioma][8],		   rec.idioma[rec.eleidioma][5],rec.idioma[rec.eleidioma][52],		   rec.idioma[rec.eleidioma][58],rec.idioma[rec.eleidioma][11]		};		int[] fieldWidths = {15,15,7,15,15,15,5,10,20,10,10};		jtxt = new JTextField[fieldNames.length];		jlbl = new JLabel[fieldNames.length];		jdcNacimiento = new JDateChooser();		jdcNacimiento.setDateFormatString("dd/MM/yyyy");				GridBagConstraints gbc = new GridBagConstraints();				gbc.gridwidth = GridBagConstraints.REMAINDER;		gbc.anchor = GridBagConstraints.CENTER;		gbc.insets = new Insets(20,0,15,0);		//panel.add(new JLabel("Alta Partner"),gbc);				gbc.anchor = GridBagConstraints.WEST;		gbc.insets = new Insets(5,10,5,5);				/*		 * Con el bucle for vamos creando tantos labels y textfields como 		 * nombres de campos hayamos metido en fieldNames.		 */	    for(int i=0;i<fieldNames.length;++i) {						/*			 * Creamos una consulta para cojer todos los paises.			 */    	if (i == 2){     		gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][45]),gbc);    		gbc.gridwidth = GridBagConstraints.REMAINDER;     		panel.add(jdcNacimiento,gbc);     		    	    	}					if (i==3){			conexion.Conectardb();			rs = conexion.ConsultaSQL("SELECT * FROM PAIS");		    	try {					while(rs.next()){								cbPais.addItem(rs.getString(2));					}				} catch (SQLException e) {					// TODO Auto-generated catch block					e.printStackTrace();				}	    	conexion.cerrarConexion();	    	conexion.Conectardb();	    					gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][46]),gbc);			gbc.anchor = GridBagConstraints.WEST;			gbc.gridwidth = GridBagConstraints.REMAINDER;			cbPais.setPreferredSize(new Dimension(233,30));						panel.add(cbPais,gbc);						indexpais = 1 + cbPais.getSelectedIndex();			System.out.println(indexpais);	/*	 * Falla en el SCROLL del JComboBox	 * 	 */			rs = conexion.ConsultaSQL("SELECT * FROM PROVINCIAS");			    	try {			    	int j = 0 ;						while(rs.next()){									prov[j]=(rs.getString(2));								idprovpais[j]= (rs.getInt(3));								j++;						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}			    	conexion.cerrarConexion();			    	gbc.gridwidth = GridBagConstraints.RELATIVE;					panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][46]),gbc);					gbc.anchor = GridBagConstraints.WEST;					gbc.gridwidth = GridBagConstraints.REMAINDER;					cbprov.setPreferredSize(new Dimension(233,30));					panel.add(cbprov,gbc);			    	for(int k = 0 ; k<240 ; k++){			    		for(int j=0 ; j <4292;j++){				    		if((idprovpais[j]) == indexpais){				    			cbprov.addItem(prov[j]);				    			}			    		}			    	}			    }														/*			if(i==7){				//  Creamos un Keylistener para admitir la entrada de s�lo n�meros				jtxt[7].addKeyListener(new KeyAdapter(){				   public void keyTyped(KeyEvent e){				      char caracter = e.getKeyChar();				      if(((caracter < '0') ||(caracter > '9')) &&				         (caracter != KeyEvent.VK_BACK_SPACE)) {				         e.consume();  				      }				   }				});			}*/					 if( ( i != 8) && ( i !=10) && (1 != 4) ){ 			 gbc.gridwidth = GridBagConstraints.RELATIVE;	        panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 	        gbc.gridwidth = GridBagConstraints.REMAINDER;	        panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);		 }		 			if(i==8){				/*				 * Creacion Del JRadioButton para seleccion de Representante				 */				gbc.gridwidth = GridBagConstraints.RELATIVE;			    panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jrad = new JCheckBox(),gbc);				/*				 * Creamos el label "FOTO" y su boton Examinar				 */				final JTextField jtxtFoto;				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][49]),gbc);				panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jbtnexaminar=new JButton(rec.idioma[rec.eleidioma][53]),gbc);								jbtnexaminar.addActionListener(new ActionListener(){						public void actionPerformed(ActionEvent arg0) {							// TODO Auto-generated method stub							JFileChooser filechooser = new JFileChooser();							int returnVal = filechooser.showSaveDialog(null);								if (returnVal == JFileChooser.APPROVE_OPTION) {									File file = filechooser.getSelectedFile();									jtxt[8].setText(file.getPath());							    } 									}					});				}			/*			 * Jlabel Contrase�a con un JPassword			 */			if(i == 10){  				gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas1 = new JPasswordField(fieldWidths[i]),gbc);		       // Verificar Contrase�a    		     /*   gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas2 = new JPasswordField(fieldWidths[i]),gbc);		        KeyListener accion = new KeyListener(){	    			@Override	    			public void keyPressed(KeyEvent arg0) {	    					    				if (jpas1.equals(jpas2)){	 				        jpas2.setBackground(Color.RED);	 				        }else{	 				        jpas2.setBackground(Color.GREEN);		 				        }					    				}	    				    			@Override	    			public void keyReleased(KeyEvent act) {				       	    			}					@Override					public void keyTyped(KeyEvent arg0) {						// TODO Auto-generated method stub											}		        };		        jpas1.addKeyListener(accion);  */	        }    }				/*		 * Creamos los dos botones para este panel 		 */			gbc.anchor = GridBagConstraints.EAST;		gbc.insets = new Insets(30,10,5,5);		gbc.gridwidth = GridBagConstraints.RELATIVE;		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);		gbc.anchor = GridBagConstraints.WEST;		gbc.gridwidth = GridBagConstraints.REMAINDER;		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][2]),gbc);			ActionListener accion = new ActionListener(){			@Override			public void actionPerformed(ActionEvent e) {				// TODO Auto-generated method stub				if(e.getActionCommand().equals("aceptar")){					ConexionDb conexdb = new ConexionDb();					ResultSet rs;					conexdb.Conectardb();					//conexdb.executeUpdate("INSERT INTO STAFF (nombre, apellidos, f_nac, codpostal, telefono) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"')");					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][23]);					conexdb.cerrarConexion();				}							}					};		jbtnaceptar.setActionCommand("aceptar");		jbtnaceptar.addActionListener(accion);		jbtncancelar.setActionCommand("cancelar");							panel.setOpaque(true);		this.setViewportView(panel);			}}		/*	ActionListener alta = new ActionListener(){		@Override		public void actionPerformed(ActionEvent e) {			// TODO Auto-generated method stub			if(e.getActionCommand().equals("crear")){				ConexionDb conexdb = new ConexionDb();				ResultSet rs;				conexdb.Conectardb();				conexdb.executeUpdate("INSERT INTO STAFF (,dni,nombre, categoria, representante, f_nac, pais,region,ciudad,direccion,codpostal, telefono) VALUES ('"+ jtxt1.getText()+"','"+jtxt2.getText()+"','"+jtxt3.getText()+"','"+jtxt4.getText()+"','"+jtxt5.getText()+"')");				JFrame aviso = new JFrame();				JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][23]);				conexdb.cerrarConexion();			}		}			};		jbtnCrear.setActionCommand("crear");	jbtnCrear.addActionListener(alta);	jbtnSalir.setActionCommand("Salir");			panel.setOpaque(true);	this.setViewportView(panel);}}*/