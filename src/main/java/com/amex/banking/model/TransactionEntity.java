package com.amex.banking.model;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.amex.banking.enumeration.CustomerEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String trans_ref_no;
	private LocalDate trans_date;
	private String trans_reason;
	private double trans_process;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cust_id", nullable = false)
	private CustomerEntity cust;
	
	public TransactionEntity( String trans_ref_no, LocalDate trans_date, String trans_reason,
			double trans_process) {
		super();
		this.trans_ref_no = trans_ref_no;
		this.trans_date = trans_date;
		this.trans_reason = trans_reason;
		this.trans_process = trans_process;
	}
	
	
	

	


}
