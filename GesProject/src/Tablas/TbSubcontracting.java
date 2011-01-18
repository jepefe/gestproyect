package Tablas;

import javax.swing.JTabbedPane;

import pkGesproject.GesIdioma;
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Staff.pnlAlta_staff;
import pkGesproject.Tareas.PnlModificarTarea;

public class TbSubcontracting extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	
	
	public TbSubcontracting(){
		this.add(rec.idioma[rec.eleidioma][87],new pnlAlta_staff());
		this.add(rec.idioma[rec.eleidioma][88],new PnlAltasocio());
		this.add(rec.idioma[rec.eleidioma][89],new PnlModificarTarea());
	}
}
