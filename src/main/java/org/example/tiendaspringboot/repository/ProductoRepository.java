package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    
}
