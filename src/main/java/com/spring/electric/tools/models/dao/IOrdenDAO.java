package com.spring.electric.tools.models.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Orden;

public interface IOrdenDAO extends CrudRepository<Orden, Long> {
	
	/**
	 * Filtrar las ordenes segun un rango de fechas especificas
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return
	 */
	@Query("SELECT o FROM Orden o WHERE o.fechaEntrada  >= ?1 "
			+ "and o.fechaSalida <= ?2 and o.estado LIKE 'LISTO' ")
	List<Orden> getContabilidad(LocalDate  fechaEntrada, LocalDate  fechaSalida);
	
	@Query("SELECT SUM(o.valorArreglo) FROM Orden o WHERE o.fechaEntrada  >= ?1 "
			+ "and o.fechaSalida  <= ?2 and o.estado LIKE 'LISTO' ")
	Integer getValorArreglosTotal(LocalDate  fechaEntrada, LocalDate  fechaSalida);
	
	@Query("SELECT SUM(o.valorRepuestos) FROM Orden o WHERE o.fechaEntrada  >= ?1 "
			+ "and o.fechaSalida  <= ?2 and o.estado LIKE 'LISTO' ")
	Integer getValorRepuestosTotal(LocalDate  fechaEntrada, LocalDate  fechaSalida);
	
	
}
