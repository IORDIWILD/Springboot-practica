package org.example.tiendaspringboot.api.controller;

import org.example.tiendaspringboot.dto.request.ProductoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ProductoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ProductoResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoViewController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String mostrarMenuProductos() {
        return "productos/productos";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<ProductoResponseDTO> productos = productoService.listar();
        model.addAttribute("productos", productos);
        return "productos/productos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        try {
            ProductoResponseDTO producto = productoService.buscarPorId(id);
            model.addAttribute("productos", List.of(producto));
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Producto no encontrado");
        }
        return "productos/productos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new ProductoCreateRequestDTO());
        return "productos/crear";
    }

    @PostMapping("/crear/guardar")
    public String crear(@ModelAttribute ProductoCreateRequestDTO dto) {
        productoService.crear(dto);
        return "redirect:/productos";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model) {
        model.addAttribute("producto", new ProductoUpdateRequestDTO());
        return "productos/actualizar";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id,
                             @ModelAttribute ProductoUpdateRequestDTO dto) {
        productoService.actualizar(id, dto);
        return "redirect:/productos";
    }

    @GetMapping("/borrar")
    public String mostrarFormularioBorrar() {
        return "productos/borrar";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}