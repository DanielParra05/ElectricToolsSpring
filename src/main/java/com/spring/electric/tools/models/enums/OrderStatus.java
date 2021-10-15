package com.spring.electric.tools.models.enums;

/**
 * Clase para indicar el estado de una orden
 * 
 * @author Jose Daniel Parra
 */
public enum OrderStatus {
	EN_REPARACION("En reparación"), LISTO("Listo");

	private String valor;

	private OrderStatus(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
