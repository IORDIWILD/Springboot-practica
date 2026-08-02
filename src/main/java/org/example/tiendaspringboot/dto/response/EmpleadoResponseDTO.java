package org.example.tiendaspringboot.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmpleadoResponseDTO {
    private Integer idEmpleado;
    private String nombre;
    private String puesto;
    private BigDecimal salario;
    private JefeResumenDTO jefe; //resumen del jefe
    private LocalDate fechaContratacion;

    public EmpleadoResponseDTO() {
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

    public JefeResumenDTO getJefe() {
        return jefe;
    }

    public void setJefe(JefeResumenDTO jefe) {
        this.jefe = jefe;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
