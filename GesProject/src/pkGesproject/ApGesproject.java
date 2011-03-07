package pkGesproject;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;


public class ApGesproject {
	
	public static void main(String[] args){
		
		
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
			//GesIdioma nidioma = new GesIdioma();
		
			//nidioma.eleccionidioma();
			new FrmLogin("Login - Gesproject", 300,200);
			
			//FrmPrincipal vppal = new FrmPrincipal();
		
		
	}

}
