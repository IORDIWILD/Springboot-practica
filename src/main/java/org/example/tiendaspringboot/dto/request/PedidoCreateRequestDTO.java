package org.example.tiendaspringboot.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class PedidoCreateRequestDTO {

    @NotNull
    private Integer idCliente;

    private Integer idEmpleado;

    private LocalDate fechaPedido;


    @Pattern(regexp = "pendiente|enviado|entregado|cancelado",
             message =" Estado debe ser pendiente, enviado, entregado, cancelado")
    private String estado;

    @NotNull(message = "El pedido debe de tener almenos un detalle")
    @Size(min = 1, message = "El pedido debe de tener almenos un producto")
    private List<DetallePedidoRequestDTO> detalles;

    public PedidoCreateRequestDTO() {
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

    public List<DetallePedidoRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequestDTO> detalles) {
        this.detalles = detalles;
    }
}
