package Prueba.TableLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Prueba.TableLink.model.HistorialInicioSesion;
import Prueba.TableLink.service.HistorialInicioSesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/historialSesion")
@Tag(name = "Historial de inicio", description = "Aqui estan los historiales de inicio de sesión")
public class HistorialInicioSesionController {

    @Autowired
    private HistorialInicioSesionService historialInicioSesionService;

    @GetMapping
    @Operation(summary = "Esta api llama todo a los historiales de inicio de sesión", description = "esta api se encarga de obtener todos los historiales de inicio de sesión que hay")
    public ResponseEntity<List<HistorialInicioSesion>>listar(){
        List<HistorialInicioSesion> historialInicioSesion = historialInicioSesionService.findAll();
        if(historialInicioSesion.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historialInicioSesion);
    }    

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama a los historiales de inicio de sesión", description = "esta api se encarga de obtener a los historiales de inicio de sesión")
    public ResponseEntity<HistorialInicioSesion> buscar(@PathVariable long id) {
        try{
            HistorialInicioSesion historialInicioSesion = historialInicioSesionService.getById(id);
            return ResponseEntity.ok(historialInicioSesion);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Esta api se encarga de crear historial de inicio de sesión", description = "Esta api se encarga de crear un historial de inicio de sesión")
    public ResponseEntity<HistorialInicioSesion> guardar(@RequestBody HistorialInicioSesion historialInicioSesion) {
        HistorialInicioSesion historialInicioSesion1 = historialInicioSesionService.save(historialInicioSesion);
        return ResponseEntity.status(HttpStatus.CREATED).body(historialInicioSesion1);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api elimina a un historial de inicio de sesión", description = "esta api se encarga de eliminar a un historial de inicio de sesión")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try { 
            historialInicioSesionService.delete(id);             
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    //Query
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<HistorialInicioSesion>> obtenerHistorialPorUsuario(@PathVariable("id") Long usuarioId) {
        List<HistorialInicioSesion> historial = historialInicioSesionService.ObtenerPorUsuario(usuarioId);
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }


}
