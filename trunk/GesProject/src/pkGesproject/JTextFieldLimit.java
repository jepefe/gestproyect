package pkGesproject;
import java.awt.Toolkit;

import javax.swing.text.*; 

/**
 * Clase para limitar los JTextField, solo hay que introducir en el Jtext field new JTextFieldLimit(numero).
 */

public class JTextFieldLimit extends PlainDocument { 
    int caracteresMaximos;

	public JTextFieldLimit( int caracteresMaximos ) {
			this.caracteresMaximos = caracteresMaximos;
	}

	public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {
			if ( str.indexOf("?") == -1 && str.indexOf("?") == -1 && (getLength() + str.length()) <= caracteresMaximos)
					super.insertString(offs, str, a);
			else
				Toolkit.getDefaultToolkit().beep();
			} 
} 
