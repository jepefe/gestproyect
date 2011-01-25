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
	GpComboBox cbTareas = new GpComboBox();
	ResultSet rs;
	ConexionDb conexion = new ConexionDb();
	
	
	public PnlAlta_TimeSheet(){
		
	}
	
}
