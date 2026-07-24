package org.example.tiendaspringboot.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.tiendaspringboot.repository.EmpleadoRepository;
import org.example.tiendaspringboot.model.Empleado;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping
    public List<Empleado> listar(){ return empleadoRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable int id){
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado insertar(@RequestBody Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable int id, @RequestBody Empleado datosNuevos){
        return empleadoRepository.findById(id).map(empleadoExistente ->{
            empleadoExistente.setNombre(datosNuevos.getNombre());
            empleadoExistente.setPuesto(datosNuevos.getPuesto());
            empleadoExistente.setJefe(datosNuevos.getJefe());
            empleadoExistente.setSalario(datosNuevos.getSalario());
            empleadoExistente.setFechaContratacion(datosNuevos.getFechaContratacion());
            Empleado actualizado = empleadoRepository.save(empleadoExistente);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id){
        if(!empleadoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        empleadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
