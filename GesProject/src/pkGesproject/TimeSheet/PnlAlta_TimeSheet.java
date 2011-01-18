package pkGesproject.TimeSheet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{
	
	JTextField[] jtxt;
	JLabel[] jlbl;
	JDateChooser jdc1;
	int i;
	JLabel jlblfecha;
	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	public PnlAlta_TimeSheet(){
		this.setLayout(new GridBagLayout());
		
		String[] fieldNames = {
				rec.idioma[rec.eleidioma][96],
				rec.idioma[rec.eleidioma][97]

		};
		
		int[] fieldWidths = {10,30,6};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,10,5,5);
		
		jdc1 = new JDateChooser();
	    jdc1.setDateFormatString("dd/MM/yyyy");
	    
	    
	    for(i = 0;i<fieldNames.length;i++){
			if(i==0){
			    gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblfecha=new JLabel(rec.idioma[rec.eleidioma][95]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
			}
		}
	    this.setVisible(true);
	}
	
}
