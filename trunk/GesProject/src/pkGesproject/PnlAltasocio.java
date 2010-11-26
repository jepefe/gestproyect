package pkGesproject;

import java.awt.Container;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PnlAltasocio extends JScrollPane{


	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	
	public PnlAltasocio (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][2],rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][6],
		   rec.idioma[rec.eleidioma][7],rec.idioma[rec.eleidioma][4]
		};
		int[] fieldWidths = {20,10,30,6,8};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		/*
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
		for(int i=0;i<fieldNames.length;++i) {
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		}
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][0]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					conexdb.executeUpdate("INSERT INTO PARTNER (nombre, sector, direccion, codpostal, telefono) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"')");
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][23]);
					conexdb.cerrarConexion();
				}
			}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		
		
		panel.setOpaque(true);
		this.setViewportView(panel);
		
	}
}