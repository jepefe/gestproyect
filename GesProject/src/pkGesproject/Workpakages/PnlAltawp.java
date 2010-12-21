package pkGesproject.Workpakages;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ComboBoxModel;
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
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;

public class PnlAltawp extends JScrollPane{
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	String[] proyectocb; 
	private JComboBox cbtipo = new JComboBox();;
	private JComboBox cbpart = new JComboBox();;
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	JDateChooser jdc1,jdc2;
	
	public PnlAltawp (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][4],rec.idioma[rec.eleidioma][41],
		   rec.idioma[rec.eleidioma][13],rec.idioma[rec.eleidioma][25],rec.idioma[rec.eleidioma][26]
		};
		int[] fieldWidths = {10,35,10,10,10};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
	      // Campos Calendario y formato
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("DD/MM/YYYY");
	      jdc1.setDateFormatString("DD/MM/YYYY");
		
		/*
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames y a su vez un ComboBox.
		 */
		for(int i=0;i<fieldNames.length;++i) {

			/*
			 * Se inserta el ComboBox en la 4� posicion mediante el if para los proyectos.
			 */
			if (i==3){
				/*
				 * Creacion del JComboBox y a�adir los items.
				 */
				/*
				 * Se conecta a la BD para realizar la consulta
				 */
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT * FROM PROYECTOS");
				try {
				while(rs.next()){
					
					cbtipo.addItem(rs.getString(2));
							
						
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
				
				
				
			
				
								/*
				 * Cargamos en el panel el ComboBox.
				 */
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][55]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				cbtipo.setPreferredSize(new Dimension(233,30));
				panel.add(cbtipo,gbc);
				cbtipo.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			
				});
				
				
				/*
				 * Accion a realizar cuando el JComboBox cambia de item seleccionado.
				 */
				
				
				/*
				 * Creacion del JComboBox y a�adir los items.
				 */
				/*
				 * Se conecta a la BD para realizar la consulta
				 */
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT * FROM PARTNER");
				try {
				while(rs.next()){
					
					cbpart.addItem(rs.getString(2));
							
						
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
				
				
				
			
				
								/*
				 * Cargamos en el panel el ComboBox.
				 */
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][57]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				cbpart.setPreferredSize(new Dimension(233,30));
				panel.add(cbpart,gbc);
				cbpart.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			
				});
				
				
				/*
				 * Accion a realizar cuando el JComboBox cambia de item seleccionado.
				 */
				
				
			}	
			
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
				if ((i<(fieldNames.length - 2)) && (i!=1) ){
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
				}
				if (i==(fieldNames.length - 2)){
			         gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);  
				}
				if (i==(fieldNames.length - 1)){ 
			         gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc); 
				}
				//LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
			      if(i==1){
			    	  final JTextArea textarea = (new JTextArea(3,13));
			      //textarea.setDocument(lpd);
			    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			    	gbc.gridwidth = GridBagConstraints.REMAINDER;
			    	panel.add((sp),gbc);
			      }
		}
		
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][2]),gbc);
		
		ActionListener accion = new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					//conexdb.executeUpdate("INSERT INTO WORKPACKAGES (sector, descripcion, presupuesto, id_proyecto, id_partner, f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+sqlDate1+"','"+sqlDate2+"')");
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][24]);
					conexdb.cerrarConexion();
				}
			}};
			
		
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
	
		
		panel.setOpaque(true);
		this.setViewportView(panel);
		
	}




	}

	
