package org.example.tiendaspringboot.api.restcontroller;

import jakarta.validation.Valid;
import org.example.tiendaspringboot.dto.request.ResenaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ResenaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ResenaResponseDTO;
import org.example.tiendaspringboot.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaResponseDTO>> listar() {
        return ResponseEntity.ok(resenaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(resenaService.buscarPorId(id));
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<ResenaResponseDTO>> buscarPorProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(resenaService.buscarPorProducto(idProducto));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ResenaResponseDTO>> buscarPorCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(resenaService.buscarPorCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<ResenaResponseDTO> insertar(
            @RequestBody @Valid ResenaCreateRequestDTO resena) {
        ResenaResponseDTO creada = resenaService.crear(resena);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/resenas/" + creada.getIdResena())
                .body(creada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody @Valid ResenaUpdateRequestDTO datosNuevos) {
        return ResponseEntity.ok(resenaService.actualizar(id, datosNuevos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
