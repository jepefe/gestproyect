package pkGesproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.RosterPacket;

public class MsgInt implements RosterListener{
	String nombre;
	 XMPPConnection connection;
	 RsGesproject recursos =  RsGesproject.instancia;
	 Vector<Contacto> contactos = new Vector<Contacto>();
	 Roster roster;
	 String usr;
	public MsgInt(String usr,String pass){
		recursos.instanciamsg = this;
		this.usr = usr;
		try {
			this.login(usr,pass);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void login(String userName, String password) throws XMPPException {
        ConnectionConfiguration config = new ConnectionConfiguration(recursos.JABBERSERVR, 5222);
        config.setSendPresence(false);
        connection = new XMPPConnection(config);
        connection.connect();
       
        SASLAuthentication.supportSASLMechanism("SASL", 0);
        connection.login(userName, password);
      //  connection.sendPacket(new Presence(Presence.Type.unavailable));
        roster = connection.getRoster();
        roster.setSubscriptionMode(Roster.SubscriptionMode.accept_all);
        roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.accept_all);
        
        
       
       
    }
	
	 public void mostrarContactos(PnlContactos pnlcont) {
	       
	        roster.addRosterListener(this);
	       ResultSet rs =  ConexionDbUnica.instancia.ConsultaSQL("SELECT nombre FROM PARTNER WHERE cod_part='"+recursos.getCodparter()+"'");
	       try {
			if(rs.next()){
			   try {
				nombre = rs.getString(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       if(roster.getGroup(nombre)!=null){
	        Collection<RosterEntry> entries = roster.getGroup(nombre).getEntries();

	        System.out.println("\n\n" + entries.size() + " buddy(ies):");
	        for (RosterEntry r : entries) {
	        	String usuario = r.getUser().substring(0,r.getUser().indexOf("@")); //Quitamos la parte del servidor
	            recursos.instanciacontactos.AnyadirAContactos(usuario);
	        }
	       }
	    }

	@Override
	public void entriesAdded(Collection<String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entriesDeleted(Collection<String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entriesUpdated(Collection<String> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void presenceChanged(Presence p) {
		for(Contacto c:contactos){
			String usuario = p.getFrom().substring(0,p.getFrom().indexOf("@")); //Quitamos la parte del servidor
			
			if(c.usuario.equals(usuario)){
				c.cambiarEstado(p.getType().name());
			}
		}
		
	}
	
	public void consultarEstado(){
		
		for(Contacto c:contactos){
			c.cambiarEstado(roster.getPresence(c.usuario).getType().name());
	
		}
		
		}
	
		
	}
	 
	 
	 
	
	

