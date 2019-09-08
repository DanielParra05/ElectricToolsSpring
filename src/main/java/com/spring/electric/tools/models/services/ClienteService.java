package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.electric.tools.models.entities.Cliente;

public interface ClienteService {

	List<Cliente> findAll();

	/**
	 * Traer los clientes en una lista de paginacion segubn los parametros
	 * especificados
	 * 
	 * @param pageable -> Page request con los parametros necesarios
	 * @return lista de paginador
	 */
	Page<Cliente> findAll(Pageable pageable);
	
	Page<Cliente> index(Pageable pageable, String campoBusqueda);

	Cliente findById(Long id);

	Cliente save(Cliente cliente);

	void delete(Long id);

	Cliente findByCedula(String cedula);

	/**
	 * Buscar un cliente segun por cedula o nombre
	 * 
	 * @param campoBusqueda cadena enviada para buscar como coincidencia
	 * @return Lista con los clientes que coincidieron
	 */
	Page<Cliente> buscarCliente(String campoBusqueda, Pageable pageable);

}
