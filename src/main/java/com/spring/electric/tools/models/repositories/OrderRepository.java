package com.spring.electric.tools.models.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.repositories.custom.OrderCustomRepository;

public interface OrderRepository extends CrudRepository<WorkOrder, Long>, OrderCustomRepository{
	
	/**
	 * Filtrar las ordenes segun un rango de fechas especificas
	 * @param entryDate
	 * @param leavingDate
	 * @return
	 */
	@Query("SELECT o FROM WorkOrder o WHERE o.entryDate  >= ?1 "
			+ "and o.leavingDate <= ?2 and o.orderStatus LIKE 'LISTO' ")
	List<WorkOrder> getContabilidad(LocalDate  entryDate, LocalDate  leavingDate);
	
	@Query("SELECT SUM(o.repairCost) FROM WorkOrder o WHERE o.entryDate  >= ?1 "
			+ "and o.leavingDate  <= ?2 and o.orderStatus LIKE 'LISTO' ")
	Integer getValorArreglosTotal(LocalDate  entryDate, LocalDate  leavingDate);
	
	@Query("SELECT SUM(o.replacementsCost) FROM WorkOrder o WHERE o.entryDate  >= ?1 "
			+ "and o.leavingDate  <= ?2 and o.orderStatus LIKE 'LISTO' ")
	Integer getValorRepuestosTotal(LocalDate  entryDate, LocalDate  leavingDate);
	
	List<WorkOrder> findAllByOrderByEntryDateDesc();
	
	WorkOrder findFirstByCustomer(Customer customer);
	
	Page<WorkOrder> findAllByOrderByEntryDateDesc(Pageable pageRequest);
	
	@Query("SELECT DISTINCT o from WorkOrder o INNER JOIN Customer c ON c.id = o.customer WHERE o.id = ?1 OR c.identification LIKE ?2")
	Page<WorkOrder> searchOrder(long orderId, String customerIdentification, Pageable pageRequest);
}
