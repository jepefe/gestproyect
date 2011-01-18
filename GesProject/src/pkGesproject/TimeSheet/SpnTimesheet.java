package pkGesproject.TimeSheet;

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
import pkGesproject.Socios.PnlAltasocio;
import pkGesproject.Socios.PnlModificarsocio;

public class SpnTimesheet extends JSplitPane{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JSplitPane jsplpane;
	JPanel panel = new JPanel();
	JPanel pnllateral = new JPanel();
	JTable jtblLateral;
	JScrollPane modificar;
	Border empty = new EmptyBorder(0,0,0,0);
	Component[] panlsStaff = {
			new JScrollPane(new PnlAlta_TimeSheet()),
			new JPanel(),
			new JPanel()};

	public SpnTimesheet(){
		this.setOneTouchExpandable(true);
		this.setOpaque(true);
		//modificar.setBorder(empty);
		
		Object[][] elementosbarralateral = new Object[][]{{recursos.icono[28],rec.idioma[rec.eleidioma][98]},
				{recursos.icono[29],rec.idioma[rec.eleidioma][20]},
				{recursos.icono[30],rec.idioma[rec.eleidioma][21]}};
		
		
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
        

		pnllateral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; //para que despues del label pase a la linea de abajo
        JLabel titulo = new JLabel("Titulo");
        
        JLabel socio;
		pnllateral.add(socio = new JLabel("TIMESHEET"),gbc);
        Font auxFont = titulo.getFont();
        socio.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),20));
        gbc.weighty = 1.0;	//que la fila de la tabla se estire en vertical, de este modo el label se queda en la parte norte
        gbc.fill = GridBagConstraints.BOTH;	//Para estirar la barra lateral
    	pnllateral.add(jtblLateral,gbc);
		
    	this.setLeftComponent(pnllateral);
    	this.setRightComponent(panel);
	}

}
