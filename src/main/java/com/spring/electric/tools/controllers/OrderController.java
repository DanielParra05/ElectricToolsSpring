package com.spring.electric.tools.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.enums.OrderStatus;
import com.spring.electric.tools.models.services.CustomerService;
import com.spring.electric.tools.models.services.OrderService;

@Controller
@RequestMapping("/order-management")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/orders")
	public String listOrders(Model model) {
		List<WorkOrder> workOrders = orderService.findAll();			
		model.addAttribute("workOrders", workOrders);		
		
		return "order-management/orders";
	}
	
	@GetMapping("/order-form")
	public String creatOrder(Model model) {
		WorkOrder workOrder = new WorkOrder();
		List<Customer> customersList = customerService.findAll();
		model.addAttribute("workOrder", workOrder);
		model.addAttribute("customers", customersList);

		return "order-management/order-form";
	}
	
	@PostMapping("/order-form")
	public String saveOrder(WorkOrder workOrder, Model model) {
		workOrder.setOrderStatus(OrderStatus.EN_REPARACION);
		orderService.save(workOrder);

		return "redirect:/order-management/orders";
	}

}
