package com.academia.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, V> {
	
	Mono<T> registrar(T t);
	Mono<T> modificar(T t);
	Flux<T> listar();
	Mono<T>listarPorId(V v);
	Mono<Void> eliminar(V v);
	Flux<T> listarPorEdadDesc();

}
