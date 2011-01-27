package Pruebas_POI;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import pkGesproject.ConexionDb;
import pkGesproject.Tareas.PnlAltatarea;


public class escribir_excel extends PnlAltatarea{
	public static ConexionDb conexion = new ConexionDb();
	public static ResultSet rs;

	/**
     * Crea una hoja Excel y la guarda.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Se crea el libro
        HSSFWorkbook libro = new HSSFWorkbook();
        
        //obtener el numero de celda en la que se va a escribir
        protected void updateCellNum(short)
        // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet("Staff");

        // Se crea una fila dentro de la hoja
        HSSFRow fila = hoja.createRow(0);

        // Se crea una celda dentro de la fila
        HSSFCell celda = fila.createCell((short) 0);

        // Se crea el contenido de la celda y se mete en ella.
        HSSFRichTextString texto = new HSSFRichTextString("Primera celda");
        //rs = conexion.ConsultaSQL("SELECT id_wp FROM WORKPAQUETS W WHERE W.nombre like'"+ CmbWp.getSelectedItem().toString()+"'" );
        celda.setCellValue(texto);
        
        HSSFCell celda2 = fila.createCell((short) 4);
        
        HSSFRichTextString texto2 = new HSSFRichTextString("Cuarta Celda");
        celda2.setCellValue(texto2);
        
       // Se salva el libro.
        try {
                       
         // Volcamos la información a un archivo.
            String strNombreArchivo = "src/Pruebas_POI/prueba.xls";
            File objFile = strNombreArchivo();
            FileOutputStream archivoSalida = new FileOutputStream(objFile);
            libro.write(archivoSalida);
            archivoSalida.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
}
