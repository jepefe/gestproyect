/* ESTA CLASE LA PODRIAMOS TENER COMO CLASE PARA EXPERIMENTOS
 * 
 * 
 * AQUI PODEMOS IMPLEMENTAR COSAS PARA NO IR ENSUCIANDO EL CODIGO
 * 
 */
package pkgesproyect;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import javax.swing.*;
//Creamos un panel con varios objetos para demostracion
public class PanelPreferencias extends JPanel {
	JButton Boton1 = new JButton("Hola");
	JProgressBar jpb = new JProgressBar();
	JProgressBar jpb2 = new JProgressBar();	
	JCheckBox jcb1 = new JCheckBox("Casilla 1");
	JCheckBox jcb2 = new JCheckBox("Casilla 2");
	JCheckBox jcb3 = new JCheckBox("Casilla 3");
	JRadioButton jrb = new JRadioButton("Hola!!");
	JTextField	jtf = new JTextField("Buscar...");
	frmppal principal;
	
	public PanelPreferencias(frmppal principal){//Estamos tomando como referencia al frame principal
	//Asi podemos comunicarnos con la clase de la ventana principal
		this.principal = principal;
	//	principal.setSize(1024, 768);
		
		
		this.setOpaque(true);
		jtf.putClientProperty("JTextField.variant", "search");
		jtf.putClientProperty("JTextField.Search.PlaceholderText", Boolean.TRUE);
	//	this.setLayout(new GridLayout());
		this.add(jpb);
		jpb.setMinimum(0);
		jpb.setMaximum(100);
		jpb.getModel().setValue(33);
		jpb.setStringPainted(true);
		//jpb2.setIndeterminate(true);
		
		this.add(jpb2);
		this.add(Boton1);
		this.add(jcb1);
		this.add(jcb2);
		this.add(jcb3);
		this.add(jrb);
		this.add(jtf);
		this.setBackground(new Color(0xED,0xED,0xED));
	
		// Prueba con la base de datos 
		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("gesproyect");
			dataSource.setPassword("proyecto2010");
			dataSource.setDatabaseName("gesproyect");
			dataSource.setServerName("93.189.94.177");
			Connection conexion = dataSource.getConnection ();
			Statement s = conexion.createStatement(); 
			ResultSet rs = s.executeQuery ("select * from partners");
			conexion.commit();
			conexion.close();
			while (rs.next()) 
			{ 
			    System.out.println (rs.getInt (1) + " " + rs.getInt (2)+ " " + rs.getInt(3) + " " + rs.getDate(4) + " " + rs.getInt (5)+ " " + rs.getInt(6) ); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	public void setEnabled(boolean b){
		b = !jpb2.isEnabled();
		System.out.println(b);
		
	}
	



}
