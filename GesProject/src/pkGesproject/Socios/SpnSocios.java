package pkGesproject.Socios;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;

public class SpnSocios extends JSplitPane{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JSplitPane jsplpane;
	JPanel panel = new JPanel();
	JPanel pnllateral = new JPanel();
	JTable jtblLateral;
	JScrollPane modificar;
	Border empty = new EmptyBorder(0,0,0,0);
	PnlModificarsocio mods = PnlModificarsocio.Obtener_Instancia();
	Component[] panlsStaff = {
			new PnlAltasocio(),
			modificar = new JScrollPane(mods)};

	public SpnSocios(){
		this.setOneTouchExpandable(true);
		this.setOpaque(true);
		modificar.setBorder(empty);
		
		Object[][] elementosbarralateral = new Object[][]{{recursos.icono[13],rec.idioma[rec.eleidioma][19]},
				{recursos.icono[14],rec.idioma[rec.eleidioma][20]}};
		
		
		jtblLateral  = new JTable(new DefaultTableModel(elementosbarralateral, new String[]{"Icono", "Descrip"})) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (convertColumnIndexToModel(column) == 0) {
                    return ImageIcon.class;
                } else {
                    return Object.class;
                }
            }
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
              }
        };
        
        jtblLateral.setCellEditor(null);
        jtblLateral.getColumnModel().getColumn(0).setMaxWidth(32);//Fijamos este tamaño como maximo para que no se vea feo cuando cambiemos tamaño
        jtblLateral.getColumnModel().getColumn(0).setMinWidth(32);
        jtblLateral.getColumnModel().getColumn(0).setPreferredWidth(32);//Tamaño por defecto de la primera columna
        jtblLateral.setRowHeight(32);//Tamaño de cada fila
        jtblLateral.getColumnModel().getColumn(1).setPreferredWidth(170);
        jtblLateral.getColumnModel().getColumn(1).setMinWidth(170);
        jtblLateral.setColumnSelectionAllowed(false);
        
        
        jtblLateral.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
					
					setRightComponent(panlsStaff[jtblLateral.getSelectedRow()]);
				}
			}});
        
        
		
    	this.setLeftComponent(jtblLateral);
    	this.setRightComponent(panel);
	}
}
