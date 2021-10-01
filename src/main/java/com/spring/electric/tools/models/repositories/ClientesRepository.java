package com.spring.electric.tools.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.electric.tools.models.entities.Cliente;

public interface ClientesRepository extends PagingAndSortingRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c WHERE c.cedula LIKE ?1 OR LOWER(CONCAT(c.nombre,' ',c.apellido)) LIKE LOWER( '%'||?1||'%' )")
	Page <Cliente> buscarCliente (String campoBusqueda, Pageable pageable);
	
	Cliente findByCedula(String cedula);
	
	Cliente findById(long id);
	
}