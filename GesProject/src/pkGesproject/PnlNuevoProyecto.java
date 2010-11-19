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
	
			JButton BtnAceptar = new JButton(rec.idioma[rec.eleidioma][0]); 
			JButton BtnCancelar = new JButton (rec.idioma[rec.eleidioma][1]); 
			JTextField txt_proyecto = new JTextField(20); 
			JTextArea txtarea_coment = new JTextArea( null, 5, 20 ); 
			
			JTextField txt_presupuesto = new JTextField(20); 
			JLabel lbl_proyecto = new JLabel(rec.idioma[rec.eleidioma][11]); 
			JLabel lblpresupuesto = new JLabel (rec.idioma[rec.eleidioma][12]);
			JLabel lblcomentarios = new JLabel (rec.idioma[rec.eleidioma][16]);
			JPanel jpnl = new JPanel();
			// Calendario
		
			public PnlNuevoProyecto(){
				 /**
				  * WEB MUY INTERESANTE LAYOUTS!!
				  * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
				  */
				jpnl.setOpaque(true);
				// Para poder centrar todos los botones. Hay que centrar boton por boton.
				
				// para centrar objetos.
				jpnl.setLayout (new GridBagLayout());	
				GridBagConstraints gbc  = new GridBagConstraints();
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,1,2,1,1);				
				jpnl.add(lbl_proyecto,gbc);
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,2,2,1,1);	
				jpnl.add(txt_proyecto,gbc);
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,1,3,1,1);				
				jpnl.add(lblpresupuesto,gbc); 
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,2,3,1,1);
				jpnl.add(txt_presupuesto,gbc);
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,1,8,1,1);
				jpnl.add(txtarea_coment,gbc);
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,1,10,1,1);
				jpnl.add(BtnAceptar,gbc); 
				
				pkGesproject.CentrarObjetos.CentrarObjetos(gbc,2,10,1,1);
				jpnl.add(BtnCancelar,gbc);	
				
				jpnl.add(lblcomentarios);
				
					jpnl.setVisible(true);
					this.setViewportView(jpnl);
		
				}		
}

