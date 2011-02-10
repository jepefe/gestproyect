package Tablas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;
import pkGesproject.Workpakages.PnlAltawp;
import pkGesproject.Workpakages.PnlBusquedawp;


public class PnlAlta_equipamientos extends JPanel{
		
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JLabel alerta;
	JLabel partner, descripcion,justificacion, wp, fecha;
	JButton jbtnaceptar, jbtncancelar;
	JDateChooser jdc1;
	GpComboBox CmbPar = new GpComboBox();
	GpComboBox Cmbwp = new GpComboBox();
	ConexionDb conexion = new ConexionDb();
	JTextArea textdescripcion = new JTextArea();
	JTextArea textjustificacion = new JTextArea();
	ResultSet rs;
	Border empty = new EmptyBorder(0,0,0,0);
	String[] fieldNames = {
			   rec.idioma[rec.eleidioma][114]+"*",rec.idioma[rec.eleidioma][115]+"*", rec.idioma[rec.eleidioma][116]+"*", rec.idioma[rec.eleidioma][117]+"*",
			   rec.idioma[rec.eleidioma][118]+"*",rec.idioma[rec.eleidioma][119]+"*"
	};
	
	JFrame aviso = new JFrame();
	JPanel panel = new JPanel();
	JPanel contenedor = new JPanel();
	JPanel mesage = new JPanel();
	
	int permetir_alta = 0;
		
