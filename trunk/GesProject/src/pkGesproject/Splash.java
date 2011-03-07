package pkGesproject;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;


import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class Splash extends JWindow{
	Runnable cargando;
	JProgressBar jpbcargando = new JProgressBar();	
	JLabel jlbmsgcargando = new JLabel();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	Image imagen = recursos.imagen[0];
	
	
	public Splash(){
		JPanel panel = new JPanel(){
			public void paint(Graphics g) {
				try {
					g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
					setOpaque(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				super.paint(g);

			}
		};
		
		
		this.setContentPane(panel);
		this.setSize(440,270);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
		this.setLocation(x,y);
		jpbcargando.setMaximum(10);
		jlbmsgcargando.setFont(new Font(Font.SANS_SERIF, Font.BOLD,13));
		panel.setLayout(null);
		
		
		panel.add(jlbmsgcargando);
		panel.add(jpbcargando);
		/**
		 * jpbcargando.setBounds(200, 170, 180, 130);
		 *	jlbmsgcargando.setBounds(210, 180, 180, 50);
		 */
		jpbcargando.setBounds(40, 220, 170, 130);
		jlbmsgcargando.setBounds(45, 190, 180, 50);
		jlbmsgcargando.setForeground(Color.white);
		
		AWTUtilities.setWindowOpaque(this, false);
		panel.setDoubleBuffered(true);
		



	}


	public void mostrar(){
		setVisible(true);
		Thread hilo = new Thread(new Runnable(){

			@Override
			public void run() {


				while(recursos.progresocarga <= 10) {
					jpbcargando.setValue(recursos.progresocarga);
					jlbmsgcargando.setText(recursos.txtcarga);
					if(recursos.progresocarga == 10){
						recursos.progresocarga = 11;
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}

				}
				dispose();
				
			}

		});
		hilo.start();
		this.validate();


	}



}
