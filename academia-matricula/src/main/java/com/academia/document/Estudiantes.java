package com.academia.document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "estudiantes")
public class Estudiantes {
	
	@Id
	private String id;
	
	@Field(name = "nombres") 
	@NotEmpty
	@Size(min = 3)
	private String nombres;
	
	@Field(name = "apellidos") 
	@NotEmpty
	@Size(min = 3)
	private String apellidos;
	
	@Field(name = "dni") 
	@NotEmpty
	@Size(min = 3)
	private String dni;
	
	@NotNull
	@Min(1)
	private double edad;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public double getEdad() {
		return edad;
	}
	public void setEdad(double edad) {
		this.edad = edad;
	}
	
}
