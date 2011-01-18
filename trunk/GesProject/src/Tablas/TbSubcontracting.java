package Tablas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import pkGesproject.GesIdioma;
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Staff.pnlAlta_staff;
import pkGesproject.Tareas.PnlModificarTarea;

public class TbSubcontracting extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	
	JScrollPane Subcontra;
	public TbSubcontracting(){
		this.add(rec.idioma[rec.eleidioma][87],Subcontra=new JScrollPane(new PnlAltaSub()));
		
		this.add(rec.idioma[rec.eleidioma][38],new JPanel());
		this.add(rec.idioma[rec.eleidioma][88],new JPanel());
	}
}
