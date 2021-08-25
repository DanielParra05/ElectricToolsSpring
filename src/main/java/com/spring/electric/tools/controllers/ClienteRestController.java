package com.spring.electric.tools.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.services.ClienteServiceImpl;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	private static final int PAGINATOR_SIZE = 2;
	
	@Autowired
	private ClienteServiceImpl clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page, @RequestParam(required = false) String campoBusqueda) {
		
		return clienteService.index(PageRequest.of(page, PAGINATOR_SIZE), campoBusqueda);
	}
	
	/**
	 * Buscar cliente por el parametro enviado
	 * @param campoBusqueda cadena a buscar entre los atributos de los clientes
	 * @param page pagina pedida desde el front
	 * @return pagina con los clientes que coincidan
	 */
	@GetMapping("/clientes/busqueda/{campoBusqueda}/{page}")
	public Page<Cliente> buscarClientes(@PathVariable String campoBusqueda, @PathVariable Integer page) {
		return clienteService.buscarCliente(campoBusqueda, PageRequest.of(page, PAGINATOR_SIZE));
	}

	/**
	 * Obtener un cliente
	 * 
	 * @param id id del cliente a mostrar
	 * @return cliente encontrado
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error a nivel de base de datos.");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	/**
	 * Crear un nuevo cliente
	 * 
	 * @param cliente => cliente a insertar en la BD
	 * @return
	 */
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		if (clienteService.findByCedula(cliente.getCedula()) == null) {
			try {
				clienteNew = clienteService.save(cliente);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error");
				response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "El cliente ha sido creado con exito");
			response.put("cliente", clienteNew);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			response.put("mensaje", "Un cliente con esta cedula ya existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
		}
	}

	/**
	 * Actualizar los clientes segun un cliente enviado
	 * 
	 * @param cliente => Cliente con los nuevos datos
	 * @param id      => id del cliente a actualizar
	 * @return
	 */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (clienteActual == null) {
			response.put("mensaje", "Error no se pudo editar. Cliente ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setCedula(cliente.getCedula());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActualizado = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en BD");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con exito");
		response.put("cliente", clienteActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Eliminar los clientes segun un id enviado
	 * 
	 * @param id => id del cliente a borrar
	 * @return
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en BD");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
