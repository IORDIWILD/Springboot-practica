package org.example.tiendaspringboot.dto.request;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class PedidoUpdateRequestDTO {
    private Integer idCliente;
    private Integer idEmpleado;
    private LocalDate fechaPedido;

    @Pattern(regexp = "pendiente|enviado|entregado|cancelado",
            message =" Estado debe ser pendiente, enviado, entregado, cancelado")
    private String estado;

    public PedidoUpdateRequestDTO() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
