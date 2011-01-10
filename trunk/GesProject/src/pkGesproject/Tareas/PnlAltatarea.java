package pkGesproject.Tareas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
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
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;

public class PnlAltatarea extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JDateChooser jdc1,jdc2;
	private JComboBox CmbWp = new JComboBox();;
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	
	public PnlAltatarea (){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][13], rec.idioma[rec.eleidioma][40],
		   rec.idioma[rec.eleidioma][25],rec.idioma[rec.eleidioma][26],rec.idioma[rec.eleidioma][41]
		};
		int[] fieldWidths = {20,9,15,10,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		
		//declaramos el campo que vamos a utilizar para añadir las fechas
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("DD/MM/YYYY");
	      jdc1.setDateFormatString("DD/MM/YYYY");
	    
	      
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
		
		for(int i=0;i<fieldNames.length;++i) {
		
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   //desahabilitar campos de texto
		   
		 		  
		   if(i==fieldNames.length-2){
			   gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);
			}
		   if(i==fieldNames.length-3){
			   gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc);
			}
		   if(i!=2 && i!=5 && i!=fieldNames.length-2 && i!=fieldNames.length-3){ 
			   panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			}
		   
		   if (i==2){
			   panel.add(CmbWp,gbc);
			   CmbWp.setPreferredSize(new Dimension(140,30));
			   
				/**
				 * Creacion del JComboBox y añadir los items.
				 *Se conecta a la BD para realizar la consulta
				 **/
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS");
				try {
				while(rs.next()){
					
					CmbWp.addItem(rs.getString(2));		
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
		   }
		 
		}
		  //cuadro con scroll para las descripciones
	     
		   LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		      final JTextArea textarea = (new JTextArea(3,13));
		      textarea.setDocument(lpd);
		      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		     panel.add((sp),gbc);
		/**
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
					// cambiar fecha a sql
					  java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
					  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					
			if (sqlDate1.getTime()< sqlDate2.getTime()){
					conexdb.executeUpdate("INSERT INTO TAREAS (nombre, descripcion, presupuesto,f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+ textarea.getText()+"','"+jtxt[1].getText()+"','"+sqlDate1+"','"+sqlDate2+"')");
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
					conexdb.cerrarConexion();
				}
				
				jdc1.setDate(null);
				jdc2.setDate(null);
				textarea.setText(null);
			}else{
				JOptionPane.showMessageDialog( null, "La Fecha de Fin debe ser mayor que la Fecha de Inicio"); 
				// Marcar campo FECHA con error en ROJO 
				jdc2.setBackground(Color.red);
		
		}
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	
				jdc1.setDate(null);
				jdc2.setDate(null);
				textarea.setText(null);
				
				// Borrar cuando termine de aÃ±adir
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	
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
