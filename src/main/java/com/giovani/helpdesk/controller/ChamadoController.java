package com.giovani.helpdesk.controller;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.dtos.ChamadoDTO;
import com.giovani.helpdesk.service.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
}
