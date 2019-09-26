package com.hcl.bank.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	@Mock
	TransactionRepository transactionRepository;
	
	FundTransferRequestDto fundTransferRequestDto;
	FundTransferResponseDto fundTransferResponseDto;
	
	@Before
	public void setup()
	{
		fundTransferRequestDto=new FundTransferRequestDto();
		
		fundTransferResponseDto=new FundTransferResponseDto();
	}
	
	@Test
	public void testFundTransfer()
	{
		Mockito.when(transactionServiceImpl.fundTransfer(fundTransferRequestDto)).thenReturn(fundTransferResponseDto);
		
		}

}
