package com.spring.electric.tools.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.electric.tools.models.entities.Customer;

public interface CustomerRespository extends PagingAndSortingRepository<Customer, Long> {

	@Query("SELECT c FROM Customer c WHERE c.identification LIKE ?1 OR LOWER(CONCAT(c.name,' ',c.lastName)) LIKE LOWER( '%'||?1||'%' )")
	Page <Customer> searchCustomer (String campoBusqueda, Pageable pageable);
	
	Customer findByIdentification(String cedula);
	
	Customer findById(long id);
	
	Page <Customer> findAllByOrderByNameAsc(Pageable pageable);
	
}