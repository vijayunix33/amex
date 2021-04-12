
package com.amex.banking.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amex.banking.dto.CustomerTransactionDTO;
import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/*
	 * This service retrieves all the customers from the database.
	 * 
	 */

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerEntity>> getCustomerDetails() {
		List<CustomerEntity> customerEntity = customerService.getCustomerDetails();
		if(customerEntity.isEmpty())
		    return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.OK);
	}
	
	
	/*
	 * This service retrieves all the customers based on the params from the database.
	 * @param custId, accountNo
	 * returns List<CustomerEntity>
	 */
	
	
	@GetMapping("/getCustomers")
	public ResponseEntity<List<CustomerTransactionDTO>> getCustomerDetails(@RequestParam(value = "custId", required=false) int custId, @RequestParam(value = "accountNo", required=false) int accountNo,
			@RequestParam(value = "startDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, @RequestParam(value= "toDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate, 
			@RequestParam(value= "dateFilter", required=false)  Boolean dateFilter) {
		List<CustomerTransactionDTO> customerEntity = null;
		 customerEntity= customerService.getCustomerDetailsByFilter(custId, accountNo, fromDate, toDate, dateFilter);
		if(customerEntity.isEmpty()) 
		    return new ResponseEntity<List<CustomerTransactionDTO>>(customerEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CustomerTransactionDTO>>(customerEntity, HttpStatus.OK);
	}
	
	
	/*
	 * This service retrieves all the Transactions based on the start date and end date from the database.
	 * @param startDate, toDate
	 * returns List<TransactionEntity>
	 */
	
	/*@GetMapping("/getDataBetweendates")
	public ResponseEntity<List<TransactionEntity>> getTransactionDetailsByDates(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		List<TransactionEntity> transactionEntity = null;
		transactionEntity= customerService.getTransactionDetailsByDates(fromDate, toDate);
		if(transactionEntity.isEmpty())
		    return new ResponseEntity<List<TransactionEntity>>(transactionEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<TransactionEntity>>(transactionEntity, HttpStatus.OK);
	}*/
	
	

}
