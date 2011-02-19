package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	JLabel jlbpartner = new JLabel();
	JLabel jlbFoto = new JLabel();
	GesIdioma rec = GesIdioma.obtener_instancia();
	
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
		rs = cdb.ConsultaSQL("SELECT nombre,apellidos FROM STAFF WHERE id_staff ='"+Integer.toString(recursos.getIdusuario())+"'");
		try {
			if(rs.next()){
				jlbnombre.setText(" "+rec.idioma[rec.eleidioma][3]+": " + rs.getString(1));
				jlbapellidos.setText(" "+rec.idioma[rec.eleidioma][28]+": " +  rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = cdb.ConsultaSQL("SELECT nombre,sector, direccion,codpostal,pais FROM PARTNER WHERE cod_part=(SELECT cod_part FROM STAFF WHERE id_staff ='"+Integer.toString(recursos.getIdusuario())+"')");
		try {
			if(rs.next()){
				jlbpartner.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpnlinfousuario.add(jlbFoto);
		jpnlinfousuario.add(jlbnombre);
		jpnlinfousuario.add(jlbapellidos);
		jpnlinfousuario.add(jlbpartner);
		
		
		
	}
	public void CargarPanelProyecto() {
		jpnlproyecto.setVisible(true);
		JComboBox jcbproyecto = new JComboBox();
		ConexionDb cdb = new ConexionDb();
		ResultSet rs;
		rs = GesStaff.allowedProyects();
		try {
			if(rs.next()){
				jcbproyecto.addItem(rs.getString(2));
				while(rs.next()){
					jcbproyecto.addItem(rs.getString(2));
					
				}
				jpnlproyecto.add(jcbproyecto);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
