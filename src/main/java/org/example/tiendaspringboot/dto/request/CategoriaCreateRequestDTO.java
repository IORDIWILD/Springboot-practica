package org.example.tiendaspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaCreateRequestDTO {

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    private Integer categoriaPadreId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoriaPadreId() {
        return categoriaPadreId;
    }

    public void setCategoriaPadreId(Integer categoriaPadreId) {
        this.categoriaPadreId = categoriaPadreId;
    }
}