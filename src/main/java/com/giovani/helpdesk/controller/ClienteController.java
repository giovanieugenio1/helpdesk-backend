package com.giovani.helpdesk.controller;

import com.giovani.helpdesk.domain.Cliente;
import com.giovani.helpdesk.dtos.ClienteDTO;
import com.giovani.helpdesk.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RequestMapping("/clientes")
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteDetails(@PathVariable("id") Integer id) {
        var cliente = clienteService.getClienteDetails(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllCliente() {
        List<Cliente> list = clienteService.getAll();
        List<ClienteDTO> dto = list.stream().map(ClienteDTO::new).toList();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO obj) {
        Cliente cliente = clienteService.createCliente(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO dto) {
        Cliente obj = clienteService.update(id, dto);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
