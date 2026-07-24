package org.example.tiendaspringboot.controller;

import org.example.tiendaspringboot.model.Pedido;
import org.example.tiendaspringboot.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable int id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido insertar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable int id, @RequestBody Pedido datosNuevos) {
        return pedidoRepository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setCliente(datosNuevos.getCliente());
            pedidoExistente.setEmpleado(datosNuevos.getEmpleado());
            pedidoExistente.setFechaPedido(datosNuevos.getFechaPedido());
            pedidoExistente.setEstado(datosNuevos.getEstado());
            Pedido actualizado = pedidoRepository.save(pedidoExistente);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
