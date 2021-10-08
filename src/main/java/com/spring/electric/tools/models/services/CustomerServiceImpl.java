package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.repositories.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Page<Customer> searchCustomer(String campoBusqueda, Pageable pageable) {
		return customerRepository.searchCustomer(campoBusqueda, pageable);
	}

	@Override
	public Customer findByIdentification(String cedula) {
		return customerRepository.findByIdentification(cedula);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAllByOrderByNameAsc(pageable);
	}

	@Override
	public Page<Customer> index(Pageable pageable, String campoBusqueda) {
		if (campoBusqueda == null) {
			return customerRepository.findAll(pageable);
		} else {
			return customerRepository.searchCustomer(campoBusqueda, pageable);
		}
	}

}
