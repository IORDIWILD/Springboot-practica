package org.example.tiendaspringboot.controller;


import jakarta.validation.Valid;
import org.example.tiendaspringboot.dto.request.ProductoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ProductoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ProductoResponseDTO;
import org.example.tiendaspringboot.repository.ProductoRepository;
import org.example.tiendaspringboot.model.Producto;
import org.example.tiendaspringboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    //Get /productos -> los lista todos
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    //Get /productos/{id} -> buscar uno especifico
    @GetMapping({"/{id}"})
    public ResponseEntity<ProductoResponseDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    //Post /productos -> insertar uno nuevo
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> insertar(@RequestBody @Valid ProductoCreateRequestDTO dto){
        ProductoResponseDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    //Post /productos/{id} -> actualizar uno existente

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar (@PathVariable Integer id, @RequestBody @Valid ProductoUpdateRequestDTO datosNuevos){
        return ResponseEntity.ok(productoService.actualizar(id,datosNuevos));
    }

    //Delete /productos/{id} ->borrar uno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id){
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}





