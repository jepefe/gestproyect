package pkGesproject.Staff;import java.awt.BorderLayout;import java.awt.Color;import java.awt.Container;import java.awt.Dimension;import java.awt.Font;import java.awt.GridBagConstraints;import java.awt.GridBagLayout;import java.awt.Insets;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.awt.event.FocusEvent;import java.awt.event.FocusListener;import java.awt.event.KeyAdapter;import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.io.File;import java.sql.ResultSet;import java.sql.SQLException;import javax.swing.ImageIcon;import javax.swing.JButton;import javax.swing.JCheckBox;import javax.swing.JComboBox;import javax.swing.JDialog;import javax.swing.JFileChooser;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JOptionPane;import javax.swing.JPanel;import javax.swing.JPasswordField;import javax.swing.JProgressBar;import javax.swing.JRadioButton;import javax.swing.JScrollPane;import javax.swing.JTextArea;import javax.swing.JTextField;import javax.swing.border.Border;import javax.swing.border.EmptyBorder;import javax.swing.filechooser.FileNameExtensionFilter;import com.toedter.calendar.JDateChooser;import pkGesproject.ConexionDb;import pkGesproject.GesIdioma;import pkGesproject.GesStaff;import pkGesproject.GpComboBox;import pkGesproject.JImageContainer;import pkGesproject.LimiteDocumento;import pkGesproject.RsGesproject;public class pnlAlta_staff extends JScrollPane{	GesIdioma rec = GesIdioma.obtener_instancia();	JTextField[] jtxt;	JLabel[] jlbl;	JLabel alerta;	JDialog creando = new JDialog();	JButton jbtnaceptar, jbtncancelar, jbtnexaminar;	JPanel panel = new JPanel();	JPanel contenedor = new JPanel();	JPanel mesage = new JPanel();		JFrame aviso = new JFrame();	GpComboBox cbPais =  new GpComboBox();	GpComboBox cbprov = new GpComboBox ();	GpComboBox cbCategoria = new GpComboBox();	GpComboBox cbPermisos = new GpComboBox();	GpComboBox cbPartner = new GpComboBox();	JCheckBox cbxRepresentante = new JCheckBox();	JPasswordField jpas1,jpas2 = new JPasswordField() ;	JCheckBox jrad = new JCheckBox();	ResultSet rs;	ConexionDb conexion = new ConexionDb();	String pais[] = new String[240];	int idprovpais[]= new int[4292];	String prov[] = new String[4292];	int indexpais;	String ruta, representante;	String extension;	String pasarfoto = "0";	int tam,mensaje = 0;	int permetir_alta = 0;	char caracter;	JDateChooser jdcNacimiento;	JTextField jtxtFoto;	JLabel jlblCamposOblig;	JLabel jlblimagen;	RsGesproject recursos = RsGesproject.Obtener_Instancia();	Border empty = new EmptyBorder(0,0,0,0);			JTextArea textarea = (new JTextArea(3,13));	String idpais;	String idprovincia;	String idpartner;	public pnlAlta_staff (){				this.setBorder(empty);		panel.setLayout(new GridBagLayout());		  		final String[] fieldNames = {		   rec.idioma[rec.eleidioma][3]+"*",rec.idioma[rec.eleidioma][28]+"*",		   rec.idioma[rec.eleidioma][47]+"*",rec.idioma[rec.eleidioma][48]+"*",		   rec.idioma[rec.eleidioma][7]+"*", rec.idioma[rec.eleidioma][8]+"*",		   rec.idioma[rec.eleidioma][5]+"*",rec.idioma[rec.eleidioma][73],		   rec.idioma[rec.eleidioma][71],rec.idioma[rec.eleidioma][9]+"*",		   rec.idioma[rec.eleidioma][58]+"*"		   		};		int[] fieldWidths = {15,15,20,20,20,7,15,15,15,15,15,15,15,15};		jtxt = new JTextField[fieldNames.length];		jlbl = new JLabel[fieldNames.length];		jdcNacimiento = new JDateChooser();		jdcNacimiento.setDateFormatString("dd/MM/yyyy");				final GridBagConstraints gbc = new GridBagConstraints();				gbc.gridwidth = GridBagConstraints.REMAINDER;		gbc.anchor = GridBagConstraints.CENTER;		gbc.insets = new Insets(20,0,15,0);		//panel.add(new JLabel("Alta Partner"),gbc);				gbc.anchor = GridBagConstraints.WEST;		gbc.insets = new Insets(5,10,5,5);				/*		 * Con el bucle for vamos creando tantos labels y textfields como 		 * nombres de campos hayamos metido en fieldNames.		 */			alerta=new JLabel();		alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));		mesage.add(alerta);		mesage.setBackground(Color.decode("#D0E495"));		mesage.setVisible(false);				    for(int i=0;i<fieldNames.length;++i) {						    	if (i == 2){     		gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][45]+"*"),gbc);    		gbc.gridwidth = GridBagConstraints.REMAINDER;     		panel.add(jdcNacimiento,gbc);     		    	    	}    	/*		 * Creamos una consulta para cojer todos los paises.		 */		if (i==2){			conexion.Conectardb();			rs = conexion.ConsultaSQL("SELECT * FROM PAIS");		    	try {					while(rs.next()){								cbPais.addItem(rs.getString(2));					}				} catch (SQLException e) {					// TODO Auto-generated catch block					e.printStackTrace();				}	    	conexion.cerrarConexion();	    		    					gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][46]+"*"),gbc);			gbc.anchor = GridBagConstraints.WEST;			gbc.gridwidth = GridBagConstraints.REMAINDER;			cbPais.setPreferredSize(new Dimension(233,30));			panel.add(cbPais,gbc);			cbPais.setSelectedItem(null);						cbPais.addActionListener(new ActionListener(){				@Override				public void actionPerformed(ActionEvent arg0) {					// TODO Auto-generated method stub										System.out.println("entra");					int pais = 0;															conexion.Conectardb();					rs = conexion.ConsultaSQL("select id_pais FROM PAIS WHERE pais like '"+cbPais.getSelectedItem().toString()+"'");										try {					rs.next();					pais = rs.getInt(1);										} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}					rs = conexion.ConsultaSQL("SELECT * FROM PROVINCIAS WHERE id_pais = "+pais);					System.out.print(pais);					int j = 0 ;					try {												/**						 * El problema era que cuando se metia dentro del while solo hacia las dos lineas que yo te comentado						 * guardaba los datos en esos array y claro no hacia nada						 * los he comentado xk no sirven para nada y lo que he hecho es ir rellenando el cbprov que es el combo de las provincias						 * 						 * luego lo que pasaba era que ibas cambiando de pais y se iban añadiendo sus provincias pero no se borraban las anteriores y lo que he hecho						 * es poner esta linea que hay antes del while que lo que hace es borrar todos los datos que hay en el combo para luego meter solo las provincias que toca						 **/																		cbprov.removeAllItems(); 						while(rs.next()){									//prov[j]=(rs.getString(2));								//idprovpais[j]= (rs.getInt(3));								cbprov.addItem(rs.getString(2));								j++;						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}									}															});						conexion.cerrarConexion();	    	gbc.gridwidth = GridBagConstraints.RELATIVE;			panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][61]+"*"),gbc);			gbc.anchor = GridBagConstraints.WEST;			gbc.gridwidth = GridBagConstraints.REMAINDER;			cbprov.setPreferredSize(new Dimension(233,30));			panel.add(cbprov,gbc); 				    }		 			gbc.gridwidth = GridBagConstraints.RELATIVE;	        panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc); 	        gbc.gridwidth = GridBagConstraints.REMAINDER;	        panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);		 	        			//  Creamos un Keylistener para admitir la entrada de solo numeros	        if(i==6 || i==7 || i==8){				jtxt[i].addKeyListener(new KeyAdapter(){				   public void keyTyped(KeyEvent e){				      caracter = e.getKeyChar();				      if(((caracter < '0') ||(caracter > '9')) &&				         (caracter != KeyEvent.VK_BACK_SPACE) &&				         (caracter != '+') && (caracter != '(') && (caracter != ')')) {				         e.consume();  				      }				   }				});				}	        	        			/*			 * Jlabel Contraseña con un JPassword			 */			if(i == 10){  				gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][11]+"*"),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas1 = new JPasswordField(fieldWidths[i]),gbc);		       // Verificar Contrase�a    		        gbc.gridwidth = GridBagConstraints.RELATIVE;		        panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][62]+"*"),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jpas2 = new JPasswordField(fieldWidths[i]),gbc);		        		        /**		         * Validación de contraseña. 		         * Impide que los campos "Contraseña" y "Verificar contraseña" tengan diferentes contenidos.		         **/		        		        FocusListener foco = new FocusListener(){					@Override					public void focusGained(FocusEvent arg0) {						// TODO Auto-generated method stub											}					@Override					public void focusLost(FocusEvent arg0) {						// TODO Auto-generated method stub												/**						 * Cuando sale del campo "verificar contraseña" se realiza la comparacion de las contraseñas.						 */																		if(jpas2.getText().equals("")){							if(jpas1.getText().equals("")){								jpas2.setBackground(Color.WHITE);							}else{								jpas2.setBackground(Color.decode("#ec8989"));								alerta.setText(rec.idioma[rec.eleidioma][112]);								mesage.setBackground(Color.decode("#ec8989"));								mesage.setVisible(true);							}						}else{							if(jpas1.getText().equals("")){								alerta.setText(rec.idioma[rec.eleidioma][112]);								mesage.setBackground(Color.decode("#ec8989"));								mesage.setVisible(true);							}else{		    					if (jpas1.getText().equals(jpas2.getText())){			 				        	jpas2.setBackground(Color.decode("#D0E495"));			 				        	alerta.setText(rec.idioma[rec.eleidioma][128]);										mesage.setBackground(Color.decode("#D0E495"));										mesage.setVisible(true);			 				        }else{			 				        	jpas2.setBackground(Color.decode("#ec8989"));				 				        	alerta.setText(rec.idioma[rec.eleidioma][110]);										mesage.setBackground(Color.decode("#ec8989"));										mesage.setVisible(true);			 				        }							    				}		    				}						}								        			        };		        jpas2.addFocusListener(foco); 	        }						if(i==10){								/*				 * Creamos el label "FOTO" y su boton Examinar				 */				 				gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][49]),gbc);				panel.add(jtxtFoto=new JTextField(fieldWidths[i]),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				panel.add(jbtnexaminar=new JButton(rec.idioma[rec.eleidioma][53]),gbc);								jbtnexaminar.addActionListener(new ActionListener(){						public void actionPerformed(ActionEvent arg0) {							// TODO Auto-generated method stub							JFileChooser filechooser = new JFileChooser();							FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, GIF, PNG", "jpg", "jpeg", "gif", "png");//Añadimos el filtro para que nos muestre sólo las extensiones que queremos							filechooser.setFileFilter(filter);														int returnVal = filechooser.showOpenDialog(null);								if (returnVal == JFileChooser.APPROVE_OPTION) {									File file = filechooser.getSelectedFile();									/*									 * sacamos la ruta del archivo y su extension									 */									ruta = file.getPath();									tam = ruta.length();									extension = ruta.substring(tam-3,tam);									if(extension.equalsIgnoreCase ("jpg") || extension.equalsIgnoreCase ("jpeg") || extension.equalsIgnoreCase ("gif") || extension.equalsIgnoreCase ("png")){										jtxtFoto.setText(ruta);									}																    } 									}					});												conexion.Conectardb();				rs = conexion.ConsultaSQL("SELECT * FROM RANGOS");			    	try {						while(rs.next()){									cbCategoria.addItem(rs.getString(2));						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}		    	conexion.cerrarConexion();		    	conexion.Conectardb();								gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][44]+"*"),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				cbCategoria.setPreferredSize(new Dimension(177,30));				panel.add(cbCategoria,gbc);				cbCategoria.setSelectedItem(null);								/*				 * EL combo de permisos se carga en le mismo if con "Categorias" ya que por el orden van juntos				 */								conexion.Conectardb();				rs = conexion.ConsultaSQL("SELECT * FROM PERMISOS");			    	try {						while(rs.next()){									cbPermisos.addItem(rs.getString(2));						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}		    	conexion.cerrarConexion();		    	conexion.Conectardb();								gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][50]+"*"),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				cbPermisos.setPreferredSize(new Dimension(177,30));				panel.add(cbPermisos,gbc);				cbPermisos.setSelectedItem(null);							}						if(i==10){				/*				 * El combobox para seleccionar un Partner				 */				conexion.Conectardb();				rs = conexion.ConsultaSQL("SELECT nombre FROM PARTNER");			    	try {						while(rs.next()){									cbPartner.addItem(rs.getString(1));						}					} catch (SQLException e) {						// TODO Auto-generated catch block						e.printStackTrace();					}		    	conexion.cerrarConexion();								gbc.gridwidth = GridBagConstraints.RELATIVE;				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][57]+"*"),gbc);				gbc.anchor = GridBagConstraints.WEST;				gbc.gridwidth = GridBagConstraints.REMAINDER;				cbPartner.setPreferredSize(new Dimension(177,30));				panel.add(cbPartner,gbc);				cbPartner.setSelectedItem(null);												/*				 * Creamos el textarea para las observaciones 				 */								gbc.gridwidth = GridBagConstraints.RELATIVE;		    	panel.add(new JLabel(rec.idioma[rec.eleidioma][64]),gbc); 		    	gbc.gridwidth = GridBagConstraints.REMAINDER;		    		    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea		    			    	textarea.setDocument(lpd);		    	textarea.setLineWrap(true);		    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,		    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		    	panel.add((sp),gbc);												/*				 * Creacion Del JRadioButton para seleccion de Representante				 */				gbc.gridwidth = GridBagConstraints.RELATIVE;			    panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][52]),gbc); 		        gbc.gridwidth = GridBagConstraints.REMAINDER;		        panel.add(jrad = new JCheckBox(),gbc);		        			}			if(i==11){			    panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][80]+"*"),gbc);			    jlbl[i].setForeground(Color.RED);			    			}    }			/*		 * Creamos los dos botones para este panel 		 */		gbc.anchor = GridBagConstraints.EAST;		gbc.insets = new Insets(30,10,5,5);		gbc.gridwidth = GridBagConstraints.RELATIVE;		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);		gbc.anchor = GridBagConstraints.WEST;		gbc.gridwidth = GridBagConstraints.REMAINDER;		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);				ActionListener accion = new ActionListener(){			@Override			public void actionPerformed(ActionEvent e) {				// TODO Auto-generated method stub								/*				 * miramos si han quedado campos vacios, si es asi ponemos "permetir_alta" a "1" 				 * lo cual no nos dejara dar de alta y avisará de que los campos obligatorios no pueden estar vacios				 */								if((jpas1.getText().length() > 5) && (jpas2.getText().length() > 5)){				}else{					if(jpas1.getText().length() == 0 || jpas2.getText().length() == 0){						permetir_alta = 1;					}else{						permetir_alta = 2;//lo ponemos a 2 para que si estan rellenados todos los campos pero contraseña es muy corta que nos avise sólo de que es corta. 						alerta.setText(rec.idioma[rec.eleidioma][122]);						mesage.setBackground(Color.decode("#ec8989"));						mesage.setVisible(true);					}				}								if((jdcNacimiento.getDate() != null) && (cbPais.getSelectedItem() != null) && (cbprov.getSelectedItem() != null)&& (cbCategoria.getSelectedItem() != null)&& (cbPermisos.getSelectedItem() != null)&& (cbPartner.getSelectedItem() != null)){				}else{					permetir_alta = 1;}								for(int i=0;i<11;++i) {					if(i != 7 && i != 8){						if (jtxt[i].getText().length() > 1){						}else{						permetir_alta = 1;						}					}				}				/*				 * Limpiar el formulario				 */				if(e.getActionCommand().equals("cancelar")){					for (int i=0;i<11;++i){						if(jtxt[i].getText().length() > 0){							jtxt[i].setText("");						}					}					textarea.setText("");					jtxtFoto.setText(null);					jpas1.setText(null);					jpas2.setText(null);					jpas2.setBackground(Color.WHITE);					cbprov.setSelectedItem(null);					cbCategoria.setSelectedItem(null);					cbPermisos.setSelectedItem(null);					cbPartner.setSelectedItem(null);					jdcNacimiento.setDate(null);					mensaje = 1; //para que a la hora de dar al boton "cancelar" no se salte el mensaje "Debe rellenar campos obligatorios"				}								if(e.getActionCommand().equals("aceptar")){					mensaje = 0;					if(permetir_alta == 0){						int creado=1;						representante="0";												ConexionDb conexdb = new ConexionDb();						conexdb.Conectardb();						rs = conexdb.ConsultaSQL("Select id_pais FROM PAIS WHERE pais='" + cbPais.getSelectedItem().toString()+"'");						if (rs!= null){							try {								rs.next();								idpais = Integer.toString(rs.getInt(1));							} catch (SQLException e1) {								// TODO Auto-generated catch block								e1.printStackTrace();							}							}						rs = conexdb.ConsultaSQL("Select id_provincias FROM PROVINCIAS WHERE estado='" + cbprov.getSelectedItem().toString() + "'" + "AND id_pais='" + idpais + "'");						if (rs!= null){							try {								rs.next();								idprovincia = Integer.toString(rs.getInt(1));							} catch (SQLException e1) {								// TODO Auto-generated catch block								e1.printStackTrace();							}							}												rs = conexdb.ConsultaSQL("Select cod_part FROM PARTNER WHERE nombre='" + cbPartner.getSelectedItem().toString() + "'");						if (rs!= null){							try {								rs.next();								idpartner = Integer.toString(rs.getInt(1));							} catch (SQLException e1) {								// TODO Auto-generated catch blockqw								e1.printStackTrace();							}							}													if (jrad.isSelected()) {						representante = "1";						}												GesStaff gs = new GesStaff();						java.sql.Date sqlDate1 = new java.sql.Date(jdcNacimiento.getDate().getTime());						//conexdb.executeUpdate("INSERT INTO STAFF (nombre, apellidos, f_nac, ciudad, region, direccion, codpostal, telefono, representante, foto, nick_usuario, contrase�a) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+sqlDate1+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+jtxt[6].getText()+"')");						creando = new JDialog(recursos.getRfrmppal());						creando.setPreferredSize(new Dimension(250,330));						creando.setSize(250,330);						creando.setLayout(new GridBagLayout());						creando.setLocationRelativeTo(null);						GridBagConstraints gbc2 = new GridBagConstraints();						gbc2.gridwidth = GridBagConstraints.REMAINDER;						gbc2.anchor = GridBagConstraints.CENTER;						gbc2.insets = new Insets(20,0,15,0);												JLabel lblcreando = new JLabel("Añadiendo staff");						JLabel lblcreandonombre = new JLabel();						JImageContainer jicfoto = new JImageContainer();						JProgressBar jpb = new JProgressBar();						JButton cerrar = new JButton("Cerrar");						jpb.setIndeterminate(true);											jicfoto.setSize(50,50);						lblcreandonombre.setFont(new Font(Font.SANS_SERIF, Font.BOLD,16));						lblcreandonombre.setText(jtxt[0].getText() + " " + jtxt[1].getText());						jicfoto.setPreferredSize(new Dimension(75,75));						creando.getContentPane().add(jicfoto,gbc2);						creando.getContentPane().add(jpb,gbc2);						creando.getContentPane().add(lblcreandonombre,gbc2);						creando.getContentPane().add(lblcreando,gbc2);												cerrar.addActionListener(new ActionListener(){								@Override							public void actionPerformed(ActionEvent arg0) {								// TODO Auto-generated method stub								creando.setVisible(false);							}													});											//	creando.setLocation(null);													/**							 * Permite dar de alta si no has seleccionado ninguna foto							 */														if (jtxtFoto.getText().length()==0){								pasarfoto="0";							}else{							File fil = new File(jtxtFoto.getText());							if (fil.exists()){																pasarfoto = jtxtFoto.getText();							}else{								pasarfoto = "0";							}							if (!pasarfoto.equals("0")){								jicfoto.SetImage(pasarfoto);								}else{									jicfoto.setImage(recursos.imagen[0]);								}														}							creando.setVisible(true);													creado = gs.CrearStaff(jtxt[0].getText(),jtxt[1].getText(), Integer.toString(cbCategoria.getSelectedIndex()),representante, sqlDate1.toString(), 								idpais, idprovincia, jtxt[2].getText(),jtxt[3].getText(), jtxt[4].getText(), jtxt[5].getText(), jtxt[6].getText(), 								jtxt[7].getText(),jtxt[8].getText(), jtxt[9].getText(),								pasarfoto ,jtxt[10].getText(),jpas1.getText(), Integer.toString(cbPermisos.getSelectedIndex()),								idpartner, textarea.getText());																		switch (creado){						case 0: 							//JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][77]);							creando.getContentPane().remove(jpb);										lblcreando.setText(rec.idioma[rec.eleidioma][77]);							creando.getContentPane().add(cerrar,gbc2);						break;						case 2: 							//JOptionPane.showMessageDialog(aviso , rec.idioma[rec.eleidioma][78]);							creando.getContentPane().remove(jpb);							lblcreando.setText(rec.idioma[rec.eleidioma][78]);							creando.getContentPane().add(cerrar,gbc2);						break;						}						/*						 * Limpia todos los campos despues de dar de alta correctamente						 */						for (int i=0;i<11;++i){							if(jtxt[i].getText().length() > 0){								jtxt[i].setText("");							}						}						textarea.setText("");						jtxtFoto.setText(null);						jpas1.setText(null);						jpas2.setText(null);						jpas2.setBackground(Color.WHITE);						cbprov.setSelectedItem(null);						cbCategoria.setSelectedItem(null);						cbPermisos.setSelectedItem(null);						cbPartner.setSelectedItem(null);						jdcNacimiento.setDate(null);						mesage.setVisible(false);															}else{					if(mensaje == 0 && permetir_alta == 1){						alerta.setText(rec.idioma[rec.eleidioma][79]);						mesage.setBackground(Color.decode("#ec8989"));						mesage.setVisible(true);					}					permetir_alta = 0;				}}							}		};				jbtnaceptar.setActionCommand("aceptar");		jbtnaceptar.addActionListener(accion);		jbtncancelar.setActionCommand("cancelar");		jbtncancelar.addActionListener(accion);			contenedor.setLayout(new BorderLayout());		panel.setOpaque(true);		contenedor.add(mesage,BorderLayout.NORTH);		contenedor.add(panel,BorderLayout.CENTER);				this.setViewportView(contenedor);					}}	