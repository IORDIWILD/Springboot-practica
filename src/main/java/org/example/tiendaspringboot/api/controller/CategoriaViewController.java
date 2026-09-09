package org.example.tiendaspringboot.api.controller;

import org.example.tiendaspringboot.dto.request.CategoriaCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.CategoriaUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.CategoriaResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaViewController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public String mostrarMenuCategorias(){
        return "categorias/categorias";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List<CategoriaResponseDTO> categorias = categoriaService.listar();
        model.addAttribute("categorias",categorias);
        return "categorias/categorias";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model){
        try{
            CategoriaResponseDTO categoria = categoriaService.buscarPorId(id);
            model.addAttribute("categorias", List.of(categoria));
        }catch(ResourceNotFoundException e){
            model.addAttribute("Error", "Categoria no encontrada");
        }
        return "categorias/categorias";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("categoriaRequest", new CategoriaCreateRequestDTO());
        model.addAttribute("listaCategorias", categoriaService.listar());
        return "categorias/nuevaCategoria";
    }

    @PostMapping("/nuevo/guardar")
    public String guardarCategoria(@ModelAttribute CategoriaCreateRequestDTO categoriaCreateRequestDTO){
        categoriaService.crear(categoriaCreateRequestDTO);
        return "redirect:/categorias";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model){
        model.addAttribute("categoriaRequest", new CategoriaUpdateRequestDTO());
        model.addAttribute("listaCategorias", categoriaService.listar());
        return "categorias/actualizarCategoria";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id, @ModelAttribute CategoriaUpdateRequestDTO categoriaUpdateRequestDTO){
        categoriaService.actualizar(id,categoriaUpdateRequestDTO);
        return "redirect:/categorias";
    }

    @GetMapping("/borrar")
    public  String mostrarFormularioBorrar(){
        return "categorias/borrarCategoria";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id){
        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }

}
