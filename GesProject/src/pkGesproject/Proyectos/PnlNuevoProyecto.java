package pkGesproject.Proyectos;
import java.lang.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;
/**
 * Panel Nuevo Proyecto : En este panel es donde podremos dar de alta un nuevo proyecto
 */
public class PnlNuevoProyecto extends JScrollPane{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JPanel jpnl = new JPanel();
	ScrollPane Sp = new ScrollPane();
	JTextArea textarea;
	JTextField[] jtxt;
	String Npartners[] ;	// array con partners
	JDateChooser jdc1,jdc2;
	Date dateini, datefin ;
	JFrame aviso = new JFrame();
	JButton jbtnaceptar, jbtncancelar;
	JButton jbtnaceptar2, jbtncancelar2;
    ConexionDb conexion = new ConexionDb();
	ResultSet rs, rs2;
	DefaultListModel modelo;  // listas Partners (lista2)
	DefaultListModel modelo2;
	JFormattedTextField txtformat;
	public JList listaP ,listaP2 ;    
	int cuenta =0; // cuenta para array dinamica.
	int idPro; // total partners. para saber ID.
	int id_partner; // id partner.
	int id_partner2; // id para poder borrar
	public GpComboBox CbCoordinador;
	 int estado = 1;
	 MouseListener mouseListener;
	 PnlModificarProyecto modpro = PnlModificarProyecto.Obtener_Instancia();
	 
	 /*
	  * Metodo para añadir un item
	  */
	
	 
	public PnlNuevoProyecto()  {
		// formato fecha
		
		
		  jpnl.setLayout(new GridBagLayout());
		// Array  de palabras, Fecha inico, Fecha fin, etc.
	      final String[] fieldNames = {
	    		  rec.idioma[rec.eleidioma][111], rec.idioma[rec.eleidioma][25]
	    		 ,rec.idioma[rec.eleidioma][26],rec.idioma[rec.eleidioma][13]};
	      int[] fieldWidths = {13,7,7,8};
	      
	      jtxt = new JTextField[fieldNames.length];
	      // limite de caracteres 
	      int [] limite = {20,7,10,10}; 
	      // Campos Calendario y formato
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("dd/MM/yyyy");
	      jdc1.setDateFormatString("dd/MM/yyyy");
	      
	       
			
		
				
	      // Situacion en el panel 
	      final GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);
	      for(int i=0;i<fieldNames.length;++i) {
	    	  gbc.gridwidth = GridBagConstraints.RELATIVE;
	    	 if (i ==3){ 
	    		 
	    		 jpnl.add(new JLabel(fieldNames[i]),gbc);}
	    	 	else{jpnl.add(new JLabel(fieldNames[i]),gbc);}
	         if (i != 1 || i != 2 ){gbc.gridwidth = GridBagConstraints.REMAINDER;  } 
	         if(i == 1 ||  i == 2 || i==3){}else{ jpnl.add(jtxt[i] = new JTextField( new JTextFieldLimit(limite[i]), null, fieldWidths[i]),gbc);} 
	         if(i == 3){
	      
	        	JFormattedTextField txtprecio = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	     		txtprecio.setValue(new Integer(0));
	     		txtprecio.setPreferredSize(new Dimension(165,30));
	     		jpnl.add(txtprecio,gbc);
	     		txtprecio.setVisible(false);
	        	 
	         }
	         if (i == 1 ){ gbc.gridwidth = GridBagConstraints.REMAINDER; jpnl.add(jdc1,gbc); }
	         if (i == 2){ gbc.gridwidth = GridBagConstraints.REMAINDER; jpnl.add(jdc2,gbc); }
	      }
	      
	      
	      // Label 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JLabel(rec.idioma[rec.eleidioma][17]),gbc); 
	      gbc.gridwidth = GridBagConstraints.REMAINDER;   
	    
	      // JTextArea con Scrolls 
	      LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	      textarea = (new JTextArea(3,13));
	      textarea.setLineWrap(true);
	      textarea.setDocument(lpd);
	      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      jpnl.add((sp),gbc);
	      
	      // Conexion 1 BBDD
	      conexion.Conectardb();
	    	rs = conexion.ConsultaSQL("SELECT nombre FROM PARTNER");
	    //	rs2 = conexion.ConsultaSQL("Select count(nombre) From PARTNER");
	    	 // Cuenta para hacer la matriz dinamica
	    	try {
				while(rs.next()){cuenta = cuenta +1;}
			} catch (SQLException e) {
				e.printStackTrace();}
	    	
	    	Npartners = new String [cuenta] ;
	    
	 //Conexion 2 BBDD
	    	rs = conexion.ConsultaSQL("SELECT  nombre FROM PARTNER");
	    	
