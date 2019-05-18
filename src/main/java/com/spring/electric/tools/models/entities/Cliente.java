package com.spring.electric.tools.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="CLIENTES")
public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CLIENTE")
	private Long idCliente;
	
	@Column(name = "NOMBRE",nullable = false)
	private String nombre;	
	
	@Column(name = "APELLIDO")
<<<<<<< HEAD
	private String apellido;	
	
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)	
	private List<Orden> listaOrdenes;
=======
	private String apellido;
	
	@Column(name ="EMAIL", unique = true)
	private String email;	
	
	@Column(name = "CREATE_AT")
	private LocalDate createAt;
>>>>>>> 8ffcda8c2e1160124acd77edb40682944a307353
	
	@Column(name="CEDULA")
	private String cedula;
	
	@Column(name="TELEFONO")
	private String telefono;

	public Long getId() {
		return idCliente;
	}

	public void setId(Long id) {
		this.idCliente = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
