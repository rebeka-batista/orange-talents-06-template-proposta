package br.com.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(authorizeRequests -> authorizeRequests

                .antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_proposta")
                .antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_proposta")
                .anyRequest().authenticated())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
