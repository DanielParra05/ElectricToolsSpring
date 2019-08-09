package com.spring.electric.tools.models.services;

import java.time.LocalDate;
import java.util.List;

import com.spring.electric.tools.models.entities.Orden;

public interface OrdenService {
	
	public List<Orden> findAll();
	
	public Orden findById(Long id);
	
	public Orden save(Orden orden);
	
	public List<Orden> getContabilidad(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	public Integer getValorArreglosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	public Integer getValorRepuestosTotal(LocalDate fechaEntrada, LocalDate fechaSalida);
	
	public void delete (Long id);
	
	public List<Orden> buscarOrden(String campoBusqueda);
	

}
