package com.spring.electric.tools.models.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.spring.electric.tools.models.entities.Orden;

public class OrdenRepositoryImpl implements OrdenRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Orden> buscarOrden(String campoBusqueda) {
		// TODO Auto-generated method stub

		Map<String, Object> params = new HashMap<>();

		// Construccion consulta JPQL
		String consulta = "SELECT DISTINCT o from Orden o INNER JOIN Cliente c ON c.id = o.cliente ";

		String where = "WHERE CONCAT(:campoBusqueda,'%') LIKE c.cedula OR CONCAT(:campoBusqueda,'%') LIKE LOWER(CONCAT(c.nombre,' ',c.apellido)) ";
		params.put("campoBusqueda", campoBusqueda.toLowerCase());

		try {
			long numerOrden = Long.parseLong(campoBusqueda);
			where += "OR :numerOrden = o.id";
			params.put("numerOrden", numerOrden);
		} catch (NumberFormatException e) {
			System.out.println("No es un numero de Orden.");
			e.printStackTrace();
		}

		consulta += where;
		Query query = entityManager.createQuery(consulta);
		params.forEach((k, v) -> query.setParameter(k, v));

		List<Orden> resultados= query.getResultList();
		
		return resultados;
	}

}
