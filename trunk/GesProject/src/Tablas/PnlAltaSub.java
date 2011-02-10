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
	String nomwp;
	int indexwp;
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
		   rec.idioma[rec.eleidioma][64]
		   };
		int[] fieldWidths = {10,15,10,6,6,6};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
	      
	      
		
	  //cuadro con scroll para las observaciones

	    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
	    	textarea = (new JTextArea(3,18));
	    	textarea.setDocument(lpd);
	    	textarea.setLineWrap(true);
	    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    	
	     
		
		for(int i=0;i<fieldNames.length;++i) {
		
			//System.out.println("Fieldnames = " + fieldNames.length + " / i = " + i);
			
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   //desahabilitar campos de texto
		   
		   switch(i){
		   
		   case (0):
		   	   	panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		   		break;
		   case (1):
			   panel.add(CmbSector,gbc);
		   	   CmbSector.setPreferredSize(new Dimension(140,30));
			   
				/**
				 * Creacion del JComboBox y a�adir los items.
				 *Se conecta a la BD para realizar la consulta
				 **/
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT sector,id_sector FROM SECTORES");
				try {
				while(rs.next()){
					CmbSector.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					conexion.cerrarConexion();
			   	break;
		
		   case (2):
			   panel.add(CmbPais,gbc);
		   	   CmbPais.setPreferredSize(new Dimension(140,30));
			   
				/**
				 * Creacion del JComboBox y a�adir los items.
				 *Se conecta a la BD para realizar la consulta
				 **/
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT pais,id_pais FROM PAIS");
				try {
				while(rs.next()){
					CmbPais.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					conexion.cerrarConexion();
					break;			
		   	case (3):
		   		panel.add(CmbProvincia,gbc);
		   		CmbProvincia.setPreferredSize(new Dimension(140,30));
			   
				/**
				 * Creacion del JComboBox y a�adir los items.
				 *Se conecta a la BD para realizar la consulta
				 **/
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT estado,id_provincias,id_pais FROM PROVINCIAS");
				try {
				while(rs.next()){
					CmbProvincia.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
			   	break;			
			   case (4):
				{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp),gbc);}		   		break;
		   }
		}
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
					
					rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS W WHERE W.nombre like'"+ CmbSector.getSelectedItem().toString()+"'" );
					String id_wp = null;
					try {
						rs.next();
						id_wp = rs.getString(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				textarea.setText(null);

			
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	

				textarea.setText(null);
				
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

