package com.academia.service.impl;

import org.springframework.data.domain.Sort;

import com.academia.repo.IGenericRepo;
import com.academia.service.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class ICRUDImpl<T, ID>  implements ICRUD<T, ID> {
	
	protected abstract IGenericRepo<T, ID> getRepo();
	
	@Override
	public Mono<T> registrar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Mono<T> modificar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Flux<T> listar() {
		return getRepo().findAll();
	}

	@Override
	public Mono<T> listarPorId(ID v) {
		return getRepo().findById(v);
	}
	
	@Override
	public Flux<T> listarPorEdadDesc(){	
		return getRepo().findAll(Sort.by(Sort.Direction.DESC, "edad"));		
	}
	
	@Override
	public Mono<Void> eliminar(ID v) {
		return getRepo().deleteById(v);				
	}

}
