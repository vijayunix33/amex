package com.amex.banking;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amex.banking.enumeration.customerEnum;
import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.repository.CustomerRepository;


@SpringBootApplication
public class AmexRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmexRestApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner runner(CustomerRepository repository) {
		return args -> {
		
			CustomerEntity ce1 = new CustomerEntity(101,"vijay", 1234,customerEnum.Savings, 2003.34, "Vijay account details");
			
			
			TransactionEntity te1 = new TransactionEntity("T1", LocalDate.now().minusDays(25),  "Shopped using amex card", 200.02);
			te1.setCust(ce1);
			
			TransactionEntity te2 = new TransactionEntity("T2", LocalDate.now(),  "Shopped using amex card", 200.02);
			te2.setCust(ce1);
		
			CustomerEntity ce2 = new CustomerEntity(102,"ravi", 1235,customerEnum.Current, 2003.34, "ravi account details");
			
			TransactionEntity te3 = new TransactionEntity("T3", LocalDate.now(),  "Shopped using amex card", 200.02);
			te3.setCust(ce2);
			
			List<TransactionEntity> transList =  new ArrayList<TransactionEntity>();
			transList.add(te1);
			transList.add(te2);
			
			List<TransactionEntity> transList1 =  new ArrayList<TransactionEntity>();
			transList1.add(te3);
			
			
			
			ce1.setTrans(transList);
			ce2.setTrans(transList1);
			
			repository.save(ce1);
			repository.save(ce2);
			
			
			
		};
		
	}
	
	

}
