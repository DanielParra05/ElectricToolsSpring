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
@Table(name="CUSTOMER")
@Getter @Setter
public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME",nullable = false)
	private String name;	
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name="IDENTIFICATION")
	private String identification;
	
	@Column(name="PHONE")
	private String phone;
	
}
