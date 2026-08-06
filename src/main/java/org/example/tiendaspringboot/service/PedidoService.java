package org.example.tiendaspringboot.service;

import jakarta.transaction.Transactional;
import org.example.tiendaspringboot.dto.request.DetallePedidoRequestDTO;
import org.example.tiendaspringboot.dto.request.PedidoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.PedidoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.PedidoResponseDTO;
import org.example.tiendaspringboot.exception.BusinessException;
import org.example.tiendaspringboot.exception.ResourceNotFoundException;
import org.example.tiendaspringboot.mapper.PedidoMapper;
import org.example.tiendaspringboot.model.*;
import org.example.tiendaspringboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    public List<PedidoResponseDTO> listar(){
        return pedidoRepository.findAll().stream().map(pedidoMapper::toResponseDTO).toList();
    }

    public PedidoResponseDTO buscarPorId(Integer id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido", id)
        );
        return pedidoMapper.toResponseDTO(pedido);
    }

    public PedidoResponseDTO crear(PedidoCreateRequestDTO dto){
        Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElseThrow(
                () -> new ResourceNotFoundException("Cliente", dto.getIdCliente())
        );
        Empleado empleado = null;
        if(dto.getIdEmpleado() != null){
            empleado = empleadoRepository.findById(dto.getIdEmpleado()).orElseThrow(
                    () -> new ResourceNotFoundException("Empleado", dto.getIdEmpleado())
            );
        }
        Pedido pedido = pedidoMapper.toEntity(dto);
        pedido.setCliente(cliente);
        pedido.setEmpleado(empleado);

        //procesar detalles
        List<DetallePedido> detalles = new ArrayList<>();
        for(DetallePedidoRequestDTO detDTO : dto.getDetalles()){
            Producto producto = productoRepository.findById(detDTO.getIdProducto()).orElseThrow(
                    () -> new ResourceNotFoundException("Producto", detDTO.getIdProducto())
            );
            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(detDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setPedido(pedido);
            detalles.add(detalle);
        }
        pedido.setDetalles(detalles);

        Pedido guardado = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(guardado);
    }

    public PedidoResponseDTO actualizar(Integer id, PedidoUpdateRequestDTO dto){
        Pedido existente = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido", id)
        );
        pedidoMapper.actualizarParcial(dto,existente);
        if(existente.getEstado() == Pedido.EstadoPedido.entregado ||
           existente.getEstado() == Pedido.EstadoPedido.cancelado){
            throw new BusinessException("No se puede modificar un pedido " + existente.getEstado().name());
        }

        if(dto.getIdCliente() != null){
            Cliente nuevoCliente = clienteRepository.findById(dto.getIdCliente())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente", dto.getIdCliente()));
            existente.setCliente(nuevoCliente);
        }

        if (dto.getIdEmpleado() != null) {
            Empleado nuevoEmpleado = empleadoRepository.findById(dto.getIdEmpleado())
                    .orElseThrow(() -> new ResourceNotFoundException("Empleado", dto.getIdEmpleado()));
            existente.setEmpleado(nuevoEmpleado);
        }
        Pedido actualizado = pedidoRepository.save(existente);
        return pedidoMapper.toResponseDTO(actualizado);
    }

    public void eliminar(Integer id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pedido",id)
        );
        if(pedido.getEstado() != Pedido.EstadoPedido.pendiente){
            throw new BusinessException("No se puede eliminar un pedido en estado: " + pedido.getEstado().name());
        }
        pedidoRepository.delete(pedido);
    }

    public List<PedidoResponseDTO> buscarPorCliente(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", idCliente));

        return pedidoRepository.findByCliente(cliente).stream()
                .map(pedidoMapper::toResponseDTO).toList();
    }

    public List<PedidoResponseDTO> buscarPorEstado(String estado){
        Pedido.EstadoPedido estadoEmum;
        try{
            estadoEmum = Pedido.EstadoPedido.valueOf(estado);
        }catch(IllegalArgumentException e){
            throw new BusinessException("Estado invalido " + estado);
        }
        return pedidoRepository.findByEstado(estadoEmum).stream().map(pedidoMapper::toResponseDTO).toList();
    }

    public PedidoResponseDTO actualizarEstado(Integer id, String nuevoEstado){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        Pedido.EstadoPedido estadoEnum;
        try {
            estadoEnum = Pedido.EstadoPedido.valueOf(nuevoEstado);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Estado inválido: " + nuevoEstado);
        }
        if (pedido.getEstado() == Pedido.EstadoPedido.entregado &&
                estadoEnum != Pedido.EstadoPedido.entregado) {
            throw new BusinessException("Un pedido entregado no puede cambiar de estado");
        }
        if (pedido.getEstado() == Pedido.EstadoPedido.cancelado) {
            throw new BusinessException("Un pedido cancelado no puede cambiar de estado");
        }
        pedido.setEstado(estadoEnum);
        Pedido actualizado = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(actualizado);
    }
}
