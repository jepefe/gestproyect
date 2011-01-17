package Tablas;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import pkGesproject.Staff.pnlAlta_staff;

public class TbViajes extends JTabbedPane{

	public TbViajes(){
		this.add("Viajes",new JScrollPane(new pnlAlta_staff()));
	}
}
