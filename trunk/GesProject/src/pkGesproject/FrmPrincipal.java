package pkGesproject;

import javax.swing.JFrame;
import javax.swing.JToolBar;

public class FrmPrincipal extends JFrame {

	JToolBar jtlbFrmppal = new JToolBar();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	public FrmPrincipal(){
		this.setBounds(0, 0, recursos.getFrmppalWidth(), recursos.getFrmppalHeight());
		recursos.setRfrmppal(this);
		
		
		
	}

	
}
