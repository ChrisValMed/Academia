package com.academia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.document.Rol;
import com.academia.document.Usuario;
//import com.academia.pagination.PageSupport;
import com.academia.repo.IGenericRepo;
import com.academia.repo.IUsuarioRepo;
import com.academia.security.User;
import com.academia.service.IUsuarioService;

import reactor.core.publisher.Mono;

@Service
public class UsuarioServiceImpl extends ICRUDImpl<Usuario, String> implements IUsuarioService {

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	protected IGenericRepo<Usuario, String> getRepo() {
		return repo; 
	}
	
	
	@Override
	public Mono<User> buscarPorUsuario(String usuario) {
		Mono<Usuario> monoUsuario = repo.findOneByUsuario(usuario);

		List<String> roles = new ArrayList<String>();
				
		return monoUsuario.doOnNext(u -> {
			for (Rol role : u.getRoles()) {			
				roles.add(role.getNombre());
			}
		}).flatMap(u -> {
			return Mono.just(new User(u.getUsuario(), u.getClave(), u.getEstado(), roles));
		});
	}

}
