package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import ar.com.fdvs.dj.domain.constants.Border;



public class PnlPrincipal extends JPanel {
	JPanel jpnlinfousuario = new JPanel();
	JPanel jpnlproyecto = new JPanel();
	
	JImageContainer jicusu = new JImageContainer();
	RsGesproject recursos = RsGesproject.instancia;
	ConexionDb cdb = new ConexionDb();
	ResultSet rs;
	JLabel jlbnombre = new JLabel();
	JLabel jlbapellidos = new JLabel();
	JLabel jlbciudad = new JLabel();
	JLabel jlbregion = new JLabel();
	JLabel jlbtelefono = new JLabel();
	JLabel jlbemail = new JLabel();
	JLabel jlbpartner = new JLabel();
	JLabel jlbFoto = new JLabel();
	JLabel jlbLogo = new JLabel();
	JLabel jlbpartnombre = new JLabel();
	JLabel jlbpartsector = new JLabel();
	JLabel jlbpartdireccion = new JLabel();
	JLabel jlbpartcodpostal = new JLabel();
	JLabel jlbpartpais = new JLabel();
	JLabel jlbpronom = new JLabel();
	JLabel jlbprodesc = new JLabel();
	JLabel jlbpropresu = new JLabel();
	JLabel jlbprofini = new JLabel();
	JLabel jlbproffin = new JLabel();
	JLabel jlbprocontrato = new JLabel();
	JComboBox jcbproyecto = new JComboBox();
	ActionListener alpro;
	GesIdioma rec = GesIdioma.obtener_instancia();
	Integer[] proyectos;
	
