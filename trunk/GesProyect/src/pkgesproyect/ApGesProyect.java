package pkgesproyect;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

public class ApGesProyect {
	public static void main(String[] args){
		
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
		frmppal ppal = new frmppal("GesProyect", 40,80,800,600);
	}
}
