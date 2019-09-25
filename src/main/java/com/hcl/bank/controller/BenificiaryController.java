package com.hcl.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bank.dto.BenificiaryRequestDto;
import com.hcl.bank.dto.BenificiaryResponseDto;
import com.hcl.bank.service.BenificiaryServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/bank")
public class BenificiaryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	BenificiaryServiceImpl benificiaryServiceImpl;
		
	@PostMapping("/addBenificiary")
	public ResponseEntity<BenificiaryResponseDto> addBenificiary(@RequestBody BenificiaryRequestDto benificiaryRequestDto) {

		LOGGER.info("inside benificiary");
		BenificiaryResponseDto benificiaryResponseDto= benificiaryServiceImpl.addBenificiary(benificiaryRequestDto);
		
		return new ResponseEntity<>(benificiaryResponseDto, HttpStatus.CREATED);

	}

}
