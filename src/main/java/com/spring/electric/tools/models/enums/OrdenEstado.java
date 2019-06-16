package com.spring.electric.tools.models.enums;

/**
 * Clase para indicar el estado de una orden
 * @author Jose Daniel Parra
 *
 */
public enum OrdenEstado { 
	EN_REPARACION("En Reparacion"),
	LISTO("Listo");
	
	private String valor;
	
	private OrdenEstado(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
