package pkgesproyect;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import com.seaglasslookandfeel.ui.SeaGlassRootPaneUI;

import java.awt.*;

public class frmppal extends JFrame{

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
		this.DlgLogin(170, 330, new dlgLogin());
		  }
	    
	    
	    
		
		
		
		
	
	//Metodo en construccion, no funciona
	public void DlgLogin(int x, int y, JPanel dlg){
		final JPanel glass = (JPanel) this.getGlassPane();
		//JPanel dlg = new JPanel();
		int i=0,j=0;
		dlg.setOpaque(true);
		JPanel jp = new JPanel();
		
		jp.setBorder(new DropShadowBorder());
		jp.setLayout(new BorderLayout());
		jp.add(dlg,BorderLayout.CENTER);
		glass.add(jp);
    	glass.setVisible(true);
    	dlg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
    	
	    for (i=y;i<=x+y;i++){
	    	if (j<x){
	    		j++;
	    		dlg.setSize(y,j);
	    		jp.setSize(y+9,j+9);
	    		glass.repaint();
	    	}
	    	
	    if (i%2 == 0){
	    	try {
	    		Thread.sleep(0,100);
	    		} catch (InterruptedException ex) {
	    		// aquí tratamos la excepción como queramos, haciendo nada, sacando por pantalla el error, ...
	    		}
	    		
	    		}
	    if (i==x+y){
	    	jp.setPreferredSize(new Dimension(y+9,x+9));
	    	dlg.setPreferredSize(new Dimension(y,x));
	    }
	    
		
	    }
	    jmb.setEnabled(false);
	}

}
