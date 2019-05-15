package com.spring.electric.tools.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.dao.IOrdenDAO;
import com.spring.electric.tools.models.entities.Orden;
@Service
public class OrdenServiceImpl implements OrdenService{
	
	@Autowired
	private IOrdenDAO ordenDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Orden> findAll() {
		return (List<Orden>) ordenDAO.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Orden findById(Long id) {
		return ordenDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Orden save(Orden orden) {
		return ordenDAO.save(orden);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ordenDAO.deleteById(id);
	}

}