	public PnlAlta_equipamientos (){
		//Llamamos al medoto de la interfaz
		crear_interfaz();
		
		ActionListener accion = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				validar_campos();
				if(e.getActionCommand().equals("aceptar") && permetir_alta == 0){
					ConexionDb conexdb = new ConexionDb();
					conexdb.Conectardb();
					
					// cambiar fecha a sql
					java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());

					conexdb.executeUpdate("INSERT INTO EQUIPAMIENTOS (partner,descripcion,justificacion,wp,coste_total,fecha,compra_alguiler,grado_depreciacion,meses_usara,grado_utilizacion,costes_subvencionados ) VALUES ('"
							+ Integer.toString(CmbPar.getSelectedIndex())+"','"+textdescripcion.getText()+"','"+textjustificacion.getText()+"','"+Integer.toString(Cmbwp.getSelectedIndex())+"','"+jtxt[0].getText()+"','"+sqlDate1+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"')");
					//para ver la id del workpaquets recien creado
					rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS WHERE nombre like'"+ jtxt[0].getText()+"'" );
					try {
						rs.next();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
					
					//limpiamos los campos despues de dar de alta
					for(int i=0;i<2;++i) {	
						jtxt[i].setText("");
						}	
					jdc1.setDate(null);
					textdescripcion.setText(null);
					textjustificacion.setText(null);

					for(int i=0;i<fieldNames.length;++i) {	
						jtxt[i].setText("");
					}
					CmbPar.setSelectedItem(null);
					Cmbwp.setSelectedItem(null);
					mesage.setVisible(false);
				}else{
					alerta.setText(rec.idioma[rec.eleidioma][79]);
					mesage.setBackground(Color.decode("#ec8989"));
					mesage.setVisible(true);
					permetir_alta = 0;
				}
			
			// Borrar cuando damos al boton cancelar
			if( e.getActionCommand().equals("cancelar")){
				for(int i=0;i<2;++i) {	
					jtxt[i].setText("");
					}	
				jdc1.setDate(null);
				textdescripcion.setText(null);
				textjustificacion.setText(null);
				
				// Borrar cuando termine de añadir
				for(int i=0;i<fieldNames.length;++i) {	
					jtxt[i].setText("");
					}
				CmbPar.setSelectedItem(null);
				Cmbwp.setSelectedItem(null);
				mesage.setVisible(false);
			}
		}
			
		};
		
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		jbtncancelar.addActionListener(accion);
		
		contenedor.setLayout(new BorderLayout());
		panel.setOpaque(true);
		contenedor.add(mesage,BorderLayout.NORTH);
		contenedor.add(panel,BorderLayout.CENTER);
		
		this.add(contenedor);
	  		
	}
	
	public void crear_interfaz(){
		this.setBorder(empty);
		this.setLayout(new GridBagLayout());
		  
		
		//Tamaños de los textfields
		int[] fieldWidths = {10,10,10,2,10,10};
		
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
						
		gbc.insets = new Insets(5,10,5,5);
		
		
		//declaramos el campo que vamos a utilizar para anadir las fechas
	      jdc1 = new JDateChooser();
	      jdc1.setDateFormatString("dd/MM/yyyy");
	    
	      
		/**
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */	    
		
	      
	    alerta=new JLabel();
		alerta.setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));
		mesage.add(alerta);
		mesage.setBackground(Color.decode("#D0E495"));
		mesage.setVisible(false);
	      
	  	for(int i=0;i<fieldNames.length;++i) {
			
			if(i==0){//Creo el combo del partner
				
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre FROM PARTNER");
			    	try {
						while(rs.next()){	
							CmbPar.addItem(rs.getString(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	conexion.cerrarConexion();
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.WEST;
				this.add(partner=new JLabel(rec.idioma[rec.eleidioma][57]+"*"),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				CmbPar.setPreferredSize(new Dimension(177,30));
				this.add(CmbPar,gbc);//fin combo partner
				CmbPar.setSelectedItem(null);
				
				//Creo el textarea descripcion
				gbc.gridwidth = GridBagConstraints.RELATIVE;
		    	this.add(descripcion=new JLabel(rec.idioma[rec.eleidioma][41]+"*"),gbc); 
		    	gbc.gridwidth = GridBagConstraints.REMAINDER;
		    	//cuadro con scroll para las descripciones
		    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		    	textdescripcion.setDocument(lpd);
		    	textdescripcion.setLineWrap(true);
		    	JScrollPane sp = new JScrollPane(textdescripcion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    	this.add((sp),gbc);
		    	
		    	//Creo el textarea justificacion
				gbc.gridwidth = GridBagConstraints.RELATIVE;
		    	this.add(justificacion=new JLabel(rec.idioma[rec.eleidioma][123]+"*"),gbc); 
		    	gbc.gridwidth = GridBagConstraints.REMAINDER;
		    	//cuadro con scroll para las justificacion
		    	LimiteDocumento lpd2 = new LimiteDocumento(200); // Limite JTextArea
		    	textjustificacion.setDocument(lpd2);
		    	textjustificacion.setLineWrap(true);
		    	JScrollPane sp2 = new JScrollPane(textjustificacion,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		    	JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    	this.add((sp2),gbc);
		    	
		    	//Combo de WP
		    	conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre FROM WORKPAQUETS");
			    	try {
						while(rs.next()){	
							Cmbwp.addItem(rs.getString(1));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	conexion.cerrarConexion();
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.WEST;
				this.add(wp=new JLabel(rec.idioma[rec.eleidioma][40]+"*"),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				Cmbwp.setPreferredSize(new Dimension(177,30));
				this.add(Cmbwp,gbc);//fin combo WP	
				Cmbwp.setSelectedItem(null);

				}//Fin del if
			
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.anchor = GridBagConstraints.WEST;
			this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			
			if (i==3){
				//Pongo lo de la fecha
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.WEST;
				this.add(fecha=new JLabel(rec.idioma[rec.eleidioma][95]+"*"),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
			}//fin del if
		   
		}//fin for  Dibujo de los componentes terminado
	  
	  	
	  	/*  
		 * Creamos los cuatro botones para este panel 
		 */
		//primeros dos botones del panel
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
	}
	
	public void validar_campos(){
		
		for(int i=0;i<fieldNames.length;++i) {
			if (jtxt[i].getText().length() > 0){
			}else{
				permetir_alta = 1;
			}
			if((CmbPar.getSelectedItem() != null) && (Cmbwp.getSelectedItem() != null) && (jdc1.getDate() != null) && (textdescripcion.getText().length() > 1) && (textjustificacion.getText().length() > 1)){	
			
			}else{
				permetir_alta = 1;
			}
			
	}
		
	}
	
	
	
	
}