package com.spring.electric.tools.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {

}