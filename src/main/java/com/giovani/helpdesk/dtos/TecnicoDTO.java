package com.giovani.helpdesk.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.giovani.helpdesk.domain.Tecnico;
import com.giovani.helpdesk.enums.Perfil;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

public class TecnicoDTO implements Serializable {

    protected Integer id;

    @NotBlank(message = "O campo 'Nome' precisa ser preenchido")
    protected String nome;

    @NotBlank(message = "O campo 'Cpf' precisa ser preenchido")
    protected String cpf;

    @NotBlank(message = "O campo 'Email' precisa ser preenchido")
    protected String email;

    @NotBlank(message = "O campo 'Senha' precisa ser preenchido")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = now();

    public TecnicoDTO() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfis(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfis) {
        this.perfis.add(perfis.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}