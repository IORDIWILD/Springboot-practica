package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.ClienteCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ClienteUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ClienteResponseDTO;
import org.example.tiendaspringboot.model.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteCreateRequestDTO dto){
        if(dto == null){
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setCiudad(dto.getCiudad());
        if(dto.getFechaRegistro() != null) {
            cliente.setFechaRegistro(dto.getFechaRegistro());
        }else{
            cliente.setFechaRegistro(LocalDate.now());
        }
        cliente.setTelefono(dto.getTelefono());

        return cliente;
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente){
        if(cliente == null){
            return null;
        }
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setIdCliente(cliente.getIdCliente());
        response.setNombre(cliente.getNombre());
        response.setEmail(cliente.getEmail());
        response.setCiudad(cliente.getCiudad());
        response.setFechaRegistro(cliente.getFechaRegistro());
        response.setTelefono(cliente.getTelefono());

        return response;
    }

    public void actualizarParcial(ClienteUpdateRequestDTO dto, Cliente cliente){
        if(dto == null || cliente == null){
            return;
        }
        if(dto.getNombre()!= null && !dto.getNombre().isBlank()){
            cliente.setNombre(dto.getNombre());
        }
        if(dto.getEmail()!= null && !dto.getEmail().isBlank()){
            cliente.setEmail(dto.getEmail());
        }
        if(dto.getCiudad()!=null && !dto.getCiudad().isBlank()){
            cliente.setCiudad(dto.getCiudad());
        }
        if(dto.getTelefono()!= null && !dto.getTelefono().isBlank()){
            cliente.setTelefono(dto.getTelefono());
        }
        if(dto.getFechaRegistro()!=null){
            cliente.setFechaRegistro(dto.getFechaRegistro());
        }
    }
}
