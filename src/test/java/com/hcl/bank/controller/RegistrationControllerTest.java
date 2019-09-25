package com.hcl.bank.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;
import com.hcl.bank.service.RegistrationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class RegistrationControllerTest {
	
	@InjectMocks
	RegistrationController registrationController;
	
	@Mock
	RegistrationServiceImpl registrationServiceImpl;
	
	UserRequestDto userRequestDto;
	UserResponseDto userResponseDto;
	
	@Before
	public void setup()
	{
		 userRequestDto=new UserRequestDto();
		userRequestDto.setUserName("kiruthika");
		userRequestDto.setAge(20);
		userRequestDto.setAddress("trichy");
		
		 userResponseDto=new UserResponseDto();
		userResponseDto.setAccountBalance(10000);
	    userResponseDto.setAccountNumber(987846);	
	    userResponseDto.setAccountType("savings");
	}
	
	
	@Test
	public void testRegistration()
	{
		ResponseEntity<UserResponseDto> expRes=new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
		
		Mockito.when(registrationServiceImpl.registration(userRequestDto)).thenReturn(userResponseDto);
		
		ResponseEntity<UserResponseDto> actRes= registrationController.registration(userRequestDto);
		
		assertEquals(expRes, actRes);
		
		
	}
	
	
}
