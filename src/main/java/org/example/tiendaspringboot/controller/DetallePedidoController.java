package org.example.tiendaspringboot.controller;

import org.example.tiendaspringboot.model.DetallePedido;
import org.example.tiendaspringboot.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle-pedido")
public class DetallePedidoController {
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @GetMapping
    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> buscarPorId(@PathVariable int id) {
        Optional<DetallePedido> detalle = detallePedidoRepository.findById(id);
        return detalle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetallePedido insertar(@RequestBody DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizar(@PathVariable int id, @RequestBody DetallePedido datosNuevos) {
        return detallePedidoRepository.findById(id).map(detalleExistente -> {
            detalleExistente.setPedido(datosNuevos.getPedido());
            detalleExistente.setProducto(datosNuevos.getProducto());
            detalleExistente.setCantidad(datosNuevos.getCantidad());
            detalleExistente.setPrecioUnitario(datosNuevos.getPrecioUnitario());
            DetallePedido actualizado = detallePedidoRepository.save(detalleExistente);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable int id) {
        if (!detallePedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        detallePedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
