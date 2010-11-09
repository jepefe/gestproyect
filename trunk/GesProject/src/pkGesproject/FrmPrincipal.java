package pkGesproject;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

public class FrmPrincipal extends JFrame {

	JToolBar jtlbFrmppal = new JToolBar();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	public FrmPrincipal(){
		this.setBounds(0, 0, recursos.getFrmppalWidth(), recursos.getFrmppalHeight());
		this.setTitle(recursos.APNAME);
		recursos.setRfrmppal(this);	
		this.setLayout(new BorderLayout());
		this.inicializar();
	}
	
	public void inicializar(){
		jtlbFrmppal.setFloatable(false);
		this.add(jtlbFrmppal,BorderLayout.NORTH);
		this.setVisible(true);
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);

		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
	}

	
}
