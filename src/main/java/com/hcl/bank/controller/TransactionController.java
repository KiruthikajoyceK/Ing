package com.hcl.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.dto.TransactionResponseDto;
import com.hcl.bank.service.TransactionServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/bank")
public class TransactionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	TransactionServiceImpl transactionServiceImpl;
	
	@PostMapping("/fundTransfer")
	public ResponseEntity<FundTransferResponseDto> fundTransfer(@RequestBody FundTransferRequestDto fundTransferRequestDto) {

		LOGGER.info("inside fund transfer");

		FundTransferResponseDto fundTransferResponseDto=	transactionServiceImpl.fundTransfer(fundTransferRequestDto);
		
		return new ResponseEntity<>(fundTransferResponseDto, HttpStatus.OK);

	}
	
	
	  @GetMapping("/transactionHistory/{userId}/{noOfWeeks}/{noOfMonths}") 
	  public ResponseEntity<List<TransactionResponseDto>> transactionHistory(@RequestParam int userId,@RequestParam int noOfWeeks ,@RequestParam int noOfMonths) {
	  
	  LOGGER.info("inside transactionHistory");
	  
	  List<TransactionResponseDto> transactionResponseDto=transactionServiceImpl.transactionHistory(userId,noOfWeeks,noOfMonths);
	  
	  return new ResponseEntity<>(transactionResponseDto, HttpStatus.OK);
	  
	  }
	 
}
