package com.spring.electric.tools.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.electric.tools.converters.DateConverter;
import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.services.OrderService;

@RestController
@RequestMapping("/api")
public class AccountingController {

	@Autowired
	private OrderService ordenService;

	/**
	 * Obtener ordenes que tiene fechaEntrada/fechaSalida dentro del rango
	 * especificado
	 * 
	 * @return
	 */
	@GetMapping("/contabilidad/{fechaEntrada}/{fechaSalida}")
	public List<WorkOrder> getContabilidad(@PathVariable String fechaEntrada, @PathVariable String fechaSalida) {
	
		return	ordenService.getContabilidad(DateConverter.convertToLocalDate(fechaEntrada),
				DateConverter.convertToLocalDate(fechaSalida));
	
	}

	/**
	 * Realiza las operaciones necesarias de obtener los datos
	 * 
	 * @return
	 */
	@GetMapping("/arreglos/{fechaEntrada}/{fechaSalida}")
	public Integer getValorArreglosTotal(@PathVariable String fechaEntrada, @PathVariable String fechaSalida) {

		return ordenService.getValorArreglosTotal(DateConverter.convertToLocalDate(fechaEntrada),
				DateConverter.convertToLocalDate(fechaSalida));
	}

	/**
	 * Realiza las operaciones necesarias de obtener los datos
	 * 
	 * @return
	 */
	@GetMapping("/repuestos/{fechaEntrada}/{fechaSalida}")
	public Integer getValorRepuestosTotal(@PathVariable String fechaEntrada, @PathVariable String fechaSalida) {

		return ordenService.getValorRepuestosTotal(DateConverter.convertToLocalDate(fechaEntrada),
				DateConverter.convertToLocalDate(fechaSalida));
	}

	
	

}
