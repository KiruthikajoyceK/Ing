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

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;
import com.hcl.bank.service.RegistrationServiceImpl;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/bank")
public class RegistrationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationServiceImpl registrationServiceImpl;
		
	@PostMapping("/registration")
	public ResponseEntity<UserResponseDto> registration(@RequestBody UserRequestDto userRequestDto) {

		LOGGER.info("inside registration");

		UserResponseDto userResponseDto = registrationServiceImpl.registration(userRequestDto);

		return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);

	}

}
