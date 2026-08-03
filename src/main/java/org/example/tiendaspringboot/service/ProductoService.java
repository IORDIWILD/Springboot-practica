package org.example.tiendaspringboot.service;

import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.ProductoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ProductoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ProductoResponseDTO;
import org.example.tiendaspringboot.mapper.ProductoMapper;
import org.example.tiendaspringboot.model.Categoria;
import org.example.tiendaspringboot.model.Producto;
import org.example.tiendaspringboot.repository.CategoriaRepository;
import org.example.tiendaspringboot.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ProductoMapper productoMapper;
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<ProductoResponseDTO> listar(){
        return productoRepository.findAll().stream().map(productoMapper::toResponseDTO).toList();
    }

    public ProductoResponseDTO buscarPorId(Integer id){
        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto inexistente con ID: "+ id)
        );
        return productoMapper.toResponseDTO(producto);
    }

    public ProductoResponseDTO crear(ProductoCreateRequestDTO dto){
        Producto producto = productoMapper.toEntity(dto);
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria()).orElseThrow(
                () -> new RuntimeException("Categoria inexistente con ID: "+ dto.getIdCategoria())
        );
        producto.setCategoria(categoria);
        Producto guardado = productoRepository.save(producto);
        return productoMapper.toResponseDTO(guardado);
    }

    public ProductoResponseDTO actualizar(Integer id, ProductoUpdateRequestDTO dto){
        Producto existente = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto inexistente con ID: "+ id)
        );
        productoMapper.actualizarParcial(dto,existente);
        if(dto.getIdCategoria()!=null){
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria()).orElseThrow(
                    () -> new RuntimeException("Categoria inexistente con ID: " + dto.getIdCategoria() )
            );
            existente.setCategoria(categoria);
        }
        Producto actualizado = productoRepository.save(existente);
        return productoMapper.toResponseDTO(actualizado);
    }

    public void eliminar(Integer id){
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
        }else{
            throw new RuntimeException("Producto inexistente con ID: " + id);
        }
    }

    public List<ProductoResponseDTO> buscarPorCategoria(Integer idCategoria){
        if(!categoriaRepository.existsById(idCategoria)) {
           throw new RuntimeException("Categoria inexistente con ID: " + idCategoria);
        }
        return productoRepository.findByCategoriaIdCategoria(idCategoria).stream().map(productoMapper::toResponseDTO).toList();
    }


}
