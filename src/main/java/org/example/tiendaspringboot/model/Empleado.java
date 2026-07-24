package org.example.tiendaspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre",nullable = false,length = 50)
    private String nombre;

    @Column(name = "puesto",length = 50)
    private String puesto;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "jefe_id")
    private Empleado jefe;

    @OneToMany(mappedBy = "jefe")
    @JsonIgnore
    private List<Empleado> subordinados;

    public Empleado() {
    }

    public Empleado(String nombre, String puesto, BigDecimal salario, LocalDate fechaContratacion, Empleado jefe, List<Empleado> subordinados) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.jefe = jefe;
        this.subordinados = subordinados;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    public List<Empleado> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(List<Empleado> subordinados) {
        this.subordinados = subordinados;
    }
}
