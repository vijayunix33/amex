package com.amex.banking.service;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.repository.CustomerRepository;
import com.amex.banking.repository.TransactionRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	public List<CustomerEntity> getCustomerDetails() {
		
		return customerRepository.findAll();
	}

	
	
	public List<CustomerEntity> getCustomerDetailsByFilter(int custid , int acctNo){
		if(custid != 0) {
			return customerRepository.getCustomerDetailsById(custid);
		}
		return customerRepository.getCustomerDetailsByAccountNumber(acctNo);
    }



	public List<TransactionEntity> getTransactionDetailsByDates(LocalDate startDate, LocalDate endDate) {
		return transactionRepository.getTransactionDetailsByDates(startDate, endDate);
	}
	

}
