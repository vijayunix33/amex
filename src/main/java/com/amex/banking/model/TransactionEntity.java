package com.amex.banking.model;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transaction")
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

	public TransactionEntity() {
		super();
	}





	public TransactionEntity(String trans_ref_no, LocalDate trans_date, String trans_reason, double trans_process) {
		super();
		this.trans_ref_no = trans_ref_no;
		this.trans_date = trans_date;
		this.trans_reason = trans_reason;
		this.trans_process = trans_process;
	}


	public CustomerEntity getCust() {
		return cust;
	}


	public void setCust(CustomerEntity cust) {
		this.cust = cust;
	}


	public LocalDate getTrans_date() {
		return trans_date;
	}


	public void setTrans_date(LocalDate trans_date) {
		this.trans_date = trans_date;
	}


	public String getTrans_reason() {
		return trans_reason;
	}


	public void setTrans_reason(String trans_reason) {
		this.trans_reason = trans_reason;
	}


	public double getTrans_process() {
		return trans_process;
	}


	public void setTrans_process(double trans_process) {
		this.trans_process = trans_process;
	}


	public String getTrans_ref_no() {
		return trans_ref_no;
	}


	public void setTrans_ref_no(String trans_ref_no) {
		this.trans_ref_no = trans_ref_no;
	}




}
