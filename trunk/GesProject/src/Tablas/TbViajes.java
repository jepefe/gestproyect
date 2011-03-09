package Tablas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import pkGesproject.GesIdioma;
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Staff.pnlAlta_staff;

public class TbViajes extends JTabbedPane{

	GesIdioma rec = GesIdioma.obtener_instancia();
	Border empty = new EmptyBorder(0,0,0,0);
	JScrollPane miscroll;
	public TbViajes(){
		this.add(rec.idioma[rec.eleidioma][87],miscroll=new JScrollPane(new PnlAlta_viajes()));
		this.add(rec.idioma[rec.eleidioma][38],new JPanel());
		
		miscroll.setBorder(empty);
	}
}
