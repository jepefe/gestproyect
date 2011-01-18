package pkGesproject.TimeSheet;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{

	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	public PnlAlta_TimeSheet(){
		this.setLayout(new GridBagLayout());
	}
}
