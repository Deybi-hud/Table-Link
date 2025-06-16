package Prueba.TableLink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import Prueba.TableLink.model.HistorialInicioSesion;

public interface HistorialInicioRepository extends JpaRepository<HistorialInicioSesion, Long> {

    @Query("SELECT h FROM Historial h WHERE h.usuario.id = :usuarioId")
    List<HistorialInicioSesion> obtenerPorUsuario(@Param("usuarioId") Long usuarioId);

}
