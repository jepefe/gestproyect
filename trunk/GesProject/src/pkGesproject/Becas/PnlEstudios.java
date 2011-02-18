package pkGesproject.Becas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import pkGesproject.ConexionDb;
import pkGesproject.GesIdioma;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;

public class PnlEstudios extends JPanel{
	JPanel jpnl = new JPanel();
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	GesIdioma rec = GesIdioma.obtener_instancia();
	JCheckBox check[];
	JTextField txtNota;
	

	public PnlEstudios(){
		this.add(jpnl);
		crear_panel();
	}
	
	
	public void crear_panel(){
		jpnl.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(20,0,15,0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5,10,5,5);

		
		final String[] fieldNames = {
				rec.idioma[rec.eleidioma][159],rec.idioma[rec.eleidioma][160],
				rec.idioma[rec.eleidioma][161],rec.idioma[rec.eleidioma][56],
				rec.idioma[rec.eleidioma][163],rec.idioma[rec.eleidioma][164],
				rec.idioma[rec.eleidioma][165],rec.idioma[rec.eleidioma][166],
				rec.idioma[rec.eleidioma][167],rec.idioma[rec.eleidioma][168],
				rec.idioma[rec.eleidioma][169],rec.idioma[rec.eleidioma][170],};
		
		check = new JCheckBox[fieldNames.length];
		
		//int[] fieldWidths = {15,17,7,7,8};
		
		for(int  i = 0 ;i<fieldNames.length;++i) {
			gbc.gridwidth = GridBagConstraints.RELATIVE;					
			switch(i){
				case 0:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);	
					break;
				case 1:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 2:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					txtNota = new JTextField(new JTextFieldLimit(2), null, i);
					txtNota.setPreferredSize(new Dimension(40,30));
					jpnl.add(txtNota ,gbc);
					break;
				case 3:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);			
					break;
			/*
			if (){
					switch(i){
						case 0: 
							
							break;
						case 1:
							
							break;
						case 2:
							
							break;
						case 3:
							
							break;
						case 4:
							
							break;
					
					}	
					*/
					
			
				case 4:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 5:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 6:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 7:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 8:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 9:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 10:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 11:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
				case 12:
					jpnl.add(new JLabel(fieldNames[i]),gbc);
					gbc.gridwidth = GridBagConstraints.REMAINDER; 
					jpnl.add(check[i] = new JCheckBox(),gbc);
					break;
					
				}
			}
			
		
		jpnl.setVisible(true);
	}
}
