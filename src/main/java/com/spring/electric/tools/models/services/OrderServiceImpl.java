package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.repositories.OrdenRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdenRepository ordenDAO;

	@Override
	@Transactional(readOnly = true)
	public List<WorkOrder> findAll() {
		return (List<WorkOrder>) ordenDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public WorkOrder findById(Long id) {
		return ordenDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WorkOrder> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return ordenDAO.getContabilidad(fechaEntrada, fechaSalida);
	}

	@Override
	@Transactional
	public WorkOrder save(WorkOrder orden) {
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
	public List<WorkOrder> buscarOrden(String campoBusqueda) {
		return ordenDAO.buscarOrden(campoBusqueda);
	}

}
