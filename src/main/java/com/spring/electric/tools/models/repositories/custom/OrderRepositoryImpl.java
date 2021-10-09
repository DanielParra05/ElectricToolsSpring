package com.spring.electric.tools.models.repositories.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.spring.electric.tools.models.entities.WorkOrder;

public class OrderRepositoryImpl implements OrderCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<WorkOrder> buscarOrden(String campoBusqueda) {
		Map<String, Object> params = new HashMap<>();

		// Construccion consulta JPQL
		String consulta = "SELECT DISTINCT o from WorkOrder o INNER JOIN customer c ON c.id = o.customer ";

		String where = "WHERE c.cedula LIKE :campoBusqueda OR LOWER(CONCAT(c.nombre,' ',c.apellido)) LIKE LOWER( '%'||:campoBusqueda||'%' ) ";
		params.put("campoBusqueda", campoBusqueda);

		try {
			long numerOrder = Long.parseLong(campoBusqueda);
			where += "OR :numerOrder = o.id";
			params.put("numerOrder", numerOrder);
		} catch (NumberFormatException e) {
			System.out.println("No es un numero de Order.");
		}

		consulta += where;

		Query query = entityManager.createQuery(consulta);
		params.forEach((k, v) -> query.setParameter(k, v));

		List<WorkOrder> resultados = query.getResultList();

		return resultados;
	}

}
