package br.com.clinicaMed.security.utils;

import br.com.clinicaMed.security.dto.ErroHttp;
import br.com.clinicaMed.security.dto.RespostaHttp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class SegurancaUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private SegurancaUtils() {
    }

    public static Object getUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                return (UserDetails) authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof String) {
                return (String) authentication.getPrincipal();
            }
        }

        return null;
    }

    public static void enviarErro(HttpServletResponse response, Exception exception, int codigo, String mensagem) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(codigo);
        PrintWriter writer = response.getWriter();
        ErroHttp erro = new ErroHttp("Erro de Autenticação.", exception.getMessage());
        writer.write(mapper.writeValueAsString(new RespostaHttp(codigo, mensagem, erro)));
        writer.flush();
        writer.close();
    }

    public static void enviarResposta(HttpServletResponse response, int status, Object object) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(mapper.writeValueAsString(object));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }
}