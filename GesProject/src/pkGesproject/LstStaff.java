package pkGesproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class LstStaff extends JPanel{
	ConexionDb cdb = new ConexionDb();
	ConexionFTP cftp = new ConexionFTP();
	JLabel jlbllogo = new JLabel();
	JLabel jlblnombre = new JLabel();
	JLabel jlblcontacto =  new JLabel();
	ResultSet rs;
	String codigo;
	String nombre;
	String contacto;
	GridBagConstraints constraints = new GridBagConstraints();
	RsGesproject recursos = RsGesproject.instancia;
	
	
	public LstStaff(){
		
	
	}
	public LstStaff(String cod_part){
		jlblnombre.setFont(recursos.fuente);
		jlblcontacto.setForeground(Color.gray);
		jlbllogo.setIcon(recursos.icono[1]);
		this.codigo=cod_part;
		CargaDatos();
		CargarFoto();
		constraints.insets = new Insets(0, 2, 0, 0);
		this.setLayout(new GridBagLayout());
		
		constraints.anchor=GridBagConstraints.LINE_START;
		constraints.fill = GridBagConstraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2; 
		constraints.weighty = 1.0; 
		constraints.weightx = 1.0; 
		this.add(jlbllogo,constraints);
		//constraints.weighty = 0.0;
		constraints.anchor=GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 1; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		//constraints.weighty = 1.0; 
		this.add(jlblnombre,constraints);
		//constraints.weighty = 0.0;
		
		constraints.gridx = 1; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		//constraints.weighty = 1.0; 
		this.add(jlblcontacto,constraints);
		//constraints.weighty = 0.0;
		
		this.setOpaque(true);
		this.setVisible(true);
		
		
	}
	
	
	
	public void CargaDatos(){
		rs = cdb.ConsultaSQL("SELECT nombre,apellidos,telefono,email FROM STAFF WHERE id_staff='"+codigo+"'");
		try {
			if(rs.next()){
					nombre = rs.getString(1) +" "+ rs.getString(2);;
					jlblnombre.setText(nombre);
					contacto = rs.getString(3) +" - "+ rs.getString(4);
					jlblcontacto.setText(contacto);
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void CargarFoto(){
	Thread hilologo = new Thread(new Runnable() {
			
			@Override
			public void run()  {
				ConexionFTP cftp = new ConexionFTP();
				jlbllogo.setPreferredSize(new Dimension(50,50));
				
				jlbllogo.setIcon(new ImageIcon(cftp.ObtenerImagen("fto"+codigo).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				
				System.out.println("AFOTO OBTENIDO");
				
				jlbllogo.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				
			
				recursos.getRfrmppal().repaint();
			}
		});
		hilologo.start();
		
		
	}
	
	

}
