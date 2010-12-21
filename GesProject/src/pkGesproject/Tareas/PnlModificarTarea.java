package pkGesproject.Tareas;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;

public class PnlModificarTarea extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JDateChooser jdc1,jdc2;
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	
	public PnlModificarTarea (){
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
		   
		   if(i==1){
			   
			   
		   }
		  
		   if(i==fieldNames.length-2){
			   gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);
			}
		   if(i==fieldNames.length-3){
			   gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc);
			}
		   if(i!=5 && i!=fieldNames.length-2 && i!=fieldNames.length-3){ 
			   panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			}
		   //cuadro con scroll para las descripciones
		   if(i==5){
			  // LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		      final JTextArea textarea = (new JTextArea(3,13));
		      //textarea.setDocument(lpd);
		      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		     panel.add((sp),gbc);
		      
		   }    
		}
			      
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

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					// cambiar fecha a sql
					  java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
					  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					conexdb.executeUpdate("INSERT INTO TAREAS (nombre, presupuesto, workpackage,f_ini, f_fin,descripcion) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+sqlDate1+"','"+sqlDate2+"')");
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
