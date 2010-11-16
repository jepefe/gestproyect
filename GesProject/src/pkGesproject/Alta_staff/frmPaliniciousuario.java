package pkGesproject.Alta_staff;

import javax.swing.*;

import java.awt.*;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import java.awt.*;

public class frmPaliniciousuario extends JFrame {

	JTabbedPane jtp = new JTabbedPane();

	public frmPaliniciousuario(String titulo, int x, int y) {
		super(titulo);

		this.setBounds(x, y, 800, 600);
		pnlAlta_staff panelUsuarios = new pnlAlta_staff(); // Creamos un panel
															// para la pestaña
															// usuarios
		this.add(panelUsuarios);

		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
		}
		// No puedo quitar el boton maximizar....
		this.setResizable(false);
		this.setVisible(true);
	}

}
