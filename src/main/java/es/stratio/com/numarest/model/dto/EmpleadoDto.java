package es.stratio.com.numarest.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmpleadoDto implements Serializable {
    private String nombre;
    private String apellidos;
    private String rol;
    private String dni;


}
