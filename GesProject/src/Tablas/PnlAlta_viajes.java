package Tablas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	int i;
	
	public PnlAlta_viajes(){
		this.setLayout(new GridBagLayout());
		
		/**
		 * Creamos un array de Strings que utilizaremos para los nombres de los Labels
		 */
		
		String[] fieldNames = {
				rec.idioma[rec.eleidioma][89],
				rec.idioma[rec.eleidioma][90],rec.idioma[rec.eleidioma][93],
				rec.idioma[rec.eleidioma][94],
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
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][25]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblf_ini=new JLabel(rec.idioma[rec.eleidioma][26]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc2,gbc);
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		this.setVisible(true);
	}
}