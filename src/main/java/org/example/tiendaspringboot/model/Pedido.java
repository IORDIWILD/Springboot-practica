package org.example.tiendaspringboot.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles;



    public enum EstadoPedido{
        pendiente, enviado,entregado, cancelado
    }

    public Pedido() {
    }

    public Pedido(Integer idPedido, Cliente cliente, Empleado empleado, LocalDate fechaPedido, EstadoPedido estado, List<DetallePedido> detalles) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.detalles = detalles;
    }

    public Pedido(Cliente cliente, Empleado empleado, LocalDate fechaPedido, EstadoPedido estado, List<DetallePedido> detalles) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.detalles = detalles;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
