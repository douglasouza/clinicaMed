package br.com.clinicamed.security.handlers;

import br.com.clinicamed.security.usuario.Usuario;
import br.com.clinicamed.security.usuario.UsuarioDTO;
import br.com.clinicamed.security.usuario.UsuarioRepository;
import br.com.clinicamed.security.utils.SegurancaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SucessoAutenticacaoHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Usuario usuarioLogado = repo.findByLogin(authentication.getName());

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin(usuarioLogado.getLogin());
        usuarioDTO.setTipoUsuario(usuarioLogado.getTipoUsuario());
        usuarioDTO.setAtivado(usuarioLogado.getAtivado());
        SegurancaUtils.enviarResposta(response, HttpServletResponse.SC_OK, usuarioDTO);
    }
}