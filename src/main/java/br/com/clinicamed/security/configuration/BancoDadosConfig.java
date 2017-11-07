package br.com.clinicamed.security.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BancoDadosConfig {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registro = new ServletRegistrationBean(new WebServlet());
        registro.addUrlMappings("/console/*");
        return registro;
    }
}
