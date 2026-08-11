package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.exception.BusinessException;
import org.example.tiendaspringboot.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


@Repository
public class CategoriaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Categoria> categoriaRowMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("id_categoria"));
        categoria.setNombre(rs.getString("nombre"));

        return categoria;
    };

    private final RowMapper<Categoria> categoriaConPadreRowMapper = (rs, rowNum) -> {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("id_categoria"));
        categoria.setNombre(rs.getString("nombre"));
        Integer idPadre = rs.getInt("padre_id");
        if(idPadre > 0){
            Categoria padre = new Categoria();
            padre.setIdCategoria(idPadre);
            padre.setNombre(rs.getString("padre_nombre"));
            categoria.setCategoriaPadre(padre);
        }
        return categoria;
    };


    public Categoria save(Categoria categoria){
        String query = "INSERT INTO categoria (nombre, categoria_padre_id) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,categoria.getNombre());
            ps.setObject(2,categoria.getCategoriaPadre() != null ? categoria.getCategoriaPadre().getIdCategoria():null);
            return ps;
        }, keyHolder );

        if(keyHolder.getKey()!= null){
            categoria.setIdCategoria(keyHolder.getKey().intValue());
        }
        return categoria;
    }

    public List<Categoria> findAll() {
        String sql = """
            SELECT c.id_categoria, c.nombre,
                   padre.id_categoria AS padre_id, 
                   padre.nombre AS padre_nombre
            FROM categoria c
            LEFT JOIN categoria padre ON c.categoria_padre_id = padre.id_categoria
            ORDER BY c.id_categoria DESC
        """;

        return jdbcTemplate.query(sql, categoriaConPadreRowMapper);
    }

    public Optional<Categoria> findById(Integer id){
        String query  = """
                SELECT c.id_categoria, c.nombre,
                padre.id_categoria AS padre_id,
                padre.nombre AS padre_nombre
                FROM categoria c
                LEFT JOIN categoria padre ON c.categoria_padre_id = padre.id_categoria
                WHERE c.id_categoria = ?             
                """;
        try{
            Categoria categoria = jdbcTemplate.queryForObject(query, categoriaConPadreRowMapper, id);
            return Optional.ofNullable(categoria);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    public Integer update(Categoria actualizada){
        String query = """
                UPDATE categoria SET
                nombre= ?, 
                categoria_padre_id = ? 
                WHERE id_categoria = ?
                """;
         return jdbcTemplate.update(query,actualizada.getNombre(),
                actualizada.getCategoriaPadre() != null ? actualizada.getCategoriaPadre().getIdCategoria() : null,
                actualizada.getIdCategoria());
    }

    public int deleteById(Integer id) {

        String queryCount = "SELECT COUNT(*) FROM categoria WHERE categoria_padre_id = ?";
        Integer count = jdbcTemplate.queryForObject(queryCount, Integer.class, id);

        if (count != null && count > 0) {
            throw new BusinessException("No se puede eliminar la categoría porque tiene " + count + " subcategorías");
        }

        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        return jdbcTemplate.update(sql, id);
    }

    public boolean existsById(Integer id) {
        String query = """
        SELECT EXISTS (
            SELECT 1
            FROM categoria
            WHERE id_categoria = ?
        ) AS existe
        """;
        return jdbcTemplate.queryForObject(query, Boolean.class, id);
    }


    public int countSubcategorias(Integer id) {
        String query = "SELECT COUNT(*) FROM categoria WHERE categoria_padre_id = ?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, id);
        return count != null ? count : 0;
    }
}
