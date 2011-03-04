package pkGesproject;

import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GesIdioma {

	public static String idioma[][];
	String listaidiomas[]= new String[20],columna;
	public int eleidioma=0,idiomadescarga=0;
	int i, j;
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
	private GesIdioma(){
		
		
			
	}
	
	static GesIdioma instancia = new GesIdioma();
	public static GesIdioma obtener_instancia(){
		return instancia;
	}
	
	

	public void inicializar(){
		String linea,prue;
		
	
		int cont=0,i,j,tam = 0;
		
			conexion.Conectardb();
			System.out.println("ID DEL USUARIO:    "+recursos.getIdusuario());
			rs = conexion.ConsultaSQL("SELECT idioma FROM STAFF WHERE id_staff = "+recursos.getIdusuario());
			try {
				rs.next();
				idiomadescarga = rs.getInt(1);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			rs = conexion.ConsultaSQL("SELECT COUNT(*) FROM IDIOMA");
			try {
				rs.next();
				tam = rs.getInt(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			idioma = new String[1][tam+1];
			
			rs= conexion.ConsultaSQL("SELECT columna FROM LISTA_IDIOMAS WHERE id_idioma = "+idiomadescarga);
			try {
				rs.next();
				columna = rs.getString(1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			rs = conexion.ConsultaSQL("SELECT "+columna+" FROM IDIOMA ORDER BY id_idi");
	    	i=1;
	    	try {
				while(rs.next()){
					
					idioma[0][i] = rs.getString(1);
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conexion.cerrarConexion();
	}
	/**
	 * @param eleidioma the eleidioma to set
	 */
}
