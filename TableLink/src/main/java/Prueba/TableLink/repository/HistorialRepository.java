package Prueba.TableLink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Prueba.TableLink.model.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long>  {

    @Query("SELECT h FROM Historial h WHERE h.usuario.id = :usuarioId")
    List<Historial> obtenerHistorialPorUsuario(@Param("usuarioId") Long usuarioId);


    @Modifying
    @Query("DELETE h FROM Historial h WHERE h.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}



