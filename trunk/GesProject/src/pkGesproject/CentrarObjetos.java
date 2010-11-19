package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* Clase para centrar los objetos, para realizar la llamada realizamos los siguientes pasos.
* 1ero--> GridBagConstraints gbc  = new GridBagConstraints();--> 
* creamos un nuevo GridBagConstrain con su nombre(gbc)
*2ndo -->	pkGesproject.CentrarObjetos.CentrarObjetos(gbc,1,1,1,1); 
*llamamos al metodo de la clase Centrar Objetos y introducimos (nombre,x,y ancho, alto)
*y pasamos el nombre al objet--> this.add(lblproyecto,gbc);
* 
*
 */
public class CentrarObjetos {

	// metodo para centrar los objetso
	public static void CentrarObjetos(GridBagConstraints nombre, int x, int y,int ancho, int alto) {

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
