package pkGesproject.Becas;


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

public class PnlAltaBecas extends JScrollPane{
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
	ResultSet idid;
	ResultSet idwp;
	String nomid;
	
	
	public PnlAltaBecas (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][28],
		   rec.idioma[rec.eleidioma][66],rec.idioma[rec.eleidioma][66],rec.idioma[rec.eleidioma][9],rec.idioma[rec.eleidioma][61],rec.idioma[rec.eleidioma][62],rec.idioma[rec.eleidioma][7]
		};
		int[] fieldWidths = {10,25,12,12,15,25,25,25};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta becas"),gbc);
		
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
			 * Se inserta el ComboBox en la 4� posicion mediante el if para los IDIOMASs.
			 */
			if (i==3){
				/*
				 * Creacion del JComboBox y a�adir los items.
				 */
				/*
				 * Se conecta a la BD para realizar la consulta
				 */
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT * FROM IDIOMAS");
				try {
				while(rs.next()){
					
					cbtipo.addItem(rs.getString(2));
							
						
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
				
				
				
			
				
								/*
				 * Cargamos en el panel el ComboBox para IDIOMAS.
				 */
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][65]),gbc);
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
				rs = conexion.ConsultaSQL("SELECT * FROM IDIOMAS");
				try {
				while(rs.next()){
					
					cbpart.addItem(rs.getString(1));
							
						
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
				
				
			}	
			/*
			 * Se colocan el calendario para la selecion de fecha de solicitud
			 */
			
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
				if ((i<(fieldNames.length - 2)) && (i!=1) ){
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
				}
				if (i==(fieldNames.length - 2)){
			         gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);  
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

			/*
			 * se genera en el action listener la introduccion de datos en tablas al aceptar(non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					/*
					 * Se recogen los datos para comparar.
					 */
					nomid = cbtipo.getSelectedItem().toString();
					idid = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre='" + nomid + "'");
					
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
					java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					conexdb.executeUpdate("INSERT INTO BECAS (nombre, descripcion, presupuesto, id_pro, f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+sqlDate1+"','"+sqlDate2+"')");
					idwp = conexion.ConsultaSQL ("SELECT id_wp FROM WORKPAQUETS WHERE nombre = '"+ jtxt[0].getText()+"' ");
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][24]);
					conexdb.cerrarConexion();
				}
				
				if(e.getActionCommand().equals("cancelar")){
				System.out.println(cbtipo.getSelectedItem());	
				}
			}};
			
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		jbtncancelar.addActionListener(accion);
	
		
		panel.setOpaque(true);
		this.setViewportView(panel);
		
	}




	}