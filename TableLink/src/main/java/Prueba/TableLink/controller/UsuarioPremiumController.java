package Prueba.TableLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import Prueba.TableLink.model.UsuarioPremium;
import Prueba.TableLink.service.UsuarioPremiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("api/v1/usuariosPremium")
@Tag(name = "Usuarios", description = "Aquí estan los usuarios")
public class UsuarioPremiumController {

    @Autowired
    private UsuarioPremiumService usuarioPremiumServices;


    @GetMapping
    @Operation(summary = "Esta api llama todos los usuarios", description = "esta api se encarga de obtener todos los usuarios que hay")
    public ResponseEntity<List<UsuarioPremium>> listar(){
        List<UsuarioPremium> usuario = usuarioPremiumServices.findAll();
        if(usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }    

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama a un usuarioPremium por su id", description = "esta api se encarga de obtener a un usuarios por id")
    public ResponseEntity<UsuarioPremium> buscar(@PathVariable long id) {
        try{
            UsuarioPremium usuarioPremium = usuarioPremiumServices.getById(id);
            return ResponseEntity.ok(usuarioPremium);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrarPremium")
    @Operation(summary = "Esta api se encarga de asignar premium a un usuarios", description = "Esta api se encarga de dar premium a un usuario")
    public ResponseEntity<?> registrar(@RequestBody UsuarioPremium usuarioPremium) {
        try{
        UsuarioPremium nuevoUsuarioPremium = usuarioPremiumServices.save(usuarioPremium);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuarioPremium);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puedo guardar");
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Esta api actualiza parcialmente un usuario", description = "esta api se encarga de actualizar parcialmente a un usuario existente")
    public ResponseEntity<UsuarioPremium> patchUsuarioPremium(@PathVariable Long id, @RequestBody UsuarioPremium usuarioPremium) {
        try {
            UsuarioPremium updatedUsuario = usuarioPremiumServices.patchUsuarioPremium(id, usuarioPremium);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api elimina a un usuario", description = "esta api se encarga de eliminar a un usuario existente")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try { 
            usuarioPremiumServices.delete(id);             
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
