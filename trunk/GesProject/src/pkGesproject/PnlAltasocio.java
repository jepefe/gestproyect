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
	
	public PnlAltasocio (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		 /*JFrame frame = new JFrame();
	      Container cp = frame.getContentPane();
	      cp.setLayout(new GridBagLayout());*/

	      this.setLayout(new GridBagLayout());
	      
	      String[] fieldNames = {
	         rec.idioma[rec.eleidioma][2],"Forenames","Country","Email Address",
	         "Land Line","Mobile/Cell Phone"
	      };
	      int[] fieldWidths = {10,20,30,30,15,15};

	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      this.add(new JLabel("Personal Information Form"),gbc);

	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);

	      for(int i=0;i<fieldNames.length;++i) {
	         gbc.gridwidth = GridBagConstraints.RELATIVE;
	         this.add(new JLabel(fieldNames[i]),gbc);
	         gbc.gridwidth = GridBagConstraints.REMAINDER;
	         this.add(new JTextField(fieldWidths[i]),gbc);
	      }

	      /*frame.pack();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);*/
	 
		
		this.setOpaque(true);
	}
}
