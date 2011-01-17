package pkGesproject.Socios;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAltasocio extends JScrollPane{


	GesIdioma rec = GesIdioma.obtener_instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JButton jbtnaceptar, jbtncancelar;
	JPanel panel = new JPanel();
	JFrame aviso = new JFrame();
	ConexionDb conexion= new ConexionDb();
	String paisaux, sectoraux;
	GpComboBox cbpais = new GpComboBox();
	GpComboBox cbsector = new GpComboBox();
	JTextArea textarea = (new JTextArea(3,13));
	PnlModificarsocio modso = PnlModificarsocio.Obtener_Instancia();
	ResultSet rs;
	
	public PnlAltasocio (){
		final RsGesproject recursos = RsGesproject.Obtener_Instancia();
		
		panel.setLayout(new GridBagLayout());
		  
		final String[] fieldNames = {
		   rec.idioma[rec.eleidioma][3],rec.idioma[rec.eleidioma][7],
		   rec.idioma[rec.eleidioma][8],rec.idioma[rec.eleidioma][9],rec.idioma[rec.eleidioma][70],rec.idioma[rec.eleidioma][5],
		   rec.idioma[rec.eleidioma][5],rec.idioma[rec.eleidioma][71]
		};
		int[] fieldWidths = {20,30,6,20,20,10,10,10};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		//panel.add(new JLabel("Alta Partner"),gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);
		
		/*
		 * Con el bucle for vamos creando tantos labels y textfields como 
		 * nombres de campos hayamos metido en fieldNames.
		 */
		for(int i=0;i<fieldNames.length;++i) {
			gbc.gridwidth = GridBagConstraints.RELATIVE;
			panel.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			panel.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
			
			if(i==1){
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				panel.add(new JLabel(rec.idioma[rec.eleidioma][4]),gbc);
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT * FROM SECTORES");
				
				panel.add(cbsector,gbc);
				try {
				while(rs.next()){
					cbsector.addItem(rs.getString(2));
				
				}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						}
					
					
					gbc.gridwidth = GridBagConstraints.RELATIVE;
					panel.add(new JLabel(rec.idioma[rec.eleidioma][46]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					
					rs = conexion.ConsultaSQL("SELECT * FROM PAIS");
					
					panel.add(cbpais,gbc);
					try {
					while(rs.next()){
						cbpais.addItem(rs.getString(2));
					
					}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
					}
			}
			
		}
		
		
		FocusListener foco = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
				rs = conexion.ConsultaSQL("SELECT p.nombre FROM PARTNER p WHERE nombre LIKE '"+jtxt[0].getText()+"'");
				try {
					if(rs.next()){
						
						//jtxt[0].setText("");
						jtxt[0].requestFocus();
						jtxt[0].selectAll();
						
						GridBagConstraints gb = new GridBagConstraints();
						gb.gridx = 3; // El área de texto empieza en la columna
			            gb.gridy = 0; // El área de texto empieza en la fila
						JLabel aviso = new JLabel("Ya existe");
						panel.add(aviso,gb);
						
						//JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][75]);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		jtxt[0].addFocusListener(foco);
		
		
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		   panel.add(new JLabel(rec.idioma[rec.eleidioma][64]),gbc);
		   gbc.gridwidth = GridBagConstraints.REMAINDER;
		   LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		      textarea.setDocument(lpd);
		      JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		      textarea.setLineWrap(true);
		     panel.add((sp),gbc);
		     
		
		
		/*
		 * Creamos los dos botones para este panel 
		 */
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		panel.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][74]),gbc);
		
		
		 
	     
	     
		ActionListener accion = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getActionCommand().equals("aceptar")){
					
					ConexionDb conexdb = new ConexionDb();
					ResultSet rs;
					conexdb.Conectardb();
					rs=conexion.ConsultaSQL("SELECT id_sector FROM SECTORES WHERE sector like '"+cbsector.getSelectedItem().toString()+"'");
					
					/**
					 * Creamos una variable string para obtener el id del sector y del pais
					 **/
					
					String sector = null;
					try {
						rs.next();
						sector = rs.getString(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					rs=conexion.ConsultaSQL("SELECT id_pais FROM PAIS WHERE pais like '"+cbpais.getSelectedItem().toString()+"'");
					
					String pais = null;
					try {
						rs.next();
						pais = rs.getString(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					conexdb.executeUpdate("INSERT INTO PARTNER (nombre, sector, pais, direccion, codpostal, email, email2, telefono, telefono2, fax, observaciones) VALUES ('"+ jtxt[0].getText()+"','"+sector+"','"+pais+"','"+jtxt[1].getText()+"','"+jtxt[2].getText()+"','"+jtxt[3].getText()+"','"+jtxt[4].getText()+"','"+jtxt[5].getText()+"','"+jtxt[6].getText()+"','"+jtxt[7].getText()+"','"+textarea.getText()+"')");
					for(int i=0;i<fieldNames.length;i++){
						jtxt[i].setText("");
					}
					textarea.setText("");
					cbsector.setSelectedIndex(0);
					cbpais.setSelectedIndex(0);
					//recursos.modso.cargar_tabla();
					
					modso.cuenta=modso.contar_reg();
					modso.datos = new String[modso.cuenta][modso.columnas];
					modso.auxdatos = new String[modso.cuenta][modso.columnas];
					modso.tablemodel = modso.cargar_tabla(modso.datos);
					modso.jtblLateral.setModel(modso.tablemodel);
					modso.jtblLateral.repaint();
					
					modso.llena = false;
					
					JOptionPane.showMessageDialog(aviso, rec.idioma[rec.eleidioma][24]);
					conexdb.cerrarConexion();
				}
				
				if(e.getActionCommand().equals("cancelar")){
					JOptionPane.showMessageDialog(aviso,"Esta seguro de borrar los datos?");
					for(int i=0;i<fieldNames.length;i++){
						jtxt[i].setText("");
					}
					textarea.setText("");
					cbsector.setSelectedIndex(0);
					cbpais.setSelectedIndex(0);
				}
			}
			
		};
		jbtnaceptar.setActionCommand("aceptar");
		jbtnaceptar.addActionListener(accion);
		jbtncancelar.setActionCommand("cancelar");
		jbtncancelar.addActionListener(accion);
		
		panel.setOpaque(true);
		this.setViewportView(panel);
		
	}
}
