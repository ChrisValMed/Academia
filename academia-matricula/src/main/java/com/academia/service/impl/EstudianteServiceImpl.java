package com.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.document.Estudiantes;
import com.academia.repo.IEstudianteRepo;
import com.academia.repo.IGenericRepo;
import com.academia.service.IEstudianteService;

@Service
public class EstudianteServiceImpl extends ICRUDImpl<Estudiantes, String> implements IEstudianteService  {
	
	@Autowired
	private IEstudianteRepo repo;

	@Override
	protected IGenericRepo<Estudiantes, String> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
