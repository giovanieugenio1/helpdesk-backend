package com.giovani.helpdesk.controller;

import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.dtos.TecnicoDTO;
import com.giovani.helpdesk.service.TecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/tecnicos")
@RestController
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<TecnicoDTO> getTecnicoDetails(@PathVariable("id") Integer id) {
        var tecnico = tecnicoService.getTecnicoDetails(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> getAllTecnico() {
        List<Tecnico> list = tecnicoService.getAll();
        List<TecnicoDTO> dto = list.stream().map(TecnicoDTO::new).toList();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> createTecnico(@RequestBody TecnicoDTO obj) {
        Tecnico tecnico = tecnicoService.createTecnico(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
