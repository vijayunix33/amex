package com.amex.banking.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.amex.banking.enumeration.CustomerEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;



@Entity
@Table(name = "customer")
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerEntity {
	@Id
	private int cust_id;
	private String cust_Name;
	@Column(unique = true)
	private int cust_account_no;
	private CustomerEnum cust_enum;

	private double balance;
	private String comments;
	@JsonManagedReference
	@OneToMany(targetEntity = TransactionEntity.class, mappedBy = "cust", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<TransactionEntity> transactions;
	
	public CustomerEntity(int cust_id, String cust_Name, int cust_account_no, CustomerEnum cust_enum, double balance,
			String comments) {
		super();
		this.cust_id = cust_id;
		this.cust_Name = cust_Name;
		this.cust_account_no = cust_account_no;
		this.cust_enum = cust_enum;
		this.balance = balance;
		this.comments = comments;
		
	}
	
	



}
