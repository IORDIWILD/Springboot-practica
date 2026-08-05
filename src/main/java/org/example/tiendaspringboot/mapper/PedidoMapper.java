package org.example.tiendaspringboot.mapper;

import org.example.tiendaspringboot.dto.request.PedidoCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.PedidoUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.*;
import org.example.tiendaspringboot.model.DetallePedido;
import org.example.tiendaspringboot.model.Pedido;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoCreateRequestDTO dto){
        if(dto==null) return null;
       Pedido pedido = new Pedido();
       pedido.setFechaPedido(dto.getFechaPedido() != null ? dto.getFechaPedido() : LocalDate.now());
       if(dto.getEstado() != null && !dto.getEstado().isEmpty()){
           pedido.setEstado(Pedido.EstadoPedido.valueOf(dto.getEstado()));
       }else{
           pedido.setEstado(Pedido.EstadoPedido.pendiente);
       }
       pedido.setDetalles(new ArrayList<>());
       return pedido;
    }

    public void actualizarParcial(PedidoUpdateRequestDTO dto, Pedido existente){
        if(dto.getFechaPedido() != null){
            existente.setFechaPedido(dto.getFechaPedido());
        }
        if(dto.getEstado()!=null){
            existente.setEstado(Pedido.EstadoPedido.valueOf(dto.getEstado()));
        }
        //actualizar cliente y empleado en servicio
    }

    public PedidoResponseDTO toResponseDTO(Pedido pedido){
        if(pedido==null) return null;

        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setIdPedido(pedido.getIdPedido());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setEstado(pedido.getEstado().name());


        if(pedido.getCliente() != null){
            ClienteResumenDTO clienteDTO = new ClienteResumenDTO();
            clienteDTO.setIdCliente(pedido.getCliente().getIdCliente());
            clienteDTO.setNombre(pedido.getCliente().getNombre());
            clienteDTO.setEmail(pedido.getCliente().getEmail());
            dto.setCliente(clienteDTO);
        }

        if(pedido.getEmpleado() != null){
            EmpleadoResumenDTO empleadoDTO = new EmpleadoResumenDTO();
            empleadoDTO.setIdEmpleado(pedido.getEmpleado().getIdEmpleado());
            empleadoDTO.setNombre(pedido.getEmpleado().getNombre());
            empleadoDTO.setPuesto(pedido.getEmpleado().getPuesto());
            dto.setEmpleado(empleadoDTO);
        }

        if(pedido.getDetalles() != null && !pedido.getDetalles().isEmpty()){
            List<DetallePedidoResponseDTO> detallesDTO = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;

            for(DetallePedido detalle : pedido.getDetalles()){
                DetallePedidoResponseDTO detDTO = new DetallePedidoResponseDTO();
                detDTO.setIdDetalle(detalle.getIdDetalle());
                detDTO.setCantidad(detalle.getCantidad());
                detDTO.setPrecioUnitario(detalle.getPrecioUnitario());

                //subtotal
                BigDecimal subtotal = detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad()));
                detDTO.setSubtotal(subtotal);
                total = total.add(subtotal);

                //Producto resumen
                if(detalle.getProducto() != null){
                    ProductoResumenDTO productoDTO = new ProductoResumenDTO();
                    productoDTO.setIdProducto(detalle.getProducto().getIdProducto());
                    productoDTO.setNombre(detalle.getProducto().getNombre());
                    productoDTO.setPrecio(detalle.getProducto().getPrecio());
                    detDTO.setProducto(productoDTO);
                }
                detallesDTO.add(detDTO);
            }
            dto.setDetalles(detallesDTO);
            dto.setTotal(total);

        }

        return dto;
    }


}
