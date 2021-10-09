package com.spring.electric.tools.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.electric.tools.models.entities.Settings;
import com.spring.electric.tools.models.repositories.SettingsRepository;

@Service
public class AjustesServiceImpl implements AjustesService {

	@Autowired
	private SettingsRepository ajustesDAO;

	/**
	 * Retorna la fila de ajustes existente, si no existe la crea
	 */
	@Override
	public Settings getAjuste() {
		Optional<Settings> optAjustes = ajustesDAO.findById(1L);
		if (optAjustes.isPresent()) {
			return optAjustes.get();
		}else {
			Settings primerAjuste = new Settings();
			primerAjuste.setId(1L);
			save(primerAjuste);			
			return ajustesDAO.findById(1L).get();
		}
	}

	@Override
	public Settings save(Settings ajuste) {
		// TODO Auto-generated method stub
		ajuste.setId(1L);
		return ajustesDAO.save(ajuste);
	}

}
