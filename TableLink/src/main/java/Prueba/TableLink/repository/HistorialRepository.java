package Prueba.TableLink.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import Prueba.TableLink.model.Historial;

public interface HistorialRepository extends JpaRepository<Historial, Long>  {
    
}
