	/**
	 /**
	 * Esta clase se encarga de realizar el alta de nuevas Becas 
	 * 
	 * @author Felix Perona V
	 */
	package pkGesproject.Becas;

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
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;

	import pkGesproject.ConexionDb;
	import pkGesproject.GesIdioma;
	import pkGesproject.GpComboBox;
	import pkGesproject.LimiteDocumento;
	import pkGesproject.RsGesproject;

	import com.toedter.calendar.JDateChooser; 


	public class PnlAltaBecas extends JScrollPane{
		
		GesIdioma rec = GesIdioma.obtener_instancia();
		JTextField[] jtxt;
		JLabel[] jlbl;
		JButton jbtnaceptar, jbtncancelar;
		JDateChooser jdc1,jdc2;
		private GpComboBox CmbId = new GpComboBox();;
		ConexionDb conexion = new ConexionDb();
		JTextArea textarea = new JTextArea();
		JTextArea textarea2 = new JTextArea();
		ResultSet rs;
		String nomwp;
		int indexwp;
		
		
		JPanel panel = new JPanel();
		JFrame aviso = new JFrame();
		
		public PnlAltaBecas (){
			RsGesproject recursos = RsGesproject.Obtener_Instancia();
			
			panel.setLayout(new GridBagLayout());
			  
			String[] fieldNames = {
			   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][28], rec.idioma[rec.eleidioma][56],
			   rec.idioma[rec.eleidioma][69],rec.idioma[rec.eleidioma][76],rec.idioma[rec.eleidioma][9],rec.idioma[rec.eleidioma][66],
			   rec.idioma[rec.eleidioma][67],rec.idioma[rec.eleidioma][67],rec.idioma[rec.eleidioma][68],rec.idioma[rec.eleidioma][68]
			   };
			int[] fieldWidths = {20,30,10,14,14,30,15,1010,10,10};
			
			jtxt = new JTextField[fieldNames.length];
			jlbl = new JLabel[fieldNames.length];
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(20,0,15,0);
			//panel.add(new JLabel("Alta Becas"),gbc);
			
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
			   case (1)://apellidos
			   case (2)://dni
			   case (3)://telf1
			   case (4)://telf2
			   case (5)://email
				   	panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			   		break;
			   case (6):
				   panel.add(CmbId,gbc);
				   CmbId.setPreferredSize(new Dimension(140,30));
				   
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM TITULO");
					try {
					while(rs.next()){
						CmbId.addItem(rs.getString(1));	
						
					}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
				   	break;
			   case (7):
				   panel.add(CmbId,gbc);
				   CmbId.setPreferredSize(new Dimension(140,30));
				   
					conexion.Conectardb();
					rs = conexion.ConsultaSQL("SELECT nombre,id_pro FROM IDIOMAS");
					try {
					while(rs.next()){
						CmbId.addItem(rs.getString(1));	
						
					}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
				   	break;
			   case (8):
			   		{gbc.gridwidth = GridBagConstraints.REMAINDER; panel.add(jdc1,gbc);}
			   		break;
			   		
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
						
						rs = conexion.ConsultaSQL("SELECT id_pro FROM IDIOMA WHERE nombre like'"+ CmbId.getSelectedItem().toString()+"'" );
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
						
				if (sqlDate1.getTime()< sqlDate2.getTime()){
						conexdb.executeUpdate("INSERT INTO BECAS (nombre, apellidos, telf1, telf2, dni, email, titulo, curso_lectivo, idioma, fec_sol, fec_fin, erasmus, estado) VALUES ('"+ jtxt[0].getText()+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+sqlDate1+"','"+sqlDate2+"','"+textarea2.getText()+"')");
						JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][60]);
						conexdb.cerrarConexion();
					}
					
					jdc1.setDate(null);
					jdc2.setDate(null);
					textarea.setText(null);
					textarea2.setText(null);
				}else{
					JOptionPane.showMessageDialog( null, "La Fecha de Fin debe ser mayor que la Fecha de Inicio"); 
					//Marcar campo FECHA con error en ROJO 
					jdc2.setBackground(Color.red);
			
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

			
			panel.setVisible(true);
			this.setViewportView(panel);
			
		}
	}