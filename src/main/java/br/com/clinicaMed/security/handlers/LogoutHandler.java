package br.com.clinicaMed.security.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutHandler implements LogoutSuccessHandler {

    private final HttpStatus httpStatusToReturn;

    public LogoutHandler() {
        this.httpStatusToReturn = HttpStatus.OK;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(this.httpStatusToReturn.value());
        response.getWriter().flush();
    }
}