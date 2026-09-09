package org.example.tiendaspringboot.Controllers;

import org.example.tiendaspringboot.dto.request.EmpleadoRequestDTO;
import org.example.tiendaspringboot.dto.response.EmpleadoResponseDTO;
import org.example.tiendaspringboot.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/NEmpleados")
public class EmpleadosController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping
    public String listar(@RequestParam(required = false) String filtro, Model model) {
        List<EmpleadoResponseDTO> empleados = empleadoService.listar();
        model.addAttribute("empleados", empleados);
        model.addAttribute("empleado", new EmpleadoRequestDTO());
        return "Empleados";
    }

    @PostMapping("/Crear")
    public String crear(@ModelAttribute EmpleadoRequestDTO empleadoRequestDTO) {
        // Cambiarlo por una petición /api/empleados POST
        empleadoService.crear(empleadoRequestDTO);
        return "redirect:/NEmpleados";
    }

    @PostMapping("/Modificar/{id}")
    public String modificar(@PathVariable int id, @ModelAttribute EmpleadoRequestDTO empleadoRequestDTO) {
        // Cambiarlo por una petición /api/empleados/{id} PUT
        empleadoService.actualizar(id, empleadoRequestDTO);
        return "redirect:/NEmpleados";
    }

    @GetMapping("/Borrar/{id}")
    public String borrar(@PathVariable int id) {
        // Cambiarlo por una petición /api/empleados/{id} DELETE
        empleadoService.eliminar(id);
        return "redirect:/NEmpleados";
    }
}