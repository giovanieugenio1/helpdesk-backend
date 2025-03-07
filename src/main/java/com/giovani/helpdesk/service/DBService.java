package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.domain.Cliente;
import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.enums.Perfil;
import com.giovani.helpdesk.enums.Prioridade;
import com.giovani.helpdesk.enums.Status;
import com.giovani.helpdesk.repository.ChamadoRepository;
import com.giovani.helpdesk.repository.ClienteRepository;
import com.giovani.helpdesk.repository.TecnicoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    private final TecnicoRepository tecnicoRepository;
    private final ClienteRepository clienteRepository;
    private final ChamadoRepository chamadoRepository;

    public DBService(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository, ChamadoRepository chamadoRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.clienteRepository = clienteRepository;
        this.chamadoRepository = chamadoRepository;
    }

    @PostConstruct
    public void initDB() {
        Tecnico tecnico = new Tecnico(
                null,
                "Giovani",
                "43265437867",
                "giovani@gmail.com",
                "1234"
        );
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(
                null,
                "John",
                "21543675455",
                "john@gmail.com",
                "4321"
        );
        cliente.addPerfil(Perfil.CLIENTE);

        Chamado chamado = new Chamado(
                null,
                Prioridade.MEDIA,
                Status.ABERTO,
                "CH 1",
                "Primeiro chamado",
                tecnico,
                cliente
        );

        tecnicoRepository.saveAll(List.of(tecnico));
        clienteRepository.saveAll(List.of(cliente));
        chamadoRepository.saveAll(List.of(chamado));
    }
}