	public PnlPrincipal(){
		
		this.setLayout(new BorderLayout());
		CargaInfoUsuario();
		CargarPanelProyecto();
		this.add(jpnlinfousuario,BorderLayout.WEST);
		//this.setOpaque(true);
		
		this.add(jpnlproyecto,BorderLayout.CENTER);
	}
	
	
	
	
	
	
	public void CargaInfoUsuario(){
		
		jlbapellidos.setFont(recursos.fuente);
		jlbnombre.setFont(recursos.fuente);
		jlbpartner.setFont(recursos.fuente);
		jlbciudad.setFont(recursos.fuente);
		jlbregion.setFont(recursos.fuente);
		jlbtelefono.setFont(recursos.fuente);
		
		
		
		jlbemail.setFont(recursos.fuente);
		jpnlinfousuario.setLayout(new BoxLayout(jpnlinfousuario,1));
		jlbFoto.setIcon(recursos.icono[1]);
		jpnlinfousuario.setBackground(new Color(0xED,0xED,0xED));
		jpnlinfousuario.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
		
		Thread hilofoto = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				jlbFoto.setPreferredSize(new Dimension(130,130));
				
				jlbFoto.setIcon(new ImageIcon(cftp.ObtenerImagen("fto"+Integer.toString(recursos.getIdusuario())).getScaledInstance(130, 130, 0)));
				
				System.out.println("FOTO OBTENIDA");
				
				jlbFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				
			
				
			}
		});
		hilofoto.start();
		
	Thread hilologo = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				jlbLogo.setPreferredSize(new Dimension(190,50));
				
				jlbLogo.setIcon(new ImageIcon(cftp.ObtenerImagen("logo"+Integer.toString(recursos.getCodparter())).getScaledInstance(190, 50, 0)));
				
				System.out.println("LOGO OBTENIDO");
				
				jlbLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				
			
				
			}
		});
		hilologo.start();
		
		rs = cdb.ConsultaSQL("SELECT nombre,apellidos,ciudad,region,telefono,email FROM STAFF WHERE id_staff ='"+Integer.toString(recursos.getIdusuario())+"'");
		try {
			if(rs.next()){
				jlbnombre.setText(" "+rec.idioma[rec.eleidioma][3]+": " + rs.getString(1));
				jlbapellidos.setText(" "+rec.idioma[rec.eleidioma][28]+": " +  rs.getString(2));
				jlbciudad.setText(" "+rec.idioma[rec.eleidioma][48]+": " +  rs.getString(3));
				jlbregion.setText(" "+rec.idioma[rec.eleidioma][47]+": " +  rs.getString(4));
				jlbtelefono.setText(" "+rec.idioma[rec.eleidioma][69]+": " +  rs.getString(5));
				jlbemail.setText(" "+rec.idioma[rec.eleidioma][9]+": " +  rs.getString(6));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		rs = cdb.ConsultaSQL("SELECT nombre,sector, direccion,codpostal,pais FROM PARTNER WHERE cod_part='"+Integer.toString(recursos.getCodparter())+"'");
		try {
			if(rs.next()){
				jlbpartner.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(1));
				jlbpartsector.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(2));
				jlbpartdireccion.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(3));
				jlbpartcodpostal.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(4));
				jlbpartpais.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpnlinfousuario.add(jlbFoto);
		jpnlinfousuario.add(jlbnombre);
		jpnlinfousuario.add(jlbapellidos);
		jpnlinfousuario.add(jlbciudad);
		jpnlinfousuario.add(jlbregion);
		jpnlinfousuario.add(jlbtelefono);
		jpnlinfousuario.add(jlbemail);
		jpnlinfousuario.add(jlbLogo);
		jpnlinfousuario.add(jlbpartner);
		jpnlinfousuario.add(jlbpartsector);
		jpnlinfousuario.add(jlbpartdireccion);
		jpnlinfousuario.add(jlbpartcodpostal);
		jpnlinfousuario.add(jlbpartpais);
		
		
	}
	public void CargarPanelProyecto() {
	
		
	
		jlbpronom.setText(" "+rec.idioma[rec.eleidioma][111]);
		jlbprodesc.setText(" "+rec.idioma[rec.eleidioma][41]);
		jlbpropresu.setText(" "+rec.idioma[rec.eleidioma][13]);
		jlbprofini.setText(" "+rec.idioma[rec.eleidioma][25]);
		jlbproffin.setText(" "+rec.idioma[rec.eleidioma][26]);
		jlbprocontrato.setText(" "+rec.idioma[rec.eleidioma][101]);
		jpnlproyecto.setLayout(null);
		jpnlproyecto.add(jlbpronom);
		jpnlproyecto.add(jlbprofini);
		jpnlproyecto.add(jlbproffin);
		jpnlproyecto.add(jlbpropresu);
		jpnlproyecto.add(jlbprocontrato);
		jpnlproyecto.add(jlbprodesc);
		jpnlproyecto.setVisible(true);
		jpnlproyecto.setOpaque(true);
		
		jcbproyecto.addActionListener(alpro);
		ConexionDb cdb = new ConexionDb();
		ResultSet rs;
		rs = GesStaff.allowedProyects();
		
		try {
			
			
			if(rs.next()){
				
				rs.last();
				proyectos = new Integer[rs.getRow()];
				rs.beforeFirst();
				while(rs.next()){
					proyectos[rs.getRow()-1] = rs.getInt(1);
					jcbproyecto.addItem(rs.getString(2));
					
					
				}
				
				jpnlproyecto.add(jcbproyecto);
				
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin,num_contrato,Coordinador FROM PROYECTOS WHERE id_pro='"+proyectos[jcbproyecto.getSelectedIndex()].toString() +"'");
		
		alpro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConexionDb cdb = new ConexionDb();
				ResultSet rs;
				rs = cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin,num_contrato,Coordinador FROM PROYECTOS WHERE id_pro='"+proyectos[jcbproyecto.getSelectedIndex()].toString() +"'");
				try {
					while(rs.next()){
						jlbpronom.setText(" "+rec.idioma[rec.eleidioma][111]+rs.getString(1));
						jlbprodesc.setText(" "+rec.idioma[rec.eleidioma][41]+rs.getString(2));
						jlbpropresu.setText(" "+rec.idioma[rec.eleidioma][13]+Integer.toString(rs.getInt(3)));
						jlbprofini.setText(" "+rec.idioma[rec.eleidioma][25]+rs.getDate(4).toString());
						jlbproffin.setText(" "+rec.idioma[rec.eleidioma][26]+rs.getDate(5).toString());
						jlbprocontrato.setText(" "+rec.idioma[rec.eleidioma][101]+rs.getString(6));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		};
	}
	
	

}
