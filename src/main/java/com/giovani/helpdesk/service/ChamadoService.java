package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id)
        );
    }
}
