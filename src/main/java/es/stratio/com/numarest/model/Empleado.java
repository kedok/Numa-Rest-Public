package es.stratio.com.numarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empleado {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private String apellidos;

    private int edad;

    private String direccion;

    private String dni;

    private double salario;

    private String rol;

}
