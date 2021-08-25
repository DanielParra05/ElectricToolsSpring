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

@Entity
@Table(name = "ORDENES")
public class Orden implements Serializable {

	/**
	 * 
	 */
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public String getMarcaArticulo() {
		return marcaArticulo;
	}

	public void setMarcaArticulo(String marcaArticulo) {
		this.marcaArticulo = marcaArticulo;
	}

	public String getModeloArticulo() {
		return modeloArticulo;
	}

	public void setModeloArticulo(String modeloArticulo) {
		this.modeloArticulo = modeloArticulo;
	}

	public String getSerialArticulo() {
		return serialArticulo;
	}

	public void setSerialArticulo(String serialArticulo) {
		this.serialArticulo = serialArticulo;
	}

	public String getProblemaReportado() {
		return problemaReportado;
	}

	public void setProblemaReportado(String problemaReportado) {
		this.problemaReportado = problemaReportado;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getValorArreglo() {
		return valorArreglo;
	}

	public void setValorArreglo(Integer valorArreglo) {
		this.valorArreglo = valorArreglo;
	}

	public Integer getValorRepuestos() {
		return valorRepuestos;
	}

	public void setValorRepuestos(Integer valorRepuestos) {
		this.valorRepuestos = valorRepuestos;
	}

	public OrdenEstado getEstado() {
		return estado;
	}

	public void setEstado(OrdenEstado estado) {
		this.estado = estado;
	}

	
	
	
}
