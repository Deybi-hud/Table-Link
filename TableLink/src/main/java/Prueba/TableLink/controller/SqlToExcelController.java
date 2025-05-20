package Prueba.TableLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Prueba.TableLink.service.SqlToExcelService;

@RestController
@RequestMapping("/api/v1/sql-excel")
public class SqlToExcelController {

    @Autowired
    private SqlToExcelService exportService;

    @PostMapping("/convertir")
    public ResponseEntity<byte[]> convertirArchivoSql(@RequestParam("archivoSql") MultipartFile archivoSql, @RequestParam("nombreUsuario") String nombreUsuario){       
        try {
            byte[] excelFile = exportService.convertirSqlAExcel(archivoSql, nombreUsuario);

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= resultado.xlsx")
                .header("x-mensaje","Archivo convertido exitosamente")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelFile);
            }  
            
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest()
                .body(("Archivo inválido: " + e.getMessage()).getBytes());
            } 
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error interno: " + e.getMessage()).getBytes());
        }
    }
}

