package br.com.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.POST, "/aviso/**").hasAuthority("SCOPE_proposta")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
    }

}
