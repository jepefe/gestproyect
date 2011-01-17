package Tablas;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Staff.pnlAlta_staff;

public class TbViajes extends JTabbedPane{

	public TbViajes(){
		this.add("Altas",new pnlAlta_staff());
		this.add("Listar",new PnlAltasocio());
		
	}
}
