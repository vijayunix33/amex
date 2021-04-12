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

import com.amex.banking.dto.CustomerTransactionDTO;
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
	public void getCustomerDetailsByFilterWithCustomerIdTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(101)).thenReturn(getCustomerMockData());
		when(customerRepository.getCustomerDetailsByAccountNumber(1234)).thenReturn(getCustomerMockData());
		List<CustomerTransactionDTO> lists = customerService.getCustomerDetailsByFilter(101,0,LocalDate.now(),LocalDate.now(),false);
		assertEquals(200.30, lists.get(0).getBalance());
	}
	
	
	@Test
	public void getCustomerDetailsByFilterWithCustomerIdEmptyTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(0)).thenReturn(new ArrayList<CustomerEntity>());
		when(customerRepository.getCustomerDetailsByAccountNumber(0)).thenReturn(new ArrayList<CustomerEntity>());
		List<CustomerTransactionDTO> lists = customerService.getCustomerDetailsByFilter(0,0,LocalDate.now(),LocalDate.now(),false);
		assertTrue(lists.isEmpty());
	}
	
	
	@Test
	public void getCustomerDetailsByFilterWithAccountNoTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(0)).thenReturn(new ArrayList<CustomerEntity>());
		when(customerRepository.getCustomerDetailsByAccountNumber(1234)).thenReturn(getCustomerMockData());
		List<CustomerTransactionDTO> lists = customerService.getCustomerDetailsByFilter(0,1234,LocalDate.now(),LocalDate.now(),false);
		assertEquals(200.30, lists.get(0).getBalance());
	}
	
	@Test
	public void getCustomerDetailsByFilterWithAccountNoEmptyTest() throws Exception {
		when(customerRepository.getCustomerDetailsById(0)).thenReturn(new ArrayList<CustomerEntity>());
		when(customerRepository.getCustomerDetailsByAccountNumber(0)).thenReturn(new ArrayList<CustomerEntity>());
		List<CustomerTransactionDTO> lists = customerService.getCustomerDetailsByFilter(0,0,LocalDate.now(),LocalDate.now(),false);
		assertTrue(lists.isEmpty());
	}
	
	@Test
	public void getCustomerDetailsByFilterWithCustomerAndDatesTest() throws Exception {
		when(transactionRepository.getTransactionDetailsByDates(LocalDate.now(), LocalDate.now())).thenReturn(getTransactionMockData());
		List<CustomerTransactionDTO> dtoLists = customerService.getCustomerDetailsByFilter(101,0,LocalDate.now(),LocalDate.now(),true);
		assertEquals(LocalDate.now(), dtoLists.get(0).getTransactions().get(0).getTrans_date());
	}
	

	
	 
	//mock data
	private List<CustomerEntity> getCustomerMockData() {
		List<CustomerEntity> custList = new ArrayList<CustomerEntity>();
		CustomerEntity customer = new CustomerEntity();
		customer.setBalance(200.30);
		custList.add(customer);
		return custList;
	}
	
	
	private List<TransactionEntity> getTransactionMockData() {
		List<TransactionEntity> transList = new ArrayList<TransactionEntity>();
		CustomerEntity cust = new CustomerEntity();
		cust.setCust_id(101);
		TransactionEntity trans = new TransactionEntity();
		trans.setTrans_date(LocalDate.now());
		trans.setCust(cust);
		transList.add(trans);
		return transList;
	}
	
	private List<CustomerTransactionDTO> getTransactionDtoMockData() {
		List<CustomerTransactionDTO> transList = new ArrayList<CustomerTransactionDTO>();
		CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
		TransactionEntity trans = new TransactionEntity();
		trans.setTrans_date(LocalDate.now());
		List<TransactionEntity> list = new ArrayList<TransactionEntity>();
		list.add(trans);
		customerTransactionDTO.setTransactions(list);
		
		
		transList.add(customerTransactionDTO);
		
		return transList;
	}
	
}
