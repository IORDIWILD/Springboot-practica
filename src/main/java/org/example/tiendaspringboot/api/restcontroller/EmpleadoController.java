package org.example.tiendaspringboot.api.restcontroller;


import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.example.tiendaspringboot.dto.request.EmpleadoRequestDTO;
import org.example.tiendaspringboot.dto.response.EmpleadoResponseDTO;
import org.example.tiendaspringboot.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.tiendaspringboot.model.Empleado;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> listar(){
        List<EmpleadoResponseDTO> empleados = empleadoService.listar();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> buscarPorId(@PathVariable int id){
        EmpleadoResponseDTO response = empleadoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> insertar(@RequestBody @Valid EmpleadoRequestDTO dto){
        EmpleadoResponseDTO response = empleadoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> actualizar(@PathVariable int id, @RequestBody @Valid EmpleadoRequestDTO datosNuevos) {
        EmpleadoResponseDTO response = empleadoService.actualizar(id,datosNuevos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
