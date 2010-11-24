package pkGesproject;
import java.lang.*;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

/**
 * Panel Nuevo Proyecto En este panel es donde podremos dar de alta un nuevo proyecto
 * // http://www.chuidiang.com/java/layout/GridBagLayout/GridBagLayout.php 
 */
public class PnlNuevoProyecto extends JScrollPane{
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	JPanel jpnl = new JPanel();
	ScrollPane Sp = new ScrollPane();
	JTextField[] jtxt;
	// Falta Calendario
	JFrame aviso = new JFrame();
	JButton jbtnaceptar, jbtncancelar;
	
	public PnlNuevoProyecto()  {
		  jpnl.setLayout(new GridBagLayout());
		// Array  de palabras, Fecha inico, Fecha fin, etc.
	      String[] fieldNames = {
	    		  rec.idioma[rec.eleidioma][11],rec.idioma[rec.eleidioma][12],
	    		  rec.idioma[rec.eleidioma][24],rec.idioma[rec.eleidioma][25]};
	      int[] fieldWidths = {13,5,7,7};
	      
	      jtxt = new JTextField[fieldNames.length];
	      // limite de caracteres 
	      int [] limite = {20,7,10,10}; 
	      
	       
	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.anchor = GridBagConstraints.CENTER;
	      gbc.insets = new Insets(20,0,15,0);
	      
	      gbc.anchor = GridBagConstraints.WEST;
	      gbc.insets = new Insets(5,10,5,5);

	      for(int i=0;i<fieldNames.length;++i) {
	    	
	    	  gbc.gridwidth = GridBagConstraints.RELATIVE;
	         jpnl.add(new JLabel(fieldNames[i]),gbc);
	         if (i != 2 ){  gbc.gridwidth = GridBagConstraints.REMAINDER;  } 
	         // limite de caracteres
	         jpnl.add(jtxt[i] = new JTextField( new JTextFieldLimit(limite[i]), null, fieldWidths[i]),gbc);	  
	         if (i == 2 ){ gbc.gridwidth = GridBagConstraints.REMAINDER;
	         	jpnl.add(new JButton(" "),gbc); 
	         	}
		}
	      
	 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	      jpnl.add(new JLabel(rec.idioma[rec.eleidioma][16]),gbc); 
	      gbc.gridwidth = GridBagConstraints.REMAINDER;   
	    
	      // JTextArea con Scrolls 
	      LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	      final JTextArea textarea = (new JTextArea(3,13));
	      textarea.setDocument(lpd);
	      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      jpnl.add((sp),gbc);
	      
	     // botones Aceptar Cancelar
	      gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(30,10,5,5);
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			jpnl.add(jbtnaceptar = new JButton(rec.idioma[rec.eleidioma][0]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			jpnl.add(jbtncancelar = new JButton(rec.idioma[rec.eleidioma][1]),gbc);
	     
			
		// Conectar Base de datos y pasar datos...
			
			ActionListener accion = new ActionListener(){

				@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("aceptar")){
						ConexionDb conexdb = new ConexionDb();
						ResultSet rs;
						conexdb.Conectardb();
						conexdb.executeUpdate("INSERT INTO PROYECTOS (nombre, descripcion, precupuesto, f_ini, f_fin) VALUES ('"+ jtxt[0].getText()+"','"+ textarea.getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"')");
						JOptionPane.showMessageDialog(aviso,rec.idioma[rec.eleidioma][36]);
						conexdb.cerrarConexion();
					}
				}
				
			};
			jbtnaceptar.setActionCommand("aceptar");
			jbtnaceptar.addActionListener(accion);
			jbtncancelar.setActionCommand("cancelar");
			jtxt[1].setText("Construcción");
			
		
		jpnl.setVisible(true);
		this.setViewportView(jpnl);
	}		
}

