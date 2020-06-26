package com.academia.document;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class MatriculaItem {

	
	@DBRef
	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
}
