package es.stratio.com.numarest.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import es.stratio.com.numarest.model.Empleado;
import es.stratio.com.numarest.model.IEmpleado;
import es.stratio.com.numarest.model.dto.EmpleadoDTOConverter;
import es.stratio.com.numarest.model.dto.EmpleadoDto;
import es.stratio.com.numarest.model.exceptions.EmpleadoExistsException;
import es.stratio.com.numarest.model.exceptions.EmpleadoNotFoundException;
import es.stratio.com.numarest.model.exceptions.RestError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmpleadoController {

    private final IEmpleado empleados;
    private final EmpleadoDTOConverter empleadoDTOConverter;

    @GetMapping("/getEmpleados")
    public ResponseEntity<List<EmpleadoDto>> getEmpleados() {
        List<Empleado> empleadoList = empleados.findAll();

        if (empleadoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<EmpleadoDto> empleadoDtos = empleadoList.stream().map(empleadoDTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.ok(empleadoDtos);
        }
    }

    @GetMapping("/getEmpleado")
    public Empleado getEmpleadosById(@RequestParam Long id) {
        return empleados.findById(id).orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    @PutMapping("/putEmpleado")
    public ResponseEntity<Empleado> editEmpleado(@RequestBody Empleado editar, @RequestParam Long id) {
        return empleados.findById(id).map(e -> {
            e.setNombre(editar.getNombre());
            e.setApellidos(editar.getApellidos());
            e.setEdad(editar.getEdad());
            e.setDireccion(editar.getDireccion());
            e.setDni(editar.getDni());
            e.setSalario(editar.getSalario());
            e.setRol(editar.getRol());
            return ResponseEntity.ok(empleados.save(e));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/postEmpleado")
    public ResponseEntity<Empleado> newEmpleado(@Valid @RequestBody Empleado nuevoEmpleado) {
        List<Empleado> todosEmpleados = empleados.findAll();
        if (todosEmpleados.stream().filter(empleado -> empleado.getDni().equals(nuevoEmpleado.getDni())).count() > 0) {
            throw new EmpleadoExistsException(nuevoEmpleado.getDni());
        } else {
            Empleado nuevo = empleados.save(nuevoEmpleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        }

    }

    @DeleteMapping("/delEmpleado")
    public ResponseEntity<?> borrarEmpleado(@RequestParam Long id) {
        if (empleados.existsById(id)) {
            empleados.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EmpleadoNotFoundException(id);
        }
    }

    @ExceptionHandler(EmpleadoNotFoundException.class)
    public ResponseEntity<RestError> handlerEmpleadoNotFound(EmpleadoNotFoundException exception) {
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.NOT_FOUND);
        restError.setTime(LocalDateTime.now());
        restError.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restError);

    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<RestError> handleJsonMappingException(JsonMappingException exception) {
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.BAD_REQUEST);
        restError.setTime(LocalDateTime.now());
        restError.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restError);
    }

    @ExceptionHandler(EmpleadoExistsException.class)
    public ResponseEntity<RestError> handlerEmpleadoExists(EmpleadoExistsException exception) {
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.CONFLICT);
        restError.setTime(LocalDateTime.now());
        restError.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(restError);

    }

}
