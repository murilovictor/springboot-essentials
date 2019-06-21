package br.com.springboot.essentials.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Murilo Victor on 21/06/2019
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @param http
     * @throws Exception Metodo que realiza a configuração de authenticação.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /**
     * @param auth
     * @throws Exception COnfigura os Usuários em Memoria
     * @see 'https://www.mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/'
     * o padrão PasswordEncoderera NoOpPasswordEncoderquais senhas de texto simples requeriam. No Spring Security 5, o padrão é DelegatingPasswordEncoder
     * Primeira Solução é incluir o {noop} no inicio da senha
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("MuriloUser").password("{noop}murilo").roles("USER")
                .and()
                .withUser("MuriloAdmin").password("{noop}murilo").roles("ADMIN")
                .and()
                .withUser("MuriloUserAndAdmin").password("{noop}murilo").roles("USER", "ADMIN");
    }
}
