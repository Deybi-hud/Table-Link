package Prueba.TableLink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.service.UsuarioServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;


    @GetMapping
    public ResponseEntity<List<Usuario>>listar(){
        List<Usuario> usuario = usuarioServices.findAll();
        if(usuario.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }    



    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable long id) {
        try{
            Usuario usuario = usuarioServices.getById(id);
            return ResponseEntity.ok(usuario);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) {
        try{
        Usuario nuevoUsuario = usuarioServices.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puedo guardar");
        }
    }
    
    
    
}
