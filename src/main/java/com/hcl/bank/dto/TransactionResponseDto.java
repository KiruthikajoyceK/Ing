package com.hcl.bank.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
	
	private long fromAccountNo;
	private long toAccountNo;
	private String transactionType;
	private int transactionAmount;
	private LocalDate transactionDate;

}
