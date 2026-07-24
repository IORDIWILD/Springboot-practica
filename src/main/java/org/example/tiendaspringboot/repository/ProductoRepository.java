package org.example.tiendaspringboot.repository;

import java.math.BigDecimal;
import java.util.List;
import org.example.tiendaspringboot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    List<Producto> findByCategoriaIdCategoria(Integer idCategoria);


    List<Producto> findByNombreContainingIgnoreCase(String nombre);


    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);


    List<Producto> findByPrecioGreaterThan(BigDecimal precio);

    List<Producto> findByPrecioLessThan(BigDecimal precio);
    
}
