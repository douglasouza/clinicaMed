package br.com.clinicaMed.security.handlers;

import br.com.clinicaMed.security.utils.SegurancaUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcessoNegadoHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        SegurancaUtils.enviarErro(response, exception, HttpServletResponse.SC_FORBIDDEN, "Acesso não autorizado.");
    }
}