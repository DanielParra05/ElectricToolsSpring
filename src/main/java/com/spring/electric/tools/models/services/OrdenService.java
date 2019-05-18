package com.spring.electric.tools.models.services;

import java.util.List;

import com.spring.electric.tools.models.entities.Orden;

public interface OrdenService {
	
	public List<Orden> findAll();
	
	public Orden findById(Long id);
	
	public Orden save(Orden orden);
	
	public void delete (Long id);
	

}
