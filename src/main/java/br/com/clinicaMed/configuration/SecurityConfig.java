package br.com.clinicaMed.configuration;

import br.com.clinicaMed.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@ComponentScan("br.com.clinicaMed")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

//
//    @Autowired
//    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
//
//    @Autowired
//    private AccessDeniedHandler restAccessDeniedHandler;
//
//    @Autowired
//    private AuthenticationSuccessHandler restAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler restAuthenticationFailureHandler;
//
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("./css/**", "./login.html", "./index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/medico/**").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers("/paciente/**").hasAnyAuthority("ADMINISTRADOR","RECEPCIONISTA")
                .antMatchers("/recepcionista/**").hasAnyAuthority("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and()
            .exceptionHandling()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                //.addFilterAfter(filter, AbstractPreAuthenticatedProcessingFilter.class)
            .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
