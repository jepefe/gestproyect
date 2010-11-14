package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;
/**
 * Clase de la ventana principal de la aplicación
 * @author Jesus Perez
 *
 */
public class FrmPrincipal extends JFrame {

	JToolBar jtlbFrmppal = new JToolBar();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JToggleButton jbtnUsuarios = new JToggleButton(recursos.icono[0]);
	JToggleButton jbtnSocios = new JToggleButton(recursos.icono[1]);
	PnlAltasocio nu = new PnlAltasocio();
	private Rectangle maxBounds;
	
	public FrmPrincipal(){
		super.setBounds(0, 0, recursos.getFrmppalWidth(), recursos.getFrmppalHeight());
		this.setTitle(recursos.APNAME);
		recursos.setRfrmppal(this);	
		this.setLayout(new BorderLayout());
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);//Esta linea es necesaria para que la barra de titulo y el jtoolbar sean homogeneos
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(nu,BorderLayout.CENTER);
		this.setVisible(true);
		this.inicializar();
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}

	}
	
	/**
	 * Este metodo se encarga de inicializar todas las propiedades de la ventana principal que sean necesarias
	 */
	public void inicializar(){		
			this.Carga_toolbar();
	}
	/*
	 * Metodo de carga de la barra de herramientas
	 */
	public void Carga_toolbar(){
		//Creamos un action listener para los botones de la jtoolbar
		ActionListener jtlbAcListener = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	jbtnUsuarios.setSelected(false);
	        	jbtnSocios.setSelected(false);
	        	nu.setVisible(false);
	         if (e.getActionCommand().equals("socios")){
	        	jbtnSocios.setSelected(true); 
	        	nu.setVisible(true);
	        	
	        	
	        	}
	         if (e.getActionCommand().equals("usuarios")){
		        	jbtnUsuarios.setSelected(true); 
		        new DlgSiNo("Seguro que desea salir?").start();
		        
		         }
	         } 
		};
		jbtnUsuarios.addActionListener(jtlbAcListener);
		jbtnSocios.addActionListener(jtlbAcListener);
		jbtnUsuarios.setActionCommand("usuarios");
		jbtnSocios.setActionCommand("socios");
		jtlbFrmppal.setFloatable(false); //Impedimos que el toolbar se pueda separar de la ventana
		jtlbFrmppal.setPreferredSize(new Dimension(this.getWidth(),70));
		jtlbFrmppal.setVisible(true);
		jtlbFrmppal.add(jbtnUsuarios);
		//jbtnSocios.addActionListener();
		jtlbFrmppal.add(jbtnSocios);
		this.add(jtlbFrmppal,BorderLayout.NORTH);
		

	}
	

	
	/**
	 * Metodo necesario para que un JFrame sin decoracion tenga en cuenta la taskbar del sistema al maximizar, sobreescribe a un metodo de super()
	 */
	public synchronized void setExtendedState(int state) {
       
		if (maxBounds == null &&
            (state & java.awt.Frame.MAXIMIZED_BOTH) == java.awt.Frame.MAXIMIZED_BOTH) {
            Insets screenInsets = getToolkit().getScreenInsets(getGraphicsConfiguration());         
            Rectangle screenSize = getGraphicsConfiguration().getBounds();
            Rectangle maxBounds = new Rectangle(screenInsets.left /* + screenSize.x */, 
                                        screenInsets.top /* + screenSize.y */, 
                                        screenSize.width - screenInsets.right - screenInsets.left,
                                        screenSize.height - screenInsets.bottom - screenInsets.top);
            super.setMaximizedBounds(maxBounds);
        } 
        super.setExtendedState(state);
    }
	
	/**
	 * Metodo necesario para que un JFrame sin decoracion tenga en cuenta la taskbar del sistema al maximizar, sobreescribe a un metodo de super()
	 */
	public Rectangle getMaximizedBounds()
	{
		return(maxBounds);
	}
	
	/**
	 * Metodo necesario para que un JFrame sin decoracion tenga en cuenta la taskbar del sistema al maximizar, sobreescribe a un metodo de super()
	 */
	public synchronized void setMaximizedBounds(Rectangle maxBounds)
	{
		this.maxBounds = maxBounds;
		super.setMaximizedBounds(maxBounds);
	}



	
}
