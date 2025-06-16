package Prueba.TableLink.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.service.UsuarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Usuarios", description = "Aquí estan los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;


    @GetMapping
    @Operation(summary = "Esta api llama todos los usuarios", description = "esta api se encarga de obtener todos los usuarios que hay")
    public ResponseEntity<List<Usuario>>listar(){
        List<Usuario> usuario = usuarioServices.findAll();
        if(usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }    

    @GetMapping("/{id}")
    @Operation(summary = "Esta api llama a un usuario por su id", description = "esta api se encarga de obtener a un usuarios por id")
    public ResponseEntity<Usuario> buscar(@PathVariable long id) {
        try{
            Usuario usuario = usuarioServices.getById(id);
            return ResponseEntity.ok(usuario);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrar")
    @Operation(summary = "Esta api se encarga de crear a un usuarios", description = "Esta api se encarga de crear una nuevo usuario")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try{
        Usuario nuevoUsuario = usuarioServices.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puedo guardar");
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Esta api actualiza parcialmente un usuario", description = "esta api se encarga de actualizar parcialmente a un usuario existente")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioServices.patchUsuario(id, usuario);
            return ResponseEntity.ok(updatedUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Esta api elimina a un usuario", description = "esta api se encarga de eliminar a un usuario existente")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try { 
            usuarioServices.delete(id);             
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
