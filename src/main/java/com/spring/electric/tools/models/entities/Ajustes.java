package com.spring.electric.tools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="AJUSTES")
public class Ajustes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TXT_ENCABEZADO", length=5000)
	private String txtEncabezado;

	@Column(name="TXT_PIEPAGINA", length=5000)
	private String txtPiePagina;

	

	public String getTxtEncabezado() {
		return txtEncabezado;
	}

	public void setTxtEncabezado(String txtEncabezado) {
		this.txtEncabezado = txtEncabezado;
	}

	public String getTxtPiePagina() {
		return txtPiePagina;
	}

	public void setTxtPiePagina(String txtPiePagina) {
		this.txtPiePagina = txtPiePagina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
