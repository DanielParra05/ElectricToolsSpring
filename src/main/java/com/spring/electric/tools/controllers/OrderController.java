package com.spring.electric.tools.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.entities.WorkOrder;
import com.spring.electric.tools.models.enums.OrderStatus;
import com.spring.electric.tools.models.services.CustomerService;
import com.spring.electric.tools.models.services.OrderService;
import com.spring.electric.tools.utils.PageRender;

@Controller
@RequestMapping("/order-management")
public class OrderController {

	private static final int PAGINATOR_SIZE = 5;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

	}

	@GetMapping("/orders")
	public String listOrders(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "searchField", defaultValue = "") String searchField, Model model) {
		Pageable pageRequest = PageRequest.of(page, PAGINATOR_SIZE);
		Page<WorkOrder> orders;
		PageRender<WorkOrder> pageRender;
		
		if (!searchField.isEmpty()) {
			orders = orderService.searchOrder(searchField, pageRequest);
			pageRender = new PageRender<>("/order-management/orders?searchField=".concat(searchField), orders);
		} else {
			orders = orderService.findAll(pageRequest);
			pageRender = new PageRender<>("/order-management/orders", orders);
		}
		model.addAttribute("workOrders", orders);
		model.addAttribute("page", pageRender);

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
	public String saveOrder(WorkOrder workOrder, Model model, RedirectAttributes flash) {
		workOrder.setOrderStatus(OrderStatus.EN_REPARACION);
		orderService.save(workOrder);
		flash.addFlashAttribute("success", messageSource.getMessage("msg.order.registered", null, null));

		return "redirect:/order-management/orders";
	}

	@GetMapping("/delete-order/{orderId}")
	public String deleteOrder(@PathVariable Long orderId, Model model, RedirectAttributes flash) {
		if (orderId > 0) {
			orderService.delete(orderId);
			flash.addFlashAttribute("success", messageSource.getMessage("msg.order.deleted", null, null));
		}
		return "redirect:/order-management/orders";
	}

}
