package com.giovani.helpdesk;

import com.giovani.helpdesk.domain.Chamado;
import com.giovani.helpdesk.domain.Cliente;
import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.enums.Perfil;
import com.giovani.helpdesk.enums.Prioridade;
import com.giovani.helpdesk.enums.Status;
import com.giovani.helpdesk.repository.ChamadoRepository;
import com.giovani.helpdesk.repository.ClienteRepository;
import com.giovani.helpdesk.repository.TecnicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	private final TecnicoRepository tecnicoRepository;
	private final ClienteRepository clienteRepository;
	private final ChamadoRepository chamadoRepository;

    public HelpdeskApplication(TecnicoRepository tecnicoRepository, ClienteRepository clienteRepository, ChamadoRepository chamadoRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.clienteRepository = clienteRepository;
        this.chamadoRepository = chamadoRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
