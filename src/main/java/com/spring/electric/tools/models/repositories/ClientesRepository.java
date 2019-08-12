package com.spring.electric.tools.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Cliente;

public interface ClientesRepository extends CrudRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c WHERE c.cedula LIKE ?1 OR LOWER(CONCAT(c.nombre,' ',c.apellido)) LIKE LOWER( '%'||?1||'%' )")
	List <Cliente> buscarCliente (String campoBusqueda);
	
	Cliente findByCedula(String cedula);
	
}