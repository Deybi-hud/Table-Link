package Prueba.TableLink.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import Prueba.TableLink.model.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findById(Integer id);
}
