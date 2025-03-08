package com.giovani.helpdesk.controller;

import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.dtos.TecnicoDTO;
import com.giovani.helpdesk.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
