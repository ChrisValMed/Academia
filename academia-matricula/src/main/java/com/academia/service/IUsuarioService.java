package com.academia.service;

import com.academia.document.Usuario;
import com.academia.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService extends ICRUD<Usuario, String>{
	
	Mono<User> buscarPorUsuario(String usuario);
}
