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
	JLabel jlblpartner,jlblsalida,jlbldestino,jlblf_ini,jlblf_fin;
	JButton jbtnaceptar, jbtnlimpiar;
	GpComboBox cbpartner,cbcsalida,cbpsalida,cbcdestino,cbpdestino;
	ConexionDb conexion= new ConexionDb();
	ResultSet rs;
	JDateChooser jdc1,jdc2;
	Component aviso;
	int i;
	
	public PnlAlta_viajes(){
		this.setLayout(new GridBagLayout());
		
		/**
		 * Creamos un array de Strings que utilizaremos para los nombres de los Labels
		 */
		
		String[] fieldNames = {
				rec.idioma[rec.eleidioma][89]+"*",
				rec.idioma[rec.eleidioma][90]+"*",rec.idioma[rec.eleidioma][93]+"*",
				rec.idioma[rec.eleidioma][94]+"*",
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
				this.add(jlblpartner=new JLabel(rec.idioma[rec.eleidioma][57]),gbc);
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
				
			}
			
			if(i==2){
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblsalida=new JLabel(rec.idioma[rec.eleidioma][91]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbpsalida = new GpComboBox(),gbc);
				try {
					while(rs.next()){
						cbpsalida.addItem(rs.getString(1));
					
					}
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
				this.add(jlbldestino=new JLabel(rec.idioma[rec.eleidioma][92]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				rs=conexion.ConsultaSQL("SELECT p.pais FROM PAIS p");
				this.add(cbpdestino = new GpComboBox(),gbc);
				try {
					while(rs.next()){
						cbpdestino.addItem(rs.getString(1));
					
					}
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
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][25]+"*"),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][26]+"*"),gbc);
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
		this.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(jbtnlimpiar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
	
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("cbsalida")){
					int pais = 0;
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
				
				if(e.getActionCommand().equals("cbdestino")){
					int pais = 0;
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
				
				if(e.getActionCommand().equals("aceptar")){
					System.out.print("entra");
					if(jtxt[0].equals("")||jtxt[1].equals("")||jtxt[2].equals("")||jtxt[3].equals("")){
						JOptionPane.showMessageDialog(aviso,"Revisa los campos obligatorios");
					}else{
						
						rs= conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like '"+cbpartner.getSelectedItem().toString()+"'");
						
						String partner = null;
						try {
							rs.next();
							partner = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
								rs= conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpsalida.getSelectedItem().toString()+"'");
								
								String paissalida = null;
							try {
								rs.next();
								paissalida = rs.getString(1);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							rs = null;
							rs= conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcsalida.getSelectedItem().toString()+"'");
							
							String ciudadsalida = null;
						try {
							rs.next();
							ciudadsalida = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
						rs = null;
						rs= conexion.ConsultaSQL("SELECT id_provincias FROM PROVINCIAS WHERE estado like '"+cbcdestino.getSelectedItem().toString()+"'");
							
							String ciudaddestino = null;
						try {
							rs.next();
							ciudaddestino = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
						conexion.executeUpdate("INSERT INTO TRAVEL_SUBSISTENCE (partner,nombrepersona,motivoviaje,paissalida,ciudad_salida,paisdestino,ciudad_destino,fecha_ini,fecha_fin,coste_viaje,coste_subsistencia,gastos_totales) VALUES" +
								"("+partner+",'"+jtxt[0].getText()+"','"+jtxt[1].getText()+"',"+paissalida+","+ciudadsalida+","+paisdestino+","+ciudaddestino+",'"+jdc1.getDate()+"','"+jdc2.getDate()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"')");
						JOptionPane.showMessageDialog(aviso,"Subido");
				
					}
				}
			}
			
		};
		
		
		cbpsalida.addActionListener(accion);
		cbpdestino.addActionListener(accion);
		jbtnaceptar.addActionListener(accion);
		cbpsalida.setActionCommand("cbsalida");
		cbpdestino.setActionCommand("cbdestino");
		jbtnaceptar.setActionCommand("aceptar");
		this.setVisible(true);
	}
}
