package pkGesproject;
import javax.swing.text.*; 

/**
 * Clase para limitar los JTextField, solo hay que introducir en el Jtext field new JTextFieldLimit(numero).
 */

public class JTextFieldLimit extends PlainDocument { 
    private int limit; 

    private boolean toUppercase = false ; 
    
    public JTextFieldLimit ( int limit ) { 
        super () ; 
        this .limit = limit; 
    } 
    
    JTextFieldLimit ( int limit, boolean upper ) { 
        super () ; 
        this .limit = limit; 
        toUppercase = upper; 
    } 
    
    public void insertString 
            ( int offset, String  str, AttributeSet attr ) 
            throws BadLocationException { 
        if ( str == null ) return ; 
        
        if (( getLength () + str.length ()) <= limit ) { 
            if ( toUppercase ) str = str.toUpperCase () ; 
            super .insertString ( offset, str, attr ) ; 
        } 
    } 
} 
