package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.repositories.OrderRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	@Transactional(readOnly = true)
	public List<WorkOrder> findAll() {
		return orderRepository.findAllByOrderByEntryDateDesc();
	}

	@Override
	@Transactional(readOnly = true)
	public WorkOrder findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<WorkOrder> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return orderRepository.getContabilidad(fechaEntrada, fechaSalida);
	}

	@Override
	@Transactional
	public WorkOrder save(WorkOrder orden) {
		return orderRepository.save(orden);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return orderRepository.getValorArreglosTotal(fechaEntrada, fechaSalida);
	}

	@Override
	public Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return orderRepository.getValorRepuestosTotal(fechaEntrada, fechaSalida);
	}

	@Override
	public Page<WorkOrder> findAll(Pageable pageRequest) {		
		return orderRepository.findAll(pageRequest);
	}

	@Override
	public Page<WorkOrder> searchOrder(String searchField, Pageable pageRequest) {
		return orderRepository.searchOrder(searchField, pageRequest);
	}

}