	    	 // Cuenta para hacer la matriz dinamica
	    	int i = 0;
	    	try {
				while(rs.next()){
					Npartners[i] = (rs.getString(1));
				i++;
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    	
	    // JLIST
	    	modelo = new DefaultListModel(); // modelos JLIST
	    	modelo2 = new DefaultListModel(); 
	    	
	      listaP = new JList(modelo2); 
	      // for para pasar los datos al modelo
	      for(int j=0; j<cuenta ; j++){
	    	  modelo2.addElement(Npartners[j]);  
	      }
	      
	       // Primer JLIST
	      JScrollPane sp1 = new JScrollPane(listaP);
	      listaP.setFixedCellWidth(142);
	      listaP.setFixedCellHeight(18);
	      sp1.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][84])); 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(sp1,gbc);
	      gbc.gridwidth = GridBagConstraints.REMAINDER;  
	        // Segundo JLIST
	      listaP2 = new JList(modelo);
	      JScrollPane sp2 = new JScrollPane(listaP2);
	      listaP2.setFixedCellWidth(142);
	      listaP2.setFixedCellHeight(18);
	      sp2.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][82])); 
	      jpnl.add(sp2,gbc);
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JLabel(rec.idioma[rec.eleidioma][81]),gbc);
	      gbc.gridwidth = GridBagConstraints.REMAINDER; 
	      
	      CbCoordinador = new GpComboBox() ; //ComboBox (Coordinador)
	      
	      jpnl.add(CbCoordinador,gbc);
	     
	     // Evento doble click primer JLIST
	      MouseListener mouseListener = new MouseAdapter() {
	    	 
	    	  public void mouseClicked(MouseEvent e) {
	    		  if (e.getClickCount() == 2) {
	                modelo.addElement(listaP.getSelectedValue());
	            	CbCoordinador.addItem(listaP.getSelectedValue());		    
	                modelo2.removeElement(listaP.getSelectedValue());
	               }
	          }
	      };
	      listaP.addMouseListener(mouseListener);

          modelo.toArray();
          
          
	      // Evento doble click segundo  JLIST (lsitaP2)
	      MouseListener mouseListener2 = new MouseAdapter() {
	    	 
	          public void mouseClicked(MouseEvent e) {
	              if (e.getClickCount() == 2) {
	                modelo2.addElement(listaP2.getSelectedValue());
			    	CbCoordinador.removeItem(listaP2.getSelectedValue());	
	                modelo.removeElement(listaP2.getSelectedValue());
	               }
	          }
	      };
	      listaP2.addMouseListener(mouseListener2);
       
	     // botones Aceptar Modificar
	      	gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			jpnl.add(jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jpnl.add(jbtncancelar = new JButton(rec.idioma[rec.eleidioma][74]),gbc);
			jbtncancelar.setVisible(true);
		// botonoes Aceptar 2 y Cancelar 2 para panel modificar (no visibles)	
			
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			jpnl.add(jbtnaceptar2 = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jpnl.add(jbtncancelar2 = new JButton(rec.idioma[rec.eleidioma][2]),gbc);
			jbtncancelar2.setVisible(false);
			jbtnaceptar2.setVisible(false);
			
			
		    conexion.cerrarConexion();
			
		    
		// Conectar Base de datos y pasar datos...
			ActionListener accion = new ActionListener(){

				@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
						// Poner el color de  JDC2 (Fecha2) correcto
							jdc2.setBackground(null);
							
							ConexionDb conexdb = new ConexionDb();
							ResultSet rs;
							conexdb.Conectardb();
							// cambiar fecha a sql
						    java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						    java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					  if (sqlDate1.getTime()< sqlDate2.getTime()){
							conexdb.executeUpdate("INSERT INTO PROYECTOS (nombre, descripcion,estado, f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+ textarea.getText()+"','"+estado+"','"+sqlDate1+"','"+sqlDate2+"')");

							/*
							 * Consegir id proyecto
							 */
							conexion.Conectardb();
					    	rs = conexion.ConsultaSQL("SELECT id_pro From PROYECTOS WHERE nombre = '"+jtxt[0].getText()+"'");
					    	try {
								while(rs.next()){ idPro = rs.getInt(1); }
							} catch (SQLException d) {
								// TODO Auto-generated catch block
								d.printStackTrace();}
							
							/*
							 * Conseguir id_partners.
							 */
							int cord = 0;
					    	for (int i = 0 ; i<listaP2.getModel().getSize(); i++){
							    	rs = conexion.ConsultaSQL("SELECT cod_part From PARTNER WHERE nombre = '"+modelo.getElementAt(i)+"'");
							    	try {
										while(rs.next()){id_partner = rs.getInt(1); }
									} catch (SQLException d) {
										// TODO Auto-generated catch block
										d.printStackTrace();}
										if( CbCoordinador.getSelectedItem()== modelo.getElementAt(i)){
											cord = 1;
										}else{
											cord = 0;
										}
								conexdb.executeUpdate("INSERT INTO PARTNER_PROYECTOS(cod_part, id_pro,coordinador) VALUES ('"+id_partner+"','"+idPro+"','"+cord+"')" );	

							}
					    	
					    	modpro.cuenta=modpro.contar_reg();
							modpro.datos = new String[modpro.cuenta][modpro.columnas];
							modpro.auxdatos = new String[modpro.cuenta][modpro.columnas];
							modpro.tablemodel = modpro.cargar_tabla(modpro.datos,modpro.columnas);
							modpro.jtblLateral.setModel(modpro.tablemodel);
							modpro.jtblLateral.repaint();
							modpro.llena = false;
							
							conexion.cerrarConexion();
							/*
							 * Insertar en las tablas correspondientes.
							 */	
							
							JOptionPane.showMessageDialog(aviso,rec.idioma[rec.eleidioma][60]);
							conexdb.cerrarConexion();
							
							// Borrar cuando termine de añadir		
							jtxt[0].setText("");
							jdc1.setDate(null);
							jdc2.setDate(null);
							textarea.setText(null);
							
							
					}else{		
						
							JOptionPane.showMessageDialog( null, rec.idioma[rec.eleidioma][72]); 
							// Marcar campo FECHA con error en ROJO 
							jdc2.setBackground(Color.red);								
							}						
					}// Borrar cuando damos al boton borrar datos
					if( e.getActionCommand().equals("cancelar")){
					
						
						jtxt[0].setText("");
						jdc1.setDate(null);
						jdc2.setDate(null);
						textarea.setText(null);
		                for (int i = 0 ; i<listaP2.getModel().getSize(); i++){
		                 modelo2.addElement(listaP2.getModel().getElementAt(i));
		                }
		                modelo.removeAllElements();	
					}	
				}		
			};
			jbtnaceptar.setActionCommand("aceptar");
			jbtnaceptar.addActionListener(accion);
			jbtncancelar.setActionCommand("cancelar");
			jbtncancelar.addActionListener(accion);
			
			ActionListener accion2 = new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("aceptar2")){
					    	conexion.Conectardb();
							ConexionDb conexdb = new ConexionDb();
							conexdb.Conectardb();
							// cambiar fecha a sql
						    java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						    java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
						    
						 if (sqlDate1.getTime()< sqlDate2.getTime()){
						  conexdb.executeUpdate("UPDATE PROYECTOS SET nombre='"+ jtxt[0].getText()+"', descripcion='"+textarea.getText()+"',estado='"+estado+"',f_ini='"+sqlDate1+"',f_fin='"+sqlDate2+"' WHERE id_pro ="+PnlModificarProyecto.id_pro+"");
						 }
			            for (int i = 0 ; i<listaP.getModel().getSize(); i++){

					    	rs = conexion.ConsultaSQL("SELECT p.cod_part From PARTNER as p WHERE p.nombre like '"+modelo2.getElementAt(i)+"'");
					    	try {
								rs.next();
								id_partner2 = rs.getInt(1); 
							
							} catch (SQLException d) {
								// TODO Auto-generated catch block
								d.printStackTrace();}
							conexdb.executeUpdate("DELETE FROM PARTNER_PROYECTOS WHERE cod_part = "+id_partner2+" AND id_pro="+PnlModificarProyecto.id_pro+"");

			            }
			            for (int i = 0 ; i<listaP2.getModel().getSize(); i++){
							System.out.println("Numero dentro for:"+listaP2.getModel().getSize());
					    	rs = conexion.ConsultaSQL("SELECT p.cod_part From PARTNER as p WHERE p.nombre like '"+modelo.getElementAt(i)+"'");
					    	try {
								rs.next();
								id_partner2 = rs.getInt(1); 
							
							} catch (SQLException d) {
								// TODO Auto-generated catch block
								d.printStackTrace();}
							System.out.println("id_partner2:"+id_partner2);
							int cord;
							if( CbCoordinador.getSelectedItem()== modelo.getElementAt(i)){
								cord = 1;
							}else{
								cord = 0;
							}
							conexdb.executeUpdate("INSERT INTO PARTNER_PROYECTOS (cod_part, id_pro,coordinador) VALUES('"+id_partner2+"','"+PnlModificarProyecto.id_pro+"','"+cord+"')");
							/*
							 * Salen errores porque esta duplicado y no puede duplicar la entrada. Funciona Bien.	
							 */
							
			            }
						conexion.cerrarConexion();
						conexdb.cerrarConexion();
						JOptionPane.showMessageDialog(aviso,rec.idioma[rec.eleidioma][100]);
						
					}
					if(e.getActionCommand().equals("cancelar2")){
						PnlModificarProyecto.modificar.dispose();
						}
					}
				
			};
			jbtnaceptar2.setActionCommand("aceptar2");
			jbtnaceptar2.addActionListener(accion2);
			jbtncancelar2.setActionCommand("cancelar2");
			jbtncancelar2.addActionListener(accion2);
			
		jpnl.setVisible(true);
		this.setViewportView(jpnl);
	}		
}

