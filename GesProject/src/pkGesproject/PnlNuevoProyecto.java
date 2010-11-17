package pkGesproject;



import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
			/*	this.setLayout (new GridBagLayout());
				GridBagConstraints poslblusuario = new GridBagConstraints();
				poslblusuario.gridx = 0; // El área de texto empieza en la columna 0.
				poslblusuario.gridy = 0;  // El área de texto empieza en la fila 0.
				poslblusuario.gridwidth = 2; // El área de texto ocupa 2 columnas. 
				poslblusuario.gridheight = 1; // El área de texto ocupa 1 filas.
				poslblusuario.weighty = 1.0;/*Para estirar filas y columnas,
				   							  dentro del GridBagConstraints 
				   							  tenemos los campos weigthx y weigthy*/
				
				
					this.add(lblproyecto);
					this.add(txt_proyecto);
					this.add(lblpresupuesto); 
					this.add(txt_presupuesto);
					this.add(txtarea_coment);
					this.add(BtnAceptar); 
					this.add(BtnCancelar);		
					this.setVisible(true);
					
		
				}		
}

