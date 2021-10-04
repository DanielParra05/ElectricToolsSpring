package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.electric.tools.models.entities.Cliente;

public interface ClienteService {

	/**
	 * Traer los clientes en una lista de paginacion segun los parametros
	 * especificados
	 * 
	 * @param pageable -> Page request con los parametros necesarios
	 * @return lista de paginador
	 */
	public Page<Cliente> findAll(Pageable pageable);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> index(Pageable pageable, String campoBusqueda);

	public Cliente findById(Long id);

	public Cliente save(Cliente cliente);

	public void delete(Long id);

	public Cliente findByCedula(String cedula);

	/**
	 * Buscar un cliente segun por cedula o nombre
	 * 
	 * @param campoBusqueda cadena enviada para buscar como coincidencia
	 * @return Lista con los clientes que coincidieron
	 */
	public Page<Cliente> buscarCliente(String campoBusqueda, Pageable pageable);
	

}
