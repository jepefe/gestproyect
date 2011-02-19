package pkGesproject.Avisos;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

//import clases.SendAuthentication;



public class PnlSendMail extends JPanel{
	
	JButton aceptar;
	public PnlSendMail(){
		
		this.setLayout(new GridBagLayout());
		this.add(aceptar = new JButton("Envio Mail"));
		
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SendAuthentication.Send();
				
			}
			
		};
		
		aceptar.addActionListener(accion);
	}
	
	
	
}