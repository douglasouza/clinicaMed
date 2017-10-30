package br.com.clinicaMed.security.filter;

import br.com.clinicaMed.security.utils.SegurancaUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Object usuarioLogado = SegurancaUtils.getUsuarioLogado();
        ReponseWrapper responseWrapper = new ReponseWrapper(response);
        if (usuarioLogado != null)
            responseWrapper.addHeader("Usuario-Logado", "true");
        else
            responseWrapper.addHeader("Usuario-Logado", "false");

        filterChain.doFilter(request, responseWrapper);
    }

}