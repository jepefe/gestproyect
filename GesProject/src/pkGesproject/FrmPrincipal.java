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

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import pkGesproject.Proyectos.SpnProyectos;
import pkGesproject.Socios.SpnSocios;
import pkGesproject.Staff.SpnStaff;
import pkGesproject.Tareas.SpnTareas;
import pkGesproject.TimeSheet.SpnTimesheet;
import pkGesproject.Workpakages.SpnWorkpack;
import pkGesproject.informes.SpnInformes;

import Tablas.SpnTablas;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;
/**
 * Clase de la ventana principal de la aplicaci�n
 * @author Jesus Perez
 *
 */
public class FrmPrincipal extends JFrame {
	
	public JToolBar jtlbFrmppal = new JToolBar();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JToggleButton jbtnInicio = new JToggleButton(recursos.icono[2]);
	JToggleButton jbtnUsuarios = new JToggleButton(recursos.icono[0]);
	JToggleButton jbtnSocios = new JToggleButton(recursos.icono[1]);
	JToggleButton jbtnProyectos = new JToggleButton(recursos.icono[4]);
	JToggleButton jbtnWorkpack = new JToggleButton(recursos.icono[8]);
	JToggleButton jbtnTareas = new JToggleButton(recursos.icono[9]);
	JToggleButton jbtnAgregar = new JToggleButton(recursos.icono[10]);
	JToggleButton jbtnTablas = new JToggleButton(recursos.icono[11]);
	JToggleButton jbtnTimesheet = new JToggleButton(recursos.icono[12]);
	JToggleButton jbtnInformes = new JToggleButton(recursos.icono[31]);
	JPanel jpnlppal = new JPanel();
	
	PnlBienvenida pnlbienvenida = new PnlBienvenida();
	
	SpnStaff spnstaff;
	SpnSocios spnsocios;
	SpnProyectos spnproyectos;
	SpnWorkpack spnworkpack;
	SpnTareas spntareas;
	SpnTablas spntablas;
	SpnTimesheet spntimesheet;
	SpnInformes spninformes;
	FrmNuevapalabra nueva;
	GesIdioma rec = GesIdioma.obtener_instancia();
	
	
	private Rectangle maxBounds;
	
