package pkGesproject;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel Nuevo Proyecto En este panel es donde podremos dar de alta un nuevo proyecto
 * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
 */
public class PnlNuevoProyecto extends JScrollPane{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JPanel jpnl = new JPanel();
	
	JTextField[] jtxt;
	
	
	// Calendario

	public PnlNuevoProyecto(){
		  jpnl.setLayout(new GridBagLayout());
		 
	      String[] fieldNames = {
	    		  rec.idioma[rec.eleidioma][11],rec.idioma[rec.eleidioma][12],
	    		  rec.idioma[rec.eleidioma][24],rec.idioma[rec.eleidioma][25]};
	      int[] fieldWidths = {10,5,7,7};
	      
	      jtxt = new JTextField[fieldNames.length];
	      // limite de caracteres 
	      int [] limite = {20,7,10,10}; 
	      
	       
	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);

	      for(int i=0;i<fieldNames.length;++i) {
	    	
	    	  gbc.gridwidth = GridBagConstraints.RELATIVE;
	         jpnl.add(new JLabel(fieldNames[i]),gbc);
	         gbc.gridwidth = GridBagConstraints.REMAINDER; 
	         // limite de caracteres
	         jpnl.add(jtxt[i] = new JTextField( new JTextFieldLimit(limite[i]), null, fieldWidths[i]),gbc);	  
    
		}
	      
	  //    jtxt[i] = (new JTextFieldLimit(10));
	      
	      
	      new JTextFieldLimit(10);
	      
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JLabel(rec.idioma[rec.eleidioma][16]),gbc); 
	      gbc.gridwidth = GridBagConstraints.REMAINDER;   
	      jpnl.add(new JTextArea(3,15),gbc);
	      
	      gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			jpnl.add(new JButton(rec.idioma[rec.eleidioma][0]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jpnl.add(new JButton(rec.idioma[rec.eleidioma][1]),gbc);
	   //   gbc.gridwidth = GridBagConstraints.REMAINDER;
	      jpnl.setVisible(true);
			this.setViewportView(jpnl);
	}		
}

