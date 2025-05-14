package Prueba.TableLink.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import Prueba.TableLink.service.SqlToExcelService;

@RestController
@RequestMapping("/api/sql-to-excel")
public class SqlToExcelController {

    @Autowired
    private SqlToExcelService exportService;

    @PostMapping("/convertir")
    public ResponseEntity<byte[]> convertirArchivoSql(@RequestParam("archivoSql") MultipartFile archivoSql) {
        try {
            byte[] excelFile = exportService.convertirSqlAExcel(archivoSql);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resultado.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(("Archivo inválido: " + e.getMessage()).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error interno: " + e.getMessage()).getBytes());
        }
    }
}

