package pkGesproject.Proyectos;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;

public class PnlPresupuesto extends JScrollPane {

		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		GesIdioma rec = GesIdioma.obtener_instancia();
		JPanel jpnl = new JPanel();
		ScrollPane Sp = new ScrollPane();
		JTextField[] jtxt;
		JFrame aviso = new JFrame();
		JComboBox JCpresup = new JComboBox ();
		JButton jbtnaceptar, jbtncancelar;
		ConexionDb conexion = new ConexionDb();
		ResultSet rs;
		public PnlPresupuesto()  {
			
		    
		      // Situacion en el panel 
		      GridBagConstraints gbc = new GridBagConstraints();
		      gbc.gridwidth = GridBagConstraints.REMAINDER;
		      gbc.anchor = GridBagConstraints.CENTER;
		      gbc.insets = new Insets(20,0,15,0);
		     
		   //Conexion 
		  	conexion.Conectardb();
			rs = conexion.ConsultaSQL("SELECT Nombre FROM PROYECTOS");
		    	try {
					while(rs.next()){	
							JCpresup.addItem(rs.getString(1));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	conexion.cerrarConexion();
	    	
	    	  jpnl.add(JCpresup);
		      gbc.anchor = GridBagConstraints.WEST;
		      gbc.insets = new Insets(5,10,5,5);
		     		     
		     // botones Aceptar Cancelar
		      	gbc.anchor = GridBagConstraints.EAST;
				gbc.insets = new Insets(30,10,5,5);
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				jpnl.add(jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				jpnl.add(jbtncancelar = new JButton(rec.idioma[rec.eleidioma][2]),gbc);
			
			
				//Acciones de Boton Aceptar Cancelar
				ActionListener accion = new ActionListener(){

					@Override
				public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getActionCommand().equals("aceptar")){
					
								ConexionDb conexdb = new ConexionDb();
								ResultSet rs;
								conexdb.Conectardb();
							//	conexdb.executeUpdate("INSERT INTO PROYECTOS ;
								JOptionPane.showMessageDialog(aviso,rec.idioma[rec.eleidioma][36]);
								conexdb.cerrarConexion();

								
						}
						if(e.getActionCommand().equals("cancelar")){
							
						}
					}
				};
				jbtnaceptar.setActionCommand("aceptar");
				jbtnaceptar.addActionListener(accion);
				jbtncancelar.setActionCommand("cancelar");
				jbtncancelar.addActionListener(accion);
	
				
			
				jpnl.setVisible(true);
				this.setViewportView(jpnl);
		}
		
}
