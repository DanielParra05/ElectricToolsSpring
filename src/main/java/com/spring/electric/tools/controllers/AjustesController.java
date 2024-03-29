package com.spring.electric.tools.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.electric.tools.models.entities.Settings;
import com.spring.electric.tools.models.services.AjustesService;

@Controller
public class AjustesController {

	@Autowired
	private AjustesService ajusteService;
	
	@GetMapping("/ajustes")
	public String getAjuste(Model model) {
		model.addAttribute("titulo", "Si, wenas");
		return "listar";
	}	
	
	@PostMapping("/ajustes")
	public ResponseEntity<?> create(@RequestBody Settings ajuste) {
		Settings ajusteNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			ajusteNew = ajusteService.save(ajuste);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Ajustes guardados exitosamente");
		response.put("ajustes", ajusteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
