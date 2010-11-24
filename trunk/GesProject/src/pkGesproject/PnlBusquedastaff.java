package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PnlBusquedastaff extends JScrollPane{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JPanel panel = new JPanel();
	String datos[][] = new String[100][3];
	String colu[] = {"id_staff","dni","Nombre"};
	Object[][] elementosbarralateral = new Object[][]{{recursos.icono[5],rec.idioma[rec.eleidioma][31]},
			{recursos.icono[6],rec.idioma[rec.eleidioma][32]},
			{recursos.icono[7],rec.idioma[rec.eleidioma][33]}};
	JLabel jlbl;
	JTextField jtxt;
	JButton jbtn,jbtnmodificar,jbtneliminar;
    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
    public PnlBusquedastaff(){
    	panel.setLayout(new GridBagLayout());
    	conexion.Conectardb();
    	rs = conexion.ConsultaSQL("SELECT id_staff,dni,nombre FROM STAFF");
    	int i=0;
    	try {
			while(rs.next()){
				for(int j = 1;j<4;j++){
					datos[i][j-1] = rs.getString(j);
				}
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	conexion.cerrarConexion();
    	
    		
		   
		   
    	JTable jtblLateral  = new JTable(new DefaultTableModel(datos, colu)) {
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
            /*jtblLateral.getColumnModel().getColumn(0).setMaxWidth(32);//Fijamos este tamaño como maximo para que no se vea feo cuando cambiemos tamaño
            jtblLateral.getColumnModel().getColumn(0).setMinWidth(32);
            jtblLateral.getColumnModel().getColumn(0).setPreferredWidth(32);//Tamaño por defecto de la primera columna*/
            jtblLateral.setRowHeight(32);//Tamaño de cada fila
            /*jtblLateral.getColumnModel().getColumn(1).setPreferredWidth(170);
            jtblLateral.getColumnModel().getColumn(1).setMinWidth(170);*/
            jtblLateral.setColumnSelectionAllowed(false);
            
            JTableHeader header = jtblLateral.getTableHeader();
            
           
            
            
            JScrollPane pane = new JScrollPane(jtblLateral);
            jtblLateral.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            //panel.add(pane);
            //pane.setSize(800, 600);
            //panel.setSize(800, 500);
            //gbc.gridx = 3;
            
            
GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5,10,5,5);
    	 	gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl=new JLabel("Buscar"),gbc);
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jtxt=new JTextField(20),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   gbc.anchor = GridBagConstraints.WEST;
		   panel.add(jbtn=new JButton("Buscar"),gbc);
		  
            //gbc.gridy = 3;
            panel.add(pane,gbc);
            
           gbc.gridwidth = GridBagConstraints.RELATIVE; 
 		   panel.add(jbtnmodificar=new JButton("Modificar"),gbc);
            
 		  gbc.gridwidth = GridBagConstraints.RELATIVE; 
		   panel.add(jbtneliminar=new JButton("eliminar"),gbc);
            
        	this.setViewportView(panel);
    
    }
}
