package pkGesproject.informes;

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

public class SpnInformes extends JSplitPane{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JSplitPane jsplpane;
	JPanel panel = new JPanel();
	JPanel pnllateral = new JPanel();
	JTable jtblLateral;
	JScrollPane partner,proyecto,staff,wp,tareas;
	Border empty = new EmptyBorder(0,0,0,0);
	PnlModificarsocio mods = PnlModificarsocio.Obtener_Instancia();
	Component[] panlsStaff = {
			partner = new JScrollPane(new PnlInfPartner()),
			proyecto = new JScrollPane(new PnlInfProyecto()),
			staff = new JScrollPane(new PnlInfStaff()),
			wp = new JScrollPane(new PnlInfWp()),
			tareas= new JScrollPane(new PnlInfTareas()),};

	public SpnInformes(){
		this.setOneTouchExpandable(true);
		this.setOpaque(true);
		partner.setBorder(empty);
		proyecto.setBorder(empty);
		staff.setBorder(empty);
		wp.setBorder(empty);
		tareas.setBorder(empty);
		
		Object[][] elementosbarralateral = new Object[][]{{recursos.icono[31],rec.idioma[rec.eleidioma][57]},
				{recursos.icono[31],rec.idioma[rec.eleidioma][55]},
				{recursos.icono[31],rec.idioma[rec.eleidioma][130]},
				{recursos.icono[31],rec.idioma[rec.eleidioma][40]},
				{recursos.icono[31],rec.idioma[rec.eleidioma][129]},};
		
		
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
		pnllateral.add(socio = new JLabel("INFORMES"),gbc);
        Font auxFont = titulo.getFont();
        socio.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),20));
        gbc.weighty = 1.0;	//que la fila de la tabla se estire en vertical, de este modo el label se queda en la parte norte
        gbc.fill = GridBagConstraints.BOTH;	//Para estirar la barra lateral
    	pnllateral.add(jtblLateral,gbc);
		
    	this.setLeftComponent(pnllateral);
    	this.setRightComponent(panel);
	}
}
