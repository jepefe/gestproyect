package pkGesproject;

import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GesIdioma {

	static String idioma[][] = new String[2][100];
	String listaidiomas[]= new String[20];
	int eleccion;
	
	public GesIdioma(){
		
		File f = new File("src/pkGesproject/idioma.data");
		Scanner s;
		String linea,prue;
	
		int cont=0,i,j;
		//Contacto[] persona = new Contacto[100];
		
		try{
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
		
		/*
		idioma[0][0]= "Aceptar";
		idioma[1][0]= "Accept";
			
		idioma[0][1]="Cancelar";
		idioma[1][1]="Cancel";
		
		idioma[0][2]="Nombre";
		idioma[1][2]="Name";
		
		idioma[0][3]="Sector";
		idioma[1][3]="Sector";
		
		idioma[0][4]="Número de teléfono";
		idioma[1][4]="Phone number";
		
		idioma[0][5]="BIENVENIDOS!!";
		idioma[1][5]="WELCOME!!";
		
		idioma[0][6]="Dirección";
		idioma[1][6]="Address";
		
		idioma[0][7]="Codigo Postal";
		idioma[1][7]="Postal Code";
		
		idioma[0][8]="Correo electrónico";
		idioma[1][8]="Email Address";
		*/
	}
	
	public static void eleccionidioma(){
		RsGesproject recursos = RsGesproject.Obtener_Instancia();
		Object seleccion = JOptionPane.showInputDialog(null, "Choose language",
				"Language", JOptionPane.QUESTION_MESSAGE, recursos.icono[3], new Object[] {"Español", "English"}, "Español");
		
		if(seleccion.equals("Español")){
			recursos.eleidioma = 0;
		}else
			recursos.eleidioma = 1;	
			
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
}
