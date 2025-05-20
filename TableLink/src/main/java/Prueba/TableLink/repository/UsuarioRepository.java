package Prueba.TableLink.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import Prueba.TableLink.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String  nombreUsuario);

}
