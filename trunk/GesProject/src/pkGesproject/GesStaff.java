package pkGesproject;

import java.io.File;
import java.io.IOException;
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
	 * @return 0: Usuario creado, 1:Error sin determinar 2:el nick ya existe
	 */
	
	
	
	public int CrearStaff(String nombre, String apellidos, String categoria, String representante, String f_nac,
			String pais, String provincia, String region, String ciudad, String direccion, String codpostal, String telefono, String telefono2,
			String fax, String email,String foto, String nick, String password, String permisos, String cod_part, String observaciones){
		
		int creado = 0;
		ConexionDb conex = new ConexionDb();
		ResultSet rs;
		conex.Conectardb();
		
		//Comprobamos que no exista ya el nick para evitar problemas con la FTP
		//La variable "creado" tendra los siguientes valores:
		//0: Usuario creado, 1:Error sin determinar 2:el nick ya existe
		rs = conex.ConsultaSQL("Select nick_usuario FROM STAFF WHERE nick_usuario='" + nick + "'");
		if (rs!= null){
			try {
				rs.next();
				if (rs.getString(1).contentEquals(nick)){
					creado=2;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		if (creado==0){		
		conex.executeUpdate("INSERT INTO STAFF (nombre,apellidos,categoria,representante,f_nac,pais, provincia" +
				",region,ciudad,direccion,codpostal,telefono,telefono2,fax,nick_usuario,password,email,permisos,cod_part,observaciones) " +
				"VALUES ('" + nombre + "','"+ apellidos + "','" + categoria + "','"
				+ representante+ "','" + f_nac + "','" + pais + "','" + provincia + "','" + region + "','" + ciudad + "','"
				+ direccion + "','" + codpostal + "','" + telefono + "','" + telefono2 + "','" + fax + "','" + nick + 
				"','" + password  + "','" + email + "','" + permisos + "','" + cod_part + "','" + observaciones +"')");
		}
		
		rs = conex.ConsultaSQL("Select nick_usuario,id_staff FROM STAFF WHERE nick_usuario='" + nick + "'");
		try {
			rs.next();
			if (rs.getString(1).contentEquals(nick)){
				id_staff = rs.getInt(2);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SubirFoto(foto,id_staff);
				
		
		conex.cerrarConexion();
		conex = null;
		return creado;
		
		
	}
	
	
	public void SubirFoto(String foto,int id){
		File f_foto = new File(foto);
		ConexionFTP ftp = new ConexionFTP();
		try {
			ftp.connectar();
			ftp.bin();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ftp.stor(f_foto, "fto"+Integer.toString(id));
			ftp.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
