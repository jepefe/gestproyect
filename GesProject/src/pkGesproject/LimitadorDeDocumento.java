package pkGesproject;

import java.awt.*;
import javax.swing.text.*;

public class LimitadorDeDocumento extends DefaultStyledDocument {
	int caracteresMaximos;

	public LimitadorDeDocumento( int caracteresMaximos ) {
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
