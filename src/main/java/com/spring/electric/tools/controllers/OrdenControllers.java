package com.spring.electric.tools.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.electric.tools.models.entities.Orden;
import com.spring.electric.tools.models.enums.OrdenEstado;
import com.spring.electric.tools.models.services.OrdenService;

@RestController
@RequestMapping("/api")
public class OrdenControllers {

	@Autowired
	private OrdenService ordenService;

	@GetMapping("/ordenes")
	public List<Orden> index() {
		return ordenService.findAll();
	}

	/**
	 * Obtiene una orden
	 * 
	 * @param id => Orden a mostrar
	 * @return
	 */
	@GetMapping("/ordenes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Orden orden = null;
		Map<String, Object> response = new HashMap<>();
		try {
			orden = ordenService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error a nivel de base de datos.");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (orden == null) {
			response.put("mensaje", "La Orden con ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Orden>(orden, HttpStatus.OK);
	}

	/**
	 * Crea una nueva orden
	 * 
	 * @param orden => Orden a insertar en la BD
	 * @return
	 */
	@PostMapping("/ordenes")
	public ResponseEntity<?> create(@RequestBody Orden orden) {

		Orden ordenNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			if (orden.getId() == 0) {
				orden.setEstado(OrdenEstado.EN_REPARACION);
			}
			ordenNew = ordenService.save(orden);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La orden ha sido creado con exito");
		response.put("orden", ordenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Actualiza las ordenes segun una orden enviada
	 * 
	 * @param orden => orden con los nuevos datos
	 * @param id    => id de la orden a actualizar
	 * @return
	 */
	@PutMapping("/ordenes/{id}")
	public ResponseEntity<?> update(@RequestBody Orden orden, @PathVariable Long id) {
		Orden ordenActual = ordenService.findById(id);
		Orden ordenActualizada = null;
		Map<String, Object> response = new HashMap<>();

		if (ordenActual == null) {
			response.put("mensaje", "Error no se pudo editar. Orden ID: " + id + " no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			ordenActual.setFechaSalida(orden.getFechaSalida());
			ordenActual.setMarcaArticulo(orden.getMarcaArticulo());
			ordenActual.setModeloArticulo(orden.getModeloArticulo());
			ordenActual.setNombreArticulo(orden.getNombreArticulo());
			ordenActual.setProblemaReportado(orden.getProblemaReportado());
			ordenActual.setSerialArticulo(orden.getSerialArticulo());
			ordenActual.setValorArreglo(orden.getValorArreglo());
			ordenActual.setValorRepuestos(orden.getValorRepuestos());
			ordenActual.setObservaciones(orden.getObservaciones());
			ordenActual.setCliente(orden.getCliente());
			ordenActual.setEstado(orden.getEstado());
			ordenActualizada = ordenService.save(ordenActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en BD");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La orden ha sido actualizado con exito");
		response.put("orden", ordenActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Obtener las ordenes que coincidan con los parametros enviados
	 * 
	 * @param campoBusqueda valor a buscar
	 * @return Lista de Ordenes que coincidan
	 */
	@GetMapping("/ordenes/busqueda/{campoBusqueda}")
	public List<Orden> busqueda(@PathVariable String campoBusqueda) {
		return ordenService.buscarOrden(campoBusqueda);
	}

	/**
	 * Elimina las ordenes segun un id enviado
	 * 
	 * @param id => id de la orden a borrar
	 * @return
	 */
	@DeleteMapping("/ordenes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ordenService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar en BD");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Orden eliminada con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
