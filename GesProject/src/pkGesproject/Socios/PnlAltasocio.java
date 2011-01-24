package pkGesproject.Socios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;
import pkGesproject.LimitadorDeDocumento;
public class PnlAltasocio extends JScrollPane{


	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JLabel alerta;
	JButton jbtnaceptar, jbtncancelar, jbtnaceptarmod, jbtncancelarmod;
	JPanel panel = new JPanel();
	JPanel pnlcontenedor = new JPanel();
	JPanel mensaje = new JPanel(); 
	JFrame aviso = new JFrame();
	ConexionDb conexion= new ConexionDb();
	String paisaux, sectoraux;
	GpComboBox cbpais = new GpComboBox();
	GpComboBox cbsector = new GpComboBox();
	JTextArea textarea = (new JTextArea(3,13));
	PnlModificarsocio modso = PnlModificarsocio.Obtener_Instancia();
	ResultSet rs;
	Border empty = new EmptyBorder(0,0,0,0);
	char caracter;
	FocusListener foco;
	Runnable servdisp;
	public PnlAltasocio (){
		final RsGesproject recursos = RsGesproject.Obtener_Instancia();
		this.setBorder(empty);
		
		panel.setLayout(new GridBagLayout());
		  
		final String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3]+"*",rec.idioma[rec.eleidioma][7]+"*",
		   rec.idioma[rec.eleidioma][8]+"*",rec.idioma[rec.eleidioma][9]+"*",rec.idioma[rec.eleidioma][70],rec.idioma[rec.eleidioma][5]+"*",
		   rec.idioma[rec.eleidioma][5],rec.idioma[rec.eleidioma][71]
		};
		int[] fieldWidths = {20,30,6,20,20,10,10,10};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
		conexion.Conectardb();
		
		/**
		 * Creamos el label para los avisos y ponemos los formatos elegidos
		 */
		alerta=new JLabel();
		alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
		mensaje.add(alerta);
		mensaje.setBackground(Color.decode("#D0E495"));
		mensaje.setVisible(false);
		for(int i=0;i<fieldNames.length;++i) {
			gbc.anchor = GridBagConstraints.EAST;
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			
			if(i==1){
				gbc.anchor = GridBagConstraints.EAST;
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(new JLabel(rec.idioma[rec.eleidioma][4]+"*"),gbc);
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				gbc.anchor = GridBagConstraints.WEST;
				rs = conexion.ConsultaSQL("SELECT * FROM SECTORES");
				
				panel.add(cbsector,gbc);
				try {
					System.out.println("se crea");
					
				while(rs.next()){
					cbsector.addItem(rs.getString(2));
				
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
					cbsector.setSelectedItem(null);
					gbc.anchor = GridBagConstraints.EAST;
					gbc.gridwidth = GridBagConstraints.RELATIVE;
					panel.add(new JLabel(rec.idioma[rec.eleidioma][46]+"*"),gbc);
					gbc.anchor = GridBagConstraints.WEST;
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					
					rs = conexion.ConsultaSQL("SELECT * FROM PAIS");
					
					panel.add(cbpais,gbc);
					try {
					while(rs.next()){
						cbpais.addItem(rs.getString(2));
					
					}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
					}
					cbpais.setSelectedItem(null);
			}
			/*
			 *Se limitan los caracteres de los campos 
			 */
			switch (i){
				case (0):
					LimitadorDeDocumento ljtxt0 = new LimitadorDeDocumento(40);
					jtxt[0].setDocument(ljtxt0);
					break;
				case (1):
				   	LimitadorDeDocumento ljtxt1 = new LimitadorDeDocumento(30);
					jtxt[1].setDocument(ljtxt1);
					break;
				case (2):
				   	LimitadorDeDocumento ljtxt2 = new LimitadorDeDocumento(11);
					jtxt[2].setDocument(ljtxt2);
					break;
				case (3):
				   	LimitadorDeDocumento ljtxt3 = new LimitadorDeDocumento(30);
					jtxt[3].setDocument(ljtxt3);
					break;
				case (4):
				   	LimitadorDeDocumento ljtxt4 = new LimitadorDeDocumento(30);
					jtxt[4].setDocument(ljtxt4);
					break;
				case (5):
				   	LimitadorDeDocumento ljtxt5 = new LimitadorDeDocumento(20);
					jtxt[5].setDocument(ljtxt5);
					break;
				case (6):
					LimitadorDeDocumento ljtxt6 = new LimitadorDeDocumento(20);
					jtxt[6].setDocument(ljtxt6);	
					break;
				case (7):
				   	LimitadorDeDocumento ljtxt7 = new LimitadorDeDocumento(15);
					jtxt[7].setDocument(ljtxt7);
					break;
			
			}

			   if(i==2 || i==5 || i==6 || i==7){
					jtxt[i].addKeyListener(new KeyAdapter(){
					   public void keyTyped(KeyEvent e){
					      caracter = e.getKeyChar();
					      if(((caracter < '0') ||(caracter > '9')) &&
					         (caracter != KeyEvent.VK_BACK_SPACE) &&
					         (caracter != '+') && (caracter != '(') && (caracter != ')')) {
					         e.consume();  
					      }
					   }
					});
					}
			   if(i==0 ){
				   
				   try
				   {
				       MaskFormatter mascara = new MaskFormatter("##.##");
				       JFormattedTextField textField = new JFormattedTextField(mascara);
				       textField.setValue(new Float("12.34"));
				   }
				   catch (Exception e)
				   {
				       e.printStackTrace();
				   }
					jtxt[i].addKeyListener(new KeyAdapter(){
					   public void keyTyped(KeyEvent e){
					      caracter = e.getKeyChar();
					      if(((caracter < 'a') ||(caracter > 'z')) &&
					    		  ((caracter < 'A') ||(caracter > 'Z')) &&
					         (caracter != KeyEvent.VK_BACK_SPACE) &&
					         (caracter != '+') && (caracter != '(') && (caracter != ')')) {
					         e.consume();  
					      }
					   }
					});
					}
			   
		}//fin for
		
		
		foco = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p WHERE nombre = '"+jtxt[0].getText()+"'");
				try {
					if(rs.next()){
						
						alerta.setText(rec.idioma[rec.eleidioma][75]);
						mensaje.setBackground(Color.decode("#ec8989"));
						mensaje.setVisible(true);
						jtxt[0].requestFocus();
						jtxt[0].selectAll();
						//JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][75]);
					}else{
						alerta.setText(rec.idioma[rec.eleidioma][120]);
						mensaje.setBackground(Color.decode("#D0E495"));
						mensaje.setVisible(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conexion.cerrarConexion();
			}
			
		};
		
		jtxt[0].addFocusListener(foco);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(new JLabel(rec.idioma[rec.eleidioma][64]),gbc);
		   gbc.anchor = GridBagConstraints.WEST;
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		      textarea.setDocument(lpd);
		      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		      textarea.setLineWrap(true);
		     panel.add((sp),gbc);
		     
		
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
		
		/**
		 * botones ocultos para utilizar el modificar
		 */
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptarmod=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelarmod=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
	     
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					
					if(jtxt[0].getText().equals("")||jtxt[1].getText().equals("")||cbsector.getSelectedItem() == null||cbpais.getSelectedItem() == null ||
							jtxt[2].getText().equals("")||jtxt[3].getText().equals("")||jtxt[5].getText().equals("")){
						
						alerta.setText(rec.idioma[rec.eleidioma][79]);
						mensaje.setBackground(Color.decode("#ec8989"));
						mensaje.setVisible(true);
						//JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][79]);
					}else{
						conexion.Conectardb();
						ResultSet rs;
						
						rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p WHERE nombre = '"+jtxt[0].getText()+"'");
						
							try {
								if(rs.next()){
									
									JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][75]);
								}else{
									rs=conexion.ConsultaSQL("SELECT id_sector FROM SECTORES WHERE sector like '"+cbsector.getSelectedItem().toString()+"'");
									
									/**
									 * Creamos una variable string para obtener el id del sector y del pais
									 **/
									
									String sector = null;
									try {
										rs.next();
										sector = rs.getString(1);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									rs=conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpais.getSelectedItem().toString()+"'");
									
									String pais = null;
									try {
										rs.next();
										pais = rs.getString(1);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									conexion.executeUpdate("INSERT INTO PARTNER (nombre, sector, pais, direccion, codpostal, email, email2, telefono, telefono2, fax, observaciones) VALUES ('"+ jtxt[0].getText()+"','"+sector+"','"+pais+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+jtxt[6].getText()+"','"+jtxt[7].getText()+"','"+textarea.getText()+"')");
									for(int i=0;i<fieldNames.length;i++){
										jtxt[i].setText("");
									}
									textarea.setText("");
									//cbsector.setSelectedIndex(0);
									//cbpais.setSelectedIndex(0);
									cbsector.setSelectedItem(null);
									cbpais.setSelectedItem(null);
									alerta.setText("");
									jtxt[0].setBackground(Color.white);
									//recursos.modso.cargar_tabla();
									
									modso.cuenta=modso.contar_reg();
									modso.datos = new String[modso.cuenta][modso.columnas];
									modso.auxdatos = new String[modso.cuenta][modso.columnas];
									modso.tablemodel = modso.cargar_tabla(modso.datos,modso.columnas);
									modso.jtblLateral.setModel(modso.tablemodel);
									modso.jtblLateral.repaint();
									modso.llena = false;
									
									
									
									servdisp = new Runnable(){

										@Override
										public void run() {
											// TODO Auto-generated method stub
											alerta.setText(rec.idioma[rec.eleidioma][24]);
											mensaje.setBackground(Color.decode("#D0E495"));
											mensaje.setVisible(true);
											
											try {
												Thread.sleep(50000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											mensaje.setVisible(false);
											
										}
										
									};
									Thread hilo = new Thread(servdisp);
									hilo.start();
									conexion.cerrarConexion();
									//cbsector.setSelectedItem(null);
									//cbpais.setSelectedItem(null);
								}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					
					
					
				}
				
				if(e.getActionCommand().equals("cancelar")){
					int s=JOptionPane.showConfirmDialog(aviso,"Esta seguro de borrar los datos?");
					
					if(s==0){
						for(int i=0;i<fieldNames.length;i++){
							jtxt[i].setText("");
						}
						textarea.setText("");
						cbsector.setSelectedIndex(0);
						cbpais.setSelectedIndex(0);
						alerta.setText("");
						mensaje.setVisible(false);
						jtxt[0].setBackground(Color.white);
					}
				}
				
				if(e.getActionCommand().equals("aceptarmod")){
					
					if(jtxt[0].getText().equals("")||jtxt[1].getText().equals("")||cbsector.getSelectedItem() == null||cbpais.getSelectedItem() == null ||
							jtxt[2].getText().equals("")||jtxt[3].getText().equals("")||jtxt[5].getText().equals("")||jtxt[7].getText().equals("")){
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][79]);
					}else{
						ResultSet rs;
						conexion.Conectardb();
						rs=conexion.ConsultaSQL("SELECT id_sector FROM SECTORES WHERE sector like '"+cbsector.getSelectedItem().toString()+"'");
						
						/**
						 * Creamos una variable string para obtener el id del sector y del pais
						 **/
						
						String sector = null;
						try {
							rs.next();
							sector = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						rs=conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpais.getSelectedItem().toString()+"'");
						
						String pais = null;
						try {
							rs.next();
							pais = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						conexion.executeUpdate("UPDATE PARTNER SET nombre = '"+jtxt[0].getText()+"', sector = "+sector+", pais= "+pais+", direccion = '"+jtxt[1].getText()+"', codpostal = '"+jtxt[2].getText()+"', email = '"+jtxt[3].getText()+"', email2 = '"+jtxt[4].getText()+"', telefono = '"+jtxt[5].getText()+"', telefono2 = '"+jtxt[6].getText()+"', fax = '"+jtxt[7].getText()+"', observaciones = '"+textarea.getText()+"' WHERE cod_part = '"+modso.datos[modso.jtblLateral.getSelectedRow()][3]+"'");
						modso.cuenta=modso.contar_reg();
						modso.datos = new String[modso.cuenta][modso.columnas];
						modso.auxdatos = new String[modso.cuenta][modso.columnas];
						modso.tablemodel = modso.cargar_tabla(modso.datos,modso.columnas);
						modso.jtblLateral.setModel(modso.tablemodel);
						modso.jtblLateral.repaint();
						modso.llena = false;
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][100]);
						PnlModificarsocio.modificar.dispose();
						PnlModificarsocio.modificar = null;
					}
				}
				
				if(e.getActionCommand().equals("cancelarmod")){
					PnlModificarsocio.modificar.dispose();
				}
			}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		jbtncancelar.addActionListener(accion);
		jbtnaceptarmod.setActionCommand("aceptarmod");
		jbtnaceptarmod.addActionListener(accion);
		jbtncancelarmod.setActionCommand("cancelarmod");
		jbtncancelarmod.addActionListener(accion);
		jbtnaceptarmod.setVisible(false);
		jbtncancelarmod.setVisible(false);
		conexion.cerrarConexion();
		pnlcontenedor.setLayout(new BorderLayout());
		pnlcontenedor.setOpaque(true);
		pnlcontenedor.add(mensaje,BorderLayout.NORTH);
		pnlcontenedor.add(panel,BorderLayout.CENTER);
		
		this.setViewportView(pnlcontenedor);
		
	}
}
