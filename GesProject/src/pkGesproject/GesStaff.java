package pkGesproject;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.XMPPException;

import pkGesproject.Staff.PnlModificacion_Staff;

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
	private String nom_part;
	static RsGesproject recursos = RsGesproject.Obtener_Instancia();
	
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
	
	
	
	public int CrearStaff(String nombre, String apellidos, String idioma, String representante, String f_nac,
			String pais, String provincia, String region, String ciudad, String direccion, String codpostal, String telefono, String telefono2,
			String fax, String email,String foto, String nick, String password, String permisos, String cod_part, String observaciones){
		
		int creado = 0;
		boolean subido=false;
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
		conex.executeUpdate("INSERT INTO STAFF (nombre,apellidos,idioma,representante,f_nac,pais, provincia" +
				",region,ciudad,direccion,codpostal,telefono,telefono2,fax,nick_usuario,password,email,permisos,cod_part,observaciones) " +
				"VALUES ('" + nombre + "','"+ apellidos + "','" + idioma + "','"
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
		//CREAMOS USUARIO EN SERVIDOR JABBER
		
		
		rs = conex.ConsultaSQL("Select nombre FROM PARTNER WHERE cod_part='" + cod_part+ "'");
		try {
			if (rs.next()){
				nom_part = rs.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AccountManager am =recursos.instanciamsg.connection.getAccountManager();
		try {
			am.createAccount(nick, password);
			
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conex.executeUpdate("INSERT INTO ofGroupUser(groupName,username,administrator)values('"+nom_part+"','"+nick+"','0')");

		
		
		if (creado == 0){ //Solo subimos la imagen si el staff es creado
		subido = SubirFoto(foto,id_staff);
		if (subido){
			conex.executeUpdate("INSERT INTO FICHEROS (id_propietario,nombre,ambito,descripcion) VALUES('"
					+ Integer.toString(id_staff) + "','" + "fto"+Integer.toString(id_staff)+ ".jpg" + "','0','Foto Usuario')");
		
		}
		}
		
		conex.cerrarConexion();
		conex = null;
		return creado;
		
		
	}
	
	
	
	
	public int ModificarStaff(String nombre, String apellidos, String idioma, String representante, String f_nac,
			String pais, String provincia, String region, String ciudad, String direccion, String codpostal, String telefono, String telefono2,
			String fax, String email,String foto,String password, String permisos, String cod_part, String observaciones, int ids){
		
		int creado = 0;
		boolean subido=false;
		ConexionDb conex = new ConexionDb();
		ResultSet rs;
		conex.Conectardb();
		
		//Comprobamos que no exista ya el nick para evitar problemas con la FTP
		//La variable "creado" tendra los siguientes valores:
		//0: Usuario creado, 1:Error sin determinar 2:el nick ya existe
		System.out.println("UPDATE STAFF SET nombre='"+nombre+"', apellidos = '"+apellidos+"',idioma ='"+idioma+"', representante = "+
				representante+", f_nac ='"+f_nac+"', pais = '"+pais+"', provincia = '"+provincia+"', region ='"+
				region+"', ciudad = '"+ciudad+"', direccion = '"+direccion+"', codpostal = '"+codpostal+"', telefono = '"+
				telefono+"', telefono2 = '"+telefono2+"', fax = '"+fax+"', password ='"+password+"', email = '"+email+
				"', permisos='"+permisos+"', cod_part = ' "+cod_part+"', observaciones = '"+observaciones+"' WHERE id_staff ="+Integer.toString(ids));
		
		conex.executeUpdate("UPDATE STAFF SET nombre='"+nombre+"', apellidos = '"+apellidos+"',idioma ='"+idioma+"', representante = "+
				representante+", f_nac ='"+f_nac+"', pais = '"+pais+"', provincia = '"+provincia+"', region ='"+
				region+"', ciudad = '"+ciudad+"', direccion = '"+direccion+"', codpostal = '"+codpostal+"', telefono = '"+
				telefono+"', telefono2 = '"+telefono2+"', fax = '"+fax+"', password ='"+password+"', email = '"+email+
				"', permisos='"+permisos+"', cod_part = ' "+cod_part+"', observaciones = '"+observaciones+"' WHERE id_staff ="+Integer.toString(ids));
		
		
		
				
		
		
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
		//CREAMOS USUARIO EN SERVIDOR JABBER
		
		
		rs = conex.ConsultaSQL("Select nombre FROM PARTNER WHERE cod_part='" + cod_part+ "'");
		try {
			if (rs.next()){
				nom_part = rs.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		

				
		if (!foto.equals("")){ //Solo subimos la imagen si el staff es creado
		subido = SubirFoto(foto,id_staff);
		
		}
		
		conex.cerrarConexion();
		conex = null;
		PnlModificacion_Staff.actualizar_tabla();
		return creado;
		
		
	}
	
	
	
	
	
	
	
	
	public boolean SubirFoto(String foto,int id){
		File f_foto = new File(foto);
		ConexionFTP ftp = new ConexionFTP();
		boolean subido=false;
		if (foto.equals("0")){
			subido = true;
			
		}else{
		try {
			ftp.connectar();
			ftp.bin();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			subido = ftp.stor(f_foto, "fto"+Integer.toString(id));
			ftp.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return subido;
		

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
	
	
	public static ResultSet allowedWP(){
		
		ResultSet rs;
		ConexionDbUnica cdbu = ConexionDbUnica.instancia;
		switch(recursos.representante){
		//Devolvemos todos los WP en los que staff tiene una tarea asignada
		case 0: rs = cdbu.ConsultaSQL("SELECT * FROM WORKPAQUETS  WHERE id_wp IN (SELECT id_wp FROM TAREAS WHERE id_task IN (SELECT id_task	FROM STAFF_TAREAS WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +"))");
		return rs;
		case 1:
			//Devolvemos todos los WP del partner
			rs = cdbu.ConsultaSQL("SELECT * FROM WORKPAQUETS  WHERE id_wp IN (SELECT id_wp FROM PARTNER_WORKPAQUETS WHERE cod_part = (SELECT cod_part FROM STAFF WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +"))");	
	}
		rs = cdbu.ConsultaSQL("SELECT * FROM WORKPAQUETS");
		return rs;
	
}
	public static ResultSet allowedTask(){
		
		ResultSet rs;
		ConexionDbUnica cdbu = ConexionDbUnica.instancia;
		switch(recursos.representante){
		//Devolvemos todos las tareas de un usuario
		case 0: 
			rs = cdbu.ConsultaSQL("SELECT * FROM TAREAS WHERE id_task IN (SELECT id_task FROM STAFF_TAREAS WHERE id_staff = " + Integer.toString(recursos.getIdusuario()) +"))");
		return rs;
		case 1:
			//Devolvemos todos los WP del partner
				rs = cdbu.ConsultaSQL("SELECT * FROM TAREAS  WHERE id_task IN (SELECT id_task FROM STAFF_TAREAS WHERE id_staff = " + Integer.toString(recursos.getIdusuario()) +"))");	
	}
	
		return null;
		
	}
	
	
	public static boolean esRepresentante(){
		boolean rep = false;
		if(recursos.representante == 0){
			rep = false;
		}
			else{
				rep = true;
			}
		return rep;

	}
public static ResultSet allowedProyects(){
		
		ResultSet rs = null;
		ConexionDbUnica cdbu = ConexionDbUnica.instancia;
		switch(recursos.representante){
		//Devolvemos todos los WP en los que staff tiene una tarea asignada
		case 0: rs = cdbu.ConsultaSQL("SELECT * FROM PROYECTOS WHERE id_pro IN (SELECT id_pro FROM WORKPAQUETS  WHERE id_wp IN (SELECT id_wp FROM TAREAS WHERE id_task IN (SELECT id_task	FROM STAFF_TAREAS WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +")))");
		break;
		case 1:
			//Devolvemos todos los WP del partner
			rs = cdbu.ConsultaSQL("SELECT * FROM PROYECTOS WHERE id_pro IN (SELECT id_pro FROM PARTNER_PROYECTOS WHERE cod_part = (SELECT cod_part FROM STAFF WHERE id_staff =" + Integer.toString(recursos.getIdusuario()) +"))");	
		break;
		}
		//rs = cdbu.ConsultaSQL("SELECT * FROM WORKPAQUETS");
		return rs;
	
}
	
	
}


