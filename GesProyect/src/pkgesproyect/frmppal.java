package pkgesproyect;


import javax.swing.*;

import java.awt.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

import java.awt.*;
public class frmppal extends JFrame{
	//Creamos un objeto JTabbedPane
	JTabbedPane jtp = new JTabbedPane();
	//Creamos un array en el que introducimos los iconos
		Icon[] icpref = { 
				new ImageIcon(ApGesProyect.class.getResource("imagenes/preferencias.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/informes.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/usuarios.png")),
				
		};
	//Constructor
	public frmppal(String titulo, int x, int y, int ancho, int alto){
		super(titulo);
		this.setBounds(x,y,ancho,alto);
		//Creamos un panel 
		JPanel panelInformes = new JPanel();  //Creamos un panel para la pestaña Informes
		//Añadimos el panel creado anteriormente a 
		jtp.addTab(null,icpref[1],panelInformes,"Informes");
		JPanel panelUsuarios = new JPanel();  //Creamos un panel para la pestaña usuarios
		jtp.addTab(null,icpref[2],panelUsuarios,"Usuarios");
		//Creamos un panel de tipo PanelPreferencias que hemos creado anteriormente
		PanelPreferencias panelPreferencias = new PanelPreferencias();  //Creamos un panel para la pestaña Preferencias
		jtp.addTab(null,icpref[0],panelPreferencias,"Preferencias");
		
		
		this.add(jtp);
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
			
		this.setVisible(true);
		//Opción para que cierre el probrama al cerrar la ventana si no hacemos esto
		//aunque cerremos la ventan seguira la aplicación en ejecucion
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		
	}

}
