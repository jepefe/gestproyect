package pkGesproject.Tareas;

import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import pkGesproject.ConexionDb;
import pkGesproject.ConexionFTP;
import pkGesproject.GesIdioma;
import pkGesproject.GesStaff;
import pkGesproject.GpComboBox;
import pkGesproject.JTextFieldLimit;
import pkGesproject.LimitadorDeDocumento;
import pkGesproject.LimiteDocumento;
import pkGesproject.RsGesproject;
import pkGesproject.Socios.PnlModificarsocio;

//import clases.Principal;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import javax.swing.*;

/**
 * Clase de utilidad para crear formularios.
 */



public class PnlAsignacionStaff extends JPanel{

	GpComboBox cmbProyectoyecto,cmbWp,cmbTasks,cmbStaff;

	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	GesIdioma rec = GesIdioma.obtener_instancia();
	//9637

	String ruta;
	JLabel[] jlbl,jlblWp,jlblTasks;
	String id_p = null;
	ResultSet rp;
	int[] projects = new int[200];
	ActionListener asignacion;
	ActionListener importar;
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	HSSFWorkbook tuWorkBook = new HSSFWorkbook();
	JButton btnAsignar,btnQuitar;
	GpComboBox cmbProyecto = new GpComboBox();
	GridBagConstraints gbc;
	String[] columnstaff = {"Nombre","Apellidos"},columnrolstaff = {"Nombre","Apellidos","Categoria"};
	String[][] staff,rolstaff;
	JTable jtblstaff = new JTable(),jtblrolstaff= new JTable();
	DefaultTableModel modelstaff = new DefaultTableModel(null,columnstaff);
	DefaultTableModel modelcate = new DefaultTableModel(null,columnrolstaff);
	JScrollPane tablastaff,tablarolstaff;
	int regstaff=0;
	public PnlAsignacionStaff(){

		crear_tabla();
		
		CrearInterfaz();

		
		asignacion = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargar_tabla();
			}

		};

		
		cmbProyecto.addActionListener(asignacion);
		this.setVisible(true);
	}

	public void CrearInterfaz(){
		this.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();

		String[] fieldNames = {rec.idioma[rec.eleidioma][178]};
		jlbl = new JLabel[fieldNames.length];

		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 0; // El área de texto empieza en la fila
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		//gbc.insets = new Insets(20,30,0,10);
		int i=0;
		this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
		gbc.gridx = 1; // El área de texto empieza en la columna
		this.add(cmbProyecto,gbc);
		cmbProyecto.setPreferredSize(new Dimension(140,30));

		
		gbc.gridx = 3; // El área de texto empieza en la columna
		jlblWp = new JLabel[fieldNames.length];
		this.add(jlblWp[i]=new JLabel("Work Packages"),gbc);
		gbc.gridx = 4; // El área de texto empieza en la columna
		this.add(cmbStaff= new GpComboBox(),gbc);
		cmbStaff.setPreferredSize(new Dimension(140,30));

		gbc.gridx = 5; // El área de texto empieza en la columna
		jlblTasks = new JLabel[fieldNames.length];
		this.add(jlblTasks[i]=new JLabel("Tareas"),gbc);
		gbc.gridx = 6; // El área de texto empieza en la columna
		this.add(cmbTasks= new GpComboBox(),gbc);
		cmbTasks.setPreferredSize(new Dimension(140,30));
		
		gbc.gridx = 2; // El área de texto empieza en la columna
		gbc.gridy = 1; // El área de texto empieza en la fila
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		gbc.anchor = GridBagConstraints.SOUTH; 
		gbc.insets = new Insets(130,0,0,0);
		this.add(btnAsignar = new JButton(">"),gbc);
		gbc.insets = new Insets(0,0,0,0);
		
		gbc.gridx = 2; // El área de texto empieza en la columna
		gbc.gridy = 2; // El área de texto empieza en la fila
		gbc.gridwidth = 1; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		gbc.anchor = GridBagConstraints.NORTH; 
		this.add(btnQuitar = new JButton("<"),gbc);
		
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 1; // El área de texto empieza en la fila
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		gbc.gridheight = 2; // El área de texto ocupa x filas.
		gbc.anchor = GridBagConstraints.CENTER; 
		this.add(tablastaff = new JScrollPane(jtblstaff),gbc);
		
		gbc.gridx = 3; // El área de texto empieza en la columna
		gbc.gridwidth = 4; // El área de texto ocupa x columnas.
		gbc.anchor = GridBagConstraints.CENTER; 
		this.add(tablarolstaff = new JScrollPane(jtblrolstaff),gbc);
		
		conexion.Conectardb();
		rp = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS ORDER BY nombre");
		
		int j=0;
		try {

			while(rp.next()){

				projects[j]= rp.getInt(2);

				cmbProyecto.addItem(rp.getString(1));
				j++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbProyecto.setSelectedItem(null);

	}
	
	public void crear_tabla(){
		
		modelstaff = new DefaultTableModel(staff,columnstaff);
		jtblstaff = new JTable(modelstaff){
	   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	              }
	    };
		
	    jtblstaff.setPreferredScrollableViewportSize(new Dimension(220,300));
	    
	    
	    modelcate = new DefaultTableModel(rolstaff,columnrolstaff);
	    jtblrolstaff = new JTable(modelcate){
	   		 public boolean isCellEditable(int rowIndex, int mColIndex) {
	                return false;
	              }
	    };
	    jtblrolstaff.setPreferredScrollableViewportSize(new Dimension(420,300));
	}
	
	public void cargar_tabla(){
		conexion.Conectardb();
		rp = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF WHERE cod_part = "+recursos.getCodparter()+" ORDER BY nombre");
		try {
			rp.next();
			regstaff = rp.getInt(1);
			staff = new String[regstaff][columnstaff.length];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rp = conexion.ConsultaSQL("SELECT nombre,apellidos FROM STAFF WHERE cod_part = "+recursos.getCodparter()+" ORDER BY nombre");
		for(int i =0;i< regstaff;i++){
			try {
				rp.next();
				for(int j =0;j<columnstaff.length;j++){
					staff[i][j] = rp.getString(j+1);
					System.out.print(staff[i][j]+";");
				}
				System.out.println();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		modelstaff = new DefaultTableModel(staff,columnstaff);
		jtblstaff.setModel(modelstaff);
	}

}

