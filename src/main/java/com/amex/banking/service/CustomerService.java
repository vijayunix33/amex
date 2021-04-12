package com.amex.banking.service;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amex.banking.dto.CustomerTransactionDTO;
import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.repository.CustomerRepository;
import com.amex.banking.repository.TransactionRepository;

@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final TransactionRepository transactionRepository;
	
	public CustomerService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
		super();
		this.customerRepository = customerRepository;
		this.transactionRepository = transactionRepository;
	}



	public List<CustomerEntity> getCustomerDetails() {
		
		return customerRepository.findAll();
	}

	
	
	public List<CustomerTransactionDTO> getCustomerDetailsByFilter(int custid , int acctNo, LocalDate fromDate, LocalDate toDate, Boolean dateFilter) {
		
		if(custid != 0 && !(dateFilter)) {
			List<CustomerTransactionDTO> dtoList = new ArrayList<CustomerTransactionDTO>();
			List<CustomerEntity> listById = customerRepository.getCustomerDetailsById(custid);
			listById.stream().forEach(item -> {
				CustomerTransactionDTO dto = new CustomerTransactionDTO();
				dto.setCust_id(item.getCust_id());
				dto.setCust_Name(item.getCust_Name());
				dto.setCust_account_no(item.getCust_account_no());
				dto.setCust_enum(item.getCust_enum());
				dto.setBalance(item.getBalance());
				dto.setComments(item.getComments());
				dto.setTransactions(item.getTransactions());
				dtoList.add(dto);
			});
			
			return dtoList;
		}
		else if( custid != 0 && dateFilter) {
			List<CustomerTransactionDTO> dtoList = new ArrayList<CustomerTransactionDTO>();
			List<TransactionEntity> listByDates = transactionRepository.getTransactionDetailsByDates(fromDate, toDate);
			List<TransactionEntity> transEntityList = new ArrayList<TransactionEntity>();
			CustomerTransactionDTO dto = new CustomerTransactionDTO();
			
			listByDates.stream().forEach(transaction -> {
				if(transaction.getCust() != null){
					if(custid == transaction.getCust().getCust_id()) {
						dto.setCust_id(transaction.getCust().getCust_id());
						dto.setCust_Name(transaction.getCust().getCust_Name());
						dto.setCust_account_no(transaction.getCust().getCust_account_no());
						dto.setCust_enum(transaction.getCust().getCust_enum());
						dto.setComments(transaction.getCust().getComments());
						dto.setBalance(transaction.getCust().getBalance());
						
						TransactionEntity transEntity = new TransactionEntity();
						transEntity.setTrans_ref_no(transaction.getTrans_ref_no());
						transEntity.setTrans_date(transaction.getTrans_date());
						transEntity.setTrans_process(transaction.getTrans_process());
						transEntity.setTrans_reason(transaction.getTrans_reason());
						transEntityList.add(transEntity);
					}
				}
			});
			dto.setTransactions(transEntityList);
			dtoList.add(dto);
			return dtoList;
			
		}
		else {
			List<CustomerTransactionDTO> dtoList = new ArrayList<CustomerTransactionDTO>();
			List<CustomerEntity> listByAccNo = customerRepository.getCustomerDetailsByAccountNumber(acctNo);
			listByAccNo.stream().forEach(customer -> {
				CustomerTransactionDTO dto = new CustomerTransactionDTO();
				dto.setCust_id(customer.getCust_id());
				dto.setCust_Name(customer.getCust_Name());
				dto.setCust_account_no(customer.getCust_account_no());
				dto.setCust_enum(customer.getCust_enum());
				dto.setBalance(customer.getBalance());
				dto.setComments(customer.getComments());
				dto.setTransactions(customer.getTransactions());
				dtoList.add(dto);
			});
			return dtoList;
		}
		
    }
	

}
