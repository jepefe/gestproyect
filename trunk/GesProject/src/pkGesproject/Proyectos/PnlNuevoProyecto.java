package pkGesproject.Proyectos;
import java.lang.*;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;

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
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser;
/**
 * Panel Nuevo Proyecto : En este panel es donde podremos dar de alta un nuevo proyecto
 */
public class PnlNuevoProyecto extends JScrollPane{
	
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JPanel jpnl = new JPanel();
	ScrollPane Sp = new ScrollPane();
	JTextArea textarea;
	JTextField[] jtxt;
	JDateChooser jdc1,jdc2;
	Date dateini, datefin ;
	JFrame aviso = new JFrame();
	JButton jbtnaceptar, jbtncancelar,Jbtnpresup;
    PnlModificarProyecto mod ;

	
	public PnlNuevoProyecto()  {
		// formato fecha
		
		
		  jpnl.setLayout(new GridBagLayout());
		// Array  de palabras, Fecha inico, Fecha fin, etc.
	      final String[] fieldNames = {
	    		  rec.idioma[rec.eleidioma][12],
	    		  rec.idioma[rec.eleidioma][25],rec.idioma[rec.eleidioma][26]};
	      int[] fieldWidths = {13,7,7};
	      
	      jtxt = new JTextField[fieldNames.length];
	      // limite de caracteres 
	      int [] limite = {20,7,10,10}; 
	      // Campos Calendario y formato
	      jdc1 = new JDateChooser();
	      jdc2 = new JDateChooser();
	      jdc2.setDateFormatString("dd/MM/yyyy");
	      jdc1.setDateFormatString("dd/MM/yyyy");
	      
	      // Situacion en el panel 
	      GridBagConstraints gbc = new GridBagConstraints();
	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);
	      for(int i=0;i<fieldNames.length;++i) {
	    	  gbc.gridwidth = GridBagConstraints.RELATIVE;
	         jpnl.add(new JLabel(fieldNames[i]),gbc);
	         if (i != 1 || i != 2 ){gbc.gridwidth = GridBagConstraints.REMAINDER;  } 
	         if(i == 1 ||  i == 2 ){}else{ jpnl.add(jtxt[i] = new JTextField( new JTextFieldLimit(limite[i]), null, fieldWidths[i]),gbc);	}  
	         if (i == 1 ){ gbc.gridwidth = GridBagConstraints.REMAINDER; jpnl.add(jdc1,gbc); }
	         if (i == 2){ gbc.gridwidth = GridBagConstraints.REMAINDER; jpnl.add(jdc2,gbc); }
		}
	  
    

		 
	      // Label 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JLabel(rec.idioma[rec.eleidioma][17]),gbc); 
	      gbc.gridwidth = GridBagConstraints.REMAINDER;   
	    
	      // JTextArea con Scrolls 
	      LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	      textarea = (new JTextArea(3,13));
	      textarea.setDocument(lpd);
	      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      jpnl.add((sp),gbc);
	      
	   
			
	       /**
	        * Boton Modificar, Está en No visible, solo se pondra visible si lo llamamos desde 
	        * el panel modificar Proyecto.
	        */
	      	jpnl.add(Jbtnpresup = new JButton(rec.idioma[rec.eleidioma][13]),gbc);
	      	Jbtnpresup.setVisible(false);
	     // botones Aceptar Cancelar
	      	gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			jpnl.add(jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jpnl.add(jbtncancelar = new JButton(rec.idioma[rec.eleidioma][2]),gbc);
			jbtncancelar.setVisible(false);
			
		// Conectar Base de datos y pasar datos...
			
			ActionListener accion = new ActionListener(){

				@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("aceptar")){
						// Poner el color de  JDC2 (Fecha2) correcto
							jdc2.setBackground(null);
							
							ConexionDb conexdb = new ConexionDb();
							ResultSet rs;
							conexdb.Conectardb();
							// cambiar fecha a sql
						    java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						    java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
						    if (sqlDate1.getTime()< sqlDate2.getTime()){
							conexdb.executeUpdate("INSERT INTO PROYECTOS (nombre, descripcion, f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+ textarea.getText()+"','"+sqlDate1+"','"+sqlDate2+"')");
							JOptionPane.showMessageDialog(aviso,rec.idioma[rec.eleidioma][60]);
							conexdb.cerrarConexion();
							
							// Borrar cuando termine de aÃ±adir		
							jtxt[0].setText("");
							jdc1.setDate(null);
							jdc2.setDate(null);
							textarea.setText(null);
							
						}else{					
							JOptionPane.showMessageDialog( null, rec.idioma[rec.eleidioma][72]); 
							// Marcar campo FECHA con error en ROJO 
							jdc2.setBackground(Color.red);								
							}						
					}// Borrar cuando damos al boton cancelar
					if( e.getActionCommand().equals("cancelar")){
					
						mod.modificar.dispose();
					}
					
				}
				
			};
			jbtnaceptar.setActionCommand("aceptar");
			jbtnaceptar.addActionListener(accion);
			jbtncancelar.setActionCommand("cancelar");
			jbtncancelar.addActionListener(accion);
	
	

			
		jpnl.setVisible(true);
		this.setViewportView(jpnl);
	}		
}

