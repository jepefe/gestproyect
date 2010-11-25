package pkGesproject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PnlAltawp extends JScrollPane{
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	private JComboBox cbtipo;
	
	public PnlAltawp (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][2],rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][6],
		   rec.idioma[rec.eleidioma][7],rec.idioma[rec.eleidioma][4],rec.idioma[rec.eleidioma][8],
		   rec.idioma[rec.eleidioma][9],rec.idioma[rec.eleidioma][13],rec.idioma[rec.eleidioma][10],
		   rec.idioma[rec.eleidioma][11],rec.idioma[rec.eleidioma][12]
		};
		int[] fieldWidths = {20,20,30,20,15,10,10,10,10,20,10};
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
		 * nombres de campos hayamos metido en fieldNames y a su vez un ComboBox.
		 */
		for(int i=0;i<fieldNames.length;++i) {
			/*
			 * Se inserta el ComboBox en la 4� posicion mediante el if.
			 */
			if (i==3){
				/*
				 * Creacion del JComboBox y a�adir los items.
				 */
				cbtipo = new JComboBox();
				cbtipo.addItem("tipo1");
				cbtipo.addItem("tipo2");
				cbtipo.addItem("tipo3");
				cbtipo.addItem("tipo4");
				cbtipo.addItem("tipo5");
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel("Tipo:"),gbc);
				panel.add(cbtipo);
				
				/*
				 * Accion a realizar cuando el JComboBox cambia de item seleccionado.
				 */
				cbtipo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				
					}
				);
			}else{
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);	
			}
			
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
		jtxt[1].setText("Construcci�n");
		
		panel.setOpaque(true);
		this.setViewportView(panel);
		
	}


	private int CInt(int length) {
		// TODO Auto-generated method stub
		return 0;
	}
}
