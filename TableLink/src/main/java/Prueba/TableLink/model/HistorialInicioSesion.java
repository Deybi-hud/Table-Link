package Prueba.TableLink.model;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name= "Historial_inicio_sesion")
@AllArgsConstructor
@NoArgsConstructor

public class HistorialInicioSesion {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer iniciosSesion;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaConexion;

    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
