package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Pessoa;
import com.giovani.helpdesk.domain.Cliente;
import com.giovani.helpdesk.dtos.ClienteDTO;
import com.giovani.helpdesk.exceptions.DataIntegrityViolationException;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.repository.PessoaRepository;
import com.giovani.helpdesk.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository, PessoaRepository pessoaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente  = clienteRepository.findById(id);
        return cliente.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto não encontrado. ID: " + id)
        );
    }

    public Cliente getClienteDetails(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(
                ()-> new ObjectNotFoundException("Objeto não encontrado. ID: " + id)
        );
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente createCliente(ClienteDTO dto) {
        dto.setId(null);
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        validaCpfEEmail(dto);
        Cliente cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }

    private void validaCpfEEmail(ClienteDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if (pessoa.isPresent()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        if (pessoa.isPresent()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public Cliente update(Integer id, @Valid ClienteDTO dto) {
        dto.setId(id);
        Cliente cliente = findById(id);
        validaCpfEEmail(dto);
        cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        Cliente cliente = findById(id);
        if (!cliente.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Falha ao exluir cliente! O cliente possui chamaodos em aberto");
        } else {
            clienteRepository.deleteById(id);
        }
    }
}
