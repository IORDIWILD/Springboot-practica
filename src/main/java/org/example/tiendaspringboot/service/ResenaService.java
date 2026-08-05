package org.example.tiendaspringboot.service;

import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.ResenaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ResenaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ResenaResponseDTO;
import org.example.tiendaspringboot.mapper.ResenaMapper;
import org.example.tiendaspringboot.model.Cliente;
import org.example.tiendaspringboot.model.Producto;
import org.example.tiendaspringboot.model.Resena;
import org.example.tiendaspringboot.repository.ClienteRepository;
import org.example.tiendaspringboot.repository.ProductoRepository;
import org.example.tiendaspringboot.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ResenaMapper resenaMapper;

    public List<ResenaResponseDTO> listar() {
        return resenaRepository.findAll().stream()
                .map(resenaMapper::toResponseDTO).toList();
    }


    public ResenaResponseDTO buscarPorId(Integer id) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
        return resenaMapper.toResponseDTO(resena);
    }

    public List<ResenaResponseDTO> buscarPorProducto(Integer idProducto){
        Producto producto = productoRepository.findById(idProducto).orElseThrow(
                () -> new RuntimeException("Producto no encontrado con ID: " + idProducto)
        );

        return resenaRepository.findByProducto(producto).stream().map(resenaMapper::toResponseDTO).toList();
    }

    public List<ResenaResponseDTO> buscarPorCliente(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado con ID: " + idCliente)
        );

        return resenaRepository.findByCliente(cliente).stream().map(resenaMapper::toResponseDTO).toList();
    }

    public ResenaResponseDTO crear(ResenaCreateRequestDTO dto) {
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + dto.getIdProducto()));

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + dto.getIdCliente()));

        if (resenaRepository.existsByProductoAndCliente(producto, cliente)) {
            throw new RuntimeException("El cliente ya ha reseñado este producto");
        }

        Resena resena = resenaMapper.toEntity(dto);
        resena.setProducto(producto);
        resena.setCliente(cliente);

        Resena guardada = resenaRepository.save(resena);

        return resenaMapper.toResponseDTO(guardada);
    }

    public ResenaResponseDTO actualizar(Integer id, ResenaUpdateRequestDTO dto) {
        Resena existente = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));

        resenaMapper.actualizarParcial(dto, existente);

        Resena actualizada = resenaRepository.save(existente);

        return resenaMapper.toResponseDTO(actualizada);
    }

    public void eliminar(Integer id) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
        resenaRepository.delete(resena);
    }

}
