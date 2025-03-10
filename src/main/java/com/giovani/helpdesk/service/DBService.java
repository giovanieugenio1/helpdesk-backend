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
import org.springframework.context.annotation.Bean;
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
        if (tecnicoRepository.count() > 0 || clienteRepository.count() > 0) {
            return;
        }
        Tecnico tecnico1 = new Tecnico(null, "Giovani", "12324543443", "giovani@gmail.com", "1234");
        tecnico1.addPerfil(Perfil.ADMIN);

        Cliente cliente1 = new Cliente(null, "John", "21543675455", "john@gmail.com", "4321");
        //cliente1.addPerfil(Perfil.CLIENTE);

        Tecnico tecnico2 = new Tecnico(null, "Mariana", "32165498700", "mariana@gmail.com", "5678");
        tecnico2.addPerfil(Perfil.TECNICO);

        Cliente cliente2 = new Cliente(null, "Carlos", "14785236912", "carlos@gmail.com", "8765");
        cliente2.addPerfil(Perfil.CLIENTE);

        Tecnico tecnico3 = new Tecnico(null, "Fernando", "96325874136", "fernando@gmail.com", "1357");
        tecnico3.addPerfil(Perfil.ADMIN);

        Cliente cliente3 = new Cliente(null, "Ana", "25874136985", "ana@gmail.com", "2468");
        cliente3.addPerfil(Perfil.CLIENTE);

        Tecnico tecnico4 = new Tecnico(null, "Ricardo", "75395185245", "ricardo@gmail.com", "9876");
        tecnico4.addPerfil(Perfil.TECNICO);

        Cliente cliente4 = new Cliente(null, "Beatriz", "95175325874", "beatriz@gmail.com", "3691");
        cliente4.addPerfil(Perfil.CLIENTE);

        Tecnico tecnico5 = new Tecnico(null, "Lucas", "85236974125", "lucas@gmail.com", "8520");
        tecnico5.addPerfil(Perfil.ADMIN);

        Cliente cliente5 = new Cliente(null, "Paula", "75362418579", "paula@gmail.com", "7410");
        cliente5.addPerfil(Perfil.CLIENTE);

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "CH 1", "Primeiro chamado", tecnico1, cliente1);
        Chamado chamado2 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "CH 2", "Chamado urgente", tecnico2, cliente2);
        Chamado chamado3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "CH 3", "Chamado resolvido", tecnico3, cliente3);
        Chamado chamado4 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "CH 4", "Outro chamado", tecnico4, cliente4);
        Chamado chamado5 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "CH 5", "Suporte avançado", tecnico5, cliente5);

        tecnicoRepository.saveAll(List.of(tecnico1, tecnico2, tecnico3, tecnico4, tecnico5));
        clienteRepository.saveAll(List.of(cliente1, cliente2, cliente3, cliente4, cliente5));
        chamadoRepository.saveAll(List.of(chamado1, chamado2, chamado3, chamado4, chamado5));
    }
}
