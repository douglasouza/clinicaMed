package br.com.clinicaMed.configuration;

import br.com.clinicaMed.security.RestAccessDeniedHandler;
import br.com.clinicaMed.security.RestUnauthorizedEntryPoint;
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

    @Autowired
    private RestUnauthorizedEntryPoint restUnauthorizedEntryPoint;

    //
//    @Autowired
//    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
//
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

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
        web.ignoring().antMatchers("/app/**", "/bower_components/**", "/css/**", "/index.html", "/");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/medico/**").hasAnyAuthority("ADMINISTRADOR")
                .antMatchers("/paciente/**").hasAnyAuthority("ADMINISTRADOR", "RECEPCIONISTA")
                .antMatchers("/recepcionista/**").hasAnyAuthority("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restUnauthorizedEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticate")
//                .successHandler(restAuthenticationSuccessHandler)
//                .failureHandler(restAuthenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();
    }
}
