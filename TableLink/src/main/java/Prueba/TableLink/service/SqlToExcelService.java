package Prueba.TableLink.service;




import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Prueba.TableLink.util.SqlParserUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SqlToExcelService {

    public byte[] convertirSqlAExcel(MultipartFile archivoSql) throws IOException {
        String contenidoSql = new String(archivoSql.getBytes());

        List<Map<String, String>> filas = SqlParserUtil.parseInsertStatements(contenidoSql);

        if (filas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron INSERTs válidos en el archivo SQL.");
        }

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("DatosSQL");

            
            Row headerRow = sheet.createRow(0);
            List<String> columnas = new ArrayList<>(filas.get(0).keySet());
            for (int i = 0; i < columnas.size(); i++) {
                headerRow.createCell(i).setCellValue(columnas.get(i));
            }

          
            int rowIdx = 1;
            for (Map<String, String> fila : filas) {
                Row row = sheet.createRow(rowIdx++);
                for (int i = 0; i < columnas.size(); i++) {
                    row.createCell(i).setCellValue(fila.get(columnas.get(i)));
                }
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
