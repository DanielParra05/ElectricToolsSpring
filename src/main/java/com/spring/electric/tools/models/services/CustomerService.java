package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.electric.tools.models.entities.Customer;

public interface CustomerService {

	/**
	 * Traer los customers en una lista de paginacion segun los parametros
	 * especificados
	 * 
	 * @param pageable -> Page request con los parametros necesarios
	 * @return lista de paginador
	 */
	Page<Customer> findAll(Pageable pageable);
	
	List<Customer> findAll();
	
	Page<Customer> index(Pageable pageable, String campoBusqueda);

	Customer findById(Long id);

	Customer save(Customer customer);

	void delete(Long customerId);

	Customer findByIdentification(String identification);

	/**
	 * Buscar un customer segun por cedula o nombre
	 * 
	 * @param campoBusqueda cadena enviada para buscar como coincidencia
	 * @return Lista con los customers que coincidieron
	 */
	Page<Customer> searchCustomer(String campoBusqueda, Pageable pageable);
	
	boolean hasOrder(Customer customer);

}
