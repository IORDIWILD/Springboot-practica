package org.example.tiendaspringboot.api.restcontroller;



import jakarta.validation.Valid;
import org.example.tiendaspringboot.dto.request.CategoriaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.CategoriaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.CategoriaResponseDTO;
import org.example.tiendaspringboot.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> insertar(@RequestBody @Valid CategoriaCreateRequestDTO categoria){
        CategoriaResponseDTO creado = categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(@PathVariable Integer id, @RequestBody @Valid CategoriaUpdateRequestDTO datosNuevos){
        return ResponseEntity.ok(categoriaService.actualizar(id,datosNuevos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
