package pkgesproyect;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
//Creamos un panel con varios objetos para demostracion
public class PanelPreferencias extends JPanel {
	JButton Boton1 = new JButton("Boton 1");
	JProgressBar jpb = new JProgressBar();
	JProgressBar jpb2 = new JProgressBar();	
	JCheckBox jcb1 = new JCheckBox("Casilla 1");
	JCheckBox jcb2 = new JCheckBox("Casilla 2");
	JCheckBox jcb3 = new JCheckBox("Casilla 3");
	JRadioButton jrb = new JRadioButton("Hola!!");
	public PanelPreferencias(){
		this.setLayout(new FlowLayout());
		this.add(jpb);
		jpb.setMinimum(0);
		jpb.setMaximum(100);
		jpb.getModel().setValue(33);
		jpb.setStringPainted(true);
		jpb2.setIndeterminate(true);
		this.add(jpb2);
		this.add(Boton1);
		this.add(jcb1);
		this.add(jcb2);
		this.add(jcb3);
		this.add(jrb);
		
		
	}
	



}
