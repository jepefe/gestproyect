package pkgesproyect;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
/*ESTA CLASE LA HE CREADO PARA PROBAR COSAS 
 * BORRAR SI ES NECESARIO
 */

public class panelLogin extends JPanel {
	public panelLogin() {
		JTextField jtb = new JTextField();
		JTextField jtb2 = new JTextField();
		JButton boton1 = new JButton("Aceptar"); 
		JButton boton2 = new JButton("Cancelar");
		this.setBounds(0, 0, 600, 400);
		this.add(jtb);
		this.add(jtb2);
		this.add(boton1);
		this.add(boton2);
		this.setOpaque(true);
		
	}
	

}