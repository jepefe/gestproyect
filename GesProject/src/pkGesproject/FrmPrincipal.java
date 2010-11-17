package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;
/**
 * Clase de la ventana principal de la aplicación
 * @author Jesus Perez
 *
 */
public class FrmPrincipal extends JFrame {
	
	JToolBar jtlbFrmppal = new JToolBar();
	JToolBar jtlbLateral = new JToolBar();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JToggleButton jbtnInicio = new JToggleButton(recursos.icono[2]);
	JToggleButton jbtnUsuarios = new JToggleButton(recursos.icono[0]);
	JToggleButton jbtnSocios = new JToggleButton(recursos.icono[1]);
	JToggleButton jbtnProyectos = new JToggleButton(recursos.icono[4]);
	PnlAltasocio nu = new PnlAltasocio();
	PnlBienvenida pnlbienvenida = new PnlBienvenida();
	pnlAlta_staff pnlalta_staff = new pnlAlta_staff();
	JPanel pnlcontenedor = new JPanel(true);
	PnlMenusocio pnlmenusocio = new PnlMenusocio();
	JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlmenusocio, nu);
	GesIdioma rec = GesIdioma.obtener_instancia();
	
	private Rectangle maxBounds;
	
	public FrmPrincipal(){
		super.setBounds(0, 0, recursos.getFrmppalWidth(), recursos.getFrmppalHeight());
		this.setTitle(recursos.APNAME);
		this.setLocationRelativeTo(null);
		recursos.setRfrmppal(this);	
		this.setLayout(new BorderLayout());
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);//Esta linea es necesaria para que la barra de titulo y el jtoolbar sean homogeneos
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.add(nu,BorderLayout.CENTER);
		//nu.setVisible(false);
		this.add(pnlbienvenida,BorderLayout.CENTER);
				try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}

	}
	
	/**
	 * Este metodo se encarga de inicializar todas las propiedades de la ventana principal que sean necesarias
	 */
	public void inicializar(){		
			this.setVisible(true);
			this.Carga_toolbar();
			repaint();
	}
	/*
	 * Metodo de carga de la barra de herramientas
	 */
	public void Carga_toolbar(){
		jtlbFrmppal.setLayout(new GridBagLayout());
		//Creamos un action listener para los botones de la jtoolbar
		ActionListener jtlbAcListener = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	
	        	remove(pnlbienvenida);
	        	remove(nu);
	        	remove(pnlbienvenida);
	        	remove(pnlalta_staff);
	        	remove(splitpane);
	        	remove(jtlbLateral);
	        	jbtnUsuarios.setSelected(false);
	        	jbtnSocios.setSelected(false);
	        	jbtnInicio.setSelected(false);
	        	jbtnProyectos.setSelected(false);
	        	repaint();
	        	validate();
	         if (e.getActionCommand().equals("socios")){
	        	
	        	//add(pnlcontenedor);
	        	//pnlcontenedor.add(nu,BorderLayout.WEST);
	        	//pnlcontenedor.add(pnlalta_staff);
	        	//pnlcontenedor.setOpaque(true);
	        	add(nu,BorderLayout.CENTER);
	        	jbtnSocios.setSelected(true);
	        	nu.setVisible(true);
	        	repaint();
	        	validate();
	        	
	        	}
	         if (e.getActionCommand().equals("usuarios")){
		        	jbtnUsuarios.setSelected(true); 
		        	add(pnlalta_staff, BorderLayout.CENTER);
		        	pnlalta_staff.setVisible(true);
		        	repaint();
		        	validate();
		        	//new DlgSiNo("Seguro que desea salir?").start();
		        
		         }
	         if (e.getActionCommand().equals("inicio")){
	        	 jbtnInicio.setSelected(true);
	        	 add(pnlbienvenida, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("proyectos")){
	        	 //splitpane.setOneTouchExpandable(true);
	        	 //splitpane.setDividerLocation(150);
	        	//getContentPane().add(pnlcontenedor, BorderLayout.CENTER);
	        	splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jtlbLateral, nu);
	        	jtlbLateral.setLayout(new GridLayout(7,1));
	        	jtlbLateral.setOpaque(true);
	       
	        	JToggleButton bt = new JToggleButton(recursos.icono[5]);
	        	bt.setText(rec.idioma[rec.eleidioma][13]);
	        	jtlbLateral.add(bt);
	        	
	        	JToggleButton bt1 = new JToggleButton(recursos.icono[6]);
	        	bt1.setText(rec.idioma[rec.eleidioma][14]);
	        	bt1.setHorizontalAlignment(0);
	        	//bt1.setBackground(new Color(200,200,200));
	        	jtlbLateral.add(bt1);
	        	
	        	JToggleButton bt2 = new JToggleButton(recursos.icono[7]);
	        	bt2.setText(rec.idioma[rec.eleidioma][15]);
	        	bt2.setHorizontalAlignment(0);
	        	jtlbLateral.add(bt2);
	        	
	        	splitpane.setOneTouchExpandable(true);
	        	getContentPane().add(splitpane);
	        	splitpane.setOpaque(true);
	        	//splitpane.setPreferredSize(new Dimension(800, 600));
	        	//splitpane.setLeftComponent(nu);
	        	//pnlcontenedor.add(splitpane);
	        	//splitpane.setVisible(true);
	        	jbtnProyectos.setSelected(true);
	        	repaint();
	        	validate();
	         	}
	         } 
		};
		jtlbFrmppal.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		jbtnUsuarios.addActionListener(jtlbAcListener);
		jbtnSocios.addActionListener(jtlbAcListener);
		jbtnInicio.addActionListener(jtlbAcListener);
		jbtnProyectos.addActionListener(jtlbAcListener);
		jbtnUsuarios.setActionCommand("usuarios");
		jbtnSocios.setActionCommand("socios");
		jbtnInicio.setActionCommand("inicio");
		jbtnProyectos.setActionCommand("proyectos");
		jtlbFrmppal.setFloatable(false); //Impedimos que el toolbar se pueda separar de la ventana
		jtlbFrmppal.setPreferredSize(new Dimension(this.getWidth(),66));
		jtlbFrmppal.setVisible(true);
		jtlbFrmppal.add(jbtnInicio);
		jbtnInicio.setSelected(true);
		jtlbFrmppal.add(jbtnUsuarios);
		jtlbFrmppal.add(jbtnSocios);
		jtlbFrmppal.add(jbtnProyectos);
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
