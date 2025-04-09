package com.giovani.helpdesk.service;

import com.giovani.helpdesk.domain.Pessoa;
import com.giovani.helpdesk.repository.PessoaRepository;
import com.giovani.helpdesk.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSecurity(
                    user.get().getId(),
                    user.get().getEmail(),
                    user.get().getPerfis(),
                    user.get().getSenha()
            );
        }
        throw new UsernameNotFoundException(email);
    }
}
