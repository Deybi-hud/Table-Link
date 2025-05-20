package Prueba.TableLink.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Prueba.TableLink.model.Historial;
import Prueba.TableLink.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String  nombreUsuario);

    @Query("SELECT u FROM  u WHERE u.usuario.id = :usuarioId")
    List<Historial> obtenerUsuario(@Param("usuarioId") Long usuarioId);
}
