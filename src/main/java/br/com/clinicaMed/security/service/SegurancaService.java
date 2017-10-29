package br.com.clinicaMed.security.service;

import br.com.clinicaMed.api.entity.Usuario;
import br.com.clinicaMed.api.repository.UsuarioRepository;
import br.com.clinicaMed.security.dto.UsuarioDTO;
import br.com.clinicaMed.security.utils.SegurancaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguranca")
public class SegurancaService {

    @Autowired
    private UsuarioRepository repo;

    @RequestMapping(value = "/usuarioLogado", method = RequestMethod.GET)
    public Object getUsuarioLogado() {
        UserDetails userDetails = (UserDetails) SegurancaUtils.getUsuarioLogado();
        Usuario usuario = repo.findByLogin(userDetails.getUsername());
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin(usuario.getLogin());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        return usuarioDTO;
    }

    @RequestMapping(value = "/ativarUsuario", method = RequestMethod.PUT)
    public Object ativarUsuario(String login, String novaSenha) {
        Usuario usuario = repo.findByLogin(login);
        usuario.setSenha(novaSenha);
        usuario.setAtivado(true);
        return repo.save(usuario);
    }

    @RequestMapping(value = "/redefinirSenha", method = RequestMethod.PUT)
    public Object redefinirSenha(String login, String novaSenha) {
        Usuario usuario = repo.findByLogin(login);
        usuario.setSenha(novaSenha);
        return repo.save(usuario);
    }
}