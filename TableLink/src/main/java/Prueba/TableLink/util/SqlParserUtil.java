package Prueba.TableLink.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SqlParserUtil {

    /**
     * Extrae los datos de un archivo SQL, buscando las sentencias INSERT INTO.
     * 
     * @param archivoSql El archivo .sql subido
     * @return Lista de registros (como List<List<String>>) que pueden ser insertados en Excel
     * @throws IOException Si hay algún problema al leer el archivo
     */
    public static List<List<String>> extraerDatosDeInsert(MultipartFile archivoSql) throws IOException {
        List<List<String>> registros = new ArrayList<>();

        // Leer el archivo SQL como texto
        String sqlContent = new String(archivoSql.getBytes());

        // Buscar todas las sentencias INSERT INTO
        String[] lineas = sqlContent.split("\n");
        for (String linea : lineas) {
            if (linea.trim().startsWith("INSERT INTO")) {
                // Procesar cada sentencia INSERT INTO
                String valores = linea.substring(linea.indexOf("(") + 1, linea.indexOf(")"));
                String[] valoresSeparados = valores.split(",");

                // Limpiar y agregar a la lista de registros
                List<String> registro = new ArrayList<>();
                for (String valor : valoresSeparados) {
                    registro.add(valor.trim().replace("'", ""));  // Eliminar comillas simples
                }

                registros.add(registro);
            }
        }

        return registros;
    }
}
