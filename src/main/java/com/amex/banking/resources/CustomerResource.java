package com.amex.banking.resources;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerEntity>> getCustomerDetails() {
		List<CustomerEntity> customerEntity = customerService.getCustomerDetails();
		System.out.println("list of customerrs---->"+customerEntity);
		if(customerEntity.isEmpty())
		    return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.OK);
	}
	
	
	@GetMapping("/getCustomers")
	public ResponseEntity<List<CustomerEntity>> getCustomerDetails(@RequestParam("custId") int custId, @RequestParam("accountNo") int accountNo) {
		List<CustomerEntity> customerEntity = null;
		 customerEntity= customerService.getCustomerDetailsByFilter(custId, accountNo); 
		if(customerEntity.isEmpty())
		    return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<CustomerEntity>>(customerEntity, HttpStatus.OK);
	}
	
	
	@GetMapping("/getDataBetweendates")
	public ResponseEntity<List<TransactionEntity>> getTransactionDetailsByDates(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
		List<TransactionEntity> transactionEntity = null;
		transactionEntity= customerService.getTransactionDetailsByDates(fromDate, toDate);
		if(transactionEntity.isEmpty())
		    return new ResponseEntity<List<TransactionEntity>>(transactionEntity, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<TransactionEntity>>(transactionEntity, HttpStatus.OK);
	}
	
	

}
