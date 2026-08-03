package org.example.tiendaspringboot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductoCreateRequestDTO {
    @NotBlank
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;


    @NotNull(message = "El ID de la categoría es obligatorio")
    private Integer idCategoria;

    @NotNull
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    public ProductoCreateRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
