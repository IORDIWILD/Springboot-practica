package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Categoria;
import org.example.tiendaspringboot.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<Producto> productoRowMapper = (rs, rowNum) -> {
      Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getBigDecimal("precio"));
        Integer idCategoria = rs.getObject("id_categoria",Integer.class);
        if(idCategoria!=null){
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setNombre("categoria_nombre");
            producto.setCategoria(categoria);
;        }
        return producto;
    };

    public List<Producto> findAll(){
        String query = """
                SELECT p.id_producto, p.nombre, p.precio, p.id_categoria, c.nombre AS categoria_nombre FROM producto p
                LEFT JOIN categoria c ON c.id_categoria = p.id_categoria
                """;
        return jdbcTemplate.query(query,productoRowMapper);
    }

    public Optional<Producto> findById(Integer id){
        String query = """
                SELECT p.id_producto, p.nombre, p.precio, p.id_categoria, c.nombre AS categoria_nombre FROM producto p
                LEFT JOIN categoria c ON c.id_categoria = p.id_categoria
                WHERE p.id_producto = ?
                """;
        try{
            Producto producto = jdbcTemplate.queryForObject(query,productoRowMapper,id);
            return Optional.of(producto);
        }
        catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public Producto save(Producto producto){
        if(producto.getIdProducto()==null){
         //create
            String query = """
                    INSERT INTO producto (nombre, precio, id_categoria) VALUES (?,?,?)
                    """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,producto.getNombre());
                ps.setBigDecimal(2,producto.getPrecio());
                ps.setInt(3,producto.getCategoria().getIdCategoria());
                return ps;
            },keyHolder);
            if(keyHolder.getKey() != null){
                producto.setIdProducto(keyHolder.getKey().intValue());
                return producto;
            }
            return producto;


        }else{
            //update
            String query = """
                    UPDATE producto SET nombre = ? , precio = ?, id_categoria = ? 
                    WHERE id_producto = ?
                    """;
            jdbcTemplate.update(query,producto.getNombre(),producto.getPrecio(),producto.getCategoria().getIdCategoria(),
            producto.getIdProducto());
            return producto;
        }
    }

    public void deleteById(Integer id){
        String query = """
                DELETE FROM producto WHERE id_producto = ?
                """;

        jdbcTemplate.update(query, id);
    }

    public boolean existsById(Integer id){
        String query = """
                SELECT EXISTS (
                    SELECT 1 FROM producto
                    WHERE id_producto = ?
                ) as existe
                """;
        return jdbcTemplate.queryForObject(query, Boolean.class,id);
    }
}
