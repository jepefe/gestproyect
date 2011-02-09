package pkGesproject;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FrmNuevapalabra extends JPanel{

	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField txtcastellano,txtingles;
	JButton btnaceptar;
	String castellano;
	
	public FrmNuevapalabra(){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
		
		
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		btnaceptar = new JButton("Aceptar");
		txtcastellano = new JTextField(10);
		txtingles = new JTextField(10);;
		
		
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					castellano = txtcastellano.getText();
					
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					rs = conexdb.ConsultaSQL("SELECT i.castellano FROM IDIOMA i WHERE i.castellano LIKE '"+txtcastellano.getText()+"'");
					
						Component aviso = null;
						try {
							if(rs.next()){
								rs = conexdb.ConsultaSQL("SELECT i.id_idi FROM IDIOMA i WHERE i.castellano ='"+castellano+"'");
								rs.next();
								JOptionPane.showMessageDialog(aviso, "La palabra ya existe en la base de datos, en la posicion: "+ rs.getInt(1));
							}else{
								conexdb.executeUpdate("INSERT INTO IDIOMA (castellano,ingles) VALUES ('"+ txtcastellano.getText()+"','"+txtingles.getText()+"')");
								rs = conexdb.ConsultaSQL("SELECT i.id_idi FROM IDIOMA i WHERE i.castellano ='"+castellano+"'");
								rs.next();
								
								txtcastellano.setText("");
								txtingles.setText("");
								JOptionPane.showMessageDialog(aviso, "Se ha introducido en la posicion: "+ rs.getInt(1));
								
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexdb.cerrarConexion();
					
			    	
			    	
					
				}
				
			}
			
		};
		btnaceptar.setActionCommand("aceptar");
		btnaceptar.addActionListener(accion);
		this.add(btnaceptar);
		this.add(txtcastellano);
		this.add(txtingles);
		
		this.setOpaque(true);
		this.setVisible(true);
		
	}
}