	public FrmPrincipal(){
		switch(recursos.permisos){
		
		case 1:{
		recursos.progresocarga =1;
		recursos.txtcarga = "Loading  Staff";
		spnstaff = new SpnStaff();
		recursos.progresocarga =2;
		recursos.txtcarga = "Loading  Partners";
		spnsocios = new SpnSocios();
		recursos.progresocarga =4;
		recursos.txtcarga = "Loading  Projects";
		spnproyectos = new SpnProyectos();
		recursos.progresocarga =5;
		recursos.txtcarga = "Loading  Work Packages";
		spnworkpack = new SpnWorkpack();
		recursos.progresocarga =6;
		recursos.txtcarga = "Loading  Tasks";
		spntareas = new SpnTareas();
		recursos.progresocarga =7;
		recursos.txtcarga = "Loading  Tables";
		spntablas = new SpnTablas();
		recursos.progresocarga =9;
		recursos.txtcarga = "Loading  Time Sheets";
		spntimesheet = new SpnTimesheet();
		recursos.progresocarga =10;
		recursos.txtcarga = "Loading  Data";
		nueva = new FrmNuevapalabra();
		//a falta de progres bar
		spninformes = new SpnInformes();
		jtlbFrmppal.add(jbtnInicio);
		jbtnInicio.setSelected(true);
		jtlbFrmppal.add(jbtnUsuarios);
		jtlbFrmppal.add(jbtnSocios);
		jtlbFrmppal.add(jbtnProyectos);
		jtlbFrmppal.add(jbtnWorkpack);
		jtlbFrmppal.add(jbtnTareas);
		jtlbFrmppal.add(jbtnTablas);
		jtlbFrmppal.add(jbtnTimesheet);
		jtlbFrmppal.add(jbtnInformes);
		jtlbFrmppal.add(jbtnAgregar);
		}break;
		case 2:{
			recursos.txtcarga = "Loading  Time Sheets";
			spntimesheet = new SpnTimesheet();
			jtlbFrmppal.add(jbtnTimesheet);

			recursos.progresocarga =10;
		}
		case 3:{
			recursos.txtcarga = "Loading  Time Sheets";
			spntimesheet = new SpnTimesheet();
			recursos.progresocarga =10;
			jtlbFrmppal.add(jbtnTimesheet);

		}
		}
		
		//Maximizamos la ventana principal
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jbtnAgregar.setVisible(true);
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
			jpnlppal.setLayout(new BorderLayout());
			add(jpnlppal,BorderLayout.CENTER);
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
	        	int i;
	        	 if(jpnlppal.getComponentCount()>0){
	        	jpnlppal.remove(0);
	        	 }
	        	/*
	        	remove(pnlbienvenida);
	        	remove(spnsocios);
	        	remove(spnproyectos);
	        	remove(spnstaff);
	        	remove(spnworkpack);
	        	remove(spntareas);
	        	remove(nueva);
	        	remove(spntablas);
	        	remove(spntimesheet);
	        	remove(spninformes);
	        	jbtnUsuarios.setSelected(false);
	        	jbtnSocios.setSelected(false);
	        	jbtnInicio.setSelected(false);
	        	jbtnProyectos.setSelected(false);
	        	jbtnWorkpack.setSelected(false);
	        	jbtnTareas.setSelected(false);
	        	jbtnAgregar.setSelected(false);
	        	jbtnTablas.setSelected(false);
	        	jbtnTimesheet.setSelected(false);
	        	jbtnInformes.setSelected(false);
	        	repaint();
	        	validate();*/
	        	 for(i=0;i<jtlbFrmppal.getComponentCount();i++){
	        		  ((AbstractButton) jtlbFrmppal.getComponentAtIndex(i)).setSelected(false);
	        	 }
	         if (e.getActionCommand().equals("socios")){
	        	jbtnSocios.setSelected(true);
	        	jpnlppal.add(spnsocios);
	        	repaint();
	        	validate();
	        	
	        	}
	         if (e.getActionCommand().equals("usuarios")){
		        	jbtnUsuarios.setSelected(true); 
		        
		        	jpnlppal.add(spnstaff);
		        	repaint();
		        	validate();
		        
		        
		         }
	         if (e.getActionCommand().equals("inicio")){
	        	 jbtnInicio.setSelected(true);
	        	 add(pnlbienvenida, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("proyectos")){
	        	jbtnProyectos.setSelected(true);
	        	jpnlppal.add(spnproyectos);
	        	repaint();
	        	validate();
	         	}
	         
	         if (e.getActionCommand().equals("workpack")){
	        	 jbtnWorkpack.setSelected(true);
	        	 jpnlppal.add(spnworkpack, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("tareas")){
	        	 jbtnTareas.setSelected(true);
	        	 jpnlppal.add(spntareas, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("tablas")){
	        	 jbtnTablas.setSelected(true);
	        	 jpnlppal.add(spntablas, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("agregar")){
	        	 jbtnAgregar.setSelected(true);
	        	 add(nueva, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	         }
	         
	         if (e.getActionCommand().equals("timesheet")){
	        	 jbtnTimesheet.setSelected(true);
	        	 jpnlppal.add(spntimesheet, BorderLayout.CENTER);
	        	 repaint();
	        	 validate();
	         }
	         
	         if (e.getActionCommand().equals("informes")){
	        	 jbtnInformes.setSelected(true);
	        	 jpnlppal.add(spninformes, BorderLayout.CENTER);
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
		jbtnWorkpack.addActionListener(jtlbAcListener);
		jbtnTareas.addActionListener(jtlbAcListener);
		jbtnAgregar.addActionListener(jtlbAcListener);
		jbtnTablas.addActionListener(jtlbAcListener);
		jbtnTimesheet.addActionListener(jtlbAcListener);
		jbtnInformes.addActionListener(jtlbAcListener);
		jbtnUsuarios.setActionCommand("usuarios");
		jbtnSocios.setActionCommand("socios");
		jbtnInicio.setActionCommand("inicio");
		jbtnProyectos.setActionCommand("proyectos");
		jbtnWorkpack.setActionCommand("workpack");
		jbtnTareas.setActionCommand("tareas");
		jbtnAgregar.setActionCommand("agregar");
		jbtnTablas.setActionCommand("tablas");
		jbtnTimesheet.setActionCommand("timesheet");
		jbtnInformes.setActionCommand("informes");
		jtlbFrmppal.setFloatable(false); //Impedimos que el toolbar se pueda separar de la ventana
		jtlbFrmppal.setPreferredSize(new Dimension(this.getWidth(),50));
		jtlbFrmppal.setVisible(true);
		
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
