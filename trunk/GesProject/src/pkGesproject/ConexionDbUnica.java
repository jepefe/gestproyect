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
public class ConexionDbUnica {
	RsGesproject recursos = RsGesproject.Obtener_Instancia();
	public Connection conexion;
	Statement st;
	static ConexionDbUnica instancia = new ConexionDbUnica();
	public int timeout;
	boolean conectado = false;
	Thread hmantenerviva = null;
	private ConexionDbUnica(){
		//this.Conectardb();
	
	}
	
	public void Conectardb(){
		
	MysqlDataSource dataSource = new MysqlDataSource();
	dataSource.setUser(recursos.DBUSER);
	dataSource.setPassword(recursos.DBPASS);
	dataSource.setDatabaseName(recursos.DBNAME);
	dataSource.setServerName(recursos.DBSERVER);
	try {
		conexion = dataSource.getConnection();
		conectado = true;
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
		mantenerviva();
	
	
	}
	
	
	private void mantenerviva(){
		if(hmantenerviva == null){
		hmantenerviva = new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					if (timeout >= 180000){  //Si la conexion lleva tres minutos inactiva la cerramos
						cerrarConexion();
					//	System.out.println("Conexion cerrada por inactividad");
						conectado=false;
					}
				try {
					Thread.sleep(1000);
					incrementatimeout();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
			}
			
		});
	hmantenerviva.start();
		}
	}
	
	
	
	private synchronized void setTimeout(int time){
		if (time != 0){
			if(this.timeout < 180000){
			this.timeout += time;
			}
		}else{
		this.timeout = time;
		}
	}
	
	public void reseteatimeout(){
		if(!conectado){
			Conectardb();
			conectado = true;
		}

		setTimeout(0);
		
	}
	public void incrementatimeout(){
		setTimeout(1000);
	}
	
	public void cerrarConexion(){
		try {
            st.close();
            conexion.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
	}
	
	public synchronized ResultSet ConsultaSQL(String consultaSQL){
		ResultSet rs=null;
		 reseteatimeout();

		try {
			rs = st.executeQuery(consultaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No se ha podido realizar la consulta: " + consultaSQL);
		}
		return rs;
		
	}
	
	public String executeUpdate(String consultaSQL){
		ResultSet rs=null;
		String autoid = null;
		 reseteatimeout();

		try {
			st.executeUpdate(consultaSQL, Statement.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs.next()){
				autoid = Integer.toString(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autoid;
	}
	
	public void ejecutaAlter(String consultaSQL){
	  reseteatimeout();

		try {
			st.executeUpdate(consultaSQL);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}


