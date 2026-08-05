package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Cliente;
import org.example.tiendaspringboot.model.Producto;
import org.example.tiendaspringboot.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena,Integer> {

    List<Resena> findByProducto(Producto producto);
    List<Resena> findByCliente(Cliente cliente);
    boolean existsByProductoAndCliente(Producto producto, Cliente cliente);
}
