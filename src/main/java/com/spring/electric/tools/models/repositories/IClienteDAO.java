package com.spring.electric.tools.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {

}