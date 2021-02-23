package com.bazaga.javamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/categorias").permitAll() // categoria nao precisa ser autenticada
					.anyRequest().authenticated()  // as outras precisam
					.and()					
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // não manter nenhum estado/sessao
				.csrf().disable();  // cross site request forgery				
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
	
	/*
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
}
