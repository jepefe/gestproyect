package pkGesproject;
import javax.swing.SwingUtilities;
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
			Runnable doCreateAndShowGUI = new Runnable() {
			    public void run() {
			    	new FrmLogin("Login - Gesproject", 300,200);
			    }
			};
			SwingUtilities.invokeLater(doCreateAndShowGUI);
			
			
			//FrmPrincipal vppal = new FrmPrincipal();
		
		
	}

}
