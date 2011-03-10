package Tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAlta_viajes extends JScrollPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JLabel jlblpartner,jlblproyecto,jlblsalida,jlbldestino,jlblf_ini,jlblf_fin;
	JButton jbtnaceptar, jbtnlimpiar;
	GpComboBox cbpartner,cbproyecto,cbcsalida,cbpsalida,cbcdestino,cbpdestino;
	ConexionDb conexion= new ConexionDb();
	ResultSet rs1,rs2,rs3,rs4,rs;
	JDateChooser jdc1,jdc2;
	Component aviso;
	int i;
	boolean OKalta;
	char caracter;
	String cantidad1,cantidad2;
	
	JPanel contenedor = new JPanel();
	JPanel mensage = new JPanel();
	JPanel panel = new JPanel();
	JTextArea textarea = new JTextArea();
	JLabel alerta;
	Border empty = new EmptyBorder(0,0,0,0);
	
	ActionListener accion;
	
	public PnlAlta_viajes(){
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		
		/**
		 * Creamos un array de Strings que utilizaremos para los nombres de los Labels
		 */
		
		String[] fieldNames = {
				rec.idioma[rec.eleidioma][89]+"*",//nombre de la persona
				rec.idioma[rec.eleidioma][90]+"*",rec.idioma[rec.eleidioma][93]+"*",//proposito del viaje // gastos del viaje
				rec.idioma[rec.eleidioma][94]+"*",//gastos de estancia
		};
		
		/**
		 * Otro array para para los tamaños de los campos y creamos los array de
		 * campos y los de los labels
		 */
		
		int[] fieldWidths = {10,30,6,6};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;//
		gbc.anchor = GridBagConstraints.CENTER;//
		gbc.insets = new Insets(20,0,15,0);//
		
		gbc.anchor = GridBagConstraints.WEST;//
		gbc.insets = new Insets(5,10,5,5);
		
		//cuadro con scroll para las observaciones//

    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
    	textarea = (new JTextArea(3,18));
    	textarea.setDocument(lpd);
    	textarea.setLineWrap(true);
    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	
    	
    	//Pongo el panel de las alertas//
    	alerta=new JLabel();
		alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
    	mensage.add(alerta);
		mensage.setBackground(Color.decode("#D0E495"));
		mensage.setVisible(false);
		
		
		
		 // Campos Calendario y formato
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("dd/MM/yyyy");
	      jdc1.setDateFormatString("dd/MM/yyyy");
		
		conexion.Conectardb();
		
		/**
		 * con un for creamos todos los labels, campos y componentes necesarios
		 */
		for(i = 0;i<fieldNames.length;i++){
			if(i==0){
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblpartner=new JLabel(rec.idioma[rec.eleidioma][57]),gbc);//Partner
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p");
				panel.add(cbpartner = new GpComboBox(),gbc);
				try {
					while(rs.next()){
						cbpartner.addItem(rs.getString(1));
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cbpartner.setSelectedItem(null);//inicializamos en blanco
				
				//proyecto cmbbox
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblproyecto=new JLabel(rec.idioma[rec.eleidioma][55]),gbc);//Proyecto
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs2=conexion.ConsultaSQL("SELECT nombre FROM PROYECTOS");
				panel.add(cbproyecto = new GpComboBox(),gbc);
				try {
					while(rs2.next()){
						cbproyecto.addItem(rs2.getString(1));
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cbproyecto.setPreferredSize(new Dimension(233,30));
				cbproyecto.removeAllItems();
			}
			
			if(i==2){
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblsalida=new JLabel(rec.idioma[rec.eleidioma][91]),gbc);//Ciudad y Pais de salida
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				panel.add(cbpsalida = new GpComboBox(),gbc);
				try {
					while(rs.next()){
						cbpsalida.addItem(rs.getString(1));
					
					}
					cbpsalida.setSelectedItem(null);//iniciamos en blanco
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblsalida=new JLabel(),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				panel.add(cbcsalida = new GpComboBox(),gbc);
				
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlbldestino=new JLabel(rec.idioma[rec.eleidioma][92]),gbc);//Ciudad y Pais de destino
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				panel.add(cbpdestino = new GpComboBox(),gbc);
				try {
					while(rs.next()){
						cbpdestino.addItem(rs.getString(1));
					
					}
					cbpdestino.setSelectedItem(null);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlbldestino=new JLabel(),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				panel.add(cbcdestino = new GpComboBox(),gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][25]+"*"),gbc);//Fecha inicio
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(jdc1,gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				panel.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][26]+"*"),gbc);//Fecha fin
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(jdc2,gbc);
				
				cbcsalida.setPreferredSize(new Dimension(233,30));
				cbpsalida.setPreferredSize(new Dimension(233,30));
				cbcdestino.setPreferredSize(new Dimension(233,30));
				cbpdestino.setPreferredSize(new Dimension(233,30));
			}
			
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.anchor = GridBagConstraints.EAST;
			panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		}
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);//Aceptar
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtnlimpiar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);//Limpiar campos
	
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("cbsalida")){
					int pais = 0;
					if(cbpsalida.getSelectedItem()!=null){
						rs = conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpsalida.getSelectedItem().toString()+"'");
					
						try {
							rs.next();
							pais = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						rs = conexion.ConsultaSQL("SELECT estado FROM PROVINCIAS WHERE id_pais = "+pais);
						int j = 0 ;
						cbcsalida.removeAllItems(); 
						try {
							while(rs.next()){	
								cbcsalida.addItem(rs.getString(1));
								j++;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
				if(e.getActionCommand().equals("cbdestino")){
					int pais = 0;
					if(cbpdestino.getSelectedItem()!=null){	
						rs = conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpdestino.getSelectedItem().toString()+"'");
					
						try {
							rs.next();
							pais = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						rs = conexion.ConsultaSQL("SELECT estado FROM PROVINCIAS WHERE id_pais = "+pais);
						int j = 0 ;
						cbcdestino.removeAllItems(); 
						try {
							while(rs.next()){	
								
								cbcdestino.addItem(rs.getString(1));
								j++;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				//
				if(e.getActionCommand().equals("cbpartner")){
					int part = 0;
					if (cbpartner.getSelectedItem()!=null){
						rs = conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like '"+cbpartner.getSelectedItem().toString()+"'");
					
						try {
							rs.next();
							part = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						rs = conexion.ConsultaSQL("SELECT PROYECTOS.nombre FROM PROYECTOS INNER JOIN PARTNER_PROYECTOS ON PROYECTOS.id_pro = PARTNER_PROYECTOS.id_pro WHERE cod_part = "+part);
						int j = 0 ;
						cbproyecto.removeAllItems(); 
						try {
							while(rs.next()){	
								
								cbproyecto.addItem(rs.getString(1));
								j++;
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
				if(e.getActionCommand().equals("limpiar")){
				//vaciamos todos los txtboxs
					jtxt[0].setText(null);
					jtxt[1].setText(null);
					jtxt[2].setText(null);
					jtxt[3].setText(null);
				//vaciamos fechas
					jdc1.setDate(null);
					jdc2.setDate(null);
				//vaciamos comboboxs relacionados
					cbcdestino.removeAllItems(); 
					cbcsalida.removeAllItems();
					cbproyecto.removeAllItems(); 
				//ponemos a null los comboboxs principales
					cbpartner.setSelectedItem(null);
					cbpdestino.setSelectedItem(null);
					cbpsalida.setSelectedItem(null);
				//quitamos cualquier mensage mostrado en el panel superior
					mensage.setVisible(false);
				}
				//realizamos el alta
				if(e.getActionCommand().equals("aceptar")){
					System.out.println("entra");
					comprobar_campos();//comprobamos la validez de los datos introducidos
					if(OKalta = true){
						//guardamos el codigo del partner
						rs= conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like '"+cbpartner.getSelectedItem().toString()+"'");
						int partner = 0;
						try {
							rs.next();
							partner = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//guardamos el codigo del proyecto
						rs = null;
						rs = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre like '"+cbproyecto.getSelectedItem().toString()+"'");
						int proyecto = 0;
						try {
							rs.next();
							proyecto = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		
						//guardamos el id del pais de origen
						rs = null;
						rs = conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpsalida.getSelectedItem().toString()+"'");
						int paissalida = 0;
						try {
							rs.next();
							paissalida = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//guardamos el id de la provincia de salida
						rs = null;
						
						rs = conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcsalida.getSelectedItem().toString()+"'");
						int prosalida = 0;
						try {
							rs.next();
							prosalida = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	
						//guardamos el id del pais de destino
						rs = null;
						rs= conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpdestino.getSelectedItem().toString()+"'");
						int paisdestino = 0;
						try {
							rs.next();
							paisdestino = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						//guardamos el id de la provincia de destino
						rs = null;
						rs= conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcdestino.getSelectedItem().toString()+"'");
						int prodestino = 0;
						try {
							rs.next();
							prodestino = Integer.parseInt(rs.getString(1));
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//cambiamos fechas a sql
						java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
						System.out.println(sqlDate1);
						System.out.println(sqlDate2);
						
						//obtenemos los gastos
						float viaje = Float.valueOf(jtxt[2].getText());
						float otros = Float.valueOf(jtxt[3].getText());
						//realizamos el alta
						conexion.executeUpdate("INSERT INTO TRAVEL_SUBSISTENCE (partner,nombrepersona,motivoviaje,paissalida,ciudad_salida,paisdestino,ciudad_destino,fecha_ini,fecha_fin,coste_viaje,coste_subsistencia,id_pro) VALUES ('"
							+partner+"','"+jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+paissalida+"','"+prosalida+"','"+paisdestino+"','"+prodestino+"','"+sqlDate1+"','"+sqlDate2+"','"+viaje+"','"+otros+"','"+proyecto+"')");
							
						//mensage de confirmacion del alta realizada
						
							alerta.setText(rec.idioma[rec.eleidioma][60]);
							mensage.setBackground(Color.decode("#D0E495"));
							mensage.setVisible(true);
						
					}
				}
			}
		};
		
		cbpartner.addActionListener(accion);
		cbpsalida.addActionListener(accion);
		cbpdestino.addActionListener(accion);
		jbtnaceptar.addActionListener(accion);
		jbtnlimpiar.addActionListener (accion);
		cbpartner.setActionCommand("cbpartner");
		cbpsalida.setActionCommand("cbsalida");
		cbpdestino.setActionCommand("cbdestino");
		jbtnaceptar.setActionCommand("aceptar");
		jbtnlimpiar.setActionCommand("limpiar");
		
		contenedor.setLayout(new BorderLayout());
		contenedor.setOpaque(true);
		contenedor.add(mensage,BorderLayout.NORTH);
		contenedor.add(panel,BorderLayout.CENTER);
		this.setViewportView(contenedor);
		
		
	/*	
		accion = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				comprobar_campos();
				if(e.getActionCommand().equals("aceptar") && OKalta == true){
					
						ConexionDb conexdb = new ConexionDb();
						conexdb.Conectardb();
						
						
						rs = conexdb.ConsultaSQL("Select cod_part FROM PARTNER WHERE nombre ='" + cbpartner.getSelectedItem().toString()+"'");
						if (rs!= null){
							try {
								rs.next();
								String partner = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						rs = conexdb.ConsultaSQL("Select id_pro FROM PROYECTO WHERE nombre ='" + cbproyecto.getSelectedItem().toString()+"'");
						if (rs!= null){
							try {
								rs.next();
								String proyecto = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						String paissalida;
						rs = conexdb.ConsultaSQL("Select id_pais FROM PAIS WHERE pais='" + cbpsalida.getSelectedItem().toString()+"'");
						if (rs!= null){
							try {
								rs.next();
								paissalida = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
						rs = conexdb.ConsultaSQL("Select id_provincias FROM PROVINCIAS WHERE estado='" + cbcsalida.getSelectedItem().toString() + "'" + "AND id_pais='" + paissalida + "'");
						if (rs!= null){
							try {
								rs.next();
								String provinciasalida = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						String paisdestino;
						rs = conexdb.ConsultaSQL("Select id_pais FROM PAIS WHERE pais='" + cbpdestino.getSelectedItem().toString()+"'");
						if (rs!= null){
							try {
								rs.next();
								paisdestino = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
						rs = conexdb.ConsultaSQL("Select id_provincias FROM PROVINCIAS WHERE estado='" + cbcdestino.getSelectedItem().toString() + "'" + "AND id_pais='" + paisdestino + "'");
						if (rs!= null){
							try {
								rs.next();
								String provinciadestino = Integer.toString(rs.getInt(1));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
											
						conexion.executeUpdate("INSERT INTO SUBCONTRATA (nombre,sector,pais,provincia,direccion,cod_postal,telf,observaciones) VALUES ('"+ 
								jtxt[0].getText()+"','"+idsector+"','"+idpais+"','"+idprovincia+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+textarea.getText()+"')");
						JOptionPane.showMessageDialog(mensage,"Subcontrata dada de alta");
						
						
					for(int i=0;i<4;++i) {	
						jtxt[i].setText("");
					}
					cbcsalida.removeAllItems();
					textarea.setText(null);
					mensage.setVisible(false);
					PnlMod_Sub.actualizar_tabla();
				
					cbcdestino.removeAllItems();
					textarea.setText(null);
					mensage.setVisible(false);
					PnlMod_Sub.actualizar_tabla();
				
				}else{
					alerta.setText(rec.idioma[rec.eleidioma][79]);
					mensage.setBackground(Color.decode("#ec8989"));
					mensage.setVisible(true);
					OKalta = false;
				}
				
				
				// Borrar cuando damos al boton cancelar
				if( e.getActionCommand().equals("cancelar")){
					for(int i=0;i<4;++i) {	
						jtxt[i].setText("");
					}
					cbcdestino.removeAllItems();
					cbcsalida.removeAllItems();
					textarea.setText(null);
					alerta.setText("");
					mensage.setVisible(false);
				}
			}
				
			};
*/
		
		this.setVisible(true);
		//filtro para solo admitir numeros en los costes
		jtxt[2].addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
				      caracter = e.getKeyChar();
				      if(((((caracter < '0') && (caracter != '.')) || ((caracter > '9') && (caracter != '.'))) &&
				         (caracter != KeyEvent.VK_BACK_SPACE))) {
				    	  //nos aseguramos que no existan mas puntos con anterioridad, si lo es no nos dejara añadir el recienpusado
				    	  e.consume();
				    	  
				      }
				      if(caracter == '.'){
				    		 cantidad2 = String.valueOf(jtxt[3].getText());
				    		if(cantidad2.indexOf('.')>0){
				    			 e.consume();
				    		} 
				      }
				   }
			});
		jtxt[3].addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
			      caracter = e.getKeyChar();
			      if(((((caracter < '0') && (caracter != '.')) || ((caracter > '9') && (caracter != '.'))) &&
			         (caracter != KeyEvent.VK_BACK_SPACE))) {
			    	  //nos aseguramos que no existan mas puntos con anterioridad, si lo es no nos dejara añadir el recienpusado
			    	  e.consume();
			    	  
			      }
			      if(caracter == '.'){
			    		 cantidad2 = String.valueOf(jtxt[3].getText());
			    		if(cantidad2.indexOf('.')>0){
			    			 e.consume();
			    		} 
			      }
			   }
		});	
	}
	//nos aseguramos de que los campos sean validos
	public void comprobar_campos(){
		/*
		 * Revisamos si estan rellenados todos los camps obligatorios
		 */
				
		OKalta = true;
		for(int Y=0;Y<4;++Y) {
			if (jtxt[Y].getText().length() > 0){
			}else{
				OKalta = false;
				//informamos del mal alta
				mensage.setBackground(Color.decode("#ec8989"));
				mensage.setVisible(true);
			}
		}
		if((cbpartner.getSelectedItem() != null) && (cbproyecto.getSelectedItem() != null) && (cbpsalida.getSelectedItem() != null)&& (cbcsalida.getSelectedItem() != null)&& (cbpdestino.getSelectedItem() != null)&& (cbcdestino.getSelectedItem() != null)){	
			
		}else{
			OKalta = false;
			alerta.setText(rec.idioma[rec.eleidioma][79]);
			mensage.setBackground(Color.decode("#ec8989"));
			mensage.setVisible(true);
		}	
	}
	
}
