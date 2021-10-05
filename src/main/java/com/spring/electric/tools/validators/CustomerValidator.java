package com.spring.electric.tools.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.electric.tools.models.entities.Customer;
import com.spring.electric.tools.models.services.CustomerService;

@Component
public class CustomerValidator implements Validator {

	@Autowired
	CustomerService customerService;

	@Override
	public boolean supports(Class<?> clazz) {
		// Validates if the object corresponds to Customer type
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customerToValidate = (Customer) target;
		Customer customerToFound = customerService.findByIdentification(customerToValidate.getIdentification());

		if (customerToFound != null
				&& (customerToValidate.getId() == null || customerToFound.getId() != customerToValidate.getId())) {
			errors.rejectValue("identification", "identification.customer.repeated");
		}
	}
}
