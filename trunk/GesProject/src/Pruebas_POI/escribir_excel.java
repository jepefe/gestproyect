package Pruebas_POI;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class escribir_excel {

	/**
     * Crea una hoja Excel y la guarda.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Se crea el libro
        HSSFWorkbook libro = new HSSFWorkbook();

        // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet();

        // Se crea una fila dentro de la hoja
        HSSFRow fila = hoja.createRow(0);

        // Se crea una celda dentro de la fila
        HSSFCell celda = fila.createCell((short) 0);

        // Se crea el contenido de la celda y se mete en ella.
        HSSFRichTextString texto = new HSSFRichTextString("hola mundo");
        celda.setCellValue(texto);

        // Se salva el libro.
        try {
                       
         // Volcamos la información a un archivo.
            String strNombreArchivo = "E:/libro.xls";
            File objFile = new File(strNombreArchivo);
            FileOutputStream archivoSalida = new FileOutputStream(objFile);
            libro.write(archivoSalida);
            archivoSalida.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	
}
