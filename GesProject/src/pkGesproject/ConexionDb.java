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
	public ConexionDbUnica cdb = ConexionDbUnica.instancia;
//	RsGesproject recursos = RsGesproject.Obtener_Instancia();
//	public Connection conexion;
//	Statement st;

	public void Conectardb(){
	/*	
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
		try {
			st = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
	}
		
	
	*/
		   System.out.println("Se ABRE conexión");
	}
	public void cerrarConexion(){
		/*try {
            st.close();
            conexion.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
        System.out.println("Se CIERRA conexión");
	}
	
	public ResultSet ConsultaSQL(String consultaSQL){
		/*ResultSet rs=null;
		
		try {
			rs = st.executeQuery(consultaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No se ha podido realizar la consulta: " + consultaSQL);
		}*/
		return cdb.ConsultaSQL(consultaSQL);
		
	}
	
	public String executeUpdate(String consultaSQL){
		/*try {
			st.executeUpdate(consultaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return cdb.executeUpdate(consultaSQL);
	}
	
	
}


