package pkGesproject.Tareas;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;
import pkGesproject.Proyectos.PnlModificarProyecto;

public class SpnTareas extends JSplitPane{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JSplitPane jsplpane;
	JPanel panel = new JPanel();
	JTable jtblLateral;
	Border empty = new EmptyBorder(0,0,0,0);
	JScrollPane miscroll;
	Component[] panlsStaff = {
			new PnlAltatarea(),
			 miscroll=new JScrollPane(new PnlModificarTarea()),
			 new JPanel()};

	public SpnTareas(){
		this.setOneTouchExpandable(true);
		this.setOpaque(true);
		miscroll.setBorder(empty);
		Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][32]},
				{recursos.icono[6],rec.idioma[rec.eleidioma][33]},
				{recursos.icono[7],rec.idioma[rec.eleidioma][34]}};
		
		
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
        
        JPanel pnllateral = new JPanel();
		pnllateral .setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; //para que despues del label pase a la linea de abajo
        JLabel titulo = new JLabel("Titulo");
        
        JLabel tareas;
		pnllateral.add(tareas = new JLabel("TAREAS"),gbc);
        Font auxFont = titulo.getFont();
        tareas.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),20));
        gbc.weighty = 1.0;	//que la fila de la tabla se estire en vertical, de este modo el label se queda en la parte norte
        gbc.fill = GridBagConstraints.BOTH;	//Para estirar la barra lateral
    	pnllateral.add(jtblLateral,gbc);
		
    	this.setLeftComponent(pnllateral);
    	this.setRightComponent(panel);
	}
}
