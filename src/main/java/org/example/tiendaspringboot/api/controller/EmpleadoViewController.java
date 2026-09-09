package org.example.tiendaspringboot.api.controller;


import org.example.tiendaspringboot.dto.request.EmpleadoRequestDTO;
import org.example.tiendaspringboot.dto.response.EmpleadoResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoViewController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String mostrarMenuEmpleados(){
        return "empleados/empleados";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        List<EmpleadoResponseDTO> empleados = empleadoService.listar();
        model.addAttribute("empleados", empleados);
        return "empleados/empleados";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model){
        try{
            EmpleadoResponseDTO empleadoResponseDTO = empleadoService.buscarPorId(id);
            model.addAttribute("empleados", List.of(empleadoResponseDTO));
        }catch(ResourceNotFoundException e){
            model.addAttribute("Error", "Empleado no encontrado");
        }
        return "empleados/empleados";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("empleado", new EmpleadoRequestDTO());
        return "empleados/crear";
    }

    @PostMapping("/crear/guardar")
    public String crear(@ModelAttribute EmpleadoRequestDTO empleadoRequestDTO){
        empleadoService.crear(empleadoRequestDTO);
        return "redirect:/empleados";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model){
        model.addAttribute("empleado", new EmpleadoRequestDTO());
        return "empleados/actualizar";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id, @ModelAttribute EmpleadoRequestDTO empleadoRequestDTO){
        empleadoService.actualizar(id, empleadoRequestDTO);
        return "redirect:/empleados";
    }

    @GetMapping("/borrar")
    public String mostrarFormularioBorrar(){
        return "empleados/borrar";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id){
        empleadoService.eliminar(id);
        return "redirect:/empleados";
    }
}