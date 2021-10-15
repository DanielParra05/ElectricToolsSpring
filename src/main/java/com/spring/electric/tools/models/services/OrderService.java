package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.electric.tools.models.entities.WorkOrder;

public interface OrderService {

	List<WorkOrder> findAll();

	Page<WorkOrder> findAll(Pageable pageRequest);

	Page<WorkOrder> searchOrder(String searchField, Pageable pageRequest);

	WorkOrder findById(Long id);

	WorkOrder save(WorkOrder orden);

	List<WorkOrder> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida);

	Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);

	Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);

	void delete(Long id);

}
