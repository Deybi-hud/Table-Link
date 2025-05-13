package Prueba.TableLink.service;

import Prueba.TableLink.util.SqlParserUtil;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {

   
    public byte[] convertirSqlAExcel(MultipartFile archivoSql) throws IOException {
        List<List<String>> registros = SqlParserUtil.extraerDatosDeInsert(archivoSql);

        // Crear el archivo Excel
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Datos");

            // Crear encabezados
            if (!registros.isEmpty()) {
                Row headerRow = sheet.createRow(0);
                List<String> encabezados = registros.get(0);  // Suponemos que la primera fila tiene los encabezados
                for (int i = 0; i < encabezados.size(); i++) {
                    headerRow.createCell(i).setCellValue(encabezados.get(i));
                }

                // Llenar los datos
                for (int i = 1; i < registros.size(); i++) {
                    Row row = sheet.createRow(i);
                    List<String> registro = registros.get(i);
                    for (int j = 0; j < registro.size(); j++) {
                        row.createCell(j).setCellValue(registro.get(j));
                    }
                }
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }
}
