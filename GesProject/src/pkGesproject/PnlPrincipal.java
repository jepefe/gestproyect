package pkGesproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ar.com.fdvs.dj.domain.constants.Border;



/**
 * @author  Orion
 */
public class PnlPrincipal extends JPanel {
	JPanel jpnlinfousuario = new JPanel();
	JPanel jpncentral = new JPanel();
	JPanel jpnlproyecto = new JPanel();
	JPanel jpnlwp = new JPanel();
	JScrollPane jscppartpro = new JScrollPane();
	JScrollPane jscwpsrtpro = new JScrollPane();
	JPanel jpnlpartproyecto = new JPanel();
	JPanel jpnlstaffwp = new JPanel();
	
	/**
	 * @uml.property  name="jicusu"
	 * @uml.associationEnd  
	 */
	JImageContainer jicusu = new JImageContainer();
	/**
	 * @uml.property  name="recursos"
	 * @uml.associationEnd  
	 */
	RsGesproject recursos = RsGesproject.instancia;
	/**
	 * @uml.property  name="cdb"
	 * @uml.associationEnd  
	 */
	ConexionDb cdb = new ConexionDb();
	ResultSet rs;
	JLabel jlbnombre = new JLabel();
	JLabel jlbapellidos = new JLabel();
	JLabel jlbciudad = new JLabel();
	JLabel jlbregion = new JLabel();
	JLabel jlbtelefono = new JLabel();
	JLabel jlbemail = new JLabel();
	JLabel jlbpartner = new JLabel();
	JLabel jlbFoto = new JLabel();
	JLabel jlbLogo = new JLabel();
	JLabel jlbpartnombre = new JLabel();
	JLabel jlbpartsector = new JLabel();
	JLabel jlbpartdireccion = new JLabel();
	JLabel jlbpartcodpostal = new JLabel();
	JLabel jlbpartpais = new JLabel();
	JLabel jlbpronom = new JLabel();
	JLabel jlbprodesc = new JLabel();
	JLabel jlbpropresu = new JLabel();
	JLabel jlbprofini = new JLabel();
	JLabel jlbproffin = new JLabel();
	JLabel jlbwpnom = new JLabel();
	JLabel jlbwpdes = new JLabel();
	JLabel jlbwppresu = new JLabel();
	JLabel jlbwpfini = new JLabel();
	JLabel jlbwpffin = new JLabel();
	JLabel jlbstaffwp =new JLabel();
	Integer[] wp;
	
	JLabel jlbprocontrato = new JLabel();
	JLabel jlbpartproyecto = new JLabel();
	JComboBox jcbproyecto = new JComboBox();
	JComboBox jcbwp = new JComboBox();
	ActionListener alpro;
	ActionListener alstaff;
	/**
	 * @uml.property  name="rec"
	 * @uml.associationEnd  
	 */
	GesIdioma rec = GesIdioma.obtener_instancia();
	Integer[] proyectos;
	JTable jtblpartners;
	JTable jtblstaff;
	String[] partners;
	String[] staff;
	DefaultTableModel tablemodelpart = new DefaultTableModel();
	DefaultTableModel tablemodelstaff = new DefaultTableModel();
	
