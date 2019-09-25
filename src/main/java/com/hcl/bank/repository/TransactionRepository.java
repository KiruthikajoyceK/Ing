package com.hcl.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	// @Query("select * from transaction where userId = :userId AND transactionDate
	// BETWEEN fromDate = :currentDate and :calculatedDate")
	@Query("SELECT t FROM Transaction t WHERE  t.user.userId = :userId AND t.transactionDate BETWEEN :calculatedDate AND :currentDate order by t.transactionDate DESC")
	public List<Transaction> findByUserAndTransactionDate(int userId, LocalDate calculatedDate, LocalDate currentDate);

}
