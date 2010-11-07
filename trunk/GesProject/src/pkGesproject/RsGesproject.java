package pkGesproject;

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
	static RsGesproject instancia = new RsGesproject();
	private int frmppalWidth = 800; //Alto por defecto de la ventana principal
	private int frmppalHeight = 600; //Ancho por defecto de la ventana principal
	private FrmPrincipal Rfrmppal;  //Referencia a la ventana principal
	

	
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
	
	
	

}
