package org.example.tiendaspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

    @OneToMany(mappedBy = "categoriaPadre")
    @JsonIgnore
    private List<Categoria> subCategorias;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombre, Categoria categoriaPadre, List<Categoria> subCategorias) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.categoriaPadre = categoriaPadre;
        this.subCategorias = subCategorias;
    }

    public Categoria(String nombre, Categoria categoriaPadre, List<Categoria> subCategorias) {
        this.nombre = nombre;
        this.categoriaPadre = categoriaPadre;
        this.subCategorias = subCategorias;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public List<Categoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<Categoria> subCategorias) {
        this.subCategorias = subCategorias;
    }
}
