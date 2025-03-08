package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico getTecnicoDetails(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto não encontrado. ID: " + id)
        );
    }

    public List<Tecnico> getAll() {
        return tecnicoRepository.findAll();
    }
}
