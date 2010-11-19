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
	// Calendario

	public PnlNuevoProyecto(){
		  jpnl.setLayout(new GridBagLayout());
	      
	      String[] fieldNames = {
	    		  rec.idioma[rec.eleidioma][11],rec.idioma[rec.eleidioma][12]};
	      int[] fieldWidths = {10,5};

	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	   //   jpnl.add(new JLabel("Personal Information Form"),gbc);

	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);

	      for(int i=0;i<fieldNames.length;++i) {
	         gbc.gridwidth = GridBagConstraints.RELATIVE;
	         jpnl.add(new JLabel(fieldNames[i]),gbc);
	         gbc.gridwidth = GridBagConstraints.REMAINDER;
	         jpnl.add(new JTextField(fieldWidths[i]),gbc);     
	      }
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JTextField(rec.idioma[rec.eleidioma][16]),gbc); 
	      gbc.gridwidth = GridBagConstraints.REMAINDER;    
	      jpnl.add(new JTextArea(5,3));
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JButton(rec.idioma[rec.eleidioma][0]),gbc);
	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      jpnl.add(new JButton(rec.idioma[rec.eleidioma][1]),gbc);
	      jpnl.setVisible(true);
			this.setViewportView(jpnl);
	}		
}

