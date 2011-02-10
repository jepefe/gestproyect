package pkGesproject.Preferencias;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.RsGesproject;

public class PnlCuentaUsuario extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JLabel jlbl[];
	JTextField jtxt[];
	JButton jbtnaplicar,jbtncancelar;
	GpComboBox gbidioma;
	GridBagConstraints gbc;
	JPanel contraseña;
	
	public PnlCuentaUsuario(){
		//llamamos al método para crear la interfaz
		crear_interfaz();
		
		
	}
	
	
	public void crear_interfaz(){
		this.setLayout(new GridBagLayout());
		jlbl = new JLabel[5];
		jtxt = new JTextField[3];

		gbc = new GridBagConstraints();
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		gbc.insets = new Insets(20,30,0,10);
		this.add(jlbl[0] = new JLabel(rec.idioma[rec.eleidioma][146]),gbc);
		
		gbc.gridx = 1; // El área de texto empieza en la columna
		this.add(gbidioma = new GpComboBox(),gbc);
		
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila
		//contraseña.setLayout(new GridBagLayout());
		
		
		
		//hacemos visible el panel
		this.setVisible(true);
	}
}
