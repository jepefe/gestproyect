package pkgesproyect;
import java.lang.*; import java.awt.*; import java.awt.event.*;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
 
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

public class dlgLogin extends JPanel {

JButton BtnAceptar = new JButton("Aceptar"); // Nuevo Boton
JTextField txt1 = new JTextField(20); // Nuevo Area de Texto de tamaÒo 20
JButton BtnCancelar = new JButton ("Cancelar"); // Nuevo Boton.
JPasswordField txtpass = new JPasswordField(20); // Nuevo Area de Texto tipo password de tamaÒo 20
JLabel lblusuario = new JLabel("    Usuario : "); // Nuevo Label Usuario
JLabel lblcontra = new JLabel ("Contaseña : ");// Nuevo Label contraseÒa



public dlgLogin(){
	 /**
	  * WEB MUY INTERESANTE LAYOUTS!!!
	  * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
	  */
	// Para poder centrar todos los botones. Hay que centrar boton por boton.
	this.setLayout (new GridBagLayout());
	GridBagConstraints poslblusuario = new GridBagConstraints();
	poslblusuario.gridx = 0; // El área de texto empieza en la columna 0.
	poslblusuario.gridy = 0;  // El área de texto empieza en la fila 0.
	poslblusuario.gridwidth = 2; // El área de texto ocupa 2 columnas. 
	poslblusuario.gridheight = 1; // El área de texto ocupa 1 filas.
	poslblusuario.weighty = 1.0;/*Para estirar filas y columnas,
	   							  dentro del GridBagConstraints 
	   							  tenemos los campos weigthx y weigthy*/
	
	GridBagConstraints postxt1 = new GridBagConstraints();
	postxt1.gridx = 2; // El área de texto empieza en la columna 2.
	postxt1.gridy = 0; // El área de texto empieza en la fila 0
	postxt1.gridwidth = 2;  // El área de texto ocupa 2 columnas. 
	postxt1.gridheight = 1;  // El área de texto ocupa 1 filas.
	
	
	GridBagConstraints poslblcontra = new GridBagConstraints();
	poslblcontra.gridx = 0; // El área de texto empieza en la columna cero.
	poslblcontra.gridy = 2; // El área de texto empieza en la fila 2.
	poslblcontra.gridwidth = 2;// El área de texto ocupa 2 columnas. 
	poslblcontra.gridheight = 1; // El área de texto ocupa 1 filas.
	poslblusuario.weighty = 0.0;  // Para que no estire la fila y se desplaze mas hacia abajo el label contraseña.
	
	GridBagConstraints postextpass = new GridBagConstraints();
	postextpass.gridx = 2; //El Text Box empieza en la columna 2.
	postextpass.gridy = 2; //El Text Box empieza en la fila 2.
	postextpass.gridwidth = 2; //El Text Box ocupa 2 columnas.
	postextpass.gridheight = 1;  //El Text Box ocupa 1 fila.
	
	GridBagConstraints posBtnaceptar = new GridBagConstraints();
	posBtnaceptar.gridx = 2; //El Boton empieza en la columna 2.
	posBtnaceptar.gridy = 4; //El Boton empieza en la fila 4.
	posBtnaceptar.gridwidth = 1; //El Boton ocupa 1 columna.
	posBtnaceptar.gridheight = 1; //El Boton ocupa 1 fila.
	
	
	GridBagConstraints posBtncancelar = new GridBagConstraints();
	posBtncancelar.gridx = 3; //El Boton empieza en la columna 3.
	posBtncancelar.gridy = 4; //El Boton empieza en la fila 4.
	posBtncancelar.gridwidth = 1; //El Boton ocupa 1 columna.
	posBtncancelar.gridheight = 1; //El Boton ocupa 1 fila.
	posBtncancelar.anchor = GridBagConstraints.WEST; // El boton se pegua al lado izquierdo
	
		this.add(lblusuario,poslblusuario);// Añadir el Label de Usuario.
		poslblusuario.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
		this.add(txt1,postxt1); // Añadir Area de Texto del Usuario
		this.add(lblcontra,poslblcontra); // Añadir el Label de Contraseña.
		this.add(txtpass,postextpass); // Añadir el Text Box del Password.
		this.add(BtnAceptar,posBtnaceptar); // Añadir el Boton de Aceptar.
		this.add(BtnCancelar,posBtncancelar); // Añadir el Boton Cancelar.		
}	
}
