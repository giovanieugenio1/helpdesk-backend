package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.domain.Cliente;
import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.dtos.ChamadoDTO;
import com.giovani.helpdesk.enums.Prioridade;
import com.giovani.helpdesk.enums.Status;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.repository.ChamadoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ClienteService clienteService;
    private final TecnicoService tecnicoService;

    public ChamadoService(ChamadoRepository chamadoRepository, ClienteService clienteService, TecnicoService tecnicoService) {
        this.chamadoRepository = chamadoRepository;
        this.clienteService = clienteService;
        this.tecnicoService = tecnicoService;
    }

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto não encontrado! id: " + id)
        );
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado createChamado(@Valid ChamadoDTO dto) {
        return chamadoRepository.save(novoChamado(dto));
    }

    private Chamado novoChamado(ChamadoDTO dto) {
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();
        if (dto.getId() != null) {
            chamado.setId(dto.getId());
        }
        if (dto.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());

        return chamado;
    }

    public Chamado updateChamado(Integer id, @Valid ChamadoDTO dto) {
        dto.setId(id);
        Chamado chamado = novoChamado(dto);
        return chamadoRepository.save(chamado);
    }
}
