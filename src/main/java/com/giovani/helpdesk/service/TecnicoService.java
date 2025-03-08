package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico getTecnicoDetails(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }
}
