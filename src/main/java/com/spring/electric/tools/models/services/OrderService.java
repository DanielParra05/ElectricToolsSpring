package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import com.spring.electric.tools.models.entities.WorkOrder;

public interface OrderService {
	
	List<WorkOrder> findAll();
	
	WorkOrder findById(Long id);
	
	WorkOrder save(WorkOrder orden);
	
	List<WorkOrder> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	void delete (Long id);
	
	List<WorkOrder> buscarOrden(String campoBusqueda);
	

}
