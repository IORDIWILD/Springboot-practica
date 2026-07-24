package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
