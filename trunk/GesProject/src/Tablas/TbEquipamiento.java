package Tablas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import pkGesproject.GesIdioma;

public class TbEquipamiento extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	
	JScrollPane Subcontra;
	public TbEquipamiento(){
		this.add(rec.idioma[rec.eleidioma][87],Subcontra=new JScrollPane(new PnlAlta_equipamientos()));
		this.add(rec.idioma[rec.eleidioma][38],new JPanel());
		this.add(rec.idioma[rec.eleidioma][88],new JPanel());
	}
}
