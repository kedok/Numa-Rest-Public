package es.stratio.com.numarest.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpleadoNotFoundException extends RuntimeException {

    public EmpleadoNotFoundException(Long id) {
        super("Empleado con id: " + id + " no es un empleado.");
    }
}
