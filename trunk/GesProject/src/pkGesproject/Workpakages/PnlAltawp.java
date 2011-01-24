/**
 /**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Freyder Espinosa y Félix Perona
 */
package pkGesproject.Workpakages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import pkGesproject.LimitadorDeDocumento;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

import com.toedter.calendar.JDateChooser; 


public class PnlAltawp extends JScrollPane{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JButton jbtnaceptar2, jbtncancelar2;
	JDateChooser jdc1,jdc2;
	GpComboBox CmbPro = new GpComboBox();
	GpComboBox CmbPar = new GpComboBox();
	ConexionDb conexion = new ConexionDb();
	JTextArea textarea = new JTextArea();
	JTextArea textarea2 = new JTextArea();
	int id_wp = 0;
	ResultSet rs;
	String nomwp;
	int indexwp;
	char caracter;
	Border empty = new EmptyBorder(0,0,0,0);
	PnlBusquedawp modwp = PnlBusquedawp.Obtener_Instancia();
	static PnlAltawp instancia = new PnlAltawp();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	
	public PnlAltawp (){
	
		this.setBorder(empty);
		panel.setLayout(new GridBagLayout());
		  
		String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][13], rec.idioma[rec.eleidioma][55], rec.idioma[rec.eleidioma][82],
		   rec.idioma[rec.eleidioma][25],rec.idioma[rec.eleidioma][26],rec.idioma[rec.eleidioma][41], rec.idioma[rec.eleidioma][64]
		   };
		int[] fieldWidths = {20,9,15,10,10,6,6,6};
		
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
	   		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	   		
	    
		
	  //cuadro con scroll para las observaciones

	    	LimiteDocumento lpd2 = new LimiteDocumento(200); // Limite JTextArea
	    	textarea2 = (new JTextArea(3,18));
	    	textarea2.setDocument(lpd2);
	    	JScrollPane sp2 = new JScrollPane(textarea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	    	JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    	
	     
		
		for(int i=0;i<fieldNames.length;++i) {
		
			//System.out.println("Fieldnames = " + fieldNames.length + " / i = " + i);
			
		   gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   //desahabilitar campos de texto
		   
		   switch(i){
		   
		   case (0)://nombre
		   case (1)://presupuesto
			   	panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		   		break;
		   case (2)://combo de proyectos
			   panel.add(CmbPro,gbc);
			   CmbPro.setPreferredSize(new Dimension(140,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS");
				try {
				while(rs.next()){
					CmbPro.addItem(rs.getString(1));	
					
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				CmbPro.setSelectedItem(null);
			   	break;
		   case (3)://combo para partner
			   panel.add(CmbPar,gbc);
			   CmbPar.setPreferredSize(new Dimension(140,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,cod_part FROM PARTNER");
				try {
					System.out.println("work");
				while(rs.next()){
					CmbPar.addItem(rs.getString(1));
					
				}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						}
				CmbPar.setSelectedItem(null);
			   	break;
		   case (4)://fecha de inicio
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);}
		   		break;
		   case (5)://fecha de fin
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc);}
		   		break;
		   case (6)://descripcion
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp),gbc);}
			   	break;
		   case (7)://observaciones
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp2),gbc);}
		   		break;
		   		
		   }
			switch (i){
			case (0):
				LimitadorDeDocumento ljtxt0 = new LimitadorDeDocumento(20);
				jtxt[0].setDocument(ljtxt0);
				break;
			case (1):
			   	LimitadorDeDocumento ljtxt1 = new LimitadorDeDocumento(15);
				jtxt[1].setDocument(ljtxt1);
				break;
			}
		   //Jformat
		   //formato para el presupuesto
		   if(i==1 ){
				jtxt[i].addKeyListener(new KeyAdapter(){
				   public void keyTyped(KeyEvent e){
				      caracter = e.getKeyChar();
				      if(((caracter < '0') ||(caracter > '9')) &&
				         (caracter != KeyEvent.VK_BACK_SPACE) &&
				         (caracter != ',')) {
				         e.consume();  
				      }
				   }
				});
				}
		   //formato para el nombre del wp
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
				    		  ((caracter < 'A') ||(caracter > 'Z')) && ((caracter < '0') ||(caracter > '9')) &&
				         (caracter != KeyEvent.VK_BACK_SPACE)&&
				         (caracter != KeyEvent.VK_SPACE) &&
				         (caracter != '+') && (caracter != '(') && (caracter != ')')) {
				         e.consume();  
				      }
				   }
				});
				}
		}//fin for
	  /*  
		**
		 * Creamos los cuatro botones para este panel 
		 */
		//primeros dos botones del panel
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
		//botones de aceptar y cancelar para modificacion
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar2=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar2=new JButton(rec.idioma[rec.eleidioma][99]),gbc);
		jbtnaceptar2.setVisible(false);
		jbtncancelar2.setVisible(false);
		
		ActionListener accion = new ActionListener(){

		public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					ConexionDb conexdb = new ConexionDb();
					conexdb.Conectardb();
					//nomwp = cbtipo.getSelectedItem().toString();
					//para id de proyecto
					//se limitan la necesidad de campos obligatorios
					if(jtxt[0].getText()== ""||jtxt[1].getText()== ""||CmbPro.getSelectedItem()==null||CmbPar.getSelectedItem()==null||(jdc1.getDate()==null)||(jdc2.getDate()==null)){
						JOptionPane.showMessageDialog( null, "Faltan campos obligatorios por rellenar"); 
					}else{
						rs = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre like'"+ CmbPro.getSelectedItem().toString()+"'" );
						String id_pro = null;
						try {
							rs.next();
							id_pro = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//para la id del partner
						rs = conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like'"+ CmbPar.getSelectedItem().toString()+"'" );
						String id_par = null;
						try {
							rs.next();
							id_par = rs.getString(1);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String id_wp = null;	
						
					// cambiar fecha a sql
						  java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					//comprueba que la fecha de fin no es anterior  a la fecha de inicio.
						  System.out.println("antesif");
					if (sqlDate1.getTime()< sqlDate2.getTime()){
					System.out.println("entraen if");
						conexdb.executeUpdate("INSERT INTO WORKPAQUETS (nombre, id_pro, descripcion, presupuesto,f_ini, f_fin, observaciones) VALUES ('"+ jtxt[0].getText()+"','"+id_pro+"','"+textarea.getText()+"','"+jtxt[1].getText()+"','"+sqlDate1+"','"+sqlDate2+"','"+textarea2.getText()+"')");
						//para ver la id del workpaquets recien creado
						rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS WHERE nombre like'"+ jtxt[0].getText()+"'" );
						try {
							rs.next();
							id_wp = rs.getString(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						}
						conexdb.executeUpdate("INSERT INTO PARTNER_WORKPAQUETS (cod_part, id_wp) VALUES ('"+id_par+"','"+id_wp+"')");
						
						modwp.cuenta=modwp.contar_reg();
						modwp.datos = new String[modwp.cuenta][modwp.columnas];
						modwp.auxdatos = new String[modwp.cuenta][modwp.columnas];
						modwp.tablemodel = modwp.cargar_tabla(modwp.datos);
						modwp.jtblLateral.setModel(modwp.tablemodel);
						modwp.jtblLateral.repaint();
						modwp.llena = false;
						
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
						conexdb.cerrarConexion();

					CmbPro.setSelectedItem(null);
					CmbPar.setSelectedItem(null);
					jtxt[0].setText("");
					jtxt[1].setText("");
					jdc1.setDate(null);
					jdc2.setDate(null);
					textarea.setText(null);
					textarea2.setText(null);
				}else{
					System.out.println("eelseee");
					JOptionPane.showMessageDialog( null, "La Fecha de Fin debe ser mayor que la Fecha de Inicio"); 
					// Marcar campo FECHA con error en ROJO 
					jdc2.setBackground(Color.red);
				}
			
			}
					}
					
			
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
		jbtncancelar.addActionListener(accion);

		ActionListener accion2 = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getActionCommand().equals("aceptar")){
						int id_pro  = 0;
						ConexionDb conexdb = new ConexionDb();
						conexdb.Conectardb();
						rs = conexion.ConsultaSQL("SELECT id_pro FROM PROYECTOS WHERE nombre like'"+CmbPro.getSelectedItem().toString()+"'" );
						try {
							rs.next();
							id_pro=rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						  java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
						  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
						conexdb.executeUpdate("UPDATE WORKPAQUETS SET id_pro='"+id_pro+"', nombre='"+jtxt[0].getText()+"', descripcion='"+textarea.getText()+"', presupuesto='"+jtxt[1].getText()+"', f_ini='"+sqlDate1+"', f_fin='"+sqlDate2+"', observaciones='"+textarea2.getText()+"' WHERE id_wp = '"+PnlBusquedawp.id_wp+"' ");
						rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS WHERE nombre like'"+jtxt[0].getText()+"'" );
						try {
							rs.next();
							id_wp = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						rs = conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like'"+CmbPar.getSelectedItem().toString()+"'" );
						int id_par = 0;
						try {
							rs.next();
							id_par = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexdb.executeUpdate("DELETE FROM PARTNER_WORKPAQUETS WHERE id_wp='"+id_wp+"' AND cod_part='"+id_par+"'");
						conexdb.executeUpdate("INSERT INTO PARTNER_WORKPAQUETS (cod_part, id_wp) VALUES ('"+id_par+"','"+id_wp+"')");
						conexdb.cerrarConexion();
						jdc1.setDate(null);
						jdc2.setDate(null);
						textarea.setText(null);
						textarea2.setText(null);
						jtxt[0].setText("");
						jtxt[1].setText("");
						
						modwp.cuenta=modwp.contar_reg();
						modwp.datos = new String[modwp.cuenta][modwp.columnas];
						modwp.auxdatos = new String[modwp.cuenta][modwp.columnas];
						modwp.tablemodel = modwp.cargar_tabla(modwp.datos);
						modwp.jtblLateral.setModel(modwp.tablemodel);
						modwp.jtblLateral.repaint();
						modwp.llena = false;
						
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][100]);
						PnlBusquedawp.modificar.dispose();
					}
					if(e.getActionCommand().equals("cancelar")){
						PnlBusquedawp.modificar.dispose();
					}
				}
			};
		jbtnaceptar2.setActionCommand("aceptar");
		jbtnaceptar2.addActionListener(accion2);
		jbtncancelar2.setActionCommand("cancelar");
		jbtncancelar2.addActionListener(accion2);
		
		panel.setVisible(true);
		this.setViewportView(panel);
		
	}
	
	public static PnlAltawp Obtener_Instancia(){
		return instancia;
	}
}