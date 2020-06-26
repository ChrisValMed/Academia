package com.academia.service;

import com.academia.document.Matricula;

import reactor.core.publisher.Mono;

public interface IMatriculaService extends ICRUD<Matricula, String>{
	
	Mono<Matricula> registrarTransaccional(Matricula f) throws Exception;
	//Mono<PageSupport<Matricula>> listarPage(Pageable page);
}
