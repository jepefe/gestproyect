package pkGesproject.Staff;import java.awt.Color;import java.awt.Container;import java.awt.Dimension;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Insets;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.io.File;import java.sql.ResultSet;import java.sql.SQLException;import javax.swing.JButton;import javax.swing.JCheckBox;import javax.swing.JComboBox;import javax.swing.JFileChooser;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JOptionPane;import javax.swing.JPanel;import javax.swing.JPasswordField;import javax.swing.JRadioButton;import javax.swing.JScrollPane;import javax.swing.JTextArea;import javax.swing.JTextField;import javax.swing.filechooser.FileNameExtensionFilter;import com.toedter.calendar.JDateChooser;import pkGesproject.ConexionDb;import pkGesproject.GesIdioma;import pkGesproject.GesStaff;import pkGesproject.GpComboBox;import pkGesproject.LimiteDocumento;import pkGesproject.RsGesproject;public class pnlAlta_staff extends JScrollPane{	GesIdioma rec = GesIdioma.obtener_instancia();	JTextField[] jtxt;	JLabel[] jlbl;	JButton jbtnaceptar, jbtncancelar, jbtnexaminar;	JPanel panel = new JPanel();	JFrame aviso = new JFrame();	GpComboBox cbPais =  new GpComboBox();	GpComboBox cbprov = new GpComboBox ();	GpComboBox cbCategoria = new GpComboBox();	JCheckBox cbxRepresentante = new JCheckBox();	JPasswordField jpas1,jpas2 = new JPasswordField() ;	JCheckBox jrad = new JCheckBox();	ResultSet rs;	ConexionDb conexion = new ConexionDb();	String pais[] = new String[240];	int idprovpais[]= new int[4292];	String prov[] = new String[4292];	int indexpais;	String ruta, representante;	String extension;	int tam;	char caracter;	JDateChooser jdcNacimiento;	JTextField jtxtFoto;	JTextArea textarea = (new JTextArea(3,13));	String idpais;	String idprovincia;	public pnlAlta_staff (){		RsGesproject recursos = RsGesproject.Obtener_Instancia();				panel.setLayout(new GridBagLayout());		  		String[] fieldNames = {		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][28],		   rec.idioma[rec.eleidioma][47],rec.idioma[rec.eleidioma][48],		   rec.idioma[rec.eleidioma][7], rec.idioma[rec.eleidioma][8],		   rec.idioma[rec.eleidioma][5],rec.idioma[rec.eleidioma][73],		   rec.idioma[rec.eleidioma][71],rec.idioma[rec.eleidioma][9],		   rec.idioma[rec.eleidioma][58],rec.idioma[rec.eleidioma][50],		   rec.idioma[rec.eleidioma][51]		   		};		int[] fieldWidths = {15,15,20,20,20,7,15,15,15,15,15,15,15,15};		jtxt = new JTextField[fieldNames.length];		jlbl = new JLabel[fieldNames.length];		jdcNacimiento = new JDateChooser();		jdcNacimiento.setDateFormatString("dd/MM/yyyy");				final GridBagConstraints gbc = new GridBagConstraints();				gbc.gridwidth = GridBagConstraints.REMAINDER;		gbc.anchor = GridBagConstraints.CENTER;		gbc.insets = new Insets(20,0,15,0);		//panel.add(new JLabel("Alta Partner"),gbc);				gbc.anchor = GridBagConstraints.WEST;		gbc.insets = new Insets(5,10,5,5);				/*		 * Con el bucle for vamos creando tantos labels y textfields como 		 * nombres de campos hayamos metido en fieldNames.		 */	    for(int i=0;i<fieldNames.length;++i) {						    	if (i == 2){     		gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][45]),gbc);    		gbc.gridwidth = GridBagConstraints.REMAINDER;     		panel.add(jdcNacimiento,gbc);     		    	    	}    	/*		 * Creamos una consulta para cojer todos los paises.		 */		if (i==2){			conexion.Conectardb();			rs = conexion.ConsultaSQL("SELECT * FROM PAIS");		    	try {					while(rs.next()){								cbPais.addItem(rs.getString(2));					}				} catch (SQLException e) {					// TODO Auto-generated catch block					e.printStackTrace();				}	    	conexion.cerrarConexion();	    		    					gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][46]),gbc);			gbc.anchor = GridBagConstraints.WEST;			gbc.gridwidth = GridBagConstraints.REMAINDER;			cbPais.setPreferredSize(new Dimension(233,30));			panel.add(cbPais,gbc);									cbPais.addActionListener(new ActionListener(){				@Override				public void actionPerformed(ActionEvent arg0) {					// TODO Auto-generated method stub										System.out.println("entra");					int pais = 0;															conexion.Conectardb();					rs = conexion.ConsultaSQL("select id_pais FROM PAIS WHERE pais like '"+cbPais.getSelectedItem().toString()+"'");										try {					rs.next();					pais = rs.getInt(1);										} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}					rs = conexion.ConsultaSQL("SELECT * FROM PROVINCIAS WHERE id_pais = "+pais);					System.out.print(pais);					int j = 0 ;					try {												/**						 * El problema era que cuando se metia dentro del while solo hacia las dos lineas que yo te comentado						 * guardaba los datos en esos array y claro no hacia nada						 * los he comentado xk no sirven para nada y lo que he hecho es ir rellenando el cbprov que es el combo de las provincias						 * 						 * luego lo que pasaba era que ibas cambiando de pais y se iban añadiendo sus provincias pero no se borraban las anteriores y lo que he hecho						 * es poner esta linea que hay antes del while que lo que hace es borrar todos los datos que hay en el combo para luego meter solo las provincias que toca						 **/																		cbprov.removeAllItems(); 						while(rs.next()){									//prov[j]=(rs.getString(2));								//idprovpais[j]= (rs.getInt(3));								cbprov.addItem(rs.getString(2));								j++;						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}									}															});						conexion.cerrarConexion();	    	gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][61]),gbc);			gbc.anchor = GridBagConstraints.WEST;			gbc.gridwidth = GridBagConstraints.REMAINDER;			cbprov.setPreferredSize(new Dimension(233,30));			panel.add(cbprov,gbc);	/*	 * Falla en el SCROLL del JComboBox	 * 	 */		/*	conexion.Conectardb();			rs = conexion.ConsultaSQL("SELECT * FROM PROVINCIAS");			    	try {			    	int j = 0 ;						while(rs.next()){									prov[j]=(rs.getString(2));								idprovpais[j]= (rs.getInt(3));								j++;						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}			    	conexion.cerrarConexion();			    	gbc.gridwidth = GridBagConstraints.RELATIVE;					panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][61]),gbc);					gbc.anchor = GridBagConstraints.WEST;					gbc.gridwidth = GridBagConstraints.REMAINDER;					cbprov.setPreferredSize(new Dimension(233,30));					panel.add(cbprov,gbc);			    						//cbprov.actionPerformed(arg0)*/													    				    }																						 //if( ( i !=10) && (1 != 4) ){ 			gbc.gridwidth = GridBagConstraints.RELATIVE;	        panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 	        gbc.gridwidth = GridBagConstraints.REMAINDER;	        panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);		 //}		 						/*			 * Jlabel Contraseña con un JPassword			 */			if(i == 10){  				gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][11]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas1 = new JPasswordField(fieldWidths[i]),gbc);		       // Verificar Contrase�a    		       /* gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][62]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas2 = new JPasswordField(fieldWidths[i]),gbc);		        KeyListener accion = new KeyListener(){	    			@Override	    			public void keyPressed(KeyEvent arg0) {	    				    				if(jpas1 == null || jpas2 == null){	    					jpas2.setBackground(Color.WHITE);	    				}	    				else{	    					if (jpas1.equals(jpas2)){		 				        jpas2.setBackground(Color.RED);		 				        }else{		 				        jpas2.setBackground(Color.GREEN);			 				        }						    				}	    				}	    						    						    				    			@Override	    			public void keyReleased(KeyEvent act) {				       	    			}					@Override					public void keyTyped(KeyEvent arg0) {						// TODO Auto-generated method stub											}		        };		        jpas1.addKeyListener(accion);  */	        }						if(i==10){								/*				 * Creamos el label "FOTO" y su boton Examinar				 */				 				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][49]),gbc);				panel.add(jtxtFoto=new JTextField(fieldWidths[i]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jbtnexaminar=new JButton(rec.idioma[rec.eleidioma][53]),gbc);								jbtnexaminar.addActionListener(new ActionListener(){						public void actionPerformed(ActionEvent arg0) {							// TODO Auto-generated method stub							JFileChooser filechooser = new JFileChooser();							FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, GIF, PNG", "jpg", "jpeg", "gif", "png");							filechooser.setFileFilter(filter);							int returnVal = filechooser.showSaveDialog(null);								if (returnVal == JFileChooser.APPROVE_OPTION) {									File file = filechooser.getSelectedFile();									/*									 * sacamos la ruta del archivo y su extension									 */									ruta = file.getPath();									tam = ruta.length();									extension = ruta.substring(tam-3,tam);									System.out.println(extension);									if(extension.equals ("jpg") || extension.equals ("jpeg") || extension.equals ("gif") || extension.equals ("png")){										jtxtFoto.setText(ruta);									}																    } 									}					});												conexion.Conectardb();				rs = conexion.ConsultaSQL("SELECT * FROM RANGOS");			    	try {						while(rs.next()){									cbCategoria.addItem(rs.getString(2));						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}		    	conexion.cerrarConexion();		    	conexion.Conectardb();								gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][44]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				cbCategoria.setPreferredSize(new Dimension(177,30));				panel.add(cbCategoria,gbc);											}												if(i==12){													/*				 * Creamos el textarea para las observaciones 				 */								gbc.gridwidth = GridBagConstraints.RELATIVE;		    	panel.add(new JLabel(rec.idioma[rec.eleidioma][64]),gbc); 		    	gbc.gridwidth = GridBagConstraints.REMAINDER;		    		    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea		    			    	textarea.setDocument(lpd);		    	textarea.setLineWrap(true);		    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,		    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		    	panel.add((sp),gbc);												/*				 * Creacion Del JRadioButton para seleccion de Representante				 */				gbc.gridwidth = GridBagConstraints.RELATIVE;			    panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][52]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jrad = new JCheckBox(),gbc);							}			    }	    	//  Creamos un Keylistener para admitir la entrada de s�lo n�meros	/*jtxt[5].addKeyListener(new KeyAdapter(){	   public void keyTyped(KeyEvent e){	      caracter = e.getKeyChar();	      if(((caracter < '0') ||(caracter > '9')) &&	         (caracter != KeyEvent.VK_BACK_SPACE)) {	         e.consume();  	      }	   }	});*/				/*		 * Creamos los dos botones para este panel 		 */				gbc.anchor = GridBagConstraints.EAST;		gbc.insets = new Insets(30,10,5,5);		gbc.gridwidth = GridBagConstraints.RELATIVE;		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);		gbc.anchor = GridBagConstraints.WEST;		gbc.gridwidth = GridBagConstraints.REMAINDER;		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][2]),gbc);													ActionListener accion = new ActionListener(){			@Override			public void actionPerformed(ActionEvent e) {				// TODO Auto-generated method stub								if(e.getActionCommand().equals("aceptar")){					int creado=2;					representante="0";															ConexionDb conexdb = new ConexionDb();					conexdb.Conectardb();					rs = conexdb.ConsultaSQL("Select id_pais FROM PAIS WHERE pais='" + cbPais.getSelectedItem().toString()+"'");					if (rs!= null){						try {							rs.next();							idpais = Integer.toString(rs.getInt(1));						} catch (SQLException e1) {							// TODO Auto-generated catch block							e1.printStackTrace();						}						}					rs = conexdb.ConsultaSQL("Select id_provincias FROM PROVINCIAS WHERE estado='" + cbprov.getSelectedItem().toString() + "'" + "AND id_pais='" + idpais + "'");					if (rs!= null){						try {							rs.next();							idprovincia = Integer.toString(rs.getInt(1));						} catch (SQLException e1) {							// TODO Auto-generated catch block							e1.printStackTrace();						}						}																									if (cbxRepresentante.isSelected()== true) {					representante = "1";					}										GesStaff gs = new GesStaff();					java.sql.Date sqlDate1 = new java.sql.Date(jdcNacimiento.getDate().getTime());					//conexdb.executeUpdate("INSERT INTO STAFF (nombre, apellidos, f_nac, ciudad, region, direccion, codpostal, telefono, representante, foto, nick_usuario, contrase�a) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+sqlDate1+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+jtxt[6].getText()+"')");					creado = gs.CrearStaff(jtxt[0].getText(),jtxt[1].getText(), cbCategoria.getSelectedItem().toString(),representante, sqlDate1.toString(), 							idpais, idprovincia, jtxt[2].getText(),jtxt[3].getText(), jtxt[4].getText(), jtxt[5].getText(), jtxt[6].getText(), 							jtxt[7].getText(),jtxt[8].getText(), jtxt[9].getText(),							jtxtFoto.getText() ,jtxt[10].getText(),jpas1.getText(), jtxt[11].getText(),							jtxt[12].getText(), textarea.getText());														switch (creado){					case 0: JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][76]);					break;					case 2: JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][77]);					break;					}					creado=0;				}							}					};								jbtnaceptar.setActionCommand("aceptar");		jbtnaceptar.addActionListener(accion);		jbtncancelar.setActionCommand("cancelar");							panel.setOpaque(true);		this.setViewportView(panel);			}}	