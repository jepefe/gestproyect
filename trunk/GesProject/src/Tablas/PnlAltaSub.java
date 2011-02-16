/**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Freyder Espinosa V
 */
package Tablas;

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


public class PnlAltaSub extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	GpComboBox CmbSector = new GpComboBox();
	GpComboBox CmbPais = new GpComboBox();
	GpComboBox CmbProvincia = new GpComboBox();
	ConexionDb conexion = new ConexionDb();
	JTextArea textarea = new JTextArea();
	ResultSet rs;
	int sector = 0;
	int pais = 0;
	int provincia = 0;
	char caracter;
	Border empty = new EmptyBorder(0,0,0,0);
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	protected Object cbpais;
	
	public PnlAltaSub (){
		this.setBorder(empty);
		
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][4],
		   rec.idioma[rec.eleidioma][46],rec.idioma[rec.eleidioma][48],
		   rec.idioma[rec.eleidioma][7], rec.idioma[rec.eleidioma][8],
		   rec.idioma[rec.eleidioma][69], rec.idioma[rec.eleidioma][64]
		   };
		int[] fieldWidths = {11,10,10,10,20,11,11,40};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
			
	  //cuadro con scroll para las observaciones

	    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	    	textarea = (new JTextArea(3,18));
	    	textarea.setDocument(lpd);
	    	textarea.setLineWrap(true);
	    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    	
	     //Dibujo la interfaz
			
			    gbc.gridwidth = GridBagConstraints.RELATIVE;
		   		panel.add(jlbl[0]=new JLabel(fieldNames[0]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   	   	panel.add(jtxt[0]=new JTextField(fieldWidths[0]),gbc);

		   	   	
			   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   	   panel.add(jlbl[1]=new JLabel(fieldNames[1]),gbc);
		   	   gbc.gridwidth = GridBagConstraints.REMAINDER;
			   panel.add(CmbSector,gbc);
		   	   CmbSector.setPreferredSize(new Dimension(140,30));
			   
			
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT sector FROM SECTORES");
				try {
					while(rs.next()){
					CmbSector.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					
					conexion.cerrarConexion();

					
		   		
				gbc.gridwidth = GridBagConstraints.RELATIVE;
			   	panel.add(jlbl[2]=new JLabel(fieldNames[2]),gbc);
			   	gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		panel.add(CmbPais,gbc);
		   	    CmbPais.setPreferredSize(new Dimension(140,30));
			   

		   	    
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT pais FROM PAIS");
				try {
					while(rs.next()){
						CmbPais.addItem(rs.getString(1));	
						
					}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				

				//Hacer que	se actulize las provincias al cambiar el pais ¿¿ ??	
				
				
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("select id_pais FROM PAIS WHERE pais like '"+CmbPais.getSelectedItem().toString()+"'");
					
					try {
					rs.next();
					pais = rs.getInt(1);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				gbc.gridwidth = GridBagConstraints.RELATIVE;
			   	panel.add(jlbl[3]=new JLabel(fieldNames[3]),gbc);
			   	gbc.gridwidth = GridBagConstraints.REMAINDER;
				panel.add(CmbProvincia,gbc);
		   		CmbProvincia.setPreferredSize(new Dimension(140,30));
			   		   		
		   		conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT * FROM PROVINCIAS WHERE id_pais = "+pais);
				try {
					
					CmbProvincia.removeAllItems(); 
					while(rs.next()){	
						CmbProvincia.addItem(rs.getString(2));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					conexion.cerrarConexion();

		   		
					gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[4]=new JLabel(fieldNames[4]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[4]=new JTextField(fieldWidths[4]),gbc);
			   		
			   		
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[5]=new JLabel(fieldNames[5]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[5]=new JTextField(fieldWidths[5]),gbc);
			   		
			   		
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[6]=new JLabel(fieldNames[6]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[6]=new JTextField(fieldWidths[6]),gbc);

		   			
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[7]=new JLabel(fieldNames[7]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp),gbc);	   		


			   		
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
										
					conexion.executeUpdate("INSERT INTO SUBCONTRATA (nombre,sector,pais,provincia,direccion,cod_postal,telf,observaciones) VALUES ('"+ 
							jtxt[0].getText()+"','"+sector+"','"+pais+"','"+provincia+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+jtxt[6].getText()+textarea.getText()+"')");
					JOptionPane.showMessageDialog(aviso,"Subido");
				}
				textarea.setText(null);

			/*
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<7;++i) {	
					jtxt[i].setText("");
					}	

				textarea.setText(null);
				
				// Borrar cuando termine de añadir
				for(int i=0;i<7;++i) {	
					jtxt[i].setText("");
					}	
			}*/
		}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar"); 

		
		panel.setVisible(true);
		this.setViewportView(panel);
		
	}
}

