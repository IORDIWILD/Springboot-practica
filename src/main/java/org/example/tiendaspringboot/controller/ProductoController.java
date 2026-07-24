package org.example.tiendaspringboot.controller;


import org.example.tiendaspringboot.repository.ProductoRepository;
import org.example.tiendaspringboot.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;

    //Get /productos -> los lista todos
    @GetMapping
    public List<Producto> listar(){
        return productoRepository.findAll();
    }

    //Get /productos/{id} -> buscar uno especifico
    @GetMapping({"/{id}"})
    public ResponseEntity<Producto> buscarPorId(@PathVariable Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Post /productos -> insertar uno nuevo
    @PostMapping
    public Producto insertar(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    //Post /productos/{id} -> actualizar uno existente

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar (@PathVariable Integer id, @RequestBody Producto datosNuevos){
        return productoRepository.findById(id).map(productoExistente ->{
            productoExistente.setNombre(datosNuevos.getNombre());
            productoExistente.setCategoria(datosNuevos.getCategoria());
            productoExistente.setPrecio(datosNuevos.getPrecio());
            Producto actualizado = productoRepository.save(productoExistente);
            return ResponseEntity.ok(actualizado);
        }).orElseGet( ()-> ResponseEntity.notFound().build());
    }

    //Delete /productos/{id} ->borrar uno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id){
        if(!productoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Producto> listarPorCategoria(@PathVariable Integer idCategoria){
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }

    @GetMapping("/buscar")
    public List<Producto> buscarPorNombre(@RequestParam String nombre){
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }


    @GetMapping("/precio")
    public List<Producto> buscarPorRangoPrecio(@RequestParam(required = false)BigDecimal min, @RequestParam(required = false
    ) BigDecimal max ) {
        if(min != null && max!=null ){
            return productoRepository.findByPrecioBetween(min,max);
        } else if (min != null) {
            return productoRepository.findByPrecioGreaterThan(min);
        } else if (max!=null) {
            return productoRepository.findByPrecioLessThan(max);
        } else{
            return productoRepository.findAll();
        }
    }
}





