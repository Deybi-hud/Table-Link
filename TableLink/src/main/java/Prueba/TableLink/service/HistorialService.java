package Prueba.TableLink.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Prueba.TableLink.model.Historial;
import Prueba.TableLink.repository.HistorialRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistorialService {
    
    @Autowired
    HistorialRepository historialRepository;

    public List<Historial>findAll(){
        return historialRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    public Historial getById(long id){
        return historialRepository.getById(id);
    }

    public Historial save(Historial historial){
        return historialRepository.save(historial);
    }

    public void delete(long id){
        historialRepository.deleteById(id);
    }

    public List<Historial> obtenerHistorialPorUsuario(Long usuarioId) {
        return historialRepository.obtenerHistorialPorUsuario(usuarioId);
    }

}
