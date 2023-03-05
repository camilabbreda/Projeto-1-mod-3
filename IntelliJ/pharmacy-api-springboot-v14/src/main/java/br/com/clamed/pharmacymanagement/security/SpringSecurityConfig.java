package br.com.clamed.pharmacymanagement.security;

import br.com.clamed.pharmacymanagement.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()

                .antMatchers("/usuario/**").hasRole("ADMINISTRADOR")
                .antMatchers("/farmacia/**").hasRole("ADMINISTRADOR")
                .antMatchers("/medicamento/**").hasRole("ADMINISTRADOR")
                .antMatchers("/endereco/**").hasRole("ADMINISTRADOR")


                .antMatchers(HttpMethod.POST).hasRole("GERENTE")
                .antMatchers(HttpMethod.PUT).hasRole("GERENTE")
                .antMatchers(HttpMethod.GET).hasRole("GERENTE")


                .antMatchers(HttpMethod.POST,"/farmacia/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.GET,"/farmacia/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.POST,"/medicamento/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.GET,"/medicamento/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.POST,"/endereco/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.GET,"/endereco/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.POST,"/usuario/**").hasRole("COLABORADOR")
                .antMatchers(HttpMethod.GET,"/usuario/**").hasRole("COLABORADOR")





                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtLoginFilter("/auth", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService)

                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html",
                "/v2/api-docs",
                "/webjars/**");
    }
}
