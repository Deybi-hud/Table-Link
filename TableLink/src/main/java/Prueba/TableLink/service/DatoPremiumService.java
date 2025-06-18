package Prueba.TableLink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Prueba.TableLink.model.DatoPremium;
import Prueba.TableLink.repository.DatoPremiumRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DatoPremiumService {

     @Autowired
    DatoPremiumRepository usuariorPremiumRepository;

    public List<DatoPremium>findAll(){
        return usuariorPremiumRepository.findAll();
    }

    public DatoPremium getById(long id){
        return usuariorPremiumRepository.getReferenceById(id);
    }

    public DatoPremium save(DatoPremium usuarioPremium){
        return usuariorPremiumRepository.save(usuarioPremium);
    }

    public void delete(long id){
        usuariorPremiumRepository.deleteById(id);
    }

     public DatoPremium patchUsuarioPremium(Long id, DatoPremium usuarioPremium){
        Optional<DatoPremium> usuarioOptional = usuariorPremiumRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            
            DatoPremium usuarioToUpdate = usuarioOptional.get();
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

            return usuariorPremiumRepository.save(usuarioToUpdate);
        } 
        
        else{
            return null;
        }
    }

}
