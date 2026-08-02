package org.example.tiendaspringboot.service;

import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.CategoriaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.CategoriaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.CategoriaPadreResumenDTO;
import org.example.tiendaspringboot.dto.response.CategoriaResponseDTO;
import org.example.tiendaspringboot.mapper.CategoriaMapper;
import org.example.tiendaspringboot.model.Categoria;
import org.example.tiendaspringboot.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CategoriaMapper categoriaMapper;

    public List<CategoriaResponseDTO> listar( ){
            return categoriaRepository.findAll().stream().map(categoriaMapper::toResponseDTO).toList();
    }

    public CategoriaResponseDTO buscarPorId(Integer id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria no encontrada con ID: "+ id)
        );

        return categoriaMapper.toResponseDTO(categoria);
    }

    public CategoriaResponseDTO crear(CategoriaCreateRequestDTO dto){
        Categoria padre = null;
        if(dto.getCategoriaPadreId() != null){
            padre = categoriaRepository.findById(dto.getCategoriaPadreId()).orElseThrow(
                    () -> new RuntimeException("No existe categoria padre con ID :" + dto.getCategoriaPadreId())
            );
        }
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria.setCategoriaPadre(padre);
        Categoria guardada = categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(guardada);
    }
    public CategoriaResponseDTO actualizar(Integer id, CategoriaUpdateRequestDTO dto){
        Categoria existente = categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria no existente con ID: " + id)
        );
        categoriaMapper.actualizarParcial(dto,existente);
        if(dto.getCategoriaPadreId()==null){
            return categoriaMapper.toResponseDTO(existente);
        }
        Categoria padre = categoriaRepository.findById(dto.getCategoriaPadreId()).orElseThrow(
                () -> new RuntimeException("Categoria padre inexistente con ID:" +dto.getCategoriaPadreId())
        );
        existente.setCategoriaPadre(padre);
        Categoria actualizada = categoriaRepository.save(existente);
        return categoriaMapper.toResponseDTO(actualizada);
    }
    public void eliminar(Integer id){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Categoria inexistente con ID : "+ id)
        );
        List<Categoria> subCategorias = categoria.getSubCategorias();
        if(!subCategorias.isEmpty()){
            throw new RuntimeException("No se puede eliminar la categoria por que tiene "+
                    subCategorias.size() + " subcategorias asociadas");
        }
        categoriaRepository.delete(categoria);
    }



}
