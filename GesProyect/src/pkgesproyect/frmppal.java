package pkgesproyect;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

import java.awt.*;

public class frmppal extends JFrame{
	public final static String UNIFIED_TOOLBAR_LOOK = "true";
	public final static boolean  MenuInTitle = Boolean.TRUE;
	//Creamos un objeto JTabbedPane
	
//Creamos un array en el que introducimos los iconos
		
	ImageIcon[] icpref = { 
				new ImageIcon(ApGesProyect.class.getResource("imagenes/preferencias.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/informes.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/usuarios.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/pincel.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/tampon.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/informacion.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/pizarra.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/folio.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/bandera.png")),
				
		};
	//Constructor
	public frmppal(String titulo, int x, int y, int ancho, int alto){

		super(titulo);
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);
		
	
		JButton jb = new JButton();
		JButton jb2 = new JButton(icpref[0]);
		JButton jb3 = new JButton(icpref[2]);
		JButton jb4 = new JButton(icpref[3]);
		JButton jb5 = new JButton(icpref[4]);
		JButton jb6 = new JButton(icpref[5]);
		JButton jb7 = new JButton(icpref[6]);
		JButton jb8 = new JButton(icpref[7]);
		JButton jb9 = new JButton(icpref[8]);
		
		
		this.setBounds(x,y,ancho,alto);
		JTabbedPane jtp = new JTabbedPane();
		JToolBar jmb = new JToolBar();
		jb2.setVerticalTextPosition(SwingConstants.BOTTOM);
		jb2.setHorizontalTextPosition(SwingConstants.CENTER);
		jmb.setFloatable(false);
		
				
		jb.setIcon(icpref[1]);
		jmb.add(jb);
		jmb.add(jb2);
		jmb.add(jb3);
		jmb.add(jb4);
		jmb.add(jb5);
		jmb.add(jb6);
		jmb.add(jb7);
		jmb.add(jb8);
		jmb.add(jb9);
		
		
		this.setLayout(new BorderLayout());
		//Creamos un panel 
		JPanel panelInformes = new JPanel();  //Creamos un panel para la pestaña Informes
		
		//Añadimos el panel creado anteriormente 
		
		jtp.addTab(null,icpref[1],panelInformes,"Informes");
		JPanel panelUsuarios = new JPanel();  //Creamos un panel para la pestaña usuarios
		jtp.addTab(null,icpref[2],panelUsuarios,"Usuarios");
		
		//Creamos un panel de tipo PanelPreferencias que hemos creado anteriormente
		PanelPreferencias panelPreferencias = new PanelPreferencias();  //Creamos un panel para la pestaña Preferencias
		jtp.addTab(null,icpref[0],panelPreferencias,"Preferencias");
		
			//this.add(jtp);
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
			
		this.add(panelPreferencias,BorderLayout.CENTER);
		
		jmb.setVisible(true);
		this.setVisible(true);
		this.add(jmb,BorderLayout.NORTH);
		//Opción para que cierre el probrama al cerrar la ventana si no hacemos esto
		//aunque cerremos la ventan seguira la aplicación en ejecucion
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		
	}

}
