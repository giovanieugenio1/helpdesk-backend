package com.giovani.helpdesk.controller;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.dtos.ChamadoDTO;
import com.giovani.helpdesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getChamadoDetails(@PathVariable("id") Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<Chamado> chamados = chamadoService.findAll();
        List<ChamadoDTO> list = chamados.stream().map(ChamadoDTO::new).toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO dto) {
        Chamado chamado = chamadoService.createChamado(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(chamado.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable("id") Integer id, @Valid @RequestBody ChamadoDTO dto) {
        Chamado chamado = chamadoService.updateChamado(id, dto);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
