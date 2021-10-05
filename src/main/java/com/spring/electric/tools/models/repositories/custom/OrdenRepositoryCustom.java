package com.spring.electric.tools.models.repositories.custom;

import java.util.List;

import com.spring.electric.tools.models.entities.WorkOrder;

/**
 * 
 * @author Jose Daniel Parra
 *
 */
public interface OrdenRepositoryCustom {
	
	/**
	 * Filtrar las ordenes segun el parametro enviado
	 * @param campoBusqueda valor obtenido del campo de busqueda, puede ser un numero o 
	 * @return
	 */
	List<WorkOrder> buscarOrden(String campoBusqueda);
	
}
