package com.spring.electric.tools.models.services;

import com.spring.electric.tools.models.entities.Settings;

public interface AjustesService {
	/**
	 * Solo se usan estos metodos porque existe sola una fila
	 * en la tabla de Ajustes
	 */
	Settings getAjuste();

	Settings save(Settings ajuste);

}
