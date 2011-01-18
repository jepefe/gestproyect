/**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Freyder Espinosa V
 */
package pkGesproject.Tareas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;




//---------------
import com.toedter.calendar.JDateChooser; 


public class PnlAltatarea extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JDateChooser jdc1,jdc2;
	GpComboBox CmbWp = new GpComboBox();;
	ConexionDb conexion = new ConexionDb();
	JTextArea textarea = new JTextArea();
	JTextArea textarea2 = new JTextArea();
	ResultSet rs;
	String nomwp;
	int indexwp;
	char caracter;
	Border empty = new EmptyBorder(0,0,0,0);
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	protected Object cbpais;
	
	public PnlAltatarea (){
		this.setBorder(empty);
		
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][13], rec.idioma[rec.eleidioma][40],
		   rec.idioma[rec.eleidioma][25],rec.idioma[rec.eleidioma][26],rec.idioma[rec.eleidioma][41], rec.idioma[rec.eleidioma][64]
		   };
		int[] fieldWidths = {20,9,15,10,6,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		
		//declaramos el campo que vamos a utilizar para a�adir las fechas
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("DD/MM/YYYY");
	      jdc1.setDateFormatString("DD/MM/YYYY");
	    
	      
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
	      
	      
	   //cuadro con scroll para las descripciones
			
	  		LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	   		textarea = (new JTextArea(3,18));
	   		textarea.setLineWrap(true);
	   		textarea.setDocument(lpd);
	   		JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	   		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	   		
	    
		
	  //cuadro con scroll para las observaciones

	    	LimiteDocumento lpd2 = new LimiteDocumento(200); // Limite JTextArea
	    	textarea2 = (new JTextArea(3,18));
	    	textarea2.setDocument(lpd2);
	    	textarea2.setLineWrap(true);
	    	JScrollPane sp2 = new JScrollPane(textarea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    	
	     
		
		for(int i=0;i<fieldNames.length;++i) {
		
			//System.out.println("Fieldnames = " + fieldNames.length + " / i = " + i);
			
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   //desahabilitar campos de texto
		   
		   switch(i){
		   
		   case (0):
		   case (1):
			   	panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		   		break;
		   case (2):
			   panel.add(CmbWp,gbc);
			   CmbWp.setPreferredSize(new Dimension(140,30));
			   
				/**
				 * Creacion del JComboBox y a�adir los items.
				 *Se conecta a la BD para realizar la consulta
				 **/
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_wp FROM WORKPAQUETS");
				try {
				while(rs.next()){
					CmbWp.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
			   	break;
		   case (3):
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);}
		   		break;
		   case (4):
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc);}
		   		break;
		   case (5):
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp),gbc);}
			   	break;
		   case (6):
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp2),gbc);}
		   		break;
		   		
		   }
		   //Jformat
		   if(i==1 ){
				jtxt[i].addKeyListener(new KeyAdapter(){
				   public void keyTyped(KeyEvent e){
				      caracter = e.getKeyChar();
				      if(((caracter < '0') ||(caracter > '9')) &&
				         (caracter != KeyEvent.VK_BACK_SPACE) &&
				         (caracter != '+') && (caracter != '(') && (caracter != ')')) {
				         e.consume();  
				      }
				   }
				});
				}
		   
		   if(i==0 ){
			   
			   try
			   {
			       MaskFormatter mascara = new MaskFormatter("##.##");
			       JFormattedTextField textField = new JFormattedTextField(mascara);
			       textField.setValue(new Float("12.34"));
			   }
			   catch (Exception e)
			   {
			       e.printStackTrace();
			   }
				jtxt[i].addKeyListener(new KeyAdapter(){
				   public void keyTyped(KeyEvent e){
				      caracter = e.getKeyChar();
				      if(((caracter < 'a') ||(caracter > 'z')) &&
				    		  ((caracter < 'A') ||(caracter > 'Z')) &&
				         (caracter != KeyEvent.VK_BACK_SPACE) &&
				         (caracter != '+') && (caracter != '(') && (caracter != ')')) {
				         e.consume();  
				      }
				   }
				});
				}
		}//fin for
	  /*  
		**
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
					conexdb.Conectardb();
					//nomwp = cbtipo.getSelectedItem().toString();
					
					rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS W WHERE W.nombre like'"+ CmbWp.getSelectedItem().toString()+"'" );
					String id_wp = null;
					try {
						rs.next();
						id_wp = rs.getString(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
									
					// cambiar fecha a sql
					  java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
					  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					
			//if (sqlDate1.getTime()< sqlDate2.getTime()){
					conexdb.executeUpdate("INSERT INTO TAREAS (nombre, id_wp, descripcion, presupuesto,f_ini, f_fin, observaciones) VALUES ('"+ jtxt[0].getText()+"','"+id_wp+"','"+textarea.getText()+"','"+jtxt[1].getText()+"','"+sqlDate1+"','"+sqlDate2+"','"+textarea2.getText()+"')");
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
					conexdb.cerrarConexion();
				}
				
				jdc1.setDate(null);
				jdc2.setDate(null);
				textarea.setText(null);
				textarea2.setText(null);
			//}else{
				//JOptionPane.showMessageDialog( null, "La Fecha de Fin debe ser mayor que la Fecha de Inicio"); 
				// Marcar campo FECHA con error en ROJO 
				//jdc2.setBackground(Color.red);
		
		//}
			
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	
				jdc1.setDate(null);
				jdc2.setDate(null);
				textarea.setText(null);
				textarea2.setText(null);
				
				// Borrar cuando termine de añadir
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	
			}
		}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar"); 

		
		panel.setVisible(true);
		this.setViewportView(panel);
		
	}
}
