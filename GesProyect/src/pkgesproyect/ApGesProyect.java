package pkgesproyect;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

public class ApGesProyect {
	
	public static void main(String[] args){
		//Cargamos el lookandfeel seaglasss que debemos de tener importado en eclipse
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
			 frmppal formularioPrincipal = new frmppal("GesProyect", 40,80,640,480);
		
	}
}