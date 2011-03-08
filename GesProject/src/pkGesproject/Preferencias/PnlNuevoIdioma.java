package pkGesproject.Preferencias;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import pkGesproject.ConexionDb;
import pkGesproject.ConexionDbUnica;
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
	JPanel pnlmensaje,pnllista, pnlcabecera,pnlbotones;
	JButton jbtnadd,jbtnlimpiar;
	JScrollPane lista, cabecera;
	GridBagConstraints gbc = new GridBagConstraints();
	Border blackline;
	boolean vacio = false;
	ActionListener accion;
	String[] nuevo;
	public PnlNuevoIdioma(){
		crear_interfaz();
		
		accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("anadir")){
					introducir_idioma();
					//traducir();
				}
				
				if(e.getActionCommand().equals("limpiar")){
					
				}
			}
			
		};
	
		
		jbtnadd.addActionListener(accion);
		jbtnadd.setActionCommand("anadir");
		jbtnlimpiar.addActionListener(accion);
		jbtnlimpiar.setActionCommand("limpiar");
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
		lista = new JScrollPane(pnllista);
		lista.setBorder(blackline);
		title = BorderFactory.createTitledBorder("Idioma");
		lista.setBorder(title);
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(0,10,3,0);
		//gbc.anchor = GridBagConstraints.BOTH;
		
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
		
		pnlbotones = new JPanel();
		pnlbotones.setLayout(new FlowLayout());
		pnlbotones.add(jbtnadd = new JButton("Añadir"));
		pnlbotones.add(jbtnlimpiar = new JButton(rec.idioma[rec.eleidioma][74]));
		
		
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(0,10,3,0);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(pnlcabecera,BorderLayout.NORTH);
		this.add(lista,BorderLayout.CENTER);
		this.add(pnlbotones,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void introducir_idioma(){
		
		//comprobar campos vacios
		for(int i =0; i< reg;i++){
			if(jtxt[i].getText().equals("")){
				System.out.println("VACIO: "+i);
				vacio = true;
			}
		}
		
		if(vacio == true){
			System.out.print("Los campos deben estar llenos");
		}else{
			conexion.executeUpdate("ALTER TABLE IDIOMA ADD "+txtidioma.getText()+" VARCHAR(200)");
			
			for(int i = 1; i<=reg;i++){
				conexion.executeUpdate("UPDATE IDIOMA SET "+txtidioma.getText()+" = '"+jtxt[i-1].getText()+"' WHERE id_idi = '"+i+"'");
			}
			
			conexion.executeUpdate("INSERT INTO LISTA_IDIOMAS (columna,titulo) VALUES ('"+txtidioma.getText()+"','"+txtidioma.getText()+"')");
		}
	}
	
	public void traducir(){
		String translatedText = null;
		rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM IDIOMA");
		try {
			rs.next();
			reg = rs.getInt(1);
			nuevo = new String[reg];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs = conexion.ConsultaSQL("SELECT ingles FROM IDIOMA");
		for(int i = 1;i<reg+1;i++){
			
			try {
				rs.next();
				translatedText = rs.getString(1);
				try {
					Translate.setHttpReferrer("http://code.google.com/p/google-api-translate-java/");
					nuevo[i-1] = Translate.execute(translatedText,Language.ENGLISH, Language.PORTUGUESE);
					nuevo[i-1] = nuevo[i-1].replace("'", "\'");
					System.out.println(nuevo[i-1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		for(int i = 1; i<=reg;i++){
			conexion.executeUpdate("UPDATE IDIOMA SET Esperanto = '"+nuevo[i-1].replace("'", "\\'")+"' WHERE id_idi = "+i);
			System.out.println(nuevo[i-1]);
		}
		
	}
}
