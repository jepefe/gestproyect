package pkGesproject;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PnlAltasocio extends JPanel{


	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	
	public PnlAltasocio (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		 /*JFrame frame = new JFrame();
	      Container cp = frame.getContentPane();
	      cp.setLayout(new GridBagLayout());*/

	      this.setLayout(new GridBagLayout());
	      
	      String[] fieldNames = {
	         rec.idioma[rec.eleidioma][2],rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][6],
	         rec.idioma[rec.eleidioma][7],rec.idioma[rec.eleidioma][4]
	      };
	      int[] fieldWidths = {20,10,30,6,8};
	      jtxt = new JTextField[fieldNames.length];
	      jlbl = new JLabel[fieldNames.length];

	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      this.add(new JLabel("Alta Partner"),gbc);

	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);

	      for(int i=0;i<fieldNames.length;++i) {
	         gbc.gridwidth = GridBagConstraints.RELATIVE;
	         this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
	         gbc.gridwidth = GridBagConstraints.REMAINDER;
	         this.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
	      }
	     
	      jtxt[1].setText("Construcción");
	      /*frame.pack();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);*/
	 
		
		this.setOpaque(true);
	}
}
