package com.spring.electric.tools.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.electric.tools.models.entities.Ajustes;
import com.spring.electric.tools.models.repositories.AjustesRepository;

@Service
public class AjustesServiceImpl implements AjustesService {

	@Autowired
	private AjustesRepository ajustesDAO;

	/**
	 * Retorna la fila de ajustes existente, si no existe la crea
	 */
	@Override
	public Ajustes getAjuste() {
		Optional<Ajustes> optAjustes = ajustesDAO.findById(1L);
		if (optAjustes.isPresent()) {
			return optAjustes.get();
		}else {
			Ajustes primerAjuste = new Ajustes();
			primerAjuste.setId(1L);
			save(primerAjuste);			
			return ajustesDAO.findById(1L).get();
		}
	}

	@Override
	public Ajustes save(Ajustes ajuste) {
		// TODO Auto-generated method stub
		ajuste.setId(1L);
		return ajustesDAO.save(ajuste);
	}

}
