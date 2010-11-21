package pkGesproject;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class SpnStaff extends JSplitPane{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JToolBar jtlbLateral = new JToolBar();
	JSplitPane jsplpane;
	Vector botones = new Vector(3,2);
	JPanel panel = new JPanel();
	pnlAlta_staff pnlalta_staff = new pnlAlta_staff();
	int i;
	//JToggleButton bt = new JToggleButton(recursos.icono[5]);
	
	public SpnStaff(){
		this.setOneTouchExpandable(true);
		this.setOpaque(true);
		
		/*
		 * Ponemos propiedades adecuadas al toolbar
		 */
		jtlbLateral.setLayout(new GridBagLayout());
    	jtlbLateral.setOpaque(true);
    	jtlbLateral.setFloatable(false);
    	
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridwidth = GridBagConstraints.REMAINDER;
    	gbc.anchor = GridBagConstraints.NORTHWEST;
    	//gbc.fill = GridBagConstraints.WEST;
    	
    	
    	JToggleButton bt = new JToggleButton(recursos.icono[5]);
    	bt.setText(rec.idioma[rec.eleidioma][13]);
    	jtlbLateral.add(bt,gbc);
    	
    	JToggleButton bt1 = new JToggleButton(recursos.icono[6]);
    	bt1.setText(rec.idioma[rec.eleidioma][14]);
    	jtlbLateral.add(bt1,gbc);
    	
    	JToggleButton bt2 = new JToggleButton(recursos.icono[7]);
    	bt2.setText(rec.idioma[rec.eleidioma][15]);
    	jtlbLateral.add(bt2,gbc);
    	
		/*
    	jsplpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jtlbLateral, prueba);
    	jsplpane.setOneTouchExpandable(true);
    	jsplpane.setOpaque(true);
    	*/
    	
    	
    	this.setLeftComponent(jtlbLateral);
    	this.setRightComponent(pnlalta_staff);
	}
}
