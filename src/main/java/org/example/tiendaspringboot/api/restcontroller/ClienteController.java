package org.example.tiendaspringboot.api.restcontroller;



import jakarta.validation.Valid;
import org.example.tiendaspringboot.dto.request.ClienteCreateRequestDTO;
import org.example.tiendaspringboot.dto.request.ClienteUpdateRequestDTO;
import org.example.tiendaspringboot.dto.response.ClienteResponseDTO;
import org.example.tiendaspringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable int id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> insertar(@RequestBody @Valid ClienteCreateRequestDTO cliente){
       ClienteResponseDTO creado = clienteService.crear(cliente);
       return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(@PathVariable Integer id, @RequestBody @Valid ClienteUpdateRequestDTO datosNuevos){
        return ResponseEntity.ok(clienteService.actualizar(id,datosNuevos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
