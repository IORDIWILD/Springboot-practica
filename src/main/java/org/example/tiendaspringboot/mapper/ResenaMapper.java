package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.ResenaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ResenaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ClienteResumenDTO;
import org.example.tiendaspringboot.dto.response.ProductoResumenDTO;
import org.example.tiendaspringboot.dto.response.ResenaResponseDTO;
import org.example.tiendaspringboot.model.Resena;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResenaMapper {
    public Resena toEntity(ResenaCreateRequestDTO dto) {
        if (dto == null) return null;

        Resena resena = new Resena();
        resena.setCalificacion(dto.getCalificacion());
        resena.setComentario(dto.getComentario());

        resena.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());
        return resena;
    }

    public void actualizarParcial(ResenaUpdateRequestDTO dto, Resena existente) {
        if (dto.getCalificacion() != null) {
            existente.setCalificacion(dto.getCalificacion());
        }
        if (dto.getComentario() != null) {
            existente.setComentario(dto.getComentario());
        }
    }

    public ResenaResponseDTO toResponseDTO(Resena resena){
        if(resena==null) return null;

        ResenaResponseDTO dto = new ResenaResponseDTO();
        dto.setIdResena(resena.getIdResena());
        dto.setFecha(resena.getFecha());
        dto.setCalificacion(resena.getCalificacion());
        dto.setComentario(resena.getComentario());
        if (resena.getProducto() != null) {
            ProductoResumenDTO productoDTO = new ProductoResumenDTO();
            productoDTO.setIdProducto(resena.getProducto().getIdProducto());
            productoDTO.setNombre(resena.getProducto().getNombre());
            productoDTO.setPrecio(resena.getProducto().getPrecio());
            dto.setProducto(productoDTO);
        }
        if (resena.getCliente() != null) {
            ClienteResumenDTO clienteDTO = new ClienteResumenDTO();
            clienteDTO.setIdCliente(resena.getCliente().getIdCliente());
            clienteDTO.setNombre(resena.getCliente().getNombre());
            clienteDTO.setEmail(resena.getCliente().getEmail());
            dto.setCliente(clienteDTO);
        }
        return dto;
    }



}
