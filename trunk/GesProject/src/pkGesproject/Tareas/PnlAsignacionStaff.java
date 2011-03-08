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

	GesIdioma rec = GesIdioma.obtener_instancia();
	//9637

	String ruta;
	JLabel[] jlbl,jlblWp,jlblTasks;
	String id_p = null;
	ResultSet rp;
	String[] projects = new String[200];
	ActionListener asignacion;
	ActionListener importar;
	JFileChooser filechooser;
	ConexionFTP cftp=new ConexionFTP();
	ConexionDb conexion= new ConexionDb();
	HSSFWorkbook tuWorkBook = new HSSFWorkbook();
	JButton btnAsignar;
	GpComboBox cmbProyecto = new GpComboBox();
	GridBagConstraints gbc;
	String[] columnstaff = {"Staff","Apellidos"},columnrolstaff = {"Staff","Categoria"};
	String[][] staff,rolstaff;
	JTable jtblstaff = new JTable(),jtblrolstaff= new JTable();
	DefaultTableModel modelstaff = new DefaultTableModel(null,columnstaff);
	DefaultTableModel modelcate = new DefaultTableModel(null,columnrolstaff);
	
	public PnlAsignacionStaff(){

		CrearInterfaz();

		asignacion = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}

		};

		
		btnAsignar.setActionCommand("Asignar");
		btnAsignar.addActionListener(asignacion);
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

		
		gbc.gridx = 2; // El área de texto empieza en la columna
		jlblWp = new JLabel[fieldNames.length];
		this.add(jlblWp[i]=new JLabel("Work Packages"),gbc);
		gbc.gridx = 3; // El área de texto empieza en la columna
		this.add(cmbStaff= new GpComboBox(),gbc);
		cmbStaff.setPreferredSize(new Dimension(140,30));

		gbc.gridx = 4; // El área de texto empieza en la columna
		jlblTasks = new JLabel[fieldNames.length];
		this.add(jlblTasks[i]=new JLabel("Tareas"),gbc);
		gbc.gridx = 5; // El área de texto empieza en la columna
		this.add(cmbTasks= new GpComboBox(),gbc);
		cmbTasks.setPreferredSize(new Dimension(140,30));
		
		gbc.gridx = 6; // El área de texto empieza en la columna
		this.add(btnAsignar = new JButton("Asignar"),gbc);
		
		
		gbc.gridx = 0; // El área de texto empieza en la columna
		gbc.gridy = 1; // El área de texto empieza en la fila
		gbc.gridwidth = 2; // El área de texto ocupa x columnas.
		gbc.gridheight = 1; // El área de texto ocupa x filas.
		this.add(jtblstaff,gbc);
		
		conexion.Conectardb();
		rp = conexion.ConsultaSQL("SELECT nombre,id_pro FROM PROYECTOS ORDER BY nombre");

		int j=0;
		try {

			while(rp.next()){

				projects[j]= Integer.toString(rp.getInt(2));

				cmbProyecto.addItem(rp.getString(1));
				j++;
				cmbProyecto.setSelectedItem(null);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void crear_tabla(){
		conexion.Conectardb();
		rp = conexion.ConsultaSQL("SELECT COUNT(*) FROM STAFF ORDER BY nombre");
		try {
			rp.next();
			
			staff = new String[columnstaff.length][];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rp = conexion.ConsultaSQL("SELECT nombre,apellidos FROM STAFF ORDER BY nombre");
		for(int i =0;i<)
		
		//jtblstaff
	}

}

