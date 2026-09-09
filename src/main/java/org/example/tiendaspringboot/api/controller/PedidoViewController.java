package org.example.tiendaspringboot.api.controller;

import org.example.tiendaspringboot.dto.request.PedidoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.PedidoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.PedidoResponseDTO;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoViewController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String mostrarMenuPedidos() {
        return "pedidos/pedidos";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<PedidoResponseDTO> pedidos = pedidoService.listar();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/pedidos";
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam("id") Integer id, Model model) {
        try {
            PedidoResponseDTO pedido = pedidoService.buscarPorId(id);
            model.addAttribute("pedidos", List.of(pedido));
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", "Pedido no encontrado");
        }
        return "pedidos/pedidos";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("pedido", new PedidoCreateRequestDTO());
        return "pedidos/crear";
    }

    @PostMapping("/crear/guardar")
    public String crear(@ModelAttribute PedidoCreateRequestDTO dto) {
        pedidoService.crear(dto);
        return "redirect:/pedidos";
    }

    @GetMapping("/actualizar")
    public String mostrarFormularioActualizar(Model model) {
        model.addAttribute("pedido", new PedidoUpdateRequestDTO());
        return "pedidos/actualizar";
    }

    @PutMapping("/actualizar/guardar")
    public String actualizar(@RequestParam("id") Integer id,
                             @ModelAttribute PedidoUpdateRequestDTO dto) {
        pedidoService.actualizar(id, dto);
        return "redirect:/pedidos";
    }

    @GetMapping("/borrar")
    public String mostrarFormularioBorrar() {
        return "pedidos/borrar";
    }

    @DeleteMapping("/borrar/guardar")
    public String borrar(@RequestParam("id") Integer id) {
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}