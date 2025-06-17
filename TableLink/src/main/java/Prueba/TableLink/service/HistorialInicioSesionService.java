package Prueba.TableLink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Prueba.TableLink.model.HistorialInicioSesion;
import Prueba.TableLink.repository.HistorialInicioRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class HistorialInicioSesionService {

    @Autowired
    HistorialInicioRepository historialInicioRepository;

    public List<HistorialInicioSesion> findAll(){
        return historialInicioRepository.findAll();
    }

    @SuppressWarnings("deprecation")
    public HistorialInicioSesion getById(Long id){
        return historialInicioRepository.getById(id);
    }

    public  HistorialInicioSesion save(HistorialInicioSesion historialInicioSesion){
        return historialInicioRepository.save(historialInicioSesion);
    }


    public void delete (Long id){
        historialInicioRepository.deleteById(id);
    }

    public List<HistorialInicioSesion> ObtenerPorUsuario(Long UsuarioId){
        return historialInicioRepository.obtenerPorUsuario(UsuarioId);
    }

   

}
