package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Cliente;
import org.example.tiendaspringboot.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
}
