package com.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.document.Curso;
import com.academia.repo.ICursoRepo;
import com.academia.repo.IGenericRepo;
import com.academia.service.ICursoService;

@Service
public class CursoServiceImpl extends ICRUDImpl<Curso, String> implements ICursoService  {
	
	@Autowired
	private ICursoRepo repo;

	@Override
	protected IGenericRepo<Curso, String> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}


}
