package Prueba.TableLink.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Prueba.TableLink.model.Historial;
import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.repository.HistorialRepository;
import Prueba.TableLink.repository.UsuarioRepository;
import Prueba.TableLink.util.SqlParserUtil;
import jakarta.transaction.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SqlToExcelService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired 
    HistorialRepository historialRepository;


    public byte[] convertirSqlAExcel(MultipartFile archivoSql,String nombreUsuario) throws IOException {
        
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String contenidoSql = new String(archivoSql.getBytes());

        List<Map<String, String>> filas = SqlParserUtil.parseInsertStatements(contenidoSql);

        if (filas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron INSERTS válidos en el archivo SQL.");
        }

            Historial historial = new Historial();
            historial.setUsuario(usuario);
            historial.setFechaConversion(LocalDate.now());
            historial.setTipoConversion("SQL a Excel");
            historial.setDescripcion("Conversión de archivo SQL a Excel con ");
            historialRepository.save(historial);

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
