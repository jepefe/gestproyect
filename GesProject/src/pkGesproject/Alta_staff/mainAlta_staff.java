package pkGesproject.Alta_staff;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

public class mainAlta_staff {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {

		}

		frmPaliniciousuario ppal = new frmPaliniciousuario("GesProyect", 200,
				200);
	}

}
