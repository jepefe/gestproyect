package pkGesproject.Preferencias;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.RsGesproject;

public class PnlCuentaUsuario extends JPanel{

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	ConexionDb conexion= new ConexionDb();
	JLabel jlbl[];
	JTextField jtxt[];
	JButton jbtnaplicar,jbtncancelar;
	GpComboBox gbidioma;
	GridBagConstraints gbc;
	JPanel contraseña;
	ResultSet rs;
	String[][] listaidiomas;
	ActionListener accion;
	Component aviso;
	
	public PnlCuentaUsuario(){
		//llamamos al método para crear la interfaz
		crear_interfaz();
		
		accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				conexion.executeUpdate("UPDATE STAFF SET idioma = '"+listaidiomas[gbidioma.getSelectedIndex()][0]+"' WHERE id_staff = "+recursos.getIdusuario());
				
				JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][238 ]);
			}
			
		};
		
		jbtnaplicar.addActionListener(accion);
		//jbtnaplicar.setActionCommand();
	}
	
	
	public void crear_interfaz(){
		this.setLayout(new GridBagLayout());
		jlbl = new JLabel[5];
		jtxt = new JTextField[3];

		gbc = new GridBagConstraints();
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		gbc.insets = new Insets(20,30,0,10);
		this.add(jlbl[0] = new JLabel(rec.idioma[rec.eleidioma][146]),gbc);
		
		gbc.gridx = 1; // El área de texto empieza en la columna
		this.add(gbidioma = new GpComboBox(),gbc);
		
		conexion.Conectardb();
		
		rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM LISTA_IDIOMAS");
		try {
			rs.next();
			listaidiomas = new String[rs.getInt(1)+1][2];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = conexion.ConsultaSQL("SELECT id_idioma,titulo FROM LISTA_IDIOMAS");
		int i = 0;
		try {
			while(rs.next()){
				for(int j = 1;j<3;j++){
					listaidiomas[i][j-1]=rs.getString(j);
					
				}
				gbidioma.addItem(listaidiomas[i][1]);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = conexion.ConsultaSQL("SELECT idioma FROM STAFF WHERE id_staff = "+recursos.getIdusuario());
		try {
			if(rs.next()){
				gbidioma.setSelectedIndex(rs.getInt(1)-1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		gbc.gridy = 1; // El área de texto empieza en la fila
		this.add(jbtnaplicar = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila
		//contraseña.setLayout(new GridBagLayout());
		
		
		
		//hacemos visible el panel
		this.setVisible(true);
	}
}
