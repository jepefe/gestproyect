package Tablas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import pkGesproject.GesIdioma;
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Staff.pnlAlta_staff;
import pkGesproject.Tareas.PnlModificarTarea;

public class TbSubcontracting extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	JScrollPane modificar;
	Border empty = new EmptyBorder(0,0,0,0);
	
	public TbSubcontracting(){
		this.add(rec.idioma[rec.eleidioma][87],new PnlAltaSub());
		
		this.add(rec.idioma[rec.eleidioma][38],modificar = new JScrollPane(new PnlMod_Sub()));
		modificar.setBorder(empty);
		
		
	}
}
