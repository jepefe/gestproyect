package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import pkGesproject.Becas.SpnBecas;
import pkGesproject.Preferencias.SpnPreferencias;
import pkGesproject.Proyectos.SpnProyectos;
import pkGesproject.Socios.SpnSocios;
import pkGesproject.Staff.SpnStaff;
import pkGesproject.Tareas.SpnTareas;
import pkGesproject.TimeSheet.PnlAlta_TimeSheet;
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
	JToggleButton jbtnPreferencias = new JToggleButton(recursos.icono[33]);
	JToggleButton jbtnBecas = new JToggleButton(recursos.icono[35]);
	JPanel jpnlppal = new JPanel();
	PnlPrincipal ppal = new PnlPrincipal();
	JSplitPane jscpprincipal;
	PnlContactos pnlcontactos;
	JLabel jlbactual = new JLabel();
	int posicionxbtn;
	String seleccionado;
	boolean animando = false;
	boolean moviendo = false;
    Timer timer;                      
    int incremento;
	
	PnlBienvenida pnlbienvenida = new PnlBienvenida();
	
	SpnStaff spnstaff;
	SpnSocios spnsocios;
	SpnProyectos spnproyectos;
	SpnWorkpack spnworkpack;
	SpnTareas spntareas;
	SpnTablas spntablas;
	//SpnTimesheet spntimesheet;
	JScrollPane pnltimesheet;
	SpnInformes spninformes;
	FrmNuevapalabra nueva;
	SpnPreferencias spnpreferencias;
	SpnBecas spnbecas;
	GesIdioma rec = GesIdioma.obtener_instancia();
	
	
	private Rectangle maxBounds;
	
	public FrmPrincipal(){
		recursos.setRfrmppal(this);	
		this.setIconImage(recursos.icono[37].getImage());
		jtlbFrmppal.setLayout(new BoxLayout(jtlbFrmppal,BoxLayout.X_AXIS));
		int i;
		jbtnInicio.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnInicio.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnInicio.setText(rec.idioma[rec.eleidioma][172]);
				
		jbtnInicio.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnInicio.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnUsuarios.setText(rec.idioma[rec.eleidioma][172]);
		jbtnUsuarios.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnUsuarios.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnUsuarios.setText(rec.idioma[rec.eleidioma][137]);
		jbtnSocios.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnSocios.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnSocios.setText(rec.idioma[rec.eleidioma][138]);
		jbtnProyectos.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		jbtnProyectos.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnProyectos.setText(rec.idioma[rec.eleidioma][55]);
		
		
		
		jbtnTareas.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnTareas.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnTareas.setText(rec.idioma[rec.eleidioma][136]);
		
		jbtnTablas.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnTablas.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnTablas.setText(rec.idioma[rec.eleidioma][109]);
		
		jbtnTimesheet.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnTimesheet.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnTimesheet.setText(rec.idioma[rec.eleidioma][139]);
		
		jbtnInformes.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnInformes.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnInformes.setText(rec.idioma[rec.eleidioma][140]);
		
		jbtnWorkpack.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnWorkpack.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnWorkpack.setText(rec.idioma[rec.eleidioma][141]);
		
		jbtnPreferencias.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnPreferencias.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnPreferencias.setText(rec.idioma[rec.eleidioma][142]);
		
		jbtnBecas.setVerticalTextPosition(SwingConstants.BOTTOM);
		jbtnBecas.setHorizontalTextPosition(SwingConstants.CENTER);
		jbtnBecas.setText(rec.idioma[rec.eleidioma][147]);
	
		switch(recursos.permisos){
		
		case 0:{
			
		recursos.progresocarga =1;
		recursos.txtcarga = rec.idioma[rec.eleidioma][186];
		spnstaff = new SpnStaff();
		recursos.progresocarga =2;
		recursos.txtcarga = rec.idioma[rec.eleidioma][187];
		spnsocios = new SpnSocios();
		recursos.progresocarga =4;
		recursos.txtcarga = rec.idioma[rec.eleidioma][188];
		spnproyectos = new SpnProyectos();
		recursos.progresocarga =5;
		recursos.txtcarga = rec.idioma[rec.eleidioma][189];
		spnworkpack = new SpnWorkpack();
		recursos.progresocarga =6;
		recursos.txtcarga = rec.idioma[rec.eleidioma][190];
		spntareas = new SpnTareas();
		recursos.progresocarga =7;
		recursos.txtcarga = rec.idioma[rec.eleidioma][191];
		spntablas = new SpnTablas();
		recursos.progresocarga =9;
		recursos.txtcarga = rec.idioma[rec.eleidioma][192];
		pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
		//pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
		recursos.progresocarga =10;
		recursos.txtcarga = rec.idioma[rec.eleidioma][193];
	//	nueva = new FrmNuevapalabra();
		pnlcontactos = new PnlContactos();
		spninformes = new SpnInformes();
		spnpreferencias = new SpnPreferencias();
		spnbecas = new SpnBecas();
		
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
		jtlbFrmppal.add(jbtnBecas);
		jtlbFrmppal.add(jbtnPreferencias);
		//jtlbFrmppal.add(jbtnAgregar);
		repaint();
		
		}break;
		case 1:{
			recursos.progresocarga =1;
			recursos.txtcarga = rec.idioma[rec.eleidioma][186];
			spnstaff = new SpnStaff();
			recursos.progresocarga =2;
			recursos.progresocarga =4;
			recursos.txtcarga = rec.idioma[rec.eleidioma][188];
			spnproyectos = new SpnProyectos();
			recursos.progresocarga =5;
			recursos.txtcarga = rec.idioma[rec.eleidioma][189];
			spnworkpack = new SpnWorkpack();
			recursos.progresocarga =6;
			recursos.txtcarga = rec.idioma[rec.eleidioma][190];
			spntareas = new SpnTareas();
			recursos.progresocarga =7;
			spninformes = new SpnInformes();
			recursos.txtcarga = rec.idioma[rec.eleidioma][191];
			spntablas = new SpnTablas();
			recursos.progresocarga =9;
			recursos.txtcarga = rec.idioma[rec.eleidioma][192];
			pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
			//pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
			recursos.progresocarga =10;
			recursos.txtcarga = rec.idioma[rec.eleidioma][193];
		//	nueva = new FrmNuevapalabra();
			pnlcontactos = new PnlContactos();
			spnpreferencias = new SpnPreferencias();
			spnbecas = new SpnBecas();
			jtlbFrmppal.add(jbtnInicio);
			jbtnInicio.setSelected(true);
			jtlbFrmppal.add(jbtnUsuarios);
			jtlbFrmppal.add(jbtnProyectos);
			jtlbFrmppal.add(jbtnWorkpack);
			jtlbFrmppal.add(jbtnTareas);
			jtlbFrmppal.add(jbtnTablas);
			jtlbFrmppal.add(jbtnTimesheet);
			jtlbFrmppal.add(jbtnInformes);
			jtlbFrmppal.add(jbtnBecas);
			jtlbFrmppal.add(jbtnPreferencias);
			//jtlbFrmppal.add(jbtnAgregar);
			repaint();


		}break;
		case 2:{
			jtlbFrmppal.add(jbtnInicio);
			recursos.txtcarga = "Loading  Time Sheets";
			pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
			recursos.progresocarga =5;
			recursos.txtcarga = rec.idioma[rec.eleidioma][190];
			spntareas = new SpnTareas();
			spnpreferencias = new SpnPreferencias();
			recursos.progresocarga =10;
			recursos.txtcarga = rec.idioma[rec.eleidioma][193];
			jtlbFrmppal.add(jbtnTimesheet);
			jtlbFrmppal.add(jbtnTareas);
			jtlbFrmppal.add(jbtnPreferencias);
			pnlcontactos = new PnlContactos();

		}break;
		case 3:{
			jtlbFrmppal.add(jbtnInicio);
			recursos.progresocarga =6;
			recursos.txtcarga = "Loading  Time Sheets";
			pnltimesheet = new JScrollPane(new PnlAlta_TimeSheet());
			spnpreferencias = new SpnPreferencias();
			recursos.progresocarga =10;
			jtlbFrmppal.add(jbtnTimesheet);
			jtlbFrmppal.add(jbtnPreferencias);
			pnlcontactos = new PnlContactos();

		}break;
		}
		jscpprincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpnlppal,pnlcontactos);
		
		
		for(i=0;i<jtlbFrmppal.getComponentCount();i++){
	  		  ((AbstractButton) jtlbFrmppal.getComponentAtIndex(i)).setSelected(false);
	  		  ((AbstractButton) jtlbFrmppal.getComponentAtIndex(i)).setFont(new Font(Font.SANS_SERIF, Font.BOLD,11));
	  	 }
		
		//Maximizamos la ventana principal
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jbtnAgregar.setVisible(true);
		jbtnPreferencias.setVisible(true);
		super.setBounds(0, 0, recursos.getFrmppalWidth(), recursos.getFrmppalHeight());
		this.setTitle(recursos.APNAME);
		this.setLocationRelativeTo(null);
		
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
			jscpprincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpnlppal,pnlcontactos);
			jscpprincipal.setDividerLocation(0.8);
			
			jscpprincipal.setOneTouchExpandable(true);
			jscpprincipal.setLeftComponent(ppal);
			getContentPane().add(jscpprincipal,BorderLayout.CENTER);
		
			
			
			
			//jscpprincipal.setLeftComponent(new JSplitPane(PnlContactos(),BorderLayout.EAST);
			
		
			this.Carga_toolbar();
			jscpprincipal.setDividerLocation(0.8);
			jscpprincipal.setResizeWeight(0.8);
			this.setVisible(true);
	}
	/*
	 * Metodo de carga de la barra de herramientas
	 */
	
	
	public void animacion(final AbstractButton abstractButton,final String cadena){
		jtlbFrmppal.add(jlbactual);
		
		Thread hilo = new Thread(new Runnable(){
			
			@Override
			public void run() {
				
				posicionxbtn = abstractButton.getX();
				
				
				abstractButton.setSelected(false);
				jlbactual.setText(cadena);
				jtlbFrmppal.add(jlbactual);
				jlbactual.setVisible(false);
				animando = true;
				moviendo = true;
				
				timer = new Timer(1,new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(animando || moviendo){
					for(int i=0;i<jtlbFrmppal.getComponentCount();i++){
						if(abstractButton.getX()<5){
							moviendo = false;
						}else{
						int x=abstractButton.getX()-4;
						int y=abstractButton.getY();
						abstractButton.setLocation(x,y);
					}
					if(jtlbFrmppal.getComponent(i)!= abstractButton){
						if(i%2==0){
							 incremento = 7;
						}else{
							 incremento = -7;
						
						}
						 int x=jtlbFrmppal.getComponent(i).getX();
						 int y=jtlbFrmppal.getComponent(i).getY()+incremento;
						jtlbFrmppal.getComponent(i).setLocation(x,y);
						
					
					}/*else{
						if(jtlbFrmppal.getComponent(i).getX()>30){
						int x=jtlbFrmppal.getComponent(i).getX()-40;
						int y=jtlbFrmppal.getComponent(i).getY();
						
						jtlbFrmppal.getComponent(i).setLocation(x,y);
				//		jtlbFrmppal.getComponent(i).repaint();
						}else{
						moviendo=false;
						}
					}*/
					if(jtlbFrmppal.getComponent(i).getY()>70){
						animando=false;
					}
					if(abstractButton.getX()<30){
						moviendo = false;
					}
				
					}
					//repaint();
					}
					else{
						timer.stop();
						System.out.println("paramos");
						abstractButton.setSelected(true);
						
						for(int i=0;i<jtlbFrmppal.getComponentCount();i++){
							if(jtlbFrmppal.getComponent(i)!= abstractButton){
						 		// jtlbFrmppal.remove(jtlbFrmppal.getComponentAtIndex(i));
								((Component) jtlbFrmppal.getComponentAtIndex(i)).setVisible(false);
							}
							}
						jlbactual.setVisible(true);
						jlbactual.setFont(new Font(Font.SANS_SERIF, Font.BOLD,34));
						jlbactual.setText(cadena);
						jlbactual.setLocation(abstractButton.getX()+100,abstractButton.getY() );
						abstractButton.setLocation(40,abstractButton.getY());
						 animando = false;
							moviendo = false;
						
					}
					}
				}
				
			);
				timer.start();
		
				
				
				}
				
			}
    		 
    	      	 
    	 
    	 );
    	 hilo.start();
    	
		repaint();
    	 
	}
	
	public void animacionvolver(final Component boton){
		jtlbFrmppal.remove(jlbactual);	
		seleccionado = "0";
		Thread hilo = new Thread(new Runnable(){
		
				@Override
				public void run() {
					int y,x,a=0;
					 animando = true;
					animando = true;
					moviendo = true;
					
					timer = new Timer(1,new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
						
					if(boton.getX()<posicionxbtn && ((boton.getX()+1)<posicionxbtn)){
					
						boton.setLocation(boton.getX()+60,boton.getY());
										
					}else{
						timer.stop();
						
						for(int i=0;i<jtlbFrmppal.getComponentCount();i++){
							jtlbFrmppal.getComponentAtIndex(i).setVisible(true);
						}
						jtlbFrmppal.repaint();
						animando = false;
						moviendo = false;
					}
						}
					}
					);
					timer.start();
					//animando=false;
					
				
						
					
				/*	while(animando){
						for(int i=0;i<jtlbFrmppal.getComponentCount();i++){
							
							y=jtlbFrmppal.getComponentAtIndex(i).getY();
							
							if(boton!=jtlbFrmppal.getComponentAtIndex(i) && jtlbFrmppal.getComponentAtIndex(i).getY()!=boton.getY()){
								if(jtlbFrmppal.getComponentAtIndex(i).getY()>boton.getY() && jtlbFrmppal.getComponentAtIndex(i).getY()!=boton.getY()){
								
									jtlbFrmppal.getComponentAtIndex(i).setLocation(jtlbFrmppal.getComponentAtIndex(i).getX(),y+1);
								}else if(jtlbFrmppal.getComponentAtIndex(i).getY()!=boton.getY()){
								
									jtlbFrmppal.getComponentAtIndex(i).setLocation(jtlbFrmppal.getComponentAtIndex(i).getX(), y-1);
								}
								
							}
						
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
						}
						if(jtlbFrmppal.getComponentAtIndex(jtlbFrmppal.getComponentCount()-1).getY()==boton.getY()){
							animando=false;							
						}
					}*/
						
					
					
				}});
				
				hilo.start();	
				
				
				
	}
	public void Carga_toolbar(){
		
		//Creamos un action listener para los botones de la jtoolbar
		
		ActionListener jtlbAcListener = new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(((!animando)&&(!moviendo))){
	        	 int i;
	        	 int tam_split = jscpprincipal.getDividerLocation();
	        	if(((AbstractButton) e.getSource()).getActionCommand() == seleccionado){
	        		System.out.println("ya estaba pulsado");
	        		animacionvolver((Component) e.getSource());
	        	}else{
	        		seleccionado = ((AbstractButton) e.getSource()).getActionCommand();
	        			if(jpnlppal.getComponentCount()>1){
	        	jpnlppal.remove(1);
	        	 }
	        	
	        	 for(i=0;i<jtlbFrmppal.getComponentCount();i++){
	        		 if(jtlbFrmppal.getComponentAtIndex(i)==jlbactual){
	        			 jtlbFrmppal.remove(jtlbFrmppal.getComponentAtIndex(i));
	        		 }else{
	        		  ((AbstractButton) jtlbFrmppal.getComponentAtIndex(i)).setSelected(false);
	        	 }
	        	 }
	         if (e.getActionCommand().equals("socios")){
	        	jbtnSocios.setSelected(true);
	        	jscpprincipal.setLeftComponent(spnsocios);
	        	repaint();
	        	validate();
	        	}
	         if (e.getActionCommand().equals("usuarios")){
		        	jbtnUsuarios.setSelected(true); 
		        
		        	jscpprincipal.setLeftComponent(spnstaff);
		        	repaint();
		        	validate();
		        
		        
		         }
	         if (e.getActionCommand().equals("inicio")){
	        	 jbtnInicio.setSelected(true);
	        	 jscpprincipal.setLeftComponent(ppal);
	        	repaint();
	        	validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("proyectos")){
	        	jbtnProyectos.setSelected(true);
	        	jscpprincipal.setLeftComponent(spnproyectos);
	        	repaint();
	        	validate();
	         	}
	         
	         if (e.getActionCommand().equals("workpack")){
	        	 jbtnWorkpack.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spnworkpack);
	        	repaint();
	        	validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("tareas")){
	        	 jbtnTareas.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spntareas);
	        	repaint();
	        	validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("tablas")){
	        	 jbtnTablas.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spntablas);
	        	repaint();
	        	validate();
	        	 
	         	}
	         
	         if (e.getActionCommand().equals("agregar")){
	        	 jbtnAgregar.setSelected(true);
	        	 jscpprincipal.setLeftComponent(nueva);
	        	repaint();
	        	validate();
	         }
	         
	         if (e.getActionCommand().equals("timesheet")){
	        	 jbtnTimesheet.setSelected(true);
	        	 jscpprincipal.setLeftComponent(pnltimesheet);
	        	repaint();
	        	validate();
	         }
	         
	         if (e.getActionCommand().equals("informes")){
	        	 jbtnInformes.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spninformes);
	        	repaint();
	        	validate();
	         }
	         
	         if (e.getActionCommand().equals("preferencias")){
	        	 jbtnPreferencias.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spnpreferencias);
	        	repaint();
	        	validate();
	         }
	         
	         if (e.getActionCommand().equals("becas")){
	        	 jbtnBecas.setSelected(true);
	        	 jscpprincipal.setLeftComponent(spnbecas);
	        	repaint();
	        	validate();
	         }
	         animacion((AbstractButton)e.getSource(),((AbstractButton) e.getSource()).getText());
	         jscpprincipal.setDividerLocation(tam_split);
	         } 
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
		jbtnPreferencias.addActionListener(jtlbAcListener);
		jbtnBecas.addActionListener(jtlbAcListener);
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
		jbtnPreferencias.setActionCommand("preferencias");
		jbtnBecas.setActionCommand("becas");
		jtlbFrmppal.setFloatable(false); //Impedimos que el toolbar se pueda separar de la ventana
		//jtlbFrmppal.setPreferredSize(new Dimension(this.getWidth(),50));
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