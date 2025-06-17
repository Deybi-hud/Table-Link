package Prueba.TableLink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Prueba.TableLink.model.UsuarioPremium;
import Prueba.TableLink.repository.UsuarioPremiumRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioPremiumService {

     @Autowired
    UsuarioPremiumRepository usuariorPremiumRepository;

    public List<UsuarioPremium>findAll(){
        return usuariorPremiumRepository.findAll();
    }

    public UsuarioPremium getById(long id){
        return usuariorPremiumRepository.getReferenceById(id);
    }

    public UsuarioPremium save(UsuarioPremium usuarioPremium){
        return usuariorPremiumRepository.save(usuarioPremium);
    }

    public void delete(long id){
        usuariorPremiumRepository.deleteById(id);
    }

     public UsuarioPremium patchUsuarioPremium(Long id, UsuarioPremium usuarioPremium){
        Optional<UsuarioPremium> usuarioOptional = usuariorPremiumRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            
            UsuarioPremium usuarioToUpdate = usuarioOptional.get();
            if (usuarioPremium.getUsuario() != null){
                usuarioToUpdate.setUsuario(usuarioPremium.getUsuario());
            }

            if(usuarioPremium.getFechaCompra() == null) {
                usuarioToUpdate.setFechaCompra(usuarioPremium.getFechaCompra());
            }

            if (usuarioPremium.getMontoPago() == null){
                usuarioToUpdate.setMontoPago(usuarioPremium.getMontoPago());
            }
            
            if(usuarioPremium.getTipoPlan() == null){
                usuarioToUpdate.setTipoPlan(usuarioPremium.getTipoPlan());
            }

            if(usuarioPremium.getEsPremium() == null){
                usuarioToUpdate.setEsPremium(usuarioPremium.getEsPremium());
            }

            return usuariorPremiumRepository.save(usuarioToUpdate);
        } 
        
        else{
            return null;
        }
    }

}
