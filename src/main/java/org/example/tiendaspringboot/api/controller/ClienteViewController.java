package org.example.tiendaspringboot.api.controller;


import org.example.tiendaspringboot.dto.request.ClienteCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ClienteUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ClienteResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;

import org.example.tiendaspringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String mostrarMenuClientes(){
        return "clientes/clientes";
    }


    @GetMapping("/listar")
    public String listar(Model model){
        List<ClienteResponseDTO> clientes = clienteService.listar();
        model.addAttribute("clientes",clientes);
        return "clientes/clientes";
    }


    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model){
        try{
            ClienteResponseDTO empleados = clienteService.buscarPorId(id);
            model.addAttribute("empleados", List.of(empleados));
        }catch(ResourceNotFoundException e){
            model.addAttribute("Error", "Categoria no encontrada");
        }
        return "clientes/clientes";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("cliente", new ClienteCreateRequestDTO());
        return "clientes/crear";
    }

    @PostMapping("crear/guardar")
    public String crear(@ModelAttribute ClienteCreateRequestDTO clienteCreateRequestDTO){
        clienteService.crear(clienteCreateRequestDTO);
        return "redirect:/clientes";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model){
        model.addAttribute("cliente", new ClienteUpdateRequestDTO());
        return "clientes/actualizar";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id, @ModelAttribute ClienteUpdateRequestDTO clienteUpdateRequestDTO){
        clienteService.actualizar(id,clienteUpdateRequestDTO);
        return "redirect:/clientes";
    }

    @GetMapping("/borrar")
    public String mostrarFormularioborrar(){
        return "clientes/borrar";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id){
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}
