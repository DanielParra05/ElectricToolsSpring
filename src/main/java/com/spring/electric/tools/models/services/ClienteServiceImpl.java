package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.dao.IClienteDAO;
import com.spring.electric.tools.models.entities.Cliente;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private IClienteDAO clienteDAO;	
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		// Lista todos los clientes de la base de datos
		return (List<Cliente>)clienteDAO.findAll();
	}


	@Override
	@Transactional
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDAO.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		
		return clienteDAO.save(cliente);
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDAO.deleteById(id);
	}

}

