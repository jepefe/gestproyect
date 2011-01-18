package pkGesproject.TimeSheet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import pkGesproject.GesIdioma;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JDateChooser jdc1;
	int i;
	JLabel jlblfecha;
	JTextArea textarea = (new JTextArea(3,13));
	JButton jbtnaceptar, jbtncancelar;
	
	
	public PnlAlta_TimeSheet(){
		this.setLayout(new GridBagLayout());
		
		String[] fieldNames = {
				
				rec.idioma[rec.eleidioma][97]

		};
		
		int[] fieldWidths = {7};
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
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
		    	this.add(new JLabel(rec.idioma[rec.eleidioma][96]),gbc); 
		    	gbc.gridwidth = GridBagConstraints.REMAINDER;
		    
		    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		    	
		    	textarea.setDocument(lpd);
		    	textarea.setLineWrap(true);
		    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    	this.add((sp),gbc);
				
			}
			
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.anchor = GridBagConstraints.EAST;
			this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			
		}
	    gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][2]),gbc);
	    this.setVisible(true);
	}
	
}
