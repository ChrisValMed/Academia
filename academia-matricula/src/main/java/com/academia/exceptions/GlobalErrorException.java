package com.academia.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class GlobalErrorException extends AbstractErrorWebExceptionHandler{

	public GlobalErrorException(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(configurer.getWriters());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}
	
	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		Map<String, Object> errorPropertiesMap = getErrorAttributes(request, false);

		final Map<String, Object> mapException = new HashMap<>();

		var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String status = String.valueOf(errorPropertiesMap.get("status"));

		switch (status) {
		case "500":
			mapException.put("error", "500");
			mapException.put("excepcion", "Error Interno");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;			
			break;

		default:
			mapException.put("error", "400");
			mapException.put("excepcion", "Bad Req");
			httpStatus = HttpStatus.BAD_REQUEST;
			break;
		}
		
		mapException.put("mensaje", errorPropertiesMap.get("message"));		
		mapException.put("ruta", request.uri());
		mapException.put("fecha-error", LocalDateTime.now());
		
		return ServerResponse.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(mapException));
	}
}
