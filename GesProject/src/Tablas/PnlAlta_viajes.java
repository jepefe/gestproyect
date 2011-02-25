package Tablas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.RsGesproject;

public class PnlAlta_viajes extends JPanel{

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
	
	public PnlAlta_viajes(){
		this.setLayout(new GridBagLayout());
		
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
		
		gbc.insets = new Insets(5,10,5,5);
		
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
				this.add(jlblpartner=new JLabel(rec.idioma[rec.eleidioma][57]),gbc);//Partner
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p");
				this.add(cbpartner = new GpComboBox(),gbc);
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
				this.add(jlblproyecto=new JLabel(rec.idioma[rec.eleidioma][55]),gbc);//Proyecto
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs2=conexion.ConsultaSQL("SELECT nombre FROM PROYECTOS");
				this.add(cbproyecto = new GpComboBox(),gbc);
				try {
					while(rs2.next()){
						cbproyecto.addItem(rs2.getString(1));
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cbproyecto.setPreferredSize(new Dimension(233,30));
				
			}
			
			if(i==2){
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblsalida=new JLabel(rec.idioma[rec.eleidioma][91]),gbc);//Ciudad y Pais de salida
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbpsalida = new GpComboBox(),gbc);
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
				this.add(jlblsalida=new JLabel(),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbcsalida = new GpComboBox(),gbc);
				
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlbldestino=new JLabel(rec.idioma[rec.eleidioma][92]),gbc);//Ciudad y Pais de destino
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbpdestino = new GpComboBox(),gbc);
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
				this.add(jlbldestino=new JLabel(),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbcdestino = new GpComboBox(),gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][25]+"*"),gbc);//Fecha inicio
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][26]+"*"),gbc);//Fecha fin
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc2,gbc);
				
				cbcsalida.setPreferredSize(new Dimension(233,30));
				cbpsalida.setPreferredSize(new Dimension(233,30));
				cbcdestino.setPreferredSize(new Dimension(233,30));
				cbpdestino.setPreferredSize(new Dimension(233,30));
			}
			
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.anchor = GridBagConstraints.EAST;
			this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		}
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);//Aceptar
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(jbtnlimpiar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);//Limpiar campos
	
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
				}
				//realizamos el alta
				if(e.getActionCommand().equals("aceptar")){
					System.out.println("entra");
					comprobar_campos();//comprobamos la validez de los datos introducidos
					if(OKalta = true){
						//guardamos el codigo del partner
						rs= conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like '"+cbpartner.getSelectedItem().toString()+"'");
						String partner = null;
						try {
							rs.next();
							partner = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//guardamos el codigo del proyecto
						rs = null;
						rs = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre like '"+cbproyecto.getSelectedItem().toString()+"'");
						String proyecto = null;
						try {
							rs.next();
							proyecto = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		
						//guardamos el id del pais de origen
						rs = null;
						rs = conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpsalida.getSelectedItem().toString()+"'");
						String paissalida = null;
						try {
							rs.next();
							paissalida = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						//guardamos el id de la provincia de salida
						rs = null;
						rs = conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcsalida.getSelectedItem().toString()+"'");
						String prosalida = null;
						try {
							rs.next();
							prosalida = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	
						//guardamos el id del pais de destino
						rs = null;
						rs= conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpdestino.getSelectedItem().toString()+"'");
						String paisdestino = null;
						try {
							rs.next();
							paisdestino = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						//guardamos el id de la provincia de destino
						rs = null;
						rs= conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcdestino.getSelectedItem().toString()+"'");
						String prodestino = null;
						try {
							rs.next();
							prodestino = rs.getString(1);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//cambiamos fechas a sql
						java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					
						
						conexion.executeUpdate("INSERT INTO TRAVEL_SUBSISTENCE (partner,nombrepersona,motivoviaje,paissalida,ciudad_salida,paisdestino,ciudad_destino,fecha_ini,fecha_fin,coste_viaje,coste_subsistencia,id_pro) VALUES ('"
								+partner+"','"+jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+paissalida+"','"+prosalida+"','"+paisdestino+"','"+prodestino+"','"+sqlDate1+"','"+sqlDate2+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"')");
							//JOptionPane.showMessageDialog(aviso,"Subido");
				
						
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
		this.setVisible(true);
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
			}	
		}
		if((cbpartner.getSelectedItem() != null) && (cbproyecto.getSelectedItem() != null) && (cbpsalida.getSelectedItem() != null)&& (cbcsalida.getSelectedItem() != null)&& (cbpdestino.getSelectedItem() != null)&& (cbcdestino.getSelectedItem() != null)){	
			
		}else{
			OKalta = false;
		}	
	}
}
