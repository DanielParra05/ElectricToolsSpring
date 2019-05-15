package com.spring.electric.tools.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Orden;

public interface IOrdenDAO extends CrudRepository<Orden, Long>{

}
