package Prueba.TableLink.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Prueba.TableLink.model.HistorialInicioSesion;
import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.repository.HistorialInicioRepository;
import Prueba.TableLink.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InicioSesionService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistorialInicioRepository historialInicioRepository;

    public String login(String correo, String contrasena){
        
        Usuario usuario = usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new RuntimeException("Correo no encontrado"));

        if(!usuario.getContrasena().equals(contrasena)){
            throw new RuntimeException("Contraseña inválida");
        }

        LocalDate hoy = LocalDate.now();
        Optional<HistorialInicioSesion> historialExistente = historialInicioRepository.findByUsuarioAndFechaConexion(usuario, hoy);

        if(historialExistente.isPresent()){
            HistorialInicioSesion historial = historialExistente.get();
            historial.setIniciosSesion(historial.getIniciosSesion()+1);
            historialInicioRepository.save(historial);
        }
        else{
            HistorialInicioSesion nuevoHistorial = new HistorialInicioSesion();
            nuevoHistorial.setFechaConexion(hoy);
            nuevoHistorial.setIniciosSesion(1);
            nuevoHistorial.setUsuario(usuario);
            historialInicioRepository.save(nuevoHistorial);

        }

        return "Inicio de sesión correcto";
    }

}
