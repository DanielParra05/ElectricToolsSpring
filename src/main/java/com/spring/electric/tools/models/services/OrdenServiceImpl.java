package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.dao.OrdenRepository;
import com.spring.electric.tools.models.dao.OrdenRepositoryCustom;
import com.spring.electric.tools.models.entities.Orden;

@Transactional
@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	OrdenRepository ordenDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Orden> findAll() {
		return (List<Orden>) ordenDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Orden findById(Long id) {
		return ordenDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orden> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return ordenDAO.getContabilidad(fechaEntrada, fechaSalida);
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

	@Override
	public Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return ordenDAO.getValorArreglosTotal(fechaEntrada, fechaSalida);
	}

	@Override
	public Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return ordenDAO.getValorRepuestosTotal(fechaEntrada, fechaSalida);
	}

	@Override
	public List<Orden> buscarOrden(String campoBusqueda) {
		return ordenDAO.buscarOrden(campoBusqueda);
	}

}
