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
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "customer")
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
	private List<TransactionEntity> trans;



	public CustomerEntity() {
		super();
	}



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

	public List<TransactionEntity> getTrans() {
		return trans;
	}


	public void setTrans(List<TransactionEntity> trans) {
		this.trans = trans;
	}


	public String getCust_Name() {
		return cust_Name;
	}





	public void setCust_Name(String cust_Name) {
		this.cust_Name = cust_Name;
	}





	public int getCust_account_no() {
		return cust_account_no;
	}





	public void setCust_account_no(int cust_account_no) {
		this.cust_account_no = cust_account_no;
	}





	public CustomerEnum getCust_enum() {
		return cust_enum;
	}





	public void setCust_enum(CustomerEnum cust_enum) {
		this.cust_enum = cust_enum;
	}





	public int getCust_id() {
		return cust_id;
	}





	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}





	public double getBalance() {
		return balance;
	}





	public void setBalance(double balance) {
		this.balance = balance;
	}





	public String getComments() {
		return comments;
	}





	public void setComments(String comments) {
		this.comments = comments;
	}








}
