package Prueba.TableLink.controller;

import Prueba.TableLink.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/sql-to-excel")
public class ExportController {

    @Autowired
    private ExportService exportService;

    /**
     * Endpoint para convertir un archivo SQL a Excel.
     * 
     * @param archivoSql El archivo SQL recibido a través de POST
     * @return Un archivo Excel (byte array)
     */
    @PostMapping("/convertir")
    public ResponseEntity<byte[]> convertirSqlAExcel(@RequestParam("archivoSql") MultipartFile archivoSql) {
        try {
            // Llamar al servicio para convertir el SQL en un archivo Excel
            byte[] excelFile = exportService.convertirSqlAExcel(archivoSql);

            // Devolver el archivo Excel generado
            return ResponseEntity.ok()
                    .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .header("Content-Disposition", "attachment; filename=\"historial.xlsx\"")
                    .body(excelFile);
        } catch (IOException e) {
            // Manejar errores de archivo (por ejemplo, archivo no válido o error de procesamiento)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

