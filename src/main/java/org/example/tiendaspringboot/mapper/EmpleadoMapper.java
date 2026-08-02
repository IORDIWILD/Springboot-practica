package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.EmpleadoRequestDTO;
import org.example.tiendaspringboot.dto.response.EmpleadoResponseDTO;
import org.example.tiendaspringboot.dto.response.JefeResumenDTO;
import org.example.tiendaspringboot.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public Empleado toEntity(EmpleadoRequestDTO dto){
        if(dto == null){
            return null;
        }
        Empleado empleado = new Empleado();

        empleado.setNombre(dto.getNombre());
        empleado.setPuesto(dto.getPuesto());
        empleado.setSalario(dto.getSalario());

        return empleado;
    }

    public EmpleadoResponseDTO toResponseDTO(Empleado empleado){
        if(empleado == null) return null;

        EmpleadoResponseDTO dto = new EmpleadoResponseDTO();

        dto.setNombre(empleado.getNombre());
        dto.setIdEmpleado(empleado.getIdEmpleado());
        dto.setFechaContratacion(empleado.getFechaContratacion());
        dto.setSalario(empleado.getSalario());
        dto.setPuesto(empleado.getPuesto());

        if(empleado.getJefe() != null){
            JefeResumenDTO jefeDTO = new JefeResumenDTO();
            jefeDTO.setIdEmpleado(empleado.getJefe().getIdEmpleado());
            jefeDTO.setNombre(empleado.getJefe().getNombre());
            jefeDTO.setPuesto(empleado.getJefe().getPuesto());
            dto.setJefe(jefeDTO);
        }

        return dto;
    }
}
