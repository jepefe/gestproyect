package pkGesproject.Preferencias;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import pkGesproject.GesIdioma;
import Tablas.PnlAlta_equipamientos;

public class TbIdiomas extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	Border empty = new EmptyBorder(0,0,0,0);
	JScrollPane Alta,modificacion;
	public TbIdiomas(){
		this.add(rec.idioma[rec.eleidioma][87],new PnlNuevoIdioma());
		this.add(rec.idioma[rec.eleidioma][38],modificacion=new JScrollPane());
		this.add(rec.idioma[rec.eleidioma][88],new JPanel());
		
	}
}
