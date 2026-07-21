package org.example.tiendaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.tiendaspringboot.model.Producto;
import org.example.tiendaspringboot.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class TiendaSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaSpringbootApplication.class, args);
    }



}
