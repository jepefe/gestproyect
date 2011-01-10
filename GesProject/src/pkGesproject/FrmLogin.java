package pkGesproject;

import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

public class FrmLogin extends JFrame implements ActionListener{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JButton jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][1]);
	JButton jbtncancelar = new JButton(rec.idioma[rec.eleidioma][2]);
	JTextField jtxfUsuario = new JTextField("berna");
	JPasswordField jpwfPassword = new JPasswordField("123");
	GridBagConstraints cons = new GridBagConstraints();
	JLabel jlblUsuario = new JLabel(rec.idioma[rec.eleidioma][10]);
	JLabel jlblPassword = new JLabel(rec.idioma[rec.eleidioma][11]);
	
	public FrmLogin(String titulo, int x, int y){
		super(titulo);
		this.setSize(x,y);
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);//Esta linea es necesaria para que la barra de titulo y el jtoolbar sean homogeneos
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		this.inicializar();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		validate();
	}
	
	public void inicializar(){
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(this);
		jtxfUsuario.setColumns(10);
		jpwfPassword.setColumns(10);
		this.setResizable(false);
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		this.add(jlblUsuario, cons);
		jlblUsuario.setFont(recursos.f);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		this.add(jlblPassword, cons);
		jlblPassword.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth=2;
		cons.gridheight=1;
		cons.weighty = 0;
		this.add(jtxfUsuario, cons);
		jtxfUsuario.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth=2;
		cons.gridheight=1;
		cons.weighty = 0;
		this.add(jpwfPassword, cons);
		
		
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.WEST;
		this.add(jbtnaceptar, cons);
		jbtnaceptar.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.EAST;
		this.add(jbtncancelar, cons);
		jbtncancelar.setFont(recursos.f);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("aceptar")){
			ConexionDb conexdb = new ConexionDb();
			ResultSet rs;
			conexdb.Conectardb();
			rs = conexdb.ConsultaSQL("Select id_staff,password,nick_usuario,idioma from STAFF where nick_usuario = '" + jtxfUsuario.getText()+ "'");
		try {
			while (rs.next()) 
			{ 
				if ((rs.getString(2).compareTo(jpwfPassword.getText())==0) && (rs.getString(3).compareTo(jtxfUsuario.getText())==0)){
					recursos.setIdusuario(rs.getInt(1));
					rec.eleidioma = rs.getInt(4);
					System.out.println(recursos.getIdusuario() + "idioma:"+rec.eleidioma);
					FrmPrincipal vppal = new FrmPrincipal();
					recursos.getRfrmppal().inicializar();
					//if (rs.getInt(4) != null)
					this.dispose();
				}
				else{
					System.out.println("Contrase�a incorrecta");
		
			}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			conexdb.cerrarConexion();
		try {
			System.out.println(Boolean.toString(conexdb.conexion.isClosed()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}
}
