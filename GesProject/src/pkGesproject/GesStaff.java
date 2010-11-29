package pkGesproject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Jesus Perez
 *Clase para gestionar altas, bajas y modificaciones de usuarios
 */
public class GesStaff {
	private int id_staff;
	private String dni;
	private String categoria;
	private boolean representante;
	private String f_nac;
	private String pais;
	private String region;
	private String ciudad;
	private String direccion;
	private String codpostal;
	private String telefono;
	private String foto;
	private String nick;
	private String password;
	private String permisos;
	private int cod_part;
	
	/**
	 * Metodo para crear un nuevo staff
	 * @param id_staff
	 * @param dni
	 * @param categoria
	 * @param representante
	 * @param f_nac
	 * @param pais
	 * @param region
	 * @param ciudad
	 * @param direccion
	 * @param codpostal
	 * @param telefono
	 * @param foto
	 * @param nick
	 * @param password
	 * @param permisos
	 * @param cod_part
	 */
	
	
	
	public void CrearStaff(int id_staff, String dni, String categoria, boolean representante, String f_nac,
			String pais, String region, String ciudad, String direccion, String codpostal, String telefono, 
			String foto, String nick, String password, String permisos, int cod_part){
		ConexionDb conex = new ConexionDb();
		ResultSet rs;
		conex.Conectardb();
		conex.executeUpdate("INSERT INTO STAFF (dni,nombre,categoria,representante,f_nac,pais" +
				",region,ciudad,direccion,codpostal,telefono,foto,nick_usuario,contraseña,permisos,cod_part) " +
				"VALUES ('"+"','" + dni + "','" + categoria + "','"
				+ Boolean.toString(representante)+ "','" + f_nac + "','" + pais + region + ciudad 
				+ direccion + codpostal + telefono + foto + nick + "','" + password + "','" 
				+ permisos + "','" + Integer.toString(cod_part)+ "')");
		
		conex.cerrarConexion();
		conex = null;
		
	}
	/**
	 * Metodo para eliminar el registro de determinado staff
	 * @param id_staff ID de staff a eliminar
	 * @return False si no ha podido ser eliminado, true si se ha eliminado
	 */
	public boolean EliminarStaff(int id_staff){
		ConexionDb conex = new ConexionDb();
		ResultSet rs;
		boolean eliminado=false;
		conex.executeUpdate("DELETE FROM STAFF WHERE id_staff='"+Integer.toString(id_staff)+"'");
		rs = conex.ConsultaSQL("SELECT id_staff FROM STAFF WHERE id_staff='"+Integer.toString(id_staff)+"'");
		try {
			rs.next();
		if(rs.getInt(1) == id_staff){
			eliminado=false;
		}else{
			eliminado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eliminado;
	}
	
	
	
	
}
