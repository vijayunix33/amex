package com.amex.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amex.banking.model.CustomerEntity;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Query(nativeQuery = true, value="select * from customer where cust_id  = :custid")
	List<CustomerEntity> getCustomerDetailsById(@Param("custid") int custid);
	
	@Query(nativeQuery = true, value="select * from customer where cust_account_no  = :acctno")
	List<CustomerEntity> getCustomerDetailsByAccountNumber(@Param("acctno") int acctno);
	
}
 