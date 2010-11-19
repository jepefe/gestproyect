package pkGesproject;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel Nuevo Proyecto En este panel es donde podremos dar de alta un nuevo proyecto
 * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
 */
public class PnlNuevoProyecto extends JPanel{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	
			JButton BtnAceptar = new JButton(rec.idioma[rec.eleidioma][0]); 
			JButton BtnCancelar = new JButton (rec.idioma[rec.eleidioma][1]); 
			JTextField txt_proyecto = new JTextField(20); 
			JTextArea txtarea_coment = new JTextArea( null, 5, 20 ); 
			
			JTextField txt_presupuesto = new JTextField(20); 
			JLabel lblproyecto = new JLabel(rec.idioma[rec.eleidioma][11]); 
			JLabel lblpresupuesto = new JLabel (rec.idioma[rec.eleidioma][12]);
			// Calendario
		
			public PnlNuevoProyecto(){
				 /**
				  * WEB MUY INTERESANTE LAYOUTS!!
				  * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
				  */
				this.setOpaque(true);
				// Para poder centrar todos los botones. Hay que centrar boton por boton.
				
				// para centrar objetos.
				this.setLayout (new GridBagLayout());
				GridBagConstraints lbl1  = new GridBagConstraints();
				pkGesproject.CentrarObjetos.CentrarObjetos(lbl1,1,1,1,1);
				
				
				
					this.add(lblproyecto,lbl1);
					this.add(txt_proyecto);
					this.add(lblpresupuesto); 
					this.add(txt_presupuesto);
					this.add(txtarea_coment);
					
					this.add(BtnAceptar); 
					this.add(BtnCancelar);		
					this.setVisible(true);
					
		
				}		
}