	public PnlPrincipal(){
		jpncentral.setLayout(new GridLayout(0,2));
		this.setLayout(new BorderLayout());
		CargaInfoUsuario();
		CargarPanelProyecto();
		ActualizarPartnersProyecto();
		CargarWP();
		ActualizarStaffWp();
		this.add(jpnlinfousuario,BorderLayout.WEST);
		this.setOpaque(true);
		this.add(jpncentral,BorderLayout.CENTER);
		this.repaint();
		
		
	}
	
	
	
	
	
	
	public void CargaInfoUsuario(){
		
		jlbapellidos.setFont(recursos.fuente);
		jlbnombre.setFont(recursos.fuente);
		jlbpartner.setFont(recursos.fuente);
		jlbciudad.setFont(recursos.fuente);
		jlbregion.setFont(recursos.fuente);
		jlbtelefono.setFont(recursos.fuente);
		
		
		
		jlbemail.setFont(recursos.fuente);
		jpnlinfousuario.setLayout(new BoxLayout(jpnlinfousuario,1));
		jlbFoto.setIcon(recursos.icono[1]);
		jpnlinfousuario.setBackground(new Color(0xED,0xED,0xED));
		jpnlinfousuario.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
		
		Thread hilofoto = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				
				Image imgfoto = cftp.ObtenerImagen("fto"+Integer.toString(recursos.getIdusuario()));
				float ancho =130;
				float alto = 130;
				if((imgfoto.getHeight(null)<ancho)){
				
				alto = imgfoto.getHeight(null)*(ancho/(imgfoto.getWidth(null)));
				}else{
				 alto = imgfoto.getHeight(null)/(imgfoto.getWidth(null)/ancho);
				}
				jlbFoto.setPreferredSize(new Dimension((int)ancho,(int)alto));
				jlbFoto.setAlignmentX(TOP_ALIGNMENT);
				jlbFoto.setIcon(new ImageIcon(imgfoto.getScaledInstance((int)ancho, (int)alto, Image.SCALE_SMOOTH)));
				
				System.out.println("FOTO OBTENIDA");
				
				jlbFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
				
				
			
				
			}
		});
		hilofoto.start();
		
	Thread hilologo = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				Image imglogo = cftp.ObtenerImagen("logo"+Integer.toString(recursos.getCodparter()));
				float ancho =130;
				float alto = 130;
				if((imglogo.getHeight(null)<ancho)){
				
				 alto = imglogo.getHeight(null)*(ancho/(imglogo.getWidth(null)));
				}else{
					 alto = imglogo.getHeight(null)/(imglogo.getWidth(null)/ancho);
				}
				jlbLogo.setPreferredSize(new Dimension((int)ancho,(int)alto));
				
				jlbLogo.setIcon(new ImageIcon(imglogo.getScaledInstance((int)ancho, (int)alto,Image.SCALE_SMOOTH )));
				
				System.out.println("LOGO OBTENIDO");
				
				jlbLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
				
			
				
			}
		});
		hilologo.start();
		
		rs = cdb.ConsultaSQL("SELECT nombre,apellidos,ciudad,region,telefono,email FROM STAFF WHERE id_staff ='"+Integer.toString(recursos.getIdusuario())+"'");
		try {
			if(rs.next()){
				jlbnombre.setText(" "+rec.idioma[rec.eleidioma][3]+": " + rs.getString(1));
				jlbapellidos.setText(" "+rec.idioma[rec.eleidioma][28]+": " +  rs.getString(2));
				jlbciudad.setText(" "+rec.idioma[rec.eleidioma][48]+": " +  rs.getString(3));
				jlbregion.setText(" "+rec.idioma[rec.eleidioma][47]+": " +  rs.getString(4));
				jlbtelefono.setText(" "+rec.idioma[rec.eleidioma][69]+": " +  rs.getString(5));
				jlbemail.setText("<html> "+rec.idioma[rec.eleidioma][9]+":<br>  " +  rs.getString(6)+"  </html>");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		rs = cdb.ConsultaSQL("SELECT nombre,sector, direccion,codpostal,pais FROM PARTNER WHERE cod_part='"+Integer.toString(recursos.getCodparter())+"'");
		try {
			if(rs.next()){
				jlbpartner.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(1));
				jlbpartsector.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(2));
				jlbpartdireccion.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(3));
				jlbpartcodpostal.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(4));
				jlbpartpais.setText(" "+rec.idioma[rec.eleidioma][138]+": " + rs.getString(5));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jpnlinfousuario.add(jlbFoto);
		jpnlinfousuario.add(jlbnombre);
		jpnlinfousuario.add(jlbapellidos);
		jpnlinfousuario.add(jlbciudad);
		jpnlinfousuario.add(jlbregion);
		jpnlinfousuario.add(jlbtelefono);
		jpnlinfousuario.add(jlbemail);
		jpnlinfousuario.add(jlbLogo);
		jpnlinfousuario.add(jlbpartner);
		//jpnlinfousuario.add(jlbpartsector);
		//jpnlinfousuario.add(jlbpartdireccion);
		//jpnlinfousuario.add(jlbpartcodpostal);
		//jpnlinfousuario.add(jlbpartpais);
		
		
	}
	public void CargarPanelProyecto() {
	
		
		GridBagConstraints gbc = new GridBagConstraints();
		jpnlproyecto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		jlbpronom.setText(" "+rec.idioma[rec.eleidioma][111]+": ");
		jlbprodesc.setText(" "+rec.idioma[rec.eleidioma][41]+": ");
		jlbpropresu.setText(" "+rec.idioma[rec.eleidioma][13]+": ");
		jlbprofini.setText(" "+rec.idioma[rec.eleidioma][25]+": ");
		jlbproffin.setText(" "+rec.idioma[rec.eleidioma][26]+": ");
		jlbprocontrato.setText(" "+rec.idioma[rec.eleidioma][101]+": ");
		jcbproyecto.setPreferredSize(new Dimension(300,20));
		jpnlproyecto.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(0,0,5,0);
        jpnlproyecto.add(jcbproyecto,gbc);
       // gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		
		jpnlproyecto.add(jlbpronom,gbc);
		jpnlproyecto.add(jlbprofini,gbc);
		jpnlproyecto.add(jlbproffin,gbc);
		jpnlproyecto.add(jlbpropresu,gbc);
		jpnlproyecto.add(jlbprocontrato,gbc);
		jpnlproyecto.add(jlbprodesc,gbc);
		jpncentral.add(jpnlproyecto);
		//jpnlproyecto.setVisible(true);
		//jpnlproyecto.setOpaque(true);
		
		
		ConexionDb cdb = new ConexionDb();
		ResultSet rs;
		rs = GesStaff.allowedProyects();
		
		try {
			
			
			if(rs.next()){
				
				rs.last();
				proyectos = new Integer[rs.getRow()];
				rs.beforeFirst();
				while(rs.next()){
					proyectos[rs.getRow()-1] = rs.getInt(1);
					jcbproyecto.addItem(rs.getString(2));
					
					
				}
				
				
				
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin,num_contrato,Coordinador FROM PROYECTOS WHERE id_pro='"+proyectos[jcbproyecto.getSelectedIndex()].toString() +"'");
		
		alpro = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ConexionDb cdb = new ConexionDb();
				ResultSet rs;
				rs = cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin,num_contrato FROM PROYECTOS WHERE id_pro='"+proyectos[jcbproyecto.getSelectedIndex()].toString() +"'");
				try {
					while(rs.next()){
						jlbpronom.setText(" "+rec.idioma[rec.eleidioma][111]+": "+rs.getString(1));
						jlbprodesc.setText(" "+rec.idioma[rec.eleidioma][41]+": "+rs.getString(2));
						jlbpropresu.setText(" "+rec.idioma[rec.eleidioma][13]+": "+Integer.toString(rs.getInt(3)));
						jlbprofini.setText(" "+rec.idioma[rec.eleidioma][25]+": "+rs.getDate(4).toString());
						jlbproffin.setText(" "+rec.idioma[rec.eleidioma][26]+": "+rs.getDate(5).toString());
						jlbprocontrato.setText(" "+rec.idioma[rec.eleidioma][101]+": "+rs.getString(6));
					
					}
					ActualizarPartnersProyecto(Integer.toString(proyectos[jcbproyecto.getSelectedIndex()]));
					ActualizarWP();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		};
		jcbproyecto.addActionListener(alpro);
	}
	
	public void ActualizarPartnersProyecto(){
		jpnlpartproyecto.setLayout(new BoxLayout(jpnlpartproyecto, 1));
		jlbpartproyecto.setText(rec.idioma[rec.eleidioma][179]);
		jlbpartproyecto.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		jpnlpartproyecto.add(jlbpartproyecto);
		jpnlpartproyecto.setVisible(true);
		jpnlpartproyecto.setOpaque(true);
	//	jpnlpartproyecto.setBackground(Color.gray);
		//jpnlpartproyecto.add(jtblpartners);
		jscppartpro.setViewportView(jpnlpartproyecto);
		jpncentral.add(jscppartpro);
		tablemodelpart.addColumn("PARTNER");
		jtblpartners = new JTable(tablemodelpart){
	           
			@Override
            public Class<?> getColumnClass(int column) {
                if (convertColumnIndexToModel(column) == 0) {
                    return LstPartner.class;
                } else {
                    return LstPartner.class;
                }
            }
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
              }
        };
      
        jtblpartners.setDefaultRenderer(LstPartner.class, new TableCellRenderer() {
			
			@Override
			 public Component getTableCellRendererComponent(JTable table, Object value,
				        boolean isSelected, boolean hasFocus, int row, int column) {
				 
				    LstPartner panel = (LstPartner) value;

				    if (isSelected) {
				      panel.setBackground(table.getSelectionBackground());
				    }else{
				      panel.setBackground(table.getSelectionForeground());
				    }
				    return panel;
				  }
			
			});
        jtblpartners.setRowHeight(50);
		jpnlpartproyecto.add(jtblpartners);
		
		
		
		
		
	}
	public void ActualizarPartnersProyecto(String proyecto){
		//for(int i =0;i<jpnlpartproyecto.getComponentCount();i++){
		//	jpnlpartproyecto.remove(jpnlpartproyecto.getComponent(1));
	//	}
		
	//	jpnlpartproyecto.add(jlbpartproyecto);
		rs = cdb.ConsultaSQL("SELECT cod_part FROM PARTNER_PROYECTOS WHERE id_pro='"+proyecto+"'" );
		
	//	tablemodelpart = new DefaultTableModel();
		if(tablemodelpart.getRowCount()!=0){
		for(int i=0;i<tablemodelpart.getRowCount();i++){
			tablemodelpart.removeRow(i);
		}
		}
		try {
			if(rs.next()){
				rs.last();
				 partners = new String[rs.getRow()];
				 rs.beforeFirst();
			
			while (rs.next()){
				partners[rs.getRow()-1] = rs.getString(1);
				
				
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(partners!=null){
		  for(int i=0;i<partners.length;i++){
				Object []  obj = new Object[1];
				//jpnlpartproyecto.add(new LstPartner(rs.getString(1)));
				obj[0] = new LstPartner(partners[i]);
				tablemodelpart.addRow(obj);
			}}
		  partners=null;
		  jtblpartners.repaint();
		jpnlpartproyecto.validate();		
		
		
		
	}
	
	public void CargarWP(){
		GridBagConstraints gbc = new GridBagConstraints();
	   
		jlbwpnom.setText(" "+rec.idioma[rec.eleidioma][3]+": ");
		jlbwpdes.setText(" "+rec.idioma[rec.eleidioma][41]+": ");
		jlbwppresu.setText(" "+rec.idioma[rec.eleidioma][13]+": ");
		jlbwpfini.setText(" "+rec.idioma[rec.eleidioma][25]+": ");
		jlbwpffin.setText(" "+rec.idioma[rec.eleidioma][26]+": ");
		jcbwp.setPreferredSize(new Dimension(300,20));
		jpnlwp.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx=1.0;
		gbc.weighty=0.0;
		gbc.anchor = GridBagConstraints.CENTER;
	//	gbc.insets = new Insets(0,0,3,0);
		jpnlwp.add(jcbwp,gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx=1.0;
		gbc.weighty=0.0;
		gbc.anchor = GridBagConstraints.LINE_START;
		//gbc.insets = new Insets(0,0,3,0);
		jpnlwp.add(jlbwpnom,gbc);
		jpnlwp.add(jlbwpfini,gbc);
		jpnlwp.add(jlbwpffin,gbc);
		jpnlwp.add(jlbwppresu,gbc);
		gbc.weighty=1.0;
		jpnlwp.add(jlbwpdes,gbc);
		jpncentral.add(jpnlwp);
		//jpnlproyecto.setVisible(true);
		//jpnlproyecto.setOpaque(true);
		
		
		
		//cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin,num_contrato,Coordinador FROM PROYECTOS WHERE id_pro='"+proyectos[jcbproyecto.getSelectedIndex()].toString() +"'");
		
		alstaff = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConexionDb cdb = new ConexionDb();
				ResultSet rs;
				rs = cdb.ConsultaSQL("SELECT nombre,descripcion,presupuesto,f_ini,f_fin FROM WORKPAQUETS WHERE id_wp='"+wp[jcbwp.getSelectedIndex()].toString() +"'");
				try {
					while(rs.next()){
						
						jlbwpnom.setText(" "+rec.idioma[rec.eleidioma][3]+": "+rs.getString(1));
						jlbwpdes.setText(" "+rec.idioma[rec.eleidioma][41]+": "+rs.getString(2));
						jlbwppresu.setText(" "+rec.idioma[rec.eleidioma][13]+": "+Integer.toString(rs.getInt(3)));
						jlbwpfini.setText(" "+rec.idioma[rec.eleidioma][25]+": "+rs.getDate(4).toString());
						jlbwpffin.setText(" "+rec.idioma[rec.eleidioma][26]+": "+rs.getDate(5).toString());
					}
					ActualizarStaffWp(Integer.toString(wp[jcbwp.getSelectedIndex()]));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		};
		jcbwp.addActionListener(alstaff);
	}
	
	public void ActualizarWP(){
		jcbwp.removeAllItems();
		ConexionDb cdb = new ConexionDb();
		ResultSet rs=null;
		//rs = GesStaff.allowedWP();
		
		switch(recursos.representante){
		//Devolvemos todos los WP en los que staff tiene una tarea asignada
		case 0: rs = cdb.ConsultaSQL("SELECT * FROM WORKPAQUETS  WHERE (id_pro = (SELECT id_pro FROM PROYECTOS where nombre ='"+jcbproyecto.getSelectedItem().toString()+"')) AND (id_wp IN (SELECT id_wp FROM TAREAS WHERE id_task IN (SELECT id_task	FROM STAFF_TAREAS WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +")))");
		break;
		case 1:
			//Devolvemos todos los WP del partner
			rs = cdb.ConsultaSQL("SELECT * FROM WORKPAQUETS  WHERE (id_pro = (SELECT id_pro FROM PROYECTOS where nombre ='"+jcbproyecto.getSelectedItem().toString()+"')) AND (id_wp IN (SELECT id_wp FROM PARTNER_WORKPAQUETS WHERE cod_part = (SELECT cod_part FROM STAFF WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +")))");	
		break;
		}
		try {
			
			
			if(rs.next()){
				
				rs.last();
				wp = new Integer[rs.getRow()];
				rs.beforeFirst();
				while(rs.next()){
					wp[rs.getRow()-1] = rs.getInt(1);
					jcbwp.addItem(rs.getString(3));
					
					
				}
				
				
				
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void ActualizarStaffWp(){
		jpnlstaffwp.setLayout(new BoxLayout(jpnlstaffwp, 1));
		jlbstaffwp.setText(rec.idioma[rec.eleidioma][180]);
		jlbstaffwp.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		jpnlstaffwp.add(jlbstaffwp);
		//jpnlpartproyecto.add(jlbpartproyecto);
		jpnlstaffwp.setVisible(true);
		jpnlstaffwp.setOpaque(true);
	//	jpnlpartproyecto.setBackground(Color.gray);
		//jpnlpartproyecto.add(jtblpartners);
		jscwpsrtpro.setViewportView(jpnlstaffwp);
		jpncentral.add(jscwpsrtpro);
		tablemodelstaff.addColumn("STAFF");
		jtblstaff = new JTable(tablemodelstaff){
	           
			@Override
            public Class<?> getColumnClass(int column) {
                if (convertColumnIndexToModel(column) == 0) {
                    return LstStaff.class;
                } else {
                    return LstStaff.class;
                }
            }
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
              }
        };
      
        jtblstaff.setDefaultRenderer(LstStaff.class, new TableCellRenderer() {
			
			@Override
			 public Component getTableCellRendererComponent(JTable table, Object value,
				        boolean isSelected, boolean hasFocus, int row, int column) {
				 
				    LstStaff panel = (LstStaff) value;

				    if (isSelected) {
				      panel.setBackground(table.getSelectionBackground());
				    }else{
				      panel.setBackground(table.getSelectionForeground());
				    }
				    return panel;
				  }
			
			});
        jtblstaff.setRowHeight(50);
		jpnlstaffwp.add(jtblstaff);
		jtblstaff.revalidate();
		
		
		
	}
	
	public void ActualizarStaffWp(String workpack){
		
		
		rs = cdb.ConsultaSQL("SELECT id_staff FROM STAFF_TAREAS WHERE id_task IN (SELECT id_task FROM TAREAS WHERE id_wp='"+workpack+"')");
		
		//jtblstaff= null;
		//tablemodelstaff=new DefaultTableModel();
		for(int i=0;i<tablemodelstaff.getRowCount();i++){
			tablemodelstaff.removeRow(i);
		}
		try {
			if(rs.next()){
				rs.last();
				 staff = new String[rs.getRow()];
				 rs.beforeFirst();
			
			while (rs.next()){
				staff[rs.getRow()-1] = rs.getString(1);
				
				
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(staff!=null){
		  for(int i=0;i<staff.length;i++){
				Object []  obj = new Object[1];
				//jpnlpartproyecto.add(new LstPartner(rs.getString(1)));
				obj[0] = new LstStaff(staff[i]);
				tablemodelstaff.addRow(obj);
			}
		}
		  staff=null;
		
		
		
		
		
	}
	
	
	

}
