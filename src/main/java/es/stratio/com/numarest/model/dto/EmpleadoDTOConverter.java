package es.stratio.com.numarest.model.dto;

import es.stratio.com.numarest.model.Empleado;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmpleadoDTOConverter {

    private final ModelMapper modelMapper;

    public EmpleadoDto convertToDTO(Empleado empleado) {
        return modelMapper.map(empleado, EmpleadoDto.class);
    }
}
