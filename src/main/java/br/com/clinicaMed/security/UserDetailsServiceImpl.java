package br.com.clinicaMed.security;

import br.com.clinicaMed.api.entity.Usuario;
import br.com.clinicaMed.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getTipoUsuario().toString());
        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(login, usuario.getSenha(),
                grantedAuthorities);
    }
}