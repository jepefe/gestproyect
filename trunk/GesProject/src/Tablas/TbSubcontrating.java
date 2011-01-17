package Tablas;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import pkGesproject.Tareas.PnlAltatarea;

public class TbSubcontrating extends JTabbedPane{
	
	public void JTabbed(){
	this.add("Viajes", new JScrollPane(new PnlAltatarea()));
	
	
	}
}
