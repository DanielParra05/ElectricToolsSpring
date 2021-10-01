package com.spring.electric.tools.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.electric.tools.models.entities.Cliente;
import com.spring.electric.tools.models.services.ClienteService;

@Component
public class ClienteValidator implements Validator {

	@Autowired
	ClienteService clienteService;

	@Override
	public boolean supports(Class<?> clazz) {
		// Se valida si el objeto que esta pasando corresponde al tipo cliente
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cliente customerToValidate = (Cliente) target;
		Cliente customerToFound = clienteService.findByCedula(customerToValidate.getCedula());

		if (customerToFound != null
				&& (customerToValidate.getId() == null || customerToFound.getId() != customerToValidate.getId())) {
			errors.rejectValue("cedula", "cedula.cliente.repetida");
		}
	}
}
