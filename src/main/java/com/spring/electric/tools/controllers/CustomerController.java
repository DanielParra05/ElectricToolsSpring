package com.spring.electric.tools.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.services.CustomerService;
import com.spring.electric.tools.utils.PageRender;
import com.spring.electric.tools.validators.CustomerValidator;

@Controller
@RequestMapping("/customer-management")
@SessionAttributes("customer")
public class CustomerController {

	private static final int PAGINATOR_SIZE = 5;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerValidator customerValidador;
	
	@Autowired
	private MessageSource messageSource;

	@InitBinder // Validator injection
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(customerValidador);
	}

	@GetMapping("/customers")
	public String listCustomers(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "searchField", defaultValue = "") String searchField, Model model) {
		Pageable pageRequest = PageRequest.of(page, PAGINATOR_SIZE);
		Page<Customer> customers;
		PageRender<Customer> pageRender;
		if (!searchField.isEmpty()) {
			customers = customerService.searchCustomer(searchField, pageRequest);
			pageRender= new PageRender<Customer>("/customer-management/customers?searchField=".concat(searchField), customers);
		} else {
			customers = customerService.findAll(pageRequest);
			pageRender= new PageRender<Customer>("/customer-management/customers", customers);
		}
		model.addAttribute("customers", customers);
		model.addAttribute("page", pageRender);
		model.addAttribute("searchField", searchField);
		return "customer-management/customers";
	}

	@GetMapping("/customer-form")
	public String createcustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-management/customer-form";
	}

	@PostMapping("/customer-form")
	public String saveCustomer(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "customer-management/customer-form";
		}
		flash.addFlashAttribute("success",
				customer.getId() != null ? messageSource.getMessage("msg.customer.updated", null, null) : messageSource.getMessage("msg.customer.registered", null, null));
		customerService.save(customer);
		status.setComplete();
		return "redirect:/customer-management/customers";
	}

	@GetMapping("/customer-form/{id}")
	public String updateCustomer(@PathVariable Long id, Model model) {
		if (id > 0) {
			Customer customer = customerService.findById(id);
			model.addAttribute("customer", customer);
		} else {
			return "redirect:/customer-management/customers";
		}
		return "customer-management/customer-form";
	}

	@GetMapping("/delete-customer/{id}")
	public String deleteCustomer(@PathVariable Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			customerService.delete(id);
			flash.addFlashAttribute("success", messageSource.getMessage("msg.customer.deleted", null, null));
		}
		return "redirect:/customer-management/customers";
	}

}
