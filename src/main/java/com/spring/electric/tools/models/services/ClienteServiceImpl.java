package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.repositories.ClientesRepository;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClientesRepository clienteRepository;	
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Page<Cliente> buscarCliente(String campoBusqueda, Pageable pageable) {
		return clienteRepository.buscarCliente(campoBusqueda, pageable);
	}

	@Override
	public Cliente findByCedula(String cedula) {
		return clienteRepository.findByCedula(cedula);
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	public Page<Cliente> index(Pageable pageable, String campoBusqueda) {
		if (campoBusqueda == null) {
			return clienteRepository.findAll(pageable);
		}else {
			return clienteRepository.buscarCliente(campoBusqueda, pageable);
		}
	}
	
	
}

