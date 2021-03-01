package com.bazaga.javamoney.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.bazaga.javamoney.api.config.token.CustomTokenEnhancer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;
	/*
	@Autowired
	private UserDetailsService userDetailsService;*/
	
	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder; // precisei injetar para conseguir o token - 6.11 Movendo o usuário para o banco de dados
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
						.withClient("angular")
						.secret("$2y$12$nGPDOcd6AyTJfHnqBczsf.uFout6hQBb.pClRSnncABXCBdw1MD..")
						.scopes("read", "write")
						.authorizedGrantTypes("password", "refresh_token") 
						.accessTokenValiditySeconds(1800)
						.refreshTokenValiditySeconds(3600 * 24)
					.and()
						.withClient("mobile")
						.secret("$2y$12$Zpe4ySATimSnkipid0eEeeJhZLaQFXkjZCzW0upqyzTPgx6YWoFau")
						.scopes("read")
						.authorizedGrantTypes("password", "refresh_token") 
						.accessTokenValiditySeconds(1800)
						.refreshTokenValiditySeconds(3600 * 24);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
		
		endpoints
			.tokenStore(tokenStore())
			.tokenEnhancer(tokenEnhancerChain)
			.reuseRefreshTokens(false)
			//.userDetailsService(this.userDetailsService)
			.authenticationManager(this.authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("bazaga");
		return accessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
	
}