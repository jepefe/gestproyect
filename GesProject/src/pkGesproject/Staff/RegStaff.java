package pkGesproject.Staff;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;


/**
 * Esta clase se encarga del evento genrado al dar de alta un nuevo Staff
 * crea una nueva ficha de Staff en la base de tados.
 * 
 * @author Freyder Espinosa Valencia
 *
 */
public class RegStaff extends pnlAlta_staff implements java.awt.event.ActionListener{

	@Override
	/*Redefinimos el m�todo action performe para ejecutarlo como
	 * respuesta al eventeo evt producido 
	 */
	public void actionPerformed(ActionEvent e) {
	
		java.awt.event.ActionListener crear = new RegStaff();
		
		
		
		//AbstractButton //jbtnCrear;
		//jbtnCrear.addActionListener(crear);
		//System.out.println("Usuario a�adido correctamente");
	}

}
