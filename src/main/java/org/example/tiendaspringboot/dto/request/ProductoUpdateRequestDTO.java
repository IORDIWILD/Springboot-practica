package org.example.tiendaspringboot.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductoUpdateRequestDTO {

    @Size(max = 50, message = "El nombre no puede tener mas de 50 caracteres")
    private String nombre;


    private Integer idCategoria;

    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    public ProductoUpdateRequestDTO() {
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
