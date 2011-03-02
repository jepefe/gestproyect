package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;


public class Contacto extends JPanel implements MessageListener{
	ConexionDb cdb = new ConexionDb();
	ConexionFTP cftp = new ConexionFTP();
	JLabel jlbllogo = new JLabel();
	JLabel jlblnombre = new JLabel();
	JLabel jlblcontacto =  new JLabel();
	JLabel jlbchatcon = new JLabel();
	ResultSet rs;
	String usuario;
	String nombre;
	String Codigo;
	String contacto;
	String id_staff;
	GridBagConstraints constraints = new GridBagConstraints();
	RsGesproject recursos = RsGesproject.instancia;
	JPanel chat = new JPanel();
	JTextPane jtxachat = new JTextPane();
	JTextPane jtxamsg = new JTextPane();
	JPanel jpContacto = new JPanel();
	Chat nuevoChat;
	String conversacion="";
	JScrollPane jsconv;
	JScrollPane jsmsg;
	GesIdioma rec = GesIdioma.obtener_instancia();
	boolean leido;
	
	public Contacto(){
		
	
	}
	public Contacto(String usuario){
		recursos.instanciamsg.contactos.add(this);
		jlblnombre.setFont(recursos.fuente);
		jlblcontacto.setForeground(Color.gray);
		jlbllogo.setIcon(recursos.icono[1]);
		jpContacto.setOpaque(true);
		this.setOpaque(true);
		this.usuario=usuario;
		CargaDatos();
		CargarFoto();
		crearChat();
		jtxachat.setContentType("text/html");
		jtxachat.setEditable(false);
		jtxachat.setAutoscrolls(true);
		constraints.insets = new Insets(0, 2, 0, 0);
		jpContacto.setLayout(new GridBagLayout());
		jpContacto.setPreferredSize(new Dimension(250,50));
		
		constraints.anchor=GridBagConstraints.LINE_START;
		constraints.fill = GridBagConstraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 2; 
		constraints.weighty = 1.0; 
		constraints.weightx = 1.0; 
		jpContacto.add(jlbllogo,constraints);
		//constraints.weighty = 0.0;
		constraints.anchor=GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.WEST;
		constraints.gridx = 2; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 2; 
		constraints.weighty = 1.0; 
		constraints.weightx = 1.0;
		jpContacto.add(jlblnombre,constraints);
		this.add(jpContacto);
		
		
	}
	
	
	
	public void CargaDatos(){
		rs = cdb.ConsultaSQL("SELECT nombre,apellidos,id_staff FROM STAFF WHERE nick_usuario='"+usuario+"'");
		try {
			if(rs.next()){
					nombre = rs.getString(1) +" "+ rs.getString(2);
					jlblnombre.setText(nombre);
					id_staff=rs.getString(3);
					
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstado(String estado){
		
		System.out.println(usuario+": "+estado);
		
		if(estado.equals("available")){
			jtxamsg.setEnabled(true);
			System.out.println(usuario+": disponible");
		}else{
			jtxamsg.setEnabled(false);
			System.out.println(usuario+": desconectado");
		}
			
		
	}
	
	public void CargarFoto(){
	Thread hilologo = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				jlbllogo.setPreferredSize(new Dimension(40,40));
				
				jlbllogo.setIcon(new ImageIcon(cftp.ObtenerImagen("fto"+id_staff).getScaledInstance(40, 40, 0)));
				
				System.out.println("AFOTO OBTENIDO");
				
				jlbllogo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				
			
				recursos.getRfrmppal().repaint();
			}
		});
		hilologo.start();
		
		
	}
	public void crearChat(){
		ChatManager chatmanager = recursos.instanciamsg.connection.getChatManager();
		nuevoChat = chatmanager.createChat(usuario+"@"+recursos.JABBERSERVR,this);
		jlbchatcon.setBackground(new Color(0x73,0xA6,0xFF));
		jlbchatcon.setForeground(Color.white);
		jlbchatcon.setHorizontalTextPosition(SwingConstants.CENTER);
		jlbchatcon.setText(nombre);
		jlbchatcon.setPreferredSize(new Dimension(250,30));
		jlbchatcon.setFont(recursos.fuente);
		jlbchatcon.setOpaque(true);

		jsconv = new JScrollPane(jtxachat);
		jsmsg = new JScrollPane(jtxamsg);
		jsmsg.setPreferredSize(new Dimension(250,100));
		jtxamsg.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				avisamsg(true);
				
			}
		});
		jsconv.setPreferredSize(new Dimension(250,300));
		chat.setLayout(new BorderLayout());
		chat.add(jlbchatcon,BorderLayout.NORTH);
		chat.add(jsconv,BorderLayout.CENTER);
		chat.add(jsmsg,BorderLayout.SOUTH);
		jtxamsg.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				jtxamsg.setCaretPosition(jtxamsg.getDocument().getLength());
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			    	 try {
						nuevoChat.sendMessage(jtxamsg.getText());
						conversacion = conversacion +"<b>"+rec.idioma[rec.eleidioma][183]+":</b><br>"+jtxamsg.getText()+"<br>";
						jtxachat.setText(conversacion);
						
					} catch (XMPPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        jtxamsg.setText("");
			        }
			}

			@Override
			public void keyReleased(KeyEvent e) {
			
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
		});
	}
	@Override
	public void processMessage(Chat arg0, Message msg) {
		// TODO Auto-generated method stub
		conversacion = conversacion +"<b>"+nombre+":</b><br BACKGROUND-COLOR:blue>"+msg.getBody()+"<br>";
		
		jtxachat.setText(conversacion);
		jtxachat.setCaretPosition(jtxachat.getDocument().getLength());
		System.out.println(msg.getBody()+"\n");
		avisamsg(false);
	}
	public void avisamsg(boolean leido){
		this.leido=leido;
		if(jtxamsg.isFocusOwner()){
			leido=true;
		}
		if(!leido){
			jpContacto.setOpaque(true);
			jpContacto.setBackground(Color.orange);
			recursos.getRfrmppal().repaint();
		}else{
			jpContacto.setOpaque(false);
			recursos.getRfrmppal().repaint();
		}
	}
	

}
