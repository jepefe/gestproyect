package pkgesproyect;


import javax.swing.*;

import java.awt.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

import java.awt.*;
public class frmppal extends JFrame{
	JTabbedPane jtp = new JTabbedPane();
		Icon[] icpref = { 
				new ImageIcon(ApGesProyect.class.getResource("imagenes/preferencias.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/informes.png")),
				new ImageIcon(ApGesProyect.class.getResource("imagenes/usuarios.png")),
				
		};
	
	public frmppal(String titulo, int x, int y, int ancho, int alto){
		super(titulo);
		this.setBounds(x,y,ancho,alto);
		JPanel panelInformes = new JPanel();  //Creamos un panel para la pestaña Informes
		jtp.addTab(null,icpref[1],panelInformes,"Informes");
		JPanel panelUsuarios = new JPanel();  //Creamos un panel para la pestaña usuarios
		jtp.addTab(null,icpref[2],panelUsuarios,"Usuarios");
		PanelPreferencias panelPreferencias = new PanelPreferencias();  //Creamos un panel para la pestaña Preferencias
		jtp.addTab(null,icpref[0],panelPreferencias,"Preferencias");
		
		
		this.add(jtp);
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
			
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		
	}

}
