package Prueba.TableLink.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name= "Usuario_Premium")
@AllArgsConstructor
@NoArgsConstructor


public class UsuarioPremium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date fechaCompra;

    @Column(nullable = false, length = 20)
    private String tipoPlan;

    @Column(nullable = false, length = 10)
    private String montoPago;

}
