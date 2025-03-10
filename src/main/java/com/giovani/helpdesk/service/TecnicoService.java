package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Pessoa;
import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.dtos.TecnicoDTO;
import com.giovani.helpdesk.exceptions.DataIntegrityViolationException;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.repository.PessoaRepository;
import com.giovani.helpdesk.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final PessoaRepository pessoaRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository, PessoaRepository pessoaRepository) {
        this.tecnicoRepository = tecnicoRepository;
        this.pessoaRepository = pessoaRepository;
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

    public Tecnico createTecnico(TecnicoDTO dto) {
        validaCpfEEmail(dto);
        Tecnico tecnico = new Tecnico(dto);
        return tecnicoRepository.save(tecnico);
    }

    private void validaCpfEEmail(TecnicoDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if (pessoa.isPresent()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        if (pessoa.isPresent()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}
