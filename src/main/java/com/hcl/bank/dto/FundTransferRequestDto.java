package com.hcl.bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class FundTransferRequestDto {
	
	private long fromAccountNo;
	private long toAccountNo;
	private int transactionAmount;

}
