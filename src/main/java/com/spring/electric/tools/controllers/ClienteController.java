package com.spring.electric.tools.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.services.ClienteService;
import com.spring.electric.tools.utils.PageRender;
import com.spring.electric.tools.validators.ClienteValidator;

@Controller
@RequestMapping("/gestion-clientes")
@SessionAttributes("cliente")
public class ClienteController {

	private static final int PAGINATOR_SIZE = 5;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteValidator clienteValidador;

	@InitBinder // Inyeccion de validador personalizado para le objeto Cliente
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(clienteValidador);
	}

	@GetMapping("/clientes")	
	public String listarClientes(@RequestParam(name="page", defaultValue = "0") int page, Model model) {		
		Pageable pageRequest = PageRequest.of(page, PAGINATOR_SIZE);
		Page <Cliente> clientes = clienteService.findAll(pageRequest);		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/gestion-clientes/clientes", clientes);		
		
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "gestion-clientes/clientes";
	}

	@GetMapping("/registrar-cliente")
	public String registrarCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "gestion-clientes/registrar-cliente";
	}

	@PostMapping("/registrar-cliente")
	public String guardarCliente(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "gestion-clientes/registrar-cliente";
		}
		flash.addFlashAttribute("success",
				cliente.getId() != null ? "Cliente actualizado exitosamente!" : "Cliente registrado exitosamente!");
		clienteService.save(cliente);
		status.setComplete();		
		return "redirect:/gestion-clientes/clientes";
	}

	@GetMapping("/registrar-cliente/{id}")
	public String editarCliente(@PathVariable Long id, Model model) {
		if (id > 0) {
			Cliente cliente = clienteService.findById(id);
			model.addAttribute("cliente", cliente);
		} else {
			return "redirect:/gestion-clientes/clientes";
		}
		return "gestion-clientes/registrar-cliente";
	}

	@GetMapping("/eliminar-cliente/{id}")
	public String eliminarCliente(@PathVariable Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success","Cliente eliminado!");
		}
		return "redirect:/gestion-clientes/clientes";
	}


	@GetMapping("/clientes/busqueda/{campoBusqueda}")
	public Page<Cliente> buscarClientes(@PathVariable String campoBusqueda) {
		return clienteService.buscarCliente(campoBusqueda, PageRequest.of(0, PAGINATOR_SIZE));
	}

}
