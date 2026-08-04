package org.example.tiendaspringboot.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoResponseDTO {
    private Integer idPedido;
    private ClienteResumenDTO cliente;
    private EmpleadoResumenDTO empleado;
    private LocalDate fechaPedido;
    private String estado;
    private List<DetallePedidoResponseDTO> detalles;
    private BigDecimal total;

    public PedidoResponseDTO() {
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public ClienteResumenDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResumenDTO cliente) {
        this.cliente = cliente;
    }

    public EmpleadoResumenDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoResumenDTO empleado) {
        this.empleado = empleado;
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

    public List<DetallePedidoResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoResponseDTO> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
