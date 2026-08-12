package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private RowMapper<Cliente> clienteRowMapper = (rs, rowNum) -> {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setEmail(rs.getString("email"));
        cliente.setCiudad(rs.getString("ciudad"));
        cliente.setFechaRegistro(rs.getObject("fecha_registro", LocalDate.class));
        cliente.setTelefono(rs.getString("telefono"));
        return cliente;
    };

    public List<Cliente> findAll(){
        String query = """
                SELECT id_cliente, nombre, email, ciudad, fecha_registro, telefono
                FROM cliente
                """;
        return jdbcTemplate.query(query,clienteRowMapper);
    }

    public Optional<Cliente> findById(Integer id){
        String query = """
                SELECT id_cliente, nombre, email, ciudad, fecha_registro, telefono
                FROM cliente
                WHERE id_cliente = ?
                """;
        try{
            Cliente cliente = jdbcTemplate.queryForObject(query, clienteRowMapper, id);
            return Optional.ofNullable(cliente);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public Cliente save(Cliente cliente){
        String query = """
                INSERT INTO cliente (nombre, email, ciudad, fecha_registro, telefono) VALUES (?,?,?,?,?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,cliente.getNombre());
            ps.setString(2,cliente.getEmail());
            ps.setString(3,cliente.getCiudad());
            ps.setObject(4,cliente.getFechaRegistro());
            ps.setString(5,cliente.getTelefono());
            return ps;
        }, keyHolder);

        if(keyHolder.getKey()!=null){
            cliente.setIdCliente(keyHolder.getKey().intValue());
        }
        return cliente;
    }

    public Integer update(Cliente cliente){
        String query = """
                UPDATE cliente SET nombre = ? , email = ?, ciudad = ?, fecha_registro = ?, telefono = ? 
                WHERE id_cliente = ?
                """;
        return jdbcTemplate.update(query, cliente.getNombre(), cliente.getEmail(), cliente.getCiudad(),
        cliente.getFechaRegistro(), cliente.getTelefono(), cliente.getIdCliente());
    }

    public Integer deleteById(Integer id){
        String query = """
                DELETE FROM cliente WHERE id_cliente = ?
                """;
        return jdbcTemplate.update(query,id);
    }

    public boolean existsById(Integer id){
        String query = """
                SELECT EXISTS(
                    SELECT 1 FROM cliente
                    WHERE id_cliente = ?
                ) as existe
                """;
        return jdbcTemplate.queryForObject(query, Boolean.class,id);
    }



}
