package com.hcl.bank.service;

import java.util.List;

import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.dto.TransactionResponseDto;

public interface TransactionServiceIntf {
	
	public FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto);
	public List<TransactionResponseDto> transactionHistory(int userId,int noOfWeeks, int noOfMonths);

}
