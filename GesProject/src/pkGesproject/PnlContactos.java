package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jivesoftware.smack.packet.Presence;

@SuppressWarnings("serial")
public class PnlContactos extends JSplitPane{
	RsGesproject recursos =  RsGesproject.instancia;
	JComboBox jcbEstado = new JComboBox();
	JTable jtblcontactos;
	DefaultTableModel tablemodelcont = new DefaultTableModel();
	JScrollPane jscptabla;
	GesIdioma rec = GesIdioma.obtener_instancia();
	ActionListener al;
	JPanel jpnlcont = new JPanel();
	public PnlContactos(){
		jcbEstado.addItem(rec.idioma[rec.eleidioma][184]);
		jcbEstado.addItem(rec.idioma[rec.eleidioma][185]);
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		recursos.instanciacontactos=this;
		jpnlcont.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setOneTouchExpandable(true);
		
		//jpnlcont.setMinimumSize(new Dimension(250,90));
		//this.setSize(200,500);
		this.setBackground(new Color(0xED,0xED,0xED));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
		CreaListaContactos();
		//jtblcontactos.setPreferredSize(new Dimension(250,200));
		//this.setMaximumSize(new Dimension(200,200));
		//jcbEstado.setPreferredSize(new Dimension(250,30));
		jpnlcont.add(jcbEstado,BorderLayout.NORTH);
		jpnlcont.add(jscptabla,BorderLayout.CENTER);
		this.setTopComponent(jpnlcont);
		this.setBottomComponent(null);
		
		//this.add(jbtnizq,BorderLayout.WEST);
		recursos.instanciamsg.mostrarContactos(this);
		
		al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JComboBox)e.getSource()).getSelectedIndex()==0){
					recursos.instanciamsg.connection.sendPacket(new Presence(Presence.Type.available));
				}else{
					recursos.instanciamsg.connection.sendPacket(new Presence(Presence.Type.unavailable));
				}
				
			}
		};
		jcbEstado.addActionListener(al);
		jtblcontactos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
				
				if (getComponentCount()>2){
					remove(2);
				}
				
							
					setBottomComponent(recursos.instanciamsg.contactos.get(jtblcontactos.getSelectedRow()).chat);
						recursos.instanciamsg.contactos.get(jtblcontactos.getSelectedRow()).avisamsg(true);
						recursos.instanciamsg.contactos.get(jtblcontactos.getSelectedRow()).jtxamsg.requestFocus();
				recursos.instancia.getRfrmppal().repaint();
				validate();
				}
			}});
		recursos.instanciamsg.connection.sendPacket(new Presence(Presence.Type.available));
		recursos.instanciamsg.consultarEstado();

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
				    

				    panel.setPreferredSize(new Dimension(jtblcontactos.getWidth(),jtblcontactos.getRowHeight()));
				    panel.jpContacto.setPreferredSize(new Dimension(jtblcontactos.getWidth(),jtblcontactos.getRowHeight()));
				    

				    if (isSelected) {
				      panel.setBackground(table.getSelectionBackground());
				      panel.setOpaque(true);
				    }else{
				      panel.setBackground(table.getSelectionForeground());
				      panel.setOpaque(false);
				      
				    }
				    return panel;
				  }
			
			});
        jtblcontactos.setRowHeight(50);
        jtblcontactos.setAutoResizeMode(jtblcontactos.AUTO_RESIZE_ALL_COLUMNS);
        jtblcontactos.setAlignmentX(LEFT_ALIGNMENT);
        jtblcontactos.revalidate();
		jscptabla = new JScrollPane(jtblcontactos);
		
	}
	public void AnyadirAContactos(String usuario){
		Object []  obj = new Object[1];
		obj[0] = new Contacto(usuario);
		tablemodelcont.addRow(obj);
		
		
	}

}
