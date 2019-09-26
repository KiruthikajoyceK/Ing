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

import com.hcl.bank.dto.LoginRequestDto;
import com.hcl.bank.dto.LoginResponseDto;
import com.hcl.bank.service.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class LoginControllerTest {
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	LoginServiceImpl loginServiceImpl;
	
	LoginResponseDto loginResponseDto;
	LoginRequestDto loginRequestDto;
	
	@Before
	public void setup()
	{
		loginRequestDto=new LoginRequestDto();
		loginRequestDto.setUserName("kiruthika");
		loginRequestDto.setPassword("abc");
		
		loginResponseDto=new LoginResponseDto();
		loginResponseDto.setMessage("login success");
		loginResponseDto.setUserId(1);
		
	}
	
	@Test
	public void testLogin()
	{
		Mockito.when(loginServiceImpl.login(loginRequestDto)).thenReturn(loginResponseDto);
		
		ResponseEntity<LoginResponseDto> actual=loginController.login(loginRequestDto);
		
		ResponseEntity<LoginResponseDto> expected=new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
		
		assertEquals(expected.getStatusCode().value(), actual.getStatusCodeValue());
		
	}

}
