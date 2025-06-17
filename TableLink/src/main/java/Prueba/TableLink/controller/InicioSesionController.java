package Prueba.TableLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.service.InicioSesionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Inicio de sesión")
public class InicioSesionController {

    @Autowired
    private InicioSesionService inicioSesionService;

    @PostMapping("/login")
    @Operation(summary = "Esta api de iniciar sesión", description = "Esta api permite iniciar sesión")
    public ResponseEntity <?> login(@RequestBody Usuario usuario){
        String mensaje = inicioSesionService.login(usuario.getCorreo(), usuario.getContrasena());
        return ResponseEntity.ok(mensaje);
    }

}