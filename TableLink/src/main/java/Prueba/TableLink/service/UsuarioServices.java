package Prueba.TableLink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Prueba.TableLink.model.Usuario;
import Prueba.TableLink.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioServices {
    @Autowired
    UsuarioRepository usuariorRepository;

    public List<Usuario>findAll(){
        return usuariorRepository.findAll();
    }

    public Usuario getById(long id){
        return usuariorRepository.getById(id);
    }

    public Usuario save(Usuario usuario){
        return usuariorRepository.save(usuario);
    }

    public void delete(long id){
        usuariorRepository.deleteById(id);;
    }

    

}
