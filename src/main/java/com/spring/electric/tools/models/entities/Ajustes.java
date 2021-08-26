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
@Table(name="AJUSTES")
@Getter @Setter
public class Ajustes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TXT_ENCABEZADO", length=5000)
	private String txtEncabezado;

	@Column(name="TXT_PIEPAGINA", length=5000)
	private String txtPiePagina;

}
