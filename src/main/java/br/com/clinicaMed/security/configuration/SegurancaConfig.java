package br.com.clinicaMed.security.configuration;

import br.com.clinicaMed.security.handlers.AcessoNaoAutorizadoHandler;
import br.com.clinicaMed.security.handlers.AcessoNegadoHandler;
import br.com.clinicaMed.security.handlers.FalhaAutenticaoHandler;
import br.com.clinicaMed.security.handlers.LoginUsuarioHandler;
import br.com.clinicaMed.security.handlers.LogoutHandler;
import br.com.clinicaMed.security.handlers.SucessoAutenticacaoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@ComponentScan("br.com.clinicaMed.security")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SegurancaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUsuarioHandler loginUsuarioHandler;

    @Autowired
    private SucessoAutenticacaoHandler sucessoAutenticacaoHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUsuarioHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/app/**", "/libs/**", "/css/**",
                "/favicon.ico", "/404.html", "/index.html", "/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/**").permitAll()
                .antMatchers("/usuario/redefinirSenha").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers("/console/**").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers("/medico/**").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers("/paciente/**").hasAnyAuthority("ADMINISTRADOR", "RECEPCIONISTA")
                .antMatchers("/recepcionista/**").hasAnyAuthority("ADMINISTRADOR")
                .and().exceptionHandling()
                .authenticationEntryPoint(new AcessoNaoAutorizadoHandler())
                .accessDeniedHandler(new AcessoNegadoHandler())
                .and().formLogin()
                .loginProcessingUrl("/autenticar")
                .successHandler(sucessoAutenticacaoHandler)
                .failureHandler(new FalhaAutenticaoHandler())
                .usernameParameter("usuario")
                .passwordParameter("senha")
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
