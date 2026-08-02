package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.CategoriaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.CategoriaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.CategoriaPadreResumenDTO;
import org.example.tiendaspringboot.dto.response.CategoriaResponseDTO;
import org.example.tiendaspringboot.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaCreateRequestDTO dto){
        if(dto == null){
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());

        return categoria;
    }

    public CategoriaResponseDTO toResponseDTO(Categoria categoria){
        if(categoria==null){
            return null;
        }
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoria(categoria.getIdCategoria());
        response.setNombre(categoria.getNombre());

        if(categoria.getCategoriaPadre()!= null){
            CategoriaPadreResumenDTO padreDTO = new CategoriaPadreResumenDTO();
            padreDTO.setNombre(categoria.getCategoriaPadre().getNombre());
            padreDTO.setIdCategoria(categoria.getCategoriaPadre().getIdCategoria());
            response.setCategoriaPadre(padreDTO);
        }
        return response;
    }

    public void actualizarParcial(CategoriaUpdateRequestDTO dto, Categoria categoria){
        if(categoria==null || dto==null ){
            return;
        }
        if(dto.getNombre()!=null){
            categoria.setNombre(dto.getNombre());
        }

    }

}
