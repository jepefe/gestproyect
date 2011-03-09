/**
 * Esta clase se encarga de realizar el alta de nuevas Subcontratas 
 * 
 * @author Esteban Salmerón
 */
package Tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	JLabel alerta;
	JButton jbtnaceptar, jbtncancelar;
	GpComboBox CmbSector = new GpComboBox();
	GpComboBox CmbPais = new GpComboBox();
	GpComboBox CmbProvincia = new GpComboBox();
	GpComboBox CmbWP = new GpComboBox();
	ConexionDb conexion = new ConexionDb();
	JTextArea textarea = new JTextArea();
	ResultSet rs;	
	
	int pais = 0;
	String idsector;
	String idpais;
	String idprovincia;
	String wp;
	char caracter;
	int permetir_alta = 0;
	Border empty = new EmptyBorder(0,0,0,0);
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	JPanel contenedor = new JPanel();
	JPanel mesage = new JPanel();
	JPanel panel = new JPanel();
	ActionListener accion;
	
	public PnlAltaSub (){
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		  
		final String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3]+"*",rec.idioma[rec.eleidioma][4]+"*",
		   rec.idioma[rec.eleidioma][46]+"*",rec.idioma[rec.eleidioma][48]+"*",
		   rec.idioma[rec.eleidioma][7]+"*", rec.idioma[rec.eleidioma][8]+"*",
		   rec.idioma[rec.eleidioma][69]+"*", rec.idioma[rec.eleidioma][64]+"*",
		   rec.idioma[rec.eleidioma][40]+"*", rec.idioma[rec.eleidioma][109]+"*",
		   };
		int[] fieldWidths = {11,10,10,10,20,11,11,40,10,11};
		
		jtxt = new JTextField[5];
		jlbl = new JLabel[fieldNames.length];
				
			
			
		final GridBagConstraints gbc = new GridBagConstraints();
		
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
	    	
	    	
	    	//Pongo el panel de las alertas
	    	alerta=new JLabel();
			alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
	    	mesage.add(alerta);
			mesage.setBackground(Color.decode("#D0E495"));
			mesage.setVisible(false);
			
			
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
				rs = conexion.ConsultaSQL("SELECT * FROM PAIS");
				try {
					while(rs.next()){
						CmbPais.addItem(rs.getString(2));	
						
					}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				

				//Hacer que	se actulize las provincias al cambiar el pais
				
				
					
			
				CmbPais.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("select id_pais FROM PAIS WHERE pais like '"+CmbPais.getSelectedItem().toString()+"'");
					
					try {
					rs.next();
					pais = rs.getInt(1);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
			   		   		
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
				
				}
				});
				
					gbc.gridwidth = GridBagConstraints.RELATIVE;
				   	panel.add(jlbl[3]=new JLabel(fieldNames[3]),gbc);
				   	gbc.gridwidth = GridBagConstraints.REMAINDER;
					panel.add(CmbProvincia,gbc);
			   		CmbProvincia.setPreferredSize(new Dimension(140,30));
			
			   		
					gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[4]=new JLabel(fieldNames[4]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[1]=new JTextField(fieldWidths[4]),gbc);
			   		
			   		
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[5]=new JLabel(fieldNames[5]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[2]=new JTextField(fieldWidths[5]),gbc);
			   		
			   	
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[6]=new JLabel(fieldNames[6]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add(jtxt[3]=new JTextField(fieldWidths[6]),gbc);

		   			
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
			   		panel.add(jlbl[7]=new JLabel(fieldNames[7]),gbc);
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   		panel.add((sp),gbc);
			   		
			   		
			   		gbc.gridwidth = GridBagConstraints.RELATIVE;
				   	panel.add(jlbl[8]=new JLabel(fieldNames[8]),gbc);
				   	gbc.gridwidth = GridBagConstraints.REMAINDER;
				   	
			   			
					panel.add(CmbWP,gbc);
					CmbWP.setPreferredSize(new Dimension(140,30));
					   
					
						conexion.Conectardb();
						rs = conexion.ConsultaSQL("SELECT nombre FROM WORKPAQUETS");
						try {
							while(rs.next()){
							CmbWP.addItem(rs.getString(1));	
							
						}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								}
							
							conexion.cerrarConexion();
					
							
					gbc.gridwidth = GridBagConstraints.RELATIVE;
					panel.add(jlbl[9]=new JLabel(fieldNames[9]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					panel.add(jtxt[4]=new JTextField(fieldWidths[9]),gbc);
			   		
			   				   		

			   	//filtro para numeros normales naturales del campo cod_postal

					jtxt[2].addKeyListener(new KeyAdapter(){
					   public void keyTyped(KeyEvent e){
					      caracter = e.getKeyChar();
					      if(((caracter < '0') ||(caracter > '9')) &&
					         (caracter != KeyEvent.VK_BACK_SPACE)) {
					         e.consume();  
					      }
					   }
					});	
			   	//filtro para numeros normales naturales del campo telf

					jtxt[3].addKeyListener(new KeyAdapter(){
					   public void keyTyped(KeyEvent e){
					      caracter = e.getKeyChar();
					      if(((caracter < '0') ||(caracter > '9')) &&
					         (caracter != KeyEvent.VK_BACK_SPACE)) {
					         e.consume();  
					      }
					   }
					});
					
				//filtro para numeros con decimales del campo gastos	
					jtxt[4].addKeyListener(new KeyAdapter(){
						   public void keyTyped(KeyEvent e){
						      caracter = e.getKeyChar();
						      if(((caracter < '0') ||(caracter > '9')) &&
						         (caracter != KeyEvent.VK_BACK_SPACE) &&
						         (caracter != '.'))
						         {
						         e.consume();  
						      }
						   }
						});
					  		
		/*  
		 *
		 * Creamos los dos botones para este panel 
		 */
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
		accion = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			validar_campos();
			if(e.getActionCommand().equals("aceptar") && permetir_alta == 0){
				
					ConexionDb conexdb = new ConexionDb();
					conexdb.Conectardb();
					
					
					rs = conexdb.ConsultaSQL("Select id_sector FROM SECTORES WHERE sector='" + CmbSector.getSelectedItem().toString()+"'");
					if (rs!= null){
						try {
							rs.next();
							idsector = Integer.toString(rs.getInt(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					
					
					rs = conexdb.ConsultaSQL("Select id_pais FROM PAIS WHERE pais='" + CmbPais.getSelectedItem().toString()+"'");
					if (rs!= null){
						try {
							rs.next();
							idpais = Integer.toString(rs.getInt(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					
					
					rs = conexdb.ConsultaSQL("Select id_provincias FROM PROVINCIAS WHERE estado='" + CmbProvincia.getSelectedItem().toString() + "'" + "AND id_pais='" + pais + "'");
					if (rs!= null){
						try {
							rs.next();
							idprovincia = Integer.toString(rs.getInt(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
					rs = conexdb.ConsultaSQL("Select id_wp FROM WORKPAQUETS WHERE nombre='" + CmbWP.getSelectedItem().toString()+"'");
					if (rs!= null){
						try {
							rs.next();
							wp = Integer.toString(rs.getInt(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
										
					conexion.executeUpdate("INSERT INTO SUBCONTRATA (nombre,sector,pais,provincia,direccion,cod_postal,telf,observaciones,wp,coste) VALUES ('"+ 
							jtxt[0].getText()+"','"+idsector+"','"+idpais+"','"+idprovincia+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+textarea.getText()+"','"+wp+"','"+jtxt[4].getText()+"')");
					JOptionPane.showMessageDialog(mesage,"Subcontrata dada de alta");
					
					
				for(int i=0;i<5;++i) {	
					jtxt[i].setText("");
				}
				CmbProvincia.removeAllItems();
				textarea.setText(null);
				mesage.setVisible(false);
				PnlMod_Sub.actualizar_tabla();
			
			}else{
				alerta.setText(rec.idioma[rec.eleidioma][79]);
				mesage.setBackground(Color.decode("#ec8989"));
				mesage.setVisible(true);
				permetir_alta = 0;
			}
			
			
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<5;++i) {	
					jtxt[i].setText("");
				}
				CmbProvincia.removeAllItems();
				textarea.setText(null);
				mesage.setVisible(false);
			}
		}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		jbtncancelar.addActionListener(accion);

		contenedor.setLayout(new BorderLayout());
		contenedor.setOpaque(true);
		contenedor.add(mesage,BorderLayout.NORTH);
		contenedor.add(panel,BorderLayout.CENTER);
		this.setViewportView(contenedor);

			
	}
	
	
	
	public void validar_campos(){
		/*
		 * Revisamos si estan rellenados todos los camps obligatorios
		 */
		
		
		permetir_alta = 0;
		for(int i=0;i<5;++i) {
			if (jtxt[i].getText().length() > 0){
			}else{
				permetir_alta = 1;				
			}	
		}
		if((CmbSector.getSelectedItem() != null) && (CmbPais.getSelectedItem() != null) && (CmbProvincia.getSelectedItem() != null) && (CmbWP.getSelectedItem() != null) &&  (textarea.getText().length() > 1)){	
			
		}else{
			permetir_alta = 1;
		}
		
	}
	
}

