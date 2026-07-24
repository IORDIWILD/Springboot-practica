package org.example.tiendaspringboot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.tiendaspringboot.model.Cliente;
import org.example.tiendaspringboot.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(){ return clienteRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable int id){
        return clienteRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente insertar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente datosNuevos){
        return clienteRepository.findById(id).map( clienteExistente -> {
            clienteExistente.setNombre(datosNuevos.getNombre());
            clienteExistente.setEmail(datosNuevos.getEmail());
            clienteExistente.setCiudad(datosNuevos.getCiudad());
            clienteExistente.setFechaRegistro(datosNuevos.getFechaRegistro());
            clienteExistente.setTelefono(datosNuevos.getTelefono());
            Cliente actualizado = clienteRepository.save(clienteExistente);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
