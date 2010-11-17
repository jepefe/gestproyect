package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class PnlMenusocio extends JPanel{
	GesIdioma rec = GesIdioma.obtener_instancia();
	JToggleButton jbtnAlta;
	JToggleButton jbtnMb = new JToggleButton(rec.idioma[rec.eleidioma][14]);
	JToggleButton jbtnMostrar = new JToggleButton(rec.idioma[rec.eleidioma][15]);
	
	public PnlMenusocio(){
		this.setLayout(new GridBagLayout());
		
		jbtnAlta  = new JToggleButton(rec.idioma[rec.eleidioma][13]);
		GridBagConstraints posjbtnalta = new GridBagConstraints();
		posjbtnalta.gridx = 0; // El ·rea de texto empieza en la columna 2.
		posjbtnalta.gridy = 0; // El ·rea de texto empieza en la fila 0
		posjbtnalta.gridwidth = 1;  // El ·rea de texto ocupa 2 columnas. 
		posjbtnalta.gridheight = 1;  // El ·rea de texto ocupa 1 filas.
		this.add(jbtnAlta,posjbtnalta);
	}
}
