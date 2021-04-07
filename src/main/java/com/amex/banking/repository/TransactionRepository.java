package com.amex.banking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.amex.banking.model.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

	
	@Query(nativeQuery = true, value="select * from transaction where trans_date  >= :startDate and  trans_date <= :endDate")
	List<TransactionEntity> getTransactionDetailsByDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}
