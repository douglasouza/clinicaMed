package br.com.clinicaMed.security.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBO {

    @Autowired
    private UsuarioRepository repo;

    public Usuario salvarUsuario(Usuario usuario) {
        usuario.setSenha("55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251");
        return repo.save(usuario);
    }
}
