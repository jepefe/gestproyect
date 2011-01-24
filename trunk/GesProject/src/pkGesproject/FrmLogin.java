package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

public class FrmLogin extends JFrame implements ActionListener{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JButton jbtnaceptar = new JButton("Login"); //new JButton(rec.idioma[rec.eleidioma][1]);
	JButton jbtncancelar = new JButton("Cancel"); //new JButton(rec.idioma[rec.eleidioma][2]);
	JTextField jtxfUsuario = new JTextField("floridae");
	JPasswordField jpwfPassword = new JPasswordField("123");
	GridBagConstraints cons = new GridBagConstraints();
	JLabel jlblUsuario = new JLabel("User: ");//new JLabel(rec.idioma[rec.eleidioma][10]);
	JLabel jlblPassword = new JLabel("Password: ");//new JLabel(rec.idioma[rec.eleidioma][11]);
	JPanel jpnl_conexion = new JPanel();
	JPanel jpnl_login = new JPanel();
	JLabel jlbconexion = new JLabel();
	Runnable servdisp;
	Thread hilo;
	boolean validado = false;
	InetAddress addr;

	
	
	public FrmLogin(String titulo, int x, int y){
		super(titulo);
		this.setSize(x,y);
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);//Esta linea es necesaria para que la barra de titulo y el jtoolbar sean homogeneos
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		jpnl_login.setLayout(new GridBagLayout());
		this.inicializar();
		this.ComprobarConexion();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		validate();
	}
	
	
	public void ComprobarConexion(){
		servdisp = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				try {
					addr = InetAddress.getByName(recursos.DBSERVER);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					
				}
				while(!validado){
					
		
				
					
					try {
						addr.isReachable(2000);
						jpnl_conexion.setVisible(false);
						  jbtnaceptar.setEnabled(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						jlbconexion.setText("Error: Server not available, verify your connection");
						jpnl_conexion.setVisible(true);
						  jbtnaceptar.setEnabled(false);
					}
					
				
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			
		};
		hilo = new Thread(servdisp);
		hilo.start();
	}
	
	
	
	public void inicializar(){
		
		
		
			KeyListener kl = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent k) {
				
								
			}

			@Override
			public void keyReleased(KeyEvent k) {
				if(k.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				
				}
			}

			@Override
			public void keyTyped(KeyEvent k) {
				
			}
			
		};
		jpwfPassword.addKeyListener(kl);
		jtxfUsuario.addKeyListener(kl);
		
		jpnl_conexion.setVisible(false);
		this.add(jpnl_conexion,BorderLayout.NORTH);
		this.add(jpnl_login,BorderLayout.CENTER);
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(this);
		jtxfUsuario.setColumns(10);
		jpwfPassword.setColumns(10);
		this.setResizable(false);
		
		jpnl_conexion.setBackground(Color.decode("#D0E495"));
		jlbconexion.setFont(new Font(Font.SANS_SERIF, Font.BOLD,11));
		jpnl_conexion.add(jlbconexion);
		
		
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		jpnl_login.add(jlblUsuario, cons);
		jlblUsuario.setFont(recursos.f);
		
		cons.gridx = 0;
		cons.gridy = 1;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		jpnl_login.add(jlblPassword, cons);
		jlblPassword.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridwidth=2;
		cons.gridheight=1;
		cons.weighty = 0;
		jpnl_login.add(jtxfUsuario, cons);
		jtxfUsuario.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 1;
		cons.gridwidth=2;
		cons.gridheight=1;
		cons.weighty = 0;
		jpnl_login.add(jpwfPassword, cons);
		
		
		cons.gridx = 0;
		cons.gridy = 2;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.WEST;
		jpnl_login.add(jbtnaceptar, cons);
		jbtnaceptar.setFont(recursos.f);
		
		cons.gridx = 1;
		cons.gridy = 2;
		cons.gridwidth=1;
		cons.gridheight=1;
		cons.weighty = 0;
		cons.anchor = GridBagConstraints.EAST;
		jpnl_login.add(jbtncancelar, cons);
		jbtncancelar.setFont(recursos.f);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getActionCommand().equals("aceptar")){
		login();
	}
	}
	
	
	public void login(){
		
		ConexionDbUnica.instancia.Conectardb();
		rec.inicializar();
		ConexionDb conexdb = new ConexionDb();
		ResultSet rs;
		conexdb.Conectardb();
		rs = conexdb.ConsultaSQL("Select id_staff,password,nick_usuario,idioma from STAFF where nick_usuario = '" + jtxfUsuario.getText()+ "'");
	try {
		if(!rs.next()){
			jlbconexion.setText("Incorrect USER/PASSWORD");
			jpnl_conexion.setVisible(true);
			System.out.println("Contraseña incorrecta");
		}else{
		rs.beforeFirst();	//Volvemos al la primera fila	
		while ((validado == false) && rs.next()) 
		{ 
			if ((rs.getString(2).compareTo(jpwfPassword.getText())==0) && (rs.getString(3).compareTo(jtxfUsuario.getText())==0)){
				validado = true;
				jpnl_conexion.setVisible(false);
				recursos.setIdusuario(rs.getInt(1));
				rec.eleidioma = rs.getInt(4);
				System.out.println(recursos.getIdusuario() + "idioma:"+rec.eleidioma);
				FrmPrincipal vppal = new FrmPrincipal();
				recursos.getRfrmppal().inicializar();
				
				//if (rs.getInt(4) != null)
				
				
				this.dispose();
			}
			else{
				jlbconexion.setText("Incorrect USER/PASSWORD");
				jpnl_conexion.setVisible(true);
				System.out.println("Contraseña incorrecta");
	
		}
		}
		}
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		conexdb.cerrarConexion();
	/*try {
		//System.out.println(Boolean.toString(conexdb.conexion.isClosed()));
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
	}
	
}
