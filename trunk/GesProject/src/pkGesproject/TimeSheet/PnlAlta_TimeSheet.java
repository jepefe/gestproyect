package pkGesproject.TimeSheet;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;

public class PnlAlta_TimeSheet extends JPanel{
	
	GesIdioma rec = GesIdioma.obtener_instancia();
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	JTextField[] jtxt;
	JLabel[] jlbl;
	JDateChooser jdc1;
	int i;
	JLabel jlblfecha;
	JTextArea textarea = (new JTextArea(3,13));
	JButton jbtnaceptar, jbtncancelar;
	GpComboBox cbNombre = new GpComboBox();
	ResultSet rs;
	ConexionDb conexion = new ConexionDb();
	
	
	public PnlAlta_TimeSheet(){
		this.setLayout(new GridBagLayout());
		
		String[] fieldNames = {
				
				rec.idioma[rec.eleidioma][55],
				rec.idioma[rec.eleidioma][101],
				rec.idioma[rec.eleidioma][102],
				rec.idioma[rec.eleidioma][97]
				

		};
		
		int[] fieldWidths = {10,10,10,7};
		jtxt = new JTextField[fieldNames.length];
		jlbl = new JLabel[fieldNames.length];
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5,10,5,5);
		
		jdc1 = new JDateChooser();
	    jdc1.setDateFormatString("dd/MM/yyyy");
	    
	    
	    for(i = 0;i<fieldNames.length;i++){
	    	
	    	gbc.gridwidth = GridBagConstraints.RELATIVE;
			gbc.anchor = GridBagConstraints.EAST;
			this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			this.add(jtxt[i]=new JTextField(fieldWidths[i]),gbc);
	    	
			
			if(i==2){
				
				conexion.Conectardb();
				rs = conexion.ConsultaSQL("SELECT nombre FROM STAFF");
			    	try {
						while(rs.next()){	
								cbNombre.addItem(rs.getString(3));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	conexion.cerrarConexion();
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
				this.add(jlbl[i]=new JLabel(rec.idioma[rec.eleidioma][44]+"*"),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				cbNombre.setPreferredSize(new Dimension(177,30));
				this.add(cbNombre,gbc);
				
			}
			
			if(i==3){
			    gbc.gridwidth = GridBagConstraints.RELATIVE;
				gbc.anchor = GridBagConstraints.EAST;
				this.add(jlblfecha=new JLabel(rec.idioma[rec.eleidioma][95]),gbc);
				gbc.anchor = GridBagConstraints.WEST;
				gbc.gridwidth = GridBagConstraints.REMAINDER;
				this.add(jdc1,gbc);
				
				gbc.gridwidth = GridBagConstraints.RELATIVE;
		    	this.add(new JLabel(rec.idioma[rec.eleidioma][96]),gbc); 
		    	gbc.gridwidth = GridBagConstraints.REMAINDER;
		    
		    	LimiteDocumento lpd = new LimiteDocumento(200); // Limite JTextArea
		    	
		    	textarea.setDocument(lpd);
		    	textarea.setLineWrap(true);
		    	JScrollPane sp = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		    	JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    	this.add((sp),gbc);
				
			}
			
			
			
		}
	    gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(30,10,5,5);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		this.add(jbtnaceptar=new JButton(rec.idioma[rec.eleidioma][1]),gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		this.add(jbtncancelar=new JButton(rec.idioma[rec.eleidioma][2]),gbc);
	    this.setVisible(true);
	}
	
}
