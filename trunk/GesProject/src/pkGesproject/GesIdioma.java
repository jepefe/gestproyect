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

	static String idioma[][] = new String[2][100];
	String listaidiomas[]= new String[20];
	int eleidioma,i,j;
	ConexionDb conexion = new ConexionDb();
	ResultSet rs;
	
	private GesIdioma(){
		
		File f = new File("src/pkGesproject/idioma.data");
		Scanner s;
		String linea,prue;
	
		int cont=0,i,j;
		
		
		
			conexion.Conectardb();
			rs = conexion.ConsultaSQL("SELECT castellano,ingles FROM IDIOMA");
	    	i=1;
	    	try {
				while(rs.next()){
					for(j = 1;j<3;j++){
						idioma[j-1][i] = rs.getString(j);
					}
					i++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	conexion.cerrarConexion();
			
	    	
	    	/*
			s = new Scanner(f);
			
			//cortamos la cadena por punto y coma
			i =0;
			j=0;
			while(s.hasNextLine()){
				linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[;]");
				while(sl.hasNext()){
					idioma[j][i] = sl.next();
					i++;
				}
				j++;
				i=0;
				
				//System.out.println("\n");
				//System.out.println(linea);
			}
			s.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	*/
		
		
		/*
		ConexionDb conexion = new ConexionDb();
		conexion.Conectardb();
		
		for(int a =0;a<55;a++){
			conexion.executeUpdate("INSERT INTO IDIOMA(castellano,ingles) VALUES('"+idioma[0][a]+"','"+idioma[1][a]+"')");
		}
		*/
		
		
		
		
		
		
		
	}
	
	static GesIdioma instancia = new GesIdioma();
	public static GesIdioma obtener_instancia(){
		return instancia;
	}
	
	public static void eleccionidioma(){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		Object seleccion = JOptionPane.showInputDialog(null, "Choose language",
				"Language", JOptionPane.QUESTION_MESSAGE, recursos.icono[3], new Object[] {"Espa–ol", "English"}, "Espa–ol");
		/*
		if(seleccion.equals("Espa–ol")){
			recursos.setEleidioma(0);
		}else
			recursos.setEleidioma(1);	
		*/	
	}
	
	public static void nuevo_idioma(){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		int i=0;
		int j=0;
		
		try
		{
			fichero = new FileWriter("src/pkGesproject/idioma.data");
			pw = new PrintWriter(fichero);
			
			while(idioma[j][i]!=null){
				while(idioma[j][i]!=null){
					
					pw.print(idioma[j][i]+";");
					i++;
				}
				pw.print("\n");
				j++;
				i=0;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(null != fichero);
					fichero.close();
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @param eleidioma the eleidioma to set
	 */
}
