package pkGesproject;

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

public class MsgInt implements RosterListener{
	
	 XMPPConnection connection;
	 RsGesproject recursos =  RsGesproject.instancia;
	 Vector<Contacto> contactos = new Vector<Contacto>();
	 
	public MsgInt(String usr,String pass){
		recursos.instanciamsg = this;
		try {
			this.login(usr,pass);
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void login(String userName, String password) throws XMPPException {
        ConnectionConfiguration config = new ConnectionConfiguration(recursos.JABBERSERVR, 5222);
        connection = new XMPPConnection(config);

        connection.connect();
        SASLAuthentication.supportSASLMechanism("SASL", 0);
        connection.login(userName, password);
    }
	
	 public void mostrarContactos(PnlContactos pnlcont) {
	        Roster roster = connection.getRoster();
	        roster.addRosterListener(this);
	        Collection<RosterEntry> entries = roster.getGroup("Gesproject").getEntries();

	        System.out.println("\n\n" + entries.size() + " buddy(ies):");
	        for (RosterEntry r : entries) {
	        	String usuario = r.getUser().substring(0,r.getUser().indexOf("@")); //Quitamos la parte del servidor
	            recursos.instanciacontactos.AnyadirAContactos(usuario);
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
				c.cambiarEstado(p.getStatus());
			}
		}
		
	}
	 
	 
	 
	
	
}
