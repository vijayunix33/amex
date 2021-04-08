package com.amex.banking;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.repository.CustomerRepository;
import com.amex.banking.repository.TransactionRepository;
import com.amex.banking.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Test
	public void getCustomerDetailsTest() throws Exception {
		when(customerRepository.findAll()).thenReturn(getCustomerMockData());
		List<CustomerEntity> lists = customerService.getCustomerDetails();
		assertEquals(200.30, lists.get(0).getBalance());
	}
	
	
	@Test
	public void getCustomerDetailsTestWithEmptyTest() throws Exception {
		when(customerRepository.findAll()).thenReturn(new ArrayList<CustomerEntity>());
		List<CustomerEntity> lists = customerService.getCustomerDetails();
		assertTrue(lists.isEmpty());
	}
	
	
	@Test
	public void getCustomerDetailsByFilterTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(101)).thenReturn(getCustomerMockData());
		when(customerRepository.getCustomerDetailsByAccountNumber(1234)).thenReturn(getCustomerMockData());
		List<CustomerEntity> lists = customerService.getCustomerDetailsByFilter(101,1234);
		assertEquals(200.30, lists.get(0).getBalance());
	}
	
	
	@Test
	public void getCustomerDetailsByFilterWithEmptyTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(0)).thenReturn(new ArrayList<CustomerEntity>());
		when(customerRepository.getCustomerDetailsByAccountNumber(0)).thenReturn(new ArrayList<CustomerEntity>());
		List<CustomerEntity> lists = customerService.getCustomerDetailsByFilter(0,0);
		assertTrue(lists.isEmpty());
	}
	
	
	@Test
	public void getTransactionDetailsByDatesTest() throws Exception {
		when(transactionRepository.getTransactionDetailsByDates(LocalDate.now(), LocalDate.now())).thenReturn(getTransactionMockData());
		List<TransactionEntity> lists = transactionRepository.getTransactionDetailsByDates(LocalDate.now(),LocalDate.now());
		assertEquals(LocalDate.now(), lists.get(0).getTrans_date());
	}
	
	@Test
	public void getTransactionDetailsByDatesWithEmptyTest() throws Exception {
		when(transactionRepository.getTransactionDetailsByDates(LocalDate.now(), LocalDate.now())).thenReturn(new ArrayList<TransactionEntity>());
		List<TransactionEntity> lists = transactionRepository.getTransactionDetailsByDates(LocalDate.now(),LocalDate.now());
		assertTrue(lists.isEmpty());
	}
	
	
	
	
	
	private List<CustomerEntity> getCustomerMockData() {
		List<CustomerEntity> custList = new ArrayList<CustomerEntity>();
		CustomerEntity customer = new CustomerEntity();
		customer.setBalance(200.30);
		custList.add(customer);
		return custList;
	}
	
	
	private List<TransactionEntity> getTransactionMockData() {
		List<TransactionEntity> transList = new ArrayList<TransactionEntity>();
		TransactionEntity trans = new TransactionEntity();
		trans.setTrans_date(LocalDate.now());
		transList.add(trans);
		return transList;
	}
	
}
