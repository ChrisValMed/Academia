package com.academia.document;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "matriculas")
public class Matricula {

	@Id
	private String id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaReg;

	@DBRef
	private Estudiantes estudiante;

	private List<MatriculaItem> items;
	
	@NotNull
	private Boolean estado;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getFechaReg() {
		return fechaReg;
	}

	public void setFechaReg(LocalDateTime fechaReg) {
		this.fechaReg = fechaReg;
	}

	public Estudiantes getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiantes estudiante) {
		this.estudiante = estudiante;
	}

	public List<MatriculaItem> getItems() {
		return items;
	}

	public void setItems(List<MatriculaItem> items) {
		this.items = items;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

	// @JsonIgnore
	// public Double getTotal() {
	// return
	// items.stream().collect(Collectors.summingDouble(FacturaItem::calcularMonto));
	// }

}
