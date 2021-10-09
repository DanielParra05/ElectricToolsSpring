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
import com.spring.electric.tools.models.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "work_order")
@Getter @Setter
public class WorkOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "entry_date")
	private LocalDate entryDate;

	@Column(name = "leaving_date", nullable = true)
	private LocalDate leavingDate;
	
	@Column(name = "comments")
	private String comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Customer customer;

	@Column(name = "tool_name")
	private String toolName;

	@Column(name = "tool_brand")
	private String toolBrand;

	@Column(name = "tool_model")
	private String toolModel;

	@Column(name = "tool_serial")
	private String toolSerial;

	@Column(name = "reported_issue")
	private String reportedIssue;

	@Column(name = "repair_cost", nullable = true)
	private Integer repairCost;
	
	@Column(name = "replacements_cost", nullable = true)
	private Integer replacementsCost;
	
	@Enumerated(EnumType.STRING)
	@Column(name="order_status", nullable=false)
	private OrderStatus orderStatus;
	
}
