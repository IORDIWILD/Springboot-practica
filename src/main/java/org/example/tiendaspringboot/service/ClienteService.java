package org.example.tiendaspringboot.service;


import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.ClienteCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ClienteUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ClienteResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.mapper.ClienteMapper;
import org.example.tiendaspringboot.model.Cliente;
import org.example.tiendaspringboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ClienteService {


    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    public List<ClienteResponseDTO> listar(){
        return clienteRepository.findAll().stream().map(clienteMapper::toResponseDTO).toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id){

        return clienteRepository.findById(id).map(clienteMapper::toResponseDTO).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", id)
        );

    }

    public ClienteResponseDTO crear(ClienteCreateRequestDTO dto){
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente guardado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(guardado);
    }

    public ClienteResponseDTO actualizar(Integer id, ClienteUpdateRequestDTO dto){
        Cliente existente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", id)
        );
        clienteMapper.actualizarParcial(dto,existente);
        clienteRepository.update(existente);
        return clienteMapper.toResponseDTO(existente);
    }

    public void eliminar(Integer id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Cliente", id);
        }
    }


}
