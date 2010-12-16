package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmNuevapalabra extends JPanel{

	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField txtinsertar;
	JButton btnaceptar;
	
	public FrmNuevapalabra(){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		  
		
		int[] fieldWidths = {20,10,30,6,8};
		
		
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		txtinsertar = new JTextField();
		btnaceptar = new JButton("Aceptar");
		
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*if(e.getActionCommand().equals("aceptar")){
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					conexdb.executeUpdate("INSERT INTO PARTNER (nombre, sector, direccion, codpostal, telefono) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"')");
					for(int i=0;i<fieldNames.length;i++){
						jtxt[i].setText("");
					}
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][23]);
					conexdb.cerrarConexion();
				}*/
				
			}
			
		};
		btnaceptar.setActionCommand("aceptar");
		btnaceptar.addActionListener(accion);
		this.add(btnaceptar);
		this.add(txtinsertar);
		
		
		this.setOpaque(false);
		this.setVisible(true);
		
	}
}
