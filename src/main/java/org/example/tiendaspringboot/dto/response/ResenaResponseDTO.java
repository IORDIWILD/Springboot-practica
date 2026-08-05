package org.example.tiendaspringboot.dto.response;

import java.time.LocalDate;

public class ResenaResponseDTO {
    private Integer idResena;
    private ProductoResumenDTO producto;
    private ClienteResumenDTO cliente;
    private Integer calificacion;
    private String comentario;
    private LocalDate fecha;

    public ResenaResponseDTO() {
    }

    public Integer getIdResena() {
        return idResena;
    }

    public void setIdResena(Integer idResena) {
        this.idResena = idResena;
    }

    public ProductoResumenDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoResumenDTO producto) {
        this.producto = producto;
    }

    public ClienteResumenDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumenDTO cliente) {
        this.cliente = cliente;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
