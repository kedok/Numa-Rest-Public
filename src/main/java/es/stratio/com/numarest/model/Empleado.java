package es.stratio.com.numarest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Empleado {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "The full name is required.")
    private String nombre;

    private String apellidos;

    private int edad;

    private String direccion;

    @NotBlank(message = "DNI is mandatory")
    private String dni;

    private double salario;

    private String rol;

}
