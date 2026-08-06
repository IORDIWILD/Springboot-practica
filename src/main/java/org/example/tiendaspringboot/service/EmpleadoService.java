package org.example.tiendaspringboot.service;

import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.EmpleadoRequestDTO;
import org.example.tiendaspringboot.dto.response.EmpleadoResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.mapper.EmpleadoMapper;
import org.example.tiendaspringboot.model.Empleado;
import org.example.tiendaspringboot.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;


    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto) {
        Empleado jefe = null;
        if (dto.getJefeId() != null) {
            jefe = empleadoRepository.findById((dto.getJefeId())).orElseThrow(() -> new ResourceNotFoundException("jefe no encontrado con ID:" +
                    dto.getJefeId()));
        }
        //mapear ->
        Empleado empleado = empleadoMapper.toEntity(dto);
        empleado.setJefe(jefe);

        //asignar fecha de contratacion
        if (dto.getFechaContratacion() == null) {
            empleado.setFechaContratacion(LocalDate.now());
        } else {
            empleado.setFechaContratacion(dto.getFechaContratacion());
        }
        //guardar
        Empleado guardado = empleadoRepository.save(empleado);

        return empleadoMapper.toResponseDTO(guardado);
    }

    public List<EmpleadoResponseDTO> listar() {
        return empleadoRepository.findAll().stream().map(empleadoMapper::toResponseDTO).toList();
    }

    public EmpleadoResponseDTO buscarPorId(Integer id) {
        Empleado empleado = empleadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Empleado", id)
        );
        return empleadoMapper.toResponseDTO(empleado);
    }

    public EmpleadoResponseDTO actualizar(Integer id, EmpleadoRequestDTO dto) {
        Empleado existente = empleadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Empleado", id)
        );

        existente.setNombre(dto.getNombre());
        existente.setPuesto(dto.getPuesto());
        existente.setSalario(dto.getSalario());

        if (dto.getJefeId() != null) {
            Empleado nuevoJefe = empleadoRepository.findById(dto.getJefeId()).orElseThrow(
                    () -> new ResourceNotFoundException("Jefe no encontrado con ID: " + dto.getJefeId())
            );
            existente.setJefe(nuevoJefe);
        } else {
            existente.setJefe(null);
        }
        if (dto.getFechaContratacion() != null) {
            existente.setFechaContratacion(dto.getFechaContratacion());
        }
        Empleado actualizado = empleadoRepository.save(existente);
        return empleadoMapper.toResponseDTO(actualizado);
    }

    public void eliminar(Integer id){
       if(!empleadoRepository.existsById(id)){
           throw new ResourceNotFoundException("Empleado", id);
       }else{
            empleadoRepository.deleteById(id);
       }
    }
}
