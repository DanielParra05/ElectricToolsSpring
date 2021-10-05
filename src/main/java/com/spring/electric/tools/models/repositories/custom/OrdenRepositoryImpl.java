package com.spring.electric.tools.models.repositories.custom;

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
		Map<String, Object> params = new HashMap<>();

		// Construccion consulta JPQL
		String consulta = "SELECT DISTINCT o from Orden o INNER JOIN customer c ON c.id = o.customer ";

		String where = "WHERE c.cedula LIKE :campoBusqueda OR LOWER(CONCAT(c.nombre,' ',c.apellido)) LIKE LOWER( '%'||:campoBusqueda||'%' ) ";
		params.put("campoBusqueda", campoBusqueda);

		try {
			long numerOrden = Long.parseLong(campoBusqueda);
			where += "OR :numerOrden = o.id";
			params.put("numerOrden", numerOrden);
		} catch (NumberFormatException e) {
			System.out.println("No es un numero de Orden.");
		}

		consulta += where;

		Query query = entityManager.createQuery(consulta);
		params.forEach((k, v) -> query.setParameter(k, v));

		List<Orden> resultados = query.getResultList();

		return resultados;
	}

}
