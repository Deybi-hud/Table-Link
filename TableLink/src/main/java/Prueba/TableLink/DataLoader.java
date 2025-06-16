package Prueba.TableLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import Prueba.TableLink.repository.HistorialRepository;
import Prueba.TableLink.repository.UsuarioRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired UsuarioRepository usuarioRepository;

    @Autowired HistorialRepository historialRepository;

    @Override
    public void run(String... args) throws Exception {
    }
}
