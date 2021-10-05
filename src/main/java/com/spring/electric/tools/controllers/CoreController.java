package com.spring.electric.tools.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.services.CustomerService;

@Controller
public class CoreController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		return "index";
	}
}
