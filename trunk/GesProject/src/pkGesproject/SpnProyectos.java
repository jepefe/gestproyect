package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class SpnProyectos extends JSplitPane{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JToolBar jtlbLateral = new JToolBar();
	JSplitPane jsplpane;
	JPanel panel = new JPanel();
	pnlAlta_staff pnlalta_staff = new pnlAlta_staff();
	PnlAltasocio pnlaltasocio = new PnlAltasocio();
	PnlNuevoProyecto pnlnuevoproyecto = new PnlNuevoProyecto();
	
	public SpnProyectos(){
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
    	
    	
    	final JToggleButton bt = new JToggleButton(recursos.icono[5]);
    	bt.setText(rec.idioma[rec.eleidioma][11]);
    	bt.setActionCommand("alta");
    	
    	jtlbLateral.add(bt,gbc);
    	
    	final JToggleButton bt1 = new JToggleButton(recursos.icono[6]);
    	bt1.setText(rec.idioma[rec.eleidioma][21]);
    	bt1.setActionCommand("modificacion");
    	jtlbLateral.add(bt1,gbc);
    	
    	final JToggleButton bt2 = new JToggleButton(recursos.icono[7]);
    	bt2.setText(rec.idioma[rec.eleidioma][22]);
    	bt2.setActionCommand("mostrar");
    	jtlbLateral.add(bt2,gbc);
    	
    	
    	ActionListener jtlbactionlistener = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bt.setSelected(false);
				bt1.setSelected(false);
				bt2.setSelected(false);
				remove(pnlalta_staff);
				remove(pnlaltasocio);
				remove(pnlnuevoproyecto);
				if(e.getActionCommand().equals("alta")){
					bt.setSelected(true);
					setRightComponent(pnlnuevoproyecto);
				}
				
				if(e.getActionCommand().equals("modificacion")){
					bt1.setSelected(true);
					setRightComponent(pnlalta_staff);
				}
				
				if(e.getActionCommand().equals("mostrar")){
					bt2.setSelected(true);
					setRightComponent(pnlnuevoproyecto);
				}
			}
    		
    	};
    	
    	bt.addActionListener(jtlbactionlistener);
    	bt1.addActionListener(jtlbactionlistener);
    	bt2.addActionListener(jtlbactionlistener);
    
    	this.setLeftComponent(jtlbLateral);
    	this.setRightComponent(panel);
	}
}
