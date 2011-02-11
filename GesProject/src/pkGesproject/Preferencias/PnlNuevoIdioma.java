package pkGesproject.Preferencias;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;

public class PnlNuevoIdioma extends JPanel{

	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	ConexionDb conexion= new ConexionDb();
	int reg;
	ResultSet rs;
	JLabel jlbl[],lblidioma;
	JTextField jtxt[],txtidioma;
	JPanel pnllista, pnlcabecera;
	JScrollPane lista, cabecera;
	GridBagConstraints gbc = new GridBagConstraints();
	Border blackline;
	boolean vacio = false;
	
	public PnlNuevoIdioma(){
		crear_interfaz();
		
	}
	
	public void crear_interfaz(){
		this.setLayout(new BorderLayout());
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		pnllista = new JPanel();
		pnlcabecera = new JPanel();
		pnlcabecera.setBorder(blackline);
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Idioma");
		pnlcabecera.setBorder(title);
		pnlcabecera.setLayout(new GridBagLayout());
		pnllista.setLayout(new GridBagLayout());
		
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(0,10,3,0);
		gbc.anchor = GridBagConstraints.EAST;
		
		pnlcabecera.add(lblidioma = new JLabel("Nombre idioma:"),gbc);
		pnlcabecera.add(txtidioma = new JTextField(10),gbc);
		
		
		conexion.Conectardb();
		rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM IDIOMA");
		try {
			rs.next();
			reg = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jlbl = new JLabel[reg];
		jtxt = new JTextField[reg];
		
		

		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(0,10,3,0);
		gbc.anchor = GridBagConstraints.EAST;
		
		rs = conexion.ConsultaSQL("SELECT ingles FROM IDIOMA");
		
		for(int i = 0;i<reg;i++){
			try {
				rs.next();
				pnllista.add(jlbl[i] = new JLabel(rs.getString(1)),gbc);
				gbc.anchor = GridBagConstraints.CENTER;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				pnllista.add(jtxt[i] = new JTextField(20),gbc);
				gbc.anchor = GridBagConstraints.EAST;
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		this.add(pnlcabecera,BorderLayout.NORTH);
		this.add(lista = new JScrollPane(pnllista),BorderLayout.CENTER);
		lista.setBorder(blackline);
		title = BorderFactory.createTitledBorder("Idioma");
		lista.setBorder(title);
		this.setVisible(true);
	}
	
	public void introducir_idioma(){
		
		//comprobar campos vacios
		for(int i =0; i< reg;i++){
			if(jtxt[i].getText().equals("")){
				vacio = true;
			}
		}
		
		if(vacio == true){
			System.out.print("Los campos deben estar llenos");
		}else{
			conexion.executeUpdate("ALTER TABLE IDIOMA ADD "+txtidioma+" VARCHAR(200)");
			
			for(int i = 1; i<=reg;i++){
				conexion.executeUpdate("UPDATE IDIOMA SET "+txtidioma+" = '"+jtxt[i-1]+"' WHERE id_idi = '"+i+"'");
			}
			
			conexion.executeUpdate("INSERT INTO LISTA_IDIOMAS (columna,titulo) VALUES ('"+txtidioma+"','"+txtidioma+"')");
		}
	}
}
