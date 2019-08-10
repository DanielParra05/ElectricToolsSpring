package com.spring.electric.tools.models.services;


import java.util.List;

import com.spring.electric.tools.models.entities.Cliente;


public interface ClienteService {

	List<Cliente> findAll();
	
	Cliente findById(Long id);
	
	Cliente save(Cliente cliente);
	
	void delete (Long id);
	
	/**
	 * Buscar un cliente segun por cedula o nombre
	 * @param campoBusqueda cadena enviada para buscar como coincidencia
	 * @return Lista con los clientes que coincidieron
	 */
	List<Cliente> buscarCliente (String campoBusqueda);
	
	
}
