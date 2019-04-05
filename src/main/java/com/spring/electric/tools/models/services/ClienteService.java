package com.spring.electric.tools.models.services;


import java.util.List;

import com.spring.electric.tools.models.entities.Cliente;


public interface ClienteService {

	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete (Long id);
	
}
