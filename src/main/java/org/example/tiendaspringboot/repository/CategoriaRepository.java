package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
}
