package com.spring.electric.tools.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.services.ClienteService;

@Controller
public class CoreController {

	@Autowired
	ClienteService clienteService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Cliente> clientes = clienteService.findAll();
		model.addAttribute("clientes", clientes);
		return "index";
	}
}
