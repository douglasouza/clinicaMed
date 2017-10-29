package br.com.clinicaMed.security.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ReponseWrapper extends HttpServletResponseWrapper {
    public ReponseWrapper(HttpServletResponse response) {
        super(response);
    }
}