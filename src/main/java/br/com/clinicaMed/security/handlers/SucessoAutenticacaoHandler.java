package br.com.clinicaMed.security.handlers;

import br.com.clinicaMed.api.entity.Usuario;
import br.com.clinicaMed.api.repository.UsuarioRepository;
import br.com.clinicaMed.security.dto.UsuarioDTO;
import br.com.clinicaMed.security.utils.SegurancaUtils;
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