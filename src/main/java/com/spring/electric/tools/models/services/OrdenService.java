package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import com.spring.electric.tools.models.entities.Orden;

public interface OrdenService {
	
	List<Orden> findAll();
	
	Orden findById(Long id);
	
	Orden save(Orden orden);
	
	List<Orden> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	void delete (Long id);
	
	List<Orden> buscarOrden(String campoBusqueda);
	

}
