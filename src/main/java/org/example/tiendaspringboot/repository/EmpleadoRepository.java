package org.example.tiendaspringboot.repository;

import org.example.tiendaspringboot.model.Empleado;
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
public class EmpleadoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Empleado> empleadoRowMapper = (rs, rowNum) -> {
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(rs.getInt("id_empleado"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setPuesto(rs.getString("puesto"));
        empleado.setFechaContratacion(rs.getObject("fecha_contratacion", LocalDate.class));
        empleado.setSalario(rs.getBigDecimal("salario"));
        Integer idJefe = rs.getObject("jefe_id", Integer.class);
        if(idJefe!=null){
            Empleado jefe = new Empleado();
            jefe.setIdEmpleado(idJefe);
            jefe.setNombre(rs.getString("jefe_nombre"));
            jefe.setPuesto(rs.getString("jefe_puesto"));
            empleado.setJefe(jefe);
        }
        return empleado;
    };

    public List<Empleado> findAll(){
        String query = """
                SELECT e.id_empleado, e.nombre, e.puesto, e.fecha_contratacion, e.salario, e.jefe_id,
                jefe.nombre AS jefe_nombre, jefe.puesto AS jefe_puesto FROM empleado e
                LEFT JOIN empleado jefe ON e.jefe_id = jefe.id_empleado
                """;
        return jdbcTemplate.query(query,empleadoRowMapper);
    }

    public Optional<Empleado> findById(Integer id){
        String query = """
                SELECT e.id_empleado, e.nombre, e.puesto, e.fecha_contratacion, e.salario, e.jefe_id,
                jefe.nombre AS jefe_nombre, jefe.puesto AS jefe_puesto FROM empleado e
                LEFT JOIN empleado jefe ON e.jefe_id = jefe.id_empleado
                WHERE e.id_empleado = ?
                """;
        try{
            Empleado empleado = jdbcTemplate.queryForObject(query,empleadoRowMapper,id);
            return Optional.of(empleado);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public Empleado save(Empleado empleado){
        if(empleado.getIdEmpleado()==null){
            //create
            String query = """
                    INSERT INTO empleado (nombre, puesto, fecha_contratacion, salario, jefe_id) VALUES (?,?,?,?,?)
                    """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,empleado.getNombre());
                ps.setString(2,empleado.getPuesto());
                ps.setObject(3,empleado.getFechaContratacion());
                ps.setBigDecimal(4,empleado.getSalario());
                if(empleado.getJefe()!=null){
                    ps.setInt(5,empleado.getJefe().getIdEmpleado());
                }else{
                    ps.setObject(5,null);
                }
                return ps;
            },keyHolder);
            if(keyHolder.getKey()!=null){
                empleado.setIdEmpleado(keyHolder.getKey().intValue());
            }
            return empleado;

        }else{
            //update
            String query = """
                    UPDATE empleado SET nombre = ?, puesto = ?, fecha_contratacion = ?, salario = ?, jefe_id = ?
                    WHERE id_empleado = ?
                    """;
            jdbcTemplate.update(query,empleado.getNombre(), empleado.getPuesto(), empleado.getFechaContratacion(),
                    empleado.getSalario(), empleado.getJefe() != null ? empleado.getJefe().getIdEmpleado():null,empleado.getIdEmpleado());

            return empleado;

        }
    }

    public void deleteById(Integer id){
        String query = """
                DELETE FROM empleado
                WHERE id_empleado = ?
                """;
        jdbcTemplate.update(query,id);

    }

    public boolean existsById(Integer id){
        String query = """
                SELECT EXISTS(
                    SELECT 1 FROM empleado
                    WHERE id_empleado = ?
                ) as existe
                """;
        return jdbcTemplate.queryForObject(query, Boolean.class,id);
    }




}
