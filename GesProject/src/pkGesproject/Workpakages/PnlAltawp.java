/**
 /**
 * Esta clase se encarga de realizar el alta de nuevas tareas 
 * 
 * @author Félix Perona
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import pkGesproject.JTextFieldLimit;
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
	FocusListener foco;
	DefaultListModel modelo;  // listas Partners (lista2)
	DefaultListModel modelo2;
	public JList listaP ,listaP2 ;
	JPanel jpnl = new JPanel();
	String Npartners[] ;	// array con partners
	int cuenta =0; // cuenta para array dinamica.
	
	int id_par = 0;
	
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

		   //desahabilitar campos de texto
		   
		   switch(i){
		   
		   case (0)://nombre
		   case (1)://presupuesto
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   	panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
		   		break;
		   case (2)://combo de proyectos
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
			   panel.add(CmbPro,gbc);
			   CmbPro.setPreferredSize(new Dimension(140,30));
			   
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS ORDER BY nombre");
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
		   case (3)://listas para partner	
			   
				modelo = new DefaultListModel(); // modelos JLIST
			  	modelo2 = new DefaultListModel(); 
			    listaP = new JList(modelo2); 
		       // Primer JLIST
		      JScrollPane sp1 = new JScrollPane(listaP);
		      listaP.setFixedCellWidth(142);
		      listaP.setFixedCellHeight(18);
		      sp1.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][84])); 
		      gbc.gridwidth = GridBagConstraints.RELATIVE;
		      panel.add(sp1,gbc);
		      gbc.gridwidth = GridBagConstraints.REMAINDER;  
		        // Segundo JLIST
		      listaP2 = new JList(modelo);
		      JScrollPane sp5 = new JScrollPane(listaP2);
		      listaP2.setFixedCellWidth(142);
		      listaP2.setFixedCellHeight(18);
		      sp5.setColumnHeaderView(new JLabel(rec.idioma[rec.eleidioma][82])); 
		      panel.add(sp5,gbc);
	          modelo.toArray();
			   	break;
		   case (4)://fecha de inicio
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);}
		   		break;
		   case (5)://fecha de fin
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc2,gbc);}
		   		break;
		   case (6)://descripcion
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp),gbc);}
			   	break;
		   case (7)://observaciones
			   panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		   		gbc.gridwidth = GridBagConstraints.REMAINDER;
		   		{gbc.gridwidth = GridBagConstraints.REMAINDER;panel.add((sp2),gbc);}
		   		break;
		   		
		   }
		   //se limitan los caracteres de los campos
			switch (i){
			case (0):
				JTextFieldLimit ljtxt0 = new JTextFieldLimit(20);
				jtxt[0].setDocument(ljtxt0);
				break;
			case (1):
				JTextFieldLimit ljtxt1 = new JTextFieldLimit(15);
				jtxt[1].setDocument(ljtxt1);
				break;
			}
			foco = new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("gana el foco");
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("pierde focus");
					java.sql.Date sqlDate1 = new java.sql.Date(jdc1.getDate().getTime());
					  java.sql.Date sqlDate2 = new java.sql.Date(jdc2.getDate().getTime());
					if (sqlDate1.getTime()> sqlDate2.getTime()){
						jdc2.setBackground(Color.red);
					}else{
						jdc2.setBackground(null);
					}
				}
				
			};
			jdc2.addFocusListener(foco);
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
		//mouse listener tabla partnets
	     // Evento doble click primer JLIST
	      MouseListener mouseListener = new MouseAdapter() {
	    	 
	    	  public void mouseClicked(MouseEvent e) {
	    		  if (e.getClickCount() == 2) {
	                modelo.addElement(listaP.getSelectedValue());		    
	                modelo2.removeElement(listaP.getSelectedValue());
	               }
	          }
	      };
	      listaP.addMouseListener(mouseListener);
	      
	   // Evento doble click segundo  JLIST (lsitaP2)
	      MouseListener mouseListener2 = new MouseAdapter() {
	    	 
	          public void mouseClicked(MouseEvent e) {
	              if (e.getClickCount() == 2) {
	                modelo2.addElement(listaP2.getSelectedValue());	
	                modelo.removeElement(listaP2.getSelectedValue());
	               }
	          }
	      };
	      listaP2.addMouseListener(mouseListener2);
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
					if(jtxt[0].getText()== ""||jtxt[1].getText()== ""||CmbPro.getSelectedItem()==null||(jdc1.getDate()==null)||(jdc2.getDate()==null)){
						JOptionPane.showMessageDialog( null, rec.idioma[rec.eleidioma][79]); 
						jtxt[0].requestFocus();
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
							id_wp = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						}
						for (int i = 0 ; i<listaP2.getModel().getSize(); i++){
							rs = conexion.ConsultaSQL("SELECT cod_part From PARTNER WHERE nombre = '"+modelo.getElementAt(i)+"'");
							try {
								while(rs.next()){id_par = rs.getInt(1); }
							} catch (SQLException d) {
								// TODO Auto-generated catch block
								d.printStackTrace();}
							conexdb.executeUpdate("INSERT INTO PARTNER_WORKPAQUETS(cod_part, id_wp) VALUES ('"+id_par+"','"+id_wp+"')" );	

						}
						
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
					jdc2.setBackground(null);
					vaciar_lista();
					
				}else{
					System.out.println("eelseee");
					JOptionPane.showMessageDialog( null, rec.idioma[rec.eleidioma][72]); 
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
				jtxt[0].setText("");
				jtxt[1].setText("");
				jdc2.setBackground(null);
				CmbPro.setSelectedItem(null);
				vaciar_lista();
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
		
		ActionListener lista = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Entra");
				rellena();
			}
		};
		CmbPro.addActionListener(lista);
		

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
						//rs = conexion.ConsultaSQL("SELECT cod_part FROM PARTNER WHERE nombre like'"+CmbPar.getSelectedItem()+"'" );
						
						/*try {
							rs.next();
							id_par = rs.getInt(1);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						conexdb.executeUpdate("DELETE FROM PARTNER_WORKPAQUETS WHERE id_wp='"+id_wp+"' AND cod_part='"+id_par+"'");
						conexdb.executeUpdate("INSERT INTO PARTNER_WORKPAQUETS (cod_part, id_wp) VALUES ('"+id_par+"','"+id_wp+"')");*/
						conexdb.cerrarConexion();
						jdc1.setDate(null);
						jdc2.setDate(null);
						textarea.setText(null);
						textarea2.setText(null);
						jtxt[0].setText("");
						jtxt[1].setText("");
						jdc2.setBackground(null);
						CmbPro.setSelectedItem(null);
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
	
	public void vaciar_lista(){
		for (int i = 0 ; i<listaP2.getModel().getSize(); i++){
			modelo2.addElement(listaP2.getModel().getElementAt(i));
		}
		modelo.removeAllElements();	
	}
	/**
	 * Metodo rella las listas.
	 */
	public void rellena(){
		modelo.removeAllElements();
		modelo2.removeAllElements();
		listaP.removeAll();
		// Conexion 1 BBDD
		System.out.println("entra rellena");
	      conexion.Conectardb();
	    /*	rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p  INNER JOIN PARTNER_PROYECTOS pp ON p.cod_part = pp.cod_part INNER JOIN PROYECTOS pr ON pp.id_pro = pr.id_pro WHERE pr.nombre = '"+CmbPro.getSelectedItem()+"' ORDER BY p.nombre");
	    //	rs2 = conexion.ConsultaSQL("Select count(nombre) From PARTNER");
	    	 // Cuenta para hacer la matriz dinamica
	    	try {
				while(rs.next()){cuenta = cuenta +1;}
			} catch (SQLException e) {
				e.printStackTrace();}
	    	
	    	Npartners = new String [cuenta] ;
	    */
	 //Conexion 2 BBDD
	    	rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p  INNER JOIN PARTNER_PROYECTOS pp ON p.cod_part = pp.cod_part INNER JOIN PROYECTOS pr ON pp.id_pro = pr.id_pro WHERE pr.nombre = '"+CmbPro.getSelectedItem()+"' ORDER BY p.nombre");
	    	
	    	 // Cuenta para hacer la matriz dinamica
	    	int k = 0;
	    	try {
	    		while(rs.next()){
					//Npartners[k] = (rs.getString(1));				
					modelo2.addElement(rs.getString(1));
				k++;
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	// JLIST
  	/*modelo = new DefaultListModel(); // modelos JLIST
  	modelo2 = new DefaultListModel(); 
  	
    listaP = new JList(modelo2); */
	// for para pasar los datos al modelo
   // for(int j=0; j<cuenta ; j++){
  	 // modelo2.addElement(Npartners[j]);  
   // }
	}
}