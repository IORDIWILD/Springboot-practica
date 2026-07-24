package org.example.tiendaspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.tiendaspringboot.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
}
