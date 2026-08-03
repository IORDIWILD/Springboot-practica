package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.ProductoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ProductoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.CategoriaResumenDTO;
import org.example.tiendaspringboot.dto.response.ProductoResponseDTO;
import org.example.tiendaspringboot.dto.response.ProductoResumenDTO;
import org.example.tiendaspringboot.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoCreateRequestDTO dto){
        if (dto == null) return null;

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return producto;

    }

    public ProductoResponseDTO toResponseDTO(Producto producto){
        if(producto==null){
            return null;
        }
        ProductoResponseDTO responseDTO = new ProductoResponseDTO();
        responseDTO.setIdProducto(producto.getIdProducto());
        responseDTO.setNombre(producto.getNombre());
        responseDTO.setPrecio(producto.getPrecio());

        if (producto.getCategoria() != null) {
            CategoriaResumenDTO categoriaDTO = new CategoriaResumenDTO();
            categoriaDTO.setIdCategoria(producto.getCategoria().getIdCategoria());
            categoriaDTO.setNombre(producto.getCategoria().getNombre());
            responseDTO.setCategoria(categoriaDTO);
        }
        return responseDTO;
    }

    public void actualizarParcial(ProductoUpdateRequestDTO dto, Producto producto){
        if(dto.getNombre()!=null){
            producto.setNombre(dto.getNombre());
        }
        if(dto.getPrecio()!=null){
            producto.setPrecio(dto.getPrecio());
        }
    }

    public ProductoResumenDTO toResumenDTO(Producto producto){
            if(producto==null){
                return null;
            }
            ProductoResumenDTO productoResumenDTO = new ProductoResumenDTO();
            productoResumenDTO.setIdProducto(producto.getIdProducto());
            productoResumenDTO.setNombre(producto.getNombre());
            productoResumenDTO.setPrecio(producto.getPrecio());
            return productoResumenDTO;
    }
}
