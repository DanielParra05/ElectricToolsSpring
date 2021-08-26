package com.spring.electric.tools.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.electric.tools.models.enums.OrdenEstado;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDENES")
@Getter @Setter
public class Orden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "FECHA_ENTRADA")
	private LocalDate fechaEntrada;

	@Column(name = "FECHA_SALIDA", nullable = true)
	private LocalDate fechaSalida;
	
	@Column(name = "OBSERVACIONES")
	private String observaciones;
	
	@ManyToOne(fetch = FetchType.LAZY) // Genera un proxy hacia la clase cliente
	@JoinColumn(name = "CLIENTE_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Se ignoran en el JSON los atributos generados
	private Cliente cliente;										// por el proxy debido a LAZY	

	@Column(name = "NOMBRE_ARTICULO")
	private String nombreArticulo;

	@Column(name = "MARCA_ARTICULO")
	private String marcaArticulo;

	@Column(name = "MODELO_ARTICULO")
	private String modeloArticulo;

	@Column(name = "SERIAL_ARTICULO")
	private String serialArticulo;

	@Column(name = "PROBLEMA_REPORTADO")
	private String problemaReportado;

	@Column(name = "VALOR_ARREGLO", nullable = true)
	private Integer valorArreglo;
	
	@Column(name = "VALOR_RESPUESTOS", nullable = true)
	private Integer valorRepuestos;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO", nullable=false)
	private OrdenEstado estado;
	
}
