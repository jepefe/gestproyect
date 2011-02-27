package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class PnlContactos extends JPanel{
	RsGesproject recursos =  RsGesproject.instancia;
	JComboBox jcbEstado = new JComboBox();
	JTable jtblcontactos;
	DefaultTableModel tablemodelcont = new DefaultTableModel();
	JButton jbtnizq = new JButton();
	public PnlContactos(){
		jcbEstado.addItem("Disponible");
		jcbEstado.addItem("Invisible");
		
		recursos.instanciacontactos=this;
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		//this.setSize(200,500);
		this.setBackground(new Color(0xED,0xED,0xED));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
		CreaListaContactos();
		jtblcontactos.setPreferredSize(new Dimension(200,200));
		jcbEstado.setPreferredSize(new Dimension(200,30));
		this.add(jcbEstado,BorderLayout.NORTH);
		this.add(jtblcontactos,BorderLayout.CENTER);
		//this.add(jbtnizq,BorderLayout.WEST);
		recursos.instanciamsg.mostrarContactos(this);

		
		jtblcontactos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
				
				if (getComponentCount()>2){
					remove(2);
				}
				
						add(recursos.instanciamsg.contactos.get(jtblcontactos.getSelectedRow()).chat,BorderLayout.SOUTH);
				
				recursos.instancia.getRfrmppal().repaint();
				validate();
				}
			}});
		

	}
	
	
	
	//Crearemos un JTable con render personalizado para los contactos
	@SuppressWarnings("serial")
	public void CreaListaContactos(){
		
		tablemodelcont.addColumn("contacto");
		jtblcontactos = new JTable(tablemodelcont){
	           
			@Override
            public Class<?> getColumnClass(int column) {
                if (convertColumnIndexToModel(column) == 0) {
                    return Contacto.class;
                } else {
                    return Contacto.class;
                }
            }
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
              }
        };
      
        jtblcontactos.setDefaultRenderer(Contacto.class, new TableCellRenderer() {
			
			@Override
			 public Component getTableCellRendererComponent(JTable table, Object value,
				        boolean isSelected, boolean hasFocus, int row, int column) {
				 
				    Contacto panel = (Contacto) value;

				    if (isSelected) {
				      panel.setBackground(table.getSelectionBackground());
				    }else{
				      panel.setBackground(table.getSelectionForeground());
				    }
				    return panel;
				  }
			
			});
        jtblcontactos.setRowHeight(50);
        jtblcontactos.revalidate();
		
		
	}
	public void AnyadirAContactos(String usuario){
		Object []  obj = new Object[1];
		obj[0] = new Contacto(usuario);
		tablemodelcont.addRow(obj);
		
		
	}

}
