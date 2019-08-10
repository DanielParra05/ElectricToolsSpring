package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.repositories.IClienteDAO;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private IClienteDAO clienteDAO;	
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}
}

