package Prueba.TableLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.repository.HistorialInicioRepository;
import Prueba.TableLink.repository.UsuarioRepository;
import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired UsuarioRepository usuarioRepository;

    @Autowired HistorialInicioRepository historialInicioRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        
        for(int i = 0; i < 10; i++){
            Usuario usuario = new Usuario();
            usuario.setId(1 + i);
            usuario.setNombreUsuario(faker.name().fullName());
            usuario.setContrasena(faker.internet().password());
            usuarioRepository.save(usuario);
        }
    }
}
