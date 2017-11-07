package br.com.clinicamed.security.usuario;

import br.com.clinicamed.security.utils.SegurancaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @RequestMapping(value = "/usuarioLogado", method = RequestMethod.GET)
    public Object getUsuarioLogado() {
        String loginUsuarioLogado = SegurancaUtils.getUsuarioLogado();
        Usuario usuario = repo.findByLogin(loginUsuarioLogado);

        if (usuario != null) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setLogin(usuario.getLogin());
            usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
            usuarioDTO.setAtivado(usuario.getAtivado());
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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