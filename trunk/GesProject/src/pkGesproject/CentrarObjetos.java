package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CentrarObjetos {

	
	public CentrarObjetos(String nomboton,int x ,int y,int ancho  ,int alto ){
			
		GridBagConstraints nombre  = new GridBagConstraints();
		
			nombre.gridx = x; // El área de texto empieza en la columna 0.
			nombre.gridy = y;  // El área de texto empieza en la fila 0.
			nombre.gridwidth = ancho; // El área de texto ocupa 2 columnas. 
			nombre.gridheight = alto; // El área de texto ocupa 1 filas.
		//	nombre.weighty = 1.0;
										/*Para estirar filas y columnas,
			   							  dentro del GridBagConstraints 
			   							  tenemos los campos weigthx y weigthy*/
		
	}
}
