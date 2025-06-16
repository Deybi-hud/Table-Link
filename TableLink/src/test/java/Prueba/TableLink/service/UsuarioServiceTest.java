package Prueba.TableLink.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    UsuarioServices usuarioServices;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario createUsuario(){
        return new Usuario(
            1,
            "D",
            "hola1234"      
        );
    }
    

}
