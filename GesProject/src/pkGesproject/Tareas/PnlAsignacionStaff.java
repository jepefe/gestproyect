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
			JButton btnAsignar = new JButton("Asignar");
			GpComboBox cmbProyecto = new GpComboBox();
			
			
			
			public PnlAsignacionStaff(){
				
				CrearInterfaz();
			
				asignacion = new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent arg0) {
					
					}
				
				};//fin event
				
				
				
				
				
				
				   
					/**
					 *Creacion del JComboBox y a�adir los items.
					 *Se conecta a la BD para realizar la consulta
					 **/
				   
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
						
		//--------------------------------------------------------
						this.setLayout(new GridBagLayout());
						this.add(btnAsignar);
				
						btnAsignar.setActionCommand("Asignar");
						
				//gbc.gridwidth = GridBagConstraints.REMAINDER;
				btnAsignar.addActionListener(asignacion);
				this.setVisible(true);
			}
			
			public void CrearInterfaz(){
				
				GridBagConstraints gbc = new GridBagConstraints();
				
				String[] fieldNames = {rec.idioma[rec.eleidioma][178]};
				jlbl = new JLabel[fieldNames.length];
				
				int i=0;
				this.add(jlbl[i]=new JLabel(fieldNames[i]),gbc);
				this.add(cmbProyecto,gbc);
				cmbProyecto.setPreferredSize(new Dimension(140,30));
				
				
				jlblWp = new JLabel[fieldNames.length];
				this.add(jlblWp[i]=new JLabel("Work Packages"),gbc);
				this.add(cmbStaff= new GpComboBox(),gbc);
				cmbStaff.setPreferredSize(new Dimension(140,30));
				
				jlblTasks = new JLabel[fieldNames.length];
				this.add(jlblTasks[i]=new JLabel("Tareas"),gbc);
				this.add(cmbTasks= new GpComboBox(),gbc);
				cmbTasks.setPreferredSize(new Dimension(140,30));
				
				
			}
	
}

	