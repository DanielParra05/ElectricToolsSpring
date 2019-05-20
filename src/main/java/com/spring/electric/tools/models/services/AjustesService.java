package com.spring.electric.tools.models.services;

import com.spring.electric.tools.models.entities.Ajustes;

public interface AjustesService {
	/**
	 * Solo se usan estos metodos porque existe sola una fila
	 * en la tabla de Ajustes
	 */
	public Ajustes getAjuste();

	public Ajustes save(Ajustes ajuste);

}
