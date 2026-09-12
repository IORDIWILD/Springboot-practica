package org.example.tiendaspringboot.Controllers;


import org.example.tiendaspringboot.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriasController{

    private final CategoriaService categoriaService;
    public CategoriasController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    
}



