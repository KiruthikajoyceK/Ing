
package com.hcl.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.dto.TransactionResponseDto;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.Benificiary;
import com.hcl.bank.entity.Transaction;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.CommonException;
import com.hcl.bank.repository.AccountRepository;
import com.hcl.bank.repository.BenificiaryRepository;
import com.hcl.bank.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	BenificiaryRepository benificiaryRepository;

	FundTransferRequestDto fundTransferRequestDto;
	FundTransferResponseDto fundTransferResponseDto;
	TransactionResponseDto transactionResponseDto1;
	TransactionResponseDto transactionResponseDto2;
	List<TransactionResponseDto> responseDtos;

	Account fromAccount;

	Account toAccount;

	Benificiary benificiary1;
	Benificiary benificiary2;
	List<Benificiary> benificiaries;
	Transaction debitTransaction;
	Transaction creditTransaction;
	
	Transaction transaction1;
	Transaction transaction2;
	List<Transaction> transactions;

	User user1;

	@Before
	public void setup() {
		fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccountNo(12345L);
		fundTransferRequestDto.setToAccountNo(127L);
		fundTransferRequestDto.setTransactionAmount(1000);

		user1 = new User();
		user1.setUserId(1);

		fromAccount = new Account();
		fromAccount.setAccountNumber(12345L);
		fromAccount.setAccountBalance(10000);
		fromAccount.setUser(user1);
		
		
		toAccount = new Account();
		toAccount.setAccountNumber(127L);
		toAccount.setAccountBalance(10000);
		toAccount.setUser(user1);
		

		benificiary1 = new Benificiary();
		benificiary1.setBenificiaryAccountNo(127L);

		benificiary2 = new Benificiary();
		benificiary2.setBenificiaryAccountNo(1234986L);

		benificiaries = new ArrayList<Benificiary>();
		benificiaries.add(benificiary1);
		benificiaries.add(benificiary2);

		
		user1.setBenificiaries(benificiaries);
	
		debitTransaction = new Transaction();
		creditTransaction = new Transaction();
		
	     transactionResponseDto1=new TransactionResponseDto();
	     transactionResponseDto1.setFromAccountNo(1234L);
	     transactionResponseDto1.setToAccountNo(134L);
	     transactionResponseDto1.setTransactionDate(LocalDate.now());
		 
	     transactionResponseDto2=new TransactionResponseDto();
	     
		 responseDtos=new ArrayList<TransactionResponseDto>();
		 
		  transaction1=new Transaction();
		  transaction1.setTransactionDate(LocalDate.now());
		  transaction1.setTransactionId(12);
		  transaction1.setFromAccountNo(1234L);
		  transaction1.setToAccountNo(134L);
		  
		 	 transaction2=new Transaction();
			 transaction2.setTransactionDate(LocalDate.now().minusWeeks(1));
			 transaction2.setTransactionId(12);
			  transaction2.setFromAccountNo(1234L);
			  transaction2.setToAccountNo(134L);
				
			 transactions=new ArrayList<Transaction>();
			transactions.add(transaction1);
			transactions.add(transaction2);
			user1.setTransactions(transactions);
			

	}

	@Test(expected = CommonException.class)
	public void testFundTransfer() {
		
		Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(fromAccount);

		Mockito.when(benificiaryRepository.findBenificiaryByUserId(Mockito.anyInt())).thenReturn(benificiaries);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.anyLong())).thenReturn(toAccount);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(fromAccount);

		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(debitTransaction);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(toAccount);

		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(creditTransaction);

		fundTransferResponseDto = transactionServiceImpl.fundTransfer(fundTransferRequestDto);

	}
	
	@Test
	public void testTransactionHistory()
	{
		int userId=1; int noOfWeeks=1; int noOfMonths=0;
		LocalDate currentDate = LocalDate.now();
		LocalDate calculatedDate = currentDate.minusWeeks(noOfWeeks);

		Mockito.when(transactionRepository.findByUserAndTransactionDate(userId, calculatedDate, currentDate)).thenReturn(transactions);
	
		List<TransactionResponseDto> responseDtos=transactionServiceImpl.transactionHistory(userId, noOfWeeks, noOfMonths);
	
		Assert.assertEquals(transactions.getClass().toString(), responseDtos.getClass().toString());
	}
	
	@Test
	public void testTransactionHistory1()
	{
		int userId=1; int noOfWeeks=0; int noOfMonths=1;
		LocalDate currentDate = LocalDate.now();
		LocalDate calculatedDate = currentDate.minusMonths(noOfMonths);

		Mockito.when(transactionRepository.findByUserAndTransactionDate(userId, calculatedDate, currentDate)).thenReturn(transactions);
	
		List<TransactionResponseDto> responseDtos=transactionServiceImpl.transactionHistory(userId, noOfWeeks, noOfMonths);
	
		Assert.assertEquals(transactions.getClass().toString(), responseDtos.getClass().toString());
	}

}
