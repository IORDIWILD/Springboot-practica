package org.example.tiendaspringboot.controller;

import jakarta.validation.Valid;
import org.example.tiendaspringboot.dto.request.PedidoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.PedidoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.PedidoResponseDTO;
import org.example.tiendaspringboot.model.Pedido;
import org.example.tiendaspringboot.repository.PedidoRepository;
import org.example.tiendaspringboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPorCliente(@PathVariable Integer idCliente){
        return ResponseEntity.ok(pedidoService.buscarPorCliente(idCliente));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPorEstado(@PathVariable String estado){
        return ResponseEntity.ok(pedidoService.buscarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> insertar(@RequestBody @Valid PedidoCreateRequestDTO pedido) {
        PedidoResponseDTO creado = pedidoService.crear(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> actualizar(@PathVariable int id, @RequestBody @Valid PedidoUpdateRequestDTO
                                                        datosNuevos) {
        return ResponseEntity.ok(pedidoService.actualizar(id,datosNuevos));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoResponseDTO> actualizarEstado(@PathVariable Integer id, @RequestParam String estado){
        return ResponseEntity.ok(pedidoService.actualizarEstado(id,estado));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id) {
      pedidoService.eliminar(id);
      return ResponseEntity.noContent().build();
    }
}
