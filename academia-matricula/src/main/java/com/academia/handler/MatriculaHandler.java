package com.academia.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.academia.document.Matricula;
import com.academia.service.IMatriculaService;
import com.academia.validators.RequestValidator;

import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;


@Component
public class MatriculaHandler {

	@Autowired
	private IMatriculaService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){	
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Matricula.class);		
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(f -> ServerResponse
								.ok()
								.contentType(MediaType.APPLICATION_JSON)
								.body(fromValue(f))
				)				
				.switchIfEmpty(ServerResponse
							.notFound()
							.build()
				);
	}
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		
		Mono<Matricula> cursoMono = req.bodyToMono(Matricula.class);
		
		return cursoMono
				.flatMap(this.validadorGeneral::validate)
				.flatMap(f-> {
			return service.registrar(f);
		}).flatMap(f -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(f.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(f)));
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		
		Mono<Matricula> cursoMono = req.bodyToMono(Matricula.class);
		
		return cursoMono
				.flatMap(this.validadorGeneral::validate)
				.flatMap(f-> {
			return service.modificar(f);
		}).flatMap(f -> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(f)));
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest req){
		String id = req.pathVariable("id");
		
		return service.listarPorId(id)
				.flatMap(f -> service.eliminar(f.getId())
				.then(ServerResponse
						.noContent()
						.build()
				)
				.switchIfEmpty(ServerResponse
						.notFound()
						.build()
				)
			);
	}
	
}
