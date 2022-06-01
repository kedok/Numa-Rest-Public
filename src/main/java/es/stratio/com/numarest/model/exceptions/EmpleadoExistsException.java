package es.stratio.com.numarest.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmpleadoExistsException extends RuntimeException {
    public EmpleadoExistsException(String dni) {
        super("Empleado con dni: " + dni + " ya existe en el sistema.");
    }
}
