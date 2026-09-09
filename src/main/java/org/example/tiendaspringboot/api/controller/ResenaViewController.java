package org.example.tiendaspringboot.api.controller;

import org.example.tiendaspringboot.dto.request.ResenaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ResenaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ResenaResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resenas")
public class ResenaViewController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public String mostrarMenuResenas() {
        return "resenas/resenas";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<ResenaResponseDTO> resenas = resenaService.listar();
        model.addAttribute("resenas", resenas);
        return "resenas/resenas";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        try {
            ResenaResponseDTO resena = resenaService.buscarPorId(id);
            model.addAttribute("resenas", List.of(resena));
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Reseña no encontrada");
        }
        return "resenas/resenas";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("resena", new ResenaCreateRequestDTO());
        return "resenas/crear";
    }

    @PostMapping("/crear/guardar")
    public String crear(@ModelAttribute ResenaCreateRequestDTO dto) {
        resenaService.crear(dto);
        return "redirect:/resenas";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model) {
        model.addAttribute("resena", new ResenaUpdateRequestDTO());
        return "resenas/actualizar";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id,
                             @ModelAttribute ResenaUpdateRequestDTO dto) {
        resenaService.actualizar(id, dto);
        return "redirect:/resenas";
    }

    @GetMapping("/borrar")
    public String mostrarFormularioBorrar() {
        return "resenas/borrar";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id) {
        resenaService.eliminar(id);
        return "redirect:/resenas";
    }
}