package com.spring.electric.tools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="CLIENTES")
@Getter @Setter
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "NOMBRE",nullable = false)
	private String nombre;	
	
	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name="CEDULA")
	private String cedula;
	
	@Column(name="TELEFONO")
	private String telefono;
	
}
