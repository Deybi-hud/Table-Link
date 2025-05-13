package Prueba.TableLink.controller;

import Prueba.TableLink.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sql-to-excel")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @PostMapping("/convertir")
    public ResponseEntity<byte[]> convertirArchivoSql(@RequestParam("archivoSql") MultipartFile archivoSql) {
        try {
            byte[] excelFile = exportService.convertirSqlAExcel(archivoSql);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resultado.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error al procesar el archivo: " + e.getMessage()).getBytes());
        }
    }
}


