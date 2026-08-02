package org.example.tiendaspringboot.dto.response;

public class CategoriaResponseDTO {
    private Integer idCategoria;
    private String nombre;
    private CategoriaPadreResumenDTO categoriaPadre;

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

    public CategoriaPadreResumenDTO getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(CategoriaPadreResumenDTO categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
}