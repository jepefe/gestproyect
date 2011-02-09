package pkGesproject;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;




/**
 * 
 * @author Jesus Perez
 * Esta clase es un singleton que se encargara almacenar y gestionar la informacion
 * comun a diferentes clases de la aplicacion asi como la configuracion.
 * 
 */

public class RsGesproject {
	/**
	 * Constructor privado
	 */
	private RsGesproject(){} //Lo hacemos privado para que solo se pueda instanciar a si mismo
	/**Variable que consiene la unica instancia de la clase*/
	static RsGesproject instancia = new RsGesproject(); //Instancia unica de esta clase
	/**Variable que referencia al objeto de la ventana principal*/
	private FrmPrincipal Rfrmppal;  //Referencia a la ventana principal
	/**Alto de la ventana principal*/
	private int frmppalWidth = 1000; //Alto por defecto de la ventana principal
	/**Ancho de la ventana principal*/
	private int frmppalHeight = 750; //Ancho por defecto de la ventana principal
	final String DBSERVER = "93.189.94.177";
	final String DBUSER	  = "gesproyect";
	final String DBPASS	  = "proyecto2010";
	final String DBNAME	  = "gesproyect";
	final String SRVNAME = "aprendizdetodo.net";
	final String FTPSERVER = "93.189.94.177";
	final String FTPUSER = "gesproyect";
	final String FTPPASS = "proyecto2010";
	public int progresocarga = 0;
	public String txtcarga;
	public FrmLogin instanciafrmlogin;
	public int permisos;
	public int representante;
	/**String constante que contiene el titulo de la aplicaion*/
	public final String APNAME = "GesProyect - Florida"; //Constante que contiene el nombre de la aplicacion
	public  Image[] imagen = {
			new ImageIcon(getClass().getResource("imagenes/Splash.png")).getImage()
	};
	public ImageIcon[] icono = { 
			new ImageIcon(getClass().getResource("imagenes/staff.png")),
			new ImageIcon(getClass().getResource("imagenes/partner.png")),
			new ImageIcon(getClass().getResource("imagenes/inicio.png")),
			new ImageIcon(getClass().getResource("imagenes/idiomas.png")),
			new ImageIcon(getClass().getResource("imagenes/proyectos.png")),
			new ImageIcon(getClass().getResource("imagenes/nuevostaff.png")),
			new ImageIcon(getClass().getResource("imagenes/editarstaff.png")),
			new ImageIcon(getClass().getResource("imagenes/buscarstaff.png")),
			new ImageIcon(getClass().getResource("imagenes/workpack.png")),
			new ImageIcon(getClass().getResource("imagenes/tareas.png")),
			new ImageIcon(getClass().getResource("imagenes/agregar.png")),
			new ImageIcon(getClass().getResource("imagenes/tablas.png")),
			new ImageIcon(getClass().getResource("imagenes/timesheet.png")),
			new ImageIcon(getClass().getResource("imagenes/altapartner.png")),
			new ImageIcon(getClass().getResource("imagenes/editarpartner.png")),
			new ImageIcon(getClass().getResource("imagenes/mostrarpartner.png")),
			new ImageIcon(getClass().getResource("imagenes/altaproyecto.png")),
			new ImageIcon(getClass().getResource("imagenes/editarproyecto.png")),
			new ImageIcon(getClass().getResource("imagenes/mostrarproyecto.png")),
			new ImageIcon(getClass().getResource("imagenes/altawp.png")),
			new ImageIcon(getClass().getResource("imagenes/editarwp.png")),
			new ImageIcon(getClass().getResource("imagenes/verwp.png")),
			new ImageIcon(getClass().getResource("imagenes/altatarea.png")),
			new ImageIcon(getClass().getResource("imagenes/editartarea.png")),
			new ImageIcon(getClass().getResource("imagenes/mostrartarea.png")),
			new ImageIcon(getClass().getResource("imagenes/viaje.png")),
			new ImageIcon(getClass().getResource("imagenes/subcontrata.png")),
			new ImageIcon(getClass().getResource("imagenes/materiales.png")),
			new ImageIcon(getClass().getResource("imagenes/nuevots.png")),
			new ImageIcon(getClass().getResource("imagenes/editarts.png")),
			new ImageIcon(getClass().getResource("imagenes/mostrarts.png")),
			new ImageIcon(getClass().getResource("imagenes/informes.png")),
			new ImageIcon(getClass().getResource("imagenes/iconoinformes.png")),
			new ImageIcon(getClass().getResource("imagenes/preferencias.png")),
			new ImageIcon(getClass().getResource("imagenes/iconoidiomas.png")),
			};
	static String SistemaOp = System.getProperty("os.name"); //Nombre del S.O por si necesitamos condicionar codigo 
	
	private int idusuario;
	public Font f = new Font( "arial",0,14 );
	/**
	 * @return Referencia a la instancia de la clase
	 */
	public static RsGesproject Obtener_Instancia(){
		return instancia;
	}
	
	/**
	 * Clase encargada de definir el ancho de la ventana principal
	 * @param frmppalWidth - Integer con el ancho de la ventana principal
	 */
	public void setFrmppalWidth(int frmppalWidth) {
		this.frmppalWidth = frmppalWidth;
	}
	
	/**
	 * 
	 * @return Ancho de la ventana principal
	 */
	public int getFrmppalWidth() {
		return frmppalWidth;
	}
	
	/**
	 * Metodo para fijar el alto de la ventana principal
	 * @param frmppalHeight - Integer con el alto de la ventana principal
	 */
	public void setFrmppalHeight(int frmppalHeight) {
		this.frmppalHeight = frmppalHeight;
	}

	/**
	 * Metodo para obtener el alto de la ventana
	 * @return - Integer con el alto de la ventana
	 */
	public int getFrmppalHeight() {
		return frmppalHeight;
	}
	
	/**
	 * Metodo para fijar la referencia a la ventana principal
	 * @param rfrmppal
	 */
	public void setRfrmppal(FrmPrincipal rfrmppal) {
		Rfrmppal = rfrmppal;
	}
	
	/**
	 * Metodo para obtener la referencia a la ventana principal
	 * @return Referencia a la ventana principal
	 */

	public FrmPrincipal getRfrmppal() {
		return Rfrmppal;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdusuario() {
		return idusuario;
	}

	/**
	 * @param eleidioma the eleidioma to set
	 */

	
	
	

}
