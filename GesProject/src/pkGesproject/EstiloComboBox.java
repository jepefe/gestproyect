package pkGesproject;

import java.awt.Rectangle;

import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import com.seaglasslookandfeel.ui.SeaGlassComboBoxUI;

/**
 * 
 * @author Jesus PŽrez
 * Clase que modifica el estilo de ComboBox y soluciona(ataja) un bug del LaF Seaglass
 *
 */
public class EstiloComboBox extends SeaGlassComboBoxUI {
	  protected ComboPopup createPopup()
	    {
	    @SuppressWarnings("serial")
		BasicComboPopup popup = new BasicComboPopup(comboBox)
	    
	      {
	      protected Rectangle computePopupBounds(int px,int py,int pw,int ph)
	        {
	    	
	        return super.computePopupBounds(px,py,Math.max(comboBox.getPreferredSize().width,pw),ph);
	        }
	      
	      };
	    popup.getAccessibleContext().setAccessibleParent(comboBox);
	   
	    
	    return popup;
	    }

}