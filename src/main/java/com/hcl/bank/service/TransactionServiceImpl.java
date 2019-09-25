package com.hcl.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.constants.FundtransferConstants;
import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.dto.TransactionResponseDto;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.Transaction;
import com.hcl.bank.exception.CommonException;
import com.hcl.bank.repository.AccountRepository;
import com.hcl.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionServiceIntf {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto) {

		double transactionAmount = fundTransferRequestDto.getTransactionAmount();

		Account fromAccount = accountRepository.findByAccountNumber(fundTransferRequestDto.getFromAccountNo());

		Account toAccount = accountRepository.findByAccountNumber(fundTransferRequestDto.getToAccountNo());
   if(fundTransferRequestDto.getTransactionAmount()==0)
   {
	   throw new CommonException(FundtransferConstants.INVALID_BALANCE);
   }
	    if(fundTransferRequestDto.getTransactionAmount()>fromAccount.getAccountBalance())	
	    {
	    throw new CommonException(FundtransferConstants.INVALID_SUFFICIENT_BALANCE);
	    }
		double userBalance = fromAccount.getAccountBalance();
		double benificiaryBalance = toAccount.getAccountBalance();
		double debitBalance = userBalance - transactionAmount;
		double creditBalance = benificiaryBalance + transactionAmount;

		fromAccount.setAccountBalance(debitBalance);
		accountRepository.save(fromAccount);

		toAccount.setAccountBalance(creditBalance);
		accountRepository.save(toAccount);

		Transaction debitTransaction = new Transaction();
		debitTransaction.setFromAccountNo(fundTransferRequestDto.getFromAccountNo());
		debitTransaction.setToAccountNo(fundTransferRequestDto.getToAccountNo());
		debitTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
		debitTransaction.setTransactionDate(LocalDate.now());
		debitTransaction.setTransactionType(FundtransferConstants.DEBITED);
		debitTransaction.setUser(fromAccount.getUser());
		transactionRepository.save(debitTransaction);

		Transaction creditTransaction = new Transaction();
		creditTransaction.setFromAccountNo(fundTransferRequestDto.getFromAccountNo());
		creditTransaction.setToAccountNo(fundTransferRequestDto.getToAccountNo());
		creditTransaction.setTransactionAmount(fundTransferRequestDto.getTransactionAmount());
		creditTransaction.setTransactionDate(LocalDate.now());
		creditTransaction.setTransactionType(FundtransferConstants.CREDITED);
		creditTransaction.setUser(toAccount.getUser());
		transactionRepository.save(creditTransaction);

		FundTransferResponseDto fundTransferResponseDto = new FundTransferResponseDto();
		fundTransferResponseDto.setMessage("transferred successfully");

		return fundTransferResponseDto;
	}

	
	
	
	public List<TransactionResponseDto> transactionHistory(int userId, int noOfWeeks, int noOfMonths) {

		LocalDate currentDate = LocalDate.now();

		List<TransactionResponseDto> transactionResponseDtos = new ArrayList<TransactionResponseDto>();

		if (noOfMonths == 0) {
			LocalDate calculatedDate = currentDate.minusWeeks(noOfWeeks);

			List<Transaction> transactions = transactionRepository.findByUserAndTransactionDate(userId, calculatedDate,
					currentDate);

			for (Transaction transaction : transactions) {
				TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

				transactionResponseDto.setFromAccountNo(transaction.getFromAccountNo());
				transactionResponseDto.setToAccountNo(transaction.getToAccountNo());
				transactionResponseDto.setTransactionAmount(transaction.getTransactionAmount());
				transactionResponseDto.setTransactionDate(transaction.getTransactionDate());
				transactionResponseDto.setTransactionType(transaction.getTransactionType());
				transactionResponseDtos.add(transactionResponseDto);
			}

		}

		else if (noOfWeeks == 0) {
			LocalDate calculatedDate = currentDate.minusMonths(noOfMonths);

			List<Transaction> transactions = transactionRepository.findByUserAndTransactionDate(userId, calculatedDate,
					currentDate);

			for (Transaction transaction : transactions) {
				TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

				transactionResponseDto.setFromAccountNo(transaction.getFromAccountNo());
				transactionResponseDto.setToAccountNo(transaction.getToAccountNo());
				transactionResponseDto.setTransactionAmount(transaction.getTransactionAmount());
				transactionResponseDto.setTransactionDate(transaction.getTransactionDate());
				transactionResponseDto.setTransactionType(transaction.getTransactionType());
				transactionResponseDtos.add(transactionResponseDto);

			}

		}
		
		return transactionResponseDtos;
		
	
	}

}
