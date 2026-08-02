package org.example.tiendaspringboot.dto.response;

import java.math.BigDecimal;

public class ProductoResponseDTO {
    private Integer idProducto;
    private String nombre;
    private CategoriaResumenDTO categoria;
    private BigDecimal precio;


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaResumenDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResumenDTO categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
