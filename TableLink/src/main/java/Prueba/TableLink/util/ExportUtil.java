package Prueba.TableLink.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;



public class ExportUtil {  

    public static String leerArchivosSql(String archivosql) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(archivosql));
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        br.close();
        return contenido.toString();
    }
    
    public static List<Map<String, String>> extraerDatosSQL(String sql){
        List<Map<String, String>> datos = new ArrayList<>();

        Pattern pattern = Pattern.compile("INSERT INTO `?(\\w+)`? \\(([^)]+)\\) VALUES \\(([^)]+)\\);", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(sql);

        while (matcher.find()) {
            String tabla = matcher.group(1);
            String[] columnas = matcher.group(2).split(",");
            String[] valores = matcher.group(3).split(",");

            Map<String, String> fila = new LinkedHashMap<>();
            for (int i = 0; i < columnas.length; i++) {
                fila.put(columnas[i].trim(), valores[i].trim());             
            }
            datos.add(fila);
        }
        return datos;
    }


}
