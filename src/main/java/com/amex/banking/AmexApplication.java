package com.amex.banking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amex.banking.enumeration.CustomerEnum;
import com.amex.banking.model.CustomerEntity;
import com.amex.banking.model.TransactionEntity;
import com.amex.banking.repository.CustomerRepository;


@SpringBootApplication
public class AmexApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmexApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner runner(CustomerRepository repository) {
		return args -> {
			
			//adding sample data into db on startup of the application.
		
			CustomerEntity ce1 = new CustomerEntity(101,"vijay", 1234,CustomerEnum.Savings, 2003.34, "Vijay account details");
			
			 
			TransactionEntity te1 = new TransactionEntity("T1", LocalDate.now().minusDays(25),  "Shopped using amex card", 200.02);
			te1.setCust(ce1);
			
			TransactionEntity te2 = new TransactionEntity("T2", LocalDate.now(),  "Shopped using amex card", 200.02);
			te2.setCust(ce1);
		
			CustomerEntity ce2 = new CustomerEntity(102,"ravi", 1235,CustomerEnum.Current, 2003.34, "ravi account details");
			
			TransactionEntity te3 = new TransactionEntity("T3", LocalDate.now(),  "Shopped using amex card", 200.02);
			te3.setCust(ce2);
			
			
            CustomerEntity ce3 = new CustomerEntity(103,"vinoth", 1236,CustomerEnum.Current, 20030.34, "vinoth account details");
			
			TransactionEntity te4 = new TransactionEntity("T4", LocalDate.now().minusDays(30),  "Shopped using amex card", 3000.02);
			te4.setCust(ce3);
			
			TransactionEntity te5 = new TransactionEntity("T5", LocalDate.now().minusDays(6),  "Shopped using amex card", 4000.02);
			te5.setCust(ce3);
			
			TransactionEntity te6 = new TransactionEntity("T6", LocalDate.now().minusDays(15),  "Shopped using amex card", 5000.02);
			te6.setCust(ce3);
			
			List<TransactionEntity> transList =  new ArrayList<TransactionEntity>();
			transList.add(te1);
			transList.add(te2);
			
		
			
			List<TransactionEntity> transList1 =  new ArrayList<TransactionEntity>();
			transList1.add(te3);
			
			List<TransactionEntity> transList2 =  new ArrayList<TransactionEntity>();
			transList2.add(te4);
			transList2.add(te5);
			transList2.add(te6);
			
			
			
			
			ce1.setTransactions(transList);
			ce2.setTransactions(transList1);
			ce3.setTransactions(transList2);
			
			repository.save(ce1);
			repository.save(ce2);
			repository.save(ce3);
			
			
			
		};
		
	}
	
	

}
