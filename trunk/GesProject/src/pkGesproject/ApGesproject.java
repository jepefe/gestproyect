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
		FrmPrincipal vppal = new FrmPrincipal();
		
	}

}
