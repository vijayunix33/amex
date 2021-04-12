package com.amex.banking.dto;


import java.util.List;



import com.amex.banking.enumeration.CustomerEnum;

import com.amex.banking.model.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerTransactionDTO {
	
	
	private int cust_id;
	private String cust_Name;
	private int cust_account_no;
	private CustomerEnum cust_enum;

	private double balance;
	private String comments;
	private List<TransactionEntity> transactions;


	
	

}
