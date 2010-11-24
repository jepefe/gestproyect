package pkGesproject;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;



/*
 * Clase para limitar los cracteres de  JTextArea
 * 
 */
	public class LimitedStyledDocument extends DefaultStyledDocument {

		int maxCharacters;

		public LimitedStyledDocument(int maxChars) {
		maxCharacters = maxChars;
		}

		public void insertString(int offs, String str, AttributeSet a)
		throws BadLocationException {

		if ((getLength() + str.length()) <= maxCharacters)
		super.insertString(offs, str, (javax.swing.text.AttributeSet) a);
		else
		Toolkit.getDefaultToolkit().beep();
		}
	}

		

