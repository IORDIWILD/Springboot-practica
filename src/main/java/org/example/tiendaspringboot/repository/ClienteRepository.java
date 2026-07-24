package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
