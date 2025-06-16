package Prueba.TableLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.service.InicioSesionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")

public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

    @PostMapping("/login")
    public ResponseEntity <?> login(@RequestBody Usuario usuario){
        String mensaje = inicioSesionService.login(usuario.getCorreo(), usuario.getContrasena());
        return ResponseEntity.ok(mensaje);
    }

}
