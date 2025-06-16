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

import Prueba.TableLink.model.Historial;
import Prueba.TableLink.service.HistorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/historiales")
@Tag(name = "Historiales", description = "Aqui estan los historiales de conversion")
public class HistorialController{

    @Autowired
    private HistorialService historialService;


    @GetMapping
    @Operation(summary = "Esta api llama a todo los historiales de conversion", description = "esta api se encarga de obtener todos los historiles que hay")
    public ResponseEntity<List<Historial>>listar(){
        List<Historial> historial = historialService.findAll();
        if(historial.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }    

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama a un historial por su id", description = "esta api se encarga de obtener a un historial por id")
    public ResponseEntity<Historial> buscar(@PathVariable long id) {
        try{
            Historial historial = historialService.getById(id);
            return ResponseEntity.ok(historial);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Esta api se encarga de crear a un historial", description = "Esta api se encarga de crear una nuevo historial")
    public ResponseEntity<Historial> guardar(@RequestBody Historial historial) {
        Historial nuevoHistorial = historialService.save(historial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHistorial);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api elimina a un historial", description = "esta api se encarga de eliminar a un historial existente")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try { 
            historialService.delete(id);             
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Query
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Historial>> obtenerHistorialPorUsuario(@PathVariable("id") Long usuarioId) {
        List<Historial> historial = historialService.obtenerHistorialPorUsuario(usuarioId);
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }


}
