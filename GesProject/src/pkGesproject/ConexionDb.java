package pkGesproject;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Esta Clase maneja las conexiones a la base de datos
 * @author jesus
 *
 */
public class ConexionDb {
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	Connection conexion;
	Statement estatuto;
	public void Conectardb(){
		
	MysqlDataSource dataSource = new MysqlDataSource();
	dataSource.setUser(recursos.DBUSER);
	dataSource.setPassword(recursos.DBPASS);
	dataSource.setDatabaseName(recursos.DBNAME);
	dataSource.setServerName(recursos.DBSERVER);
	try {
		conexion = dataSource.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
	}
	public ResultSet ConsultaSQL(String consultaSQL){
		ResultSet rs=null;
		try {
			estatuto = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = estatuto.executeQuery(consultaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No se ha podido realizar la consulta: " + consultaSQL);
		}
		return rs;
		
	}
	

}
