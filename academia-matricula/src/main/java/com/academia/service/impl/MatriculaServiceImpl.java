package com.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.document.Matricula;
import com.academia.repo.IGenericRepo;
import com.academia.repo.IMatriculaRepo;
import com.academia.service.IMatriculaService;

import reactor.core.publisher.Mono;

@Service
public class MatriculaServiceImpl extends ICRUDImpl<Matricula, String> implements IMatriculaService{

	@Autowired
	private IMatriculaRepo repo;
	
	//@Autowired
	//private IPlatoRepo pRepo;
	
	/*@Autowired
	private TransactionalOperator txo;*/
	
	@Override
	protected IGenericRepo<Matricula, String> getRepo(){
		return repo;
	}
	
	@Override
	public Mono<Matricula> registrarTransaccional(Matricula f) throws Exception{
		
		/*Plato p = new Plato();		
		p.setEstado(true);
		p.setNombre("CHAUFA");
		p.setPrecio(25);
		
		Plato p2 = new Plato();		
		p2.setEstado(true);
		p2.setNombre("CECINA");
		p2.setPrecio(27);*/
		
		/*this.txo.execute(new TransactionCallback<Factura>() {
			@Override
			public Publisher<Factura> doInTransaction(ReactiveTransaction status) {				
				return repo.save(f);
			}
		});*/
		
		//return this.txo.execute(status -> pRepo.save(p)).then(pRepo.save(p2)).then(repo.save(f));
		return null;
	}	

	
	
}
