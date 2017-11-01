package br.com.clinicaMed.security.handlers;

import br.com.clinicaMed.security.usuario.Usuario;
import br.com.clinicaMed.security.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class LoginUsuarioHandler implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null)
            throw new UsernameNotFoundException("Usuário " + login + " não encontrado na base de dados.");

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getTipoUsuario().toString());
        grantedAuthorities.add(grantedAuthority);

        return new User(login, usuario.getSenha(), grantedAuthorities);
    }
}