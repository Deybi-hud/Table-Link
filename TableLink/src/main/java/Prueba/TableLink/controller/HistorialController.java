package Prueba.TableLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Prueba.TableLink.model.Historial;
import Prueba.TableLink.service.HistorialService;

@RestController
@RequestMapping("api/v1/historiales")
public class HistorialController{

    @Autowired
    private HistorialService historialService;


    @GetMapping
    public ResponseEntity<List<Historial>>listar(){
        List<Historial> historial = historialService.findAll();
        if(historial.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }    

    @GetMapping("/{id}")
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
    public ResponseEntity<Historial> guardar(@RequestBody Historial historial) {
        Historial nuevoHistorial = historialService.save(historial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHistorial);
    }

    //Query
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Historial>> obtenerHistorialPorUsuario(@PathVariable("id") Long usuarioId) {
        List<Historial> historial = historialService.obtenerHistorialPorUsuario(usuarioId);
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    
}
