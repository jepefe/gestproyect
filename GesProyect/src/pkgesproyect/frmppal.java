package pkgesproyect;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class frmppal extends JFrame implements ActionListener{

	//Creamos un objeto JTabbedPane
	
//Creamos un array en el que introducimos los iconos
		public ImageIcon[] icpref = { 
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
		JToolBar jmb = new JToolBar();
	
	//Constructor
	public frmppal(String titulo, int x, int y, int ancho, int alto){

		super(titulo);
		/*Definir esta propiedad crea un degradado desde la barra de titulo
		 * hasta el final del frame, debido a esto se hace necesario fijar 
		 * setOpaque de todos los JPanel y otros elementos.
		 */
		this.getRootPane().putClientProperty(SeaGlassRootPaneUI.UNIFIED_TOOLBAR_LOOK, Boolean.TRUE);
		jmb.setSize(80, 80);
		this.setBounds(x,y,ancho,alto);
		//BOTONES DE PRUEBA BORRAR SI ES NECESARIO BORRAR TAMBIEN LOS ADD
		JButton jb = new JButton(icpref[1]);
		JButton jb2 = new JButton(icpref[0]);
		JButton jb3 = new JButton(icpref[2]);
		JButton jb4 = new JButton(icpref[3]);
		JButton jb5 = new JButton(icpref[4]);
		JButton jb6 = new JButton(icpref[5]);
		JButton jb7 = new JButton(icpref[6]);
		JButton jb8 = new JButton(icpref[7]);
		JButton jb9 = new JButton(icpref[8]);
		
		
		this.setBounds(x,y,ancho,alto);
		
	
		jb2.setVerticalTextPosition(SwingConstants.BOTTOM);
		jb2.setHorizontalTextPosition(SwingConstants.CENTER);
		jmb.setFloatable(false);
		
				
		
		jmb.add(jb);
		jmb.add(jb2);
		jmb.add(jb3);
		jmb.add(jb4);
		jmb.add(jb5);
		jmb.add(jb6);
		jmb.add(jb7);
		jmb.add(jb8);
		jmb.add(jb9);
		//Con este layout tenemos NORTH, SOUTH, WEST, EAST Y CENTER para fijar los objetos
		this.setLayout(new BorderLayout());
		
		
		//Creamos un panel de tipo PanelPreferencias que hemos creado anteriormente
		PanelPreferencias panelPreferencias = new PanelPreferencias(this);  //Creamos un panel para la pestaña Preferencias
		
		//Fijamos SeaGlass como Look and feel
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
			} catch (UnsupportedLookAndFeelException ex) {
			
			}
		//Los JPanel que deben ser fijados como CENTER	
		this.add(panelPreferencias,BorderLayout.CENTER);
		
		jmb.setVisible(true);
		this.setVisible(true);
		//La ToolBar debe de estar en NORTH 
		this.add(jmb,BorderLayout.NORTH);
		//Opción para que cierre el probrama al cerrar la ventana si no hacemos esto
		//aunque cerremos la ventan seguira la aplicación en ejecucion
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//----------------------------------------------
		//Prueba, quitar o comentar para normal funcionamiento de la aplicación
		this.DlgLogin(170, 330, new dlgLogin());
		  }
	    
	    
	public void actionPerformed(ActionEvent e)
	{
		
	}
		
		
		
		
	
	//Metodo para mostrar un dialogo modal de tipo login
	public void DlgLogin(int x, int y, JPanel dlg){
		final JPanel glass = (JPanel) this.getGlassPane();
		int i=0,j=0;
		dlg.setOpaque(true);
		JPanel jp = new JPanel();
		//glass.setOpaque(true);
		glass.setLayout(null);
	//	dlg.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY));
		dlg.setBackground(new Color(0xED,0xED,0xED,240));
		//jp.setLocation(200, 83);
		jp.setLocation(((jmb.getSize().width/2)-(jp.getSize().width/2)),jmb.getSize().height-1);
		glass.setBackground(new Color(0,0,0,10));
		jp.setLayout(new BorderLayout());
		jp.add(dlg,BorderLayout.CENTER);
		jp.setBorder(new DropShadowBorder(UIManager.getColor("Control"), 1, 5, .5f, 12, false, true, true, true));
		glass.add(jp);
		glass.setVisible(true);    	
    	
		try {
    		Thread.sleep(4000);
    		} catch (InterruptedException ex) {
    		// aquí tratamos la excepción como queramos, haciendo nada, sacando por pantalla el error, ...
    		}
    		
		
	    for (i=x;i<=x+y;i++){
	    	if (j<x){
	    		j+=2;
	    		dlg.setSize(y,j);
	    		jp.setSize(dlg.getWidth()+12,dlg.getHeight()+9);
	    		jp.setLocation(((jmb.getSize().width/2)-(jp.getSize().width/2)),jmb.getSize().height-1);
	    		glass.repaint();
	    	}
	    	
	 
	    	try {
	    		Thread.sleep(1);
	    		} catch (InterruptedException ex) {
	    		// aquí tratamos la excepción como queramos, haciendo nada, sacando por pantalla el error, ...
	    		}
	    		
	    		
	    if (i==x+y){
	    	dlg.setPreferredSize(new Dimension(y,x));
	    	jp.setPreferredSize(new Dimension(dlg.getWidth()+12,dlg.getHeight()+9));
	    }
	    
		
	    
	    }
	    
	    try {
    		Thread.sleep(5000);
    		} catch (InterruptedException ex) {
    		// aquí tratamos la excepción como queramos, haciendo nada, sacando por pantalla el error, ...
    		}
	    
	    
	    
	    
	    for (i=x;i>0;i--){
	    	
	    		
	    		dlg.setSize(dlg.getWidth(),dlg.getHeight()-7);
	    		jp.setSize(dlg.getWidth()+12,dlg.getHeight()+9);
	    		//glass.repaint();
	    		jp.setLocation(((jmb.getSize().width/2)-(jp.getSize().width/2)),jmb.getSize().height-1);

	 
	    	try {
	    		Thread.sleep(1);
	    		} catch (InterruptedException ex) {
	    		// aqui tratamos la excepción como queramos, haciendo nada, sacando por pantalla el error, ...
	    		}
	    }
	    		
	}

}
