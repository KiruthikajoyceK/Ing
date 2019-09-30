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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		 userRequestDto=new UserRequestDto();
		userRequestDto.setUserName("kiruthika");
		userRequestDto.setAge(20);
		userRequestDto.setAddress("trichy");
		
		 userResponseDto=new UserResponseDto();
		userResponseDto.setAccountNumber(987846);	
		
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();

		}
	
	
	@Test
	public void testRegistration() throws Exception
	{
		ResponseEntity<UserResponseDto> expRes=new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
		
		Mockito.when(registrationServiceImpl.registration(userRequestDto)).thenReturn(userResponseDto);
		
		ResponseEntity<UserResponseDto> actRes= registrationController.registration(userRequestDto);
		
		assertEquals(expRes, actRes);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bank/registration").contentType(MediaType.APPLICATION_JSON)

				.content(asJsonString(userResponseDto))).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	public static String asJsonString(final Object obj) {

		try {

			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}
		
		
	}
	
	

