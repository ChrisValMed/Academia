package com.academia.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

//Clase S5 || Se recupera el token y se expresa la informacion del token a un usuario con sus roles
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager{

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String token = authentication.getCredentials().toString();
		String usuario;
		
		try {
			usuario = jwtUtil.getUsernameFromToken(token);
		} catch (Exception e) {
			usuario = null;
		}
		
		if (usuario != null && jwtUtil.validateToken(token)) {
			Claims claims = jwtUtil.getAllClaimsFromToken(token);
			//esta linea extrae del Map el item "role" y devuelve el contenido
			List<String> rolesMap = claims.get("roles", List.class);
				
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				usuario,
				null,
				rolesMap.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList())
			);
			return Mono.just(auth);
		} else {
			//return Mono.empty();
			return Mono.error(new InterruptedException("Token no válido o ha expirado"));
		}
	}
	
	
}
