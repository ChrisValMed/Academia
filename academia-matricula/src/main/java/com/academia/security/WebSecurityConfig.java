package com.academia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

//Clase 7
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;
	
	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
	
		return http
				.exceptionHandling()
				.authenticationEntryPoint((swe, e) -> {					
					return Mono.fromRunnable(() -> {						
						swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);						
					});
				}).accessDeniedHandler((swe, e) -> {					
					return Mono.fromRunnable(() -> {						
						swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
					});
				}).and()
				.csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				.pathMatchers(HttpMethod.OPTIONS).permitAll()				
				.pathMatchers("/login").permitAll()
				//SWAGGER PARA SPRING SECURITY ini
				//.pathMatchers("/swagger-resources/**").permitAll()
				//.pathMatchers("/swagger-ui.html").permitAll()
				//.pathMatchers("/webjars/**").permitAll()
				//SWAGGER PARA SPRING SECURITY fin				
				.pathMatchers("/v2/login").permitAll()
				//.pathMatchers("/v2/**").authenticated()
				.pathMatchers("/v2/usuarios/**").authenticated()
				.pathMatchers("/cursos/**").authenticated()
				.pathMatchers("/estudiantes/**").authenticated()
				.pathMatchers("/json/**").permitAll()
				.anyExchange().authenticated()
				.and().build();
	
	}
}
