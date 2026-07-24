package org.example.tiendaspringboot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.IdGeneratorType;


@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_cliente")
    private Integer idCliente;

    @Column(name = "nombre",nullable = false,length = 50)
    private String nombre;

    @Column(name = "email",nullable = false,length = 100, unique = true)
    private String email;

    @Column(name = "ciudad",length = 50)
    private String ciudad;

    @Column(name = "fecha_registro",nullable = false)
    private String fechaRegistro;

    @Column(name = "telefono",length = 20)
    private String telefono;

    public Cliente() {
    }

    public Cliente(Integer id, String nombre, String email, String ciudad, String fechaRegistro, String telefono) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.fechaRegistro = fechaRegistro;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String email, String ciudad, String fechaRegistro, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.fechaRegistro = fechaRegistro;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString(){
        return "Cliente{id=" + idCliente + ", nombre='" + nombre + "', email='" + email + "', ciudad='" + ciudad +
                "', fecha de registro='" + fechaRegistro + "', telefono='" + telefono +"'}";
    }
}
