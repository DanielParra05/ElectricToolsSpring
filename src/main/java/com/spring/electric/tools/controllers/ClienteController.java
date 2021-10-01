package com.spring.electric.tools.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.spring.electric.tools.validators.ClienteValidator;

@Controller
@RequestMapping("/gestion-clientes")
@SessionAttributes("cliente")
public class ClienteController {

	private static final int PAGINATOR_SIZE = 2;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteValidator clienteValidador;

	@InitBinder // Inyeccion de validador personalizado para le objeto Cliente
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(clienteValidador);
	}

	@GetMapping("/clientes")
	public String listarClientes(Model model) {
		List<Cliente> clientes = clienteService.findAll();
		model.addAttribute("clientes", clientes);
		return "gestion-clientes/clientes";
	}

	@GetMapping("/registrar-cliente")
	public String registrarCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "gestion-clientes/registrar-cliente";
	}

	@PostMapping("/registrar-cliente")
	public String guardarCliente(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			return "gestion-clientes/registrar-cliente";
		}
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "Esto es para mostrar que todo ok");
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
	public String eliminarCliente(@PathVariable Long id, Model model) {
		if (id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/gestion-clientes/clientes";
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page, @RequestParam(required = false) String campoBusqueda) {
		return clienteService.index(PageRequest.of(page, PAGINATOR_SIZE), campoBusqueda);
	}

	/**
	 * Buscar cliente por el parametro enviado
	 * 
	 * @param campoBusqueda cadena a buscar entre los atributos de los clientes
	 * @param page          pagina pedida desde el front
	 * @return pagina con los clientes que coincidan
	 */
	@GetMapping("/clientes/busqueda/{campoBusqueda}/{page}")
	public Page<Cliente> buscarClientes(@PathVariable String campoBusqueda, @PathVariable Integer page) {
		return clienteService.buscarCliente(campoBusqueda, PageRequest.of(page, PAGINATOR_SIZE));
	}

}
