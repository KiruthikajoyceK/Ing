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
import com.hcl.bank.dto.BenificiaryRequestDto;
import com.hcl.bank.dto.BenificiaryResponseDto;
import com.hcl.bank.service.BenificiaryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BenificiaryControllerTest {
	
	@InjectMocks
	BenificiaryController benificiaryController;
	
	@Mock
	BenificiaryServiceImpl benificiaryServiceImpl;
	
	BenificiaryRequestDto benificiaryRequestDto;
	BenificiaryResponseDto  benificiaryResponseDto;
	
	MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		benificiaryRequestDto=new BenificiaryRequestDto();
		  benificiaryRequestDto.setBenificiaryName("kiruthika");
		  benificiaryRequestDto.setBenificiaryAccountNo(98786);
		  benificiaryRequestDto.setBenificiaryAccountType("savings");
		  benificiaryRequestDto.setBenificiaryBankName("ING");
		  benificiaryRequestDto.setIfscCode("ING098");
		 
		benificiaryResponseDto=new BenificiaryResponseDto();
		
		mockMvc = MockMvcBuilders.standaloneSetup(benificiaryController).build();

		
		}
	
	@Test
	public void testAddBenificiary() throws Exception
	{
		Mockito.when(benificiaryServiceImpl.addBenificiary(benificiaryRequestDto)).thenReturn(benificiaryResponseDto);
	
		ResponseEntity<BenificiaryResponseDto> actual=benificiaryController.addBenificiary(benificiaryRequestDto);
		ResponseEntity<BenificiaryResponseDto> expected=new ResponseEntity<>(benificiaryResponseDto, HttpStatus.CREATED);
		
		assertEquals(expected, actual);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bank/addBenificiary").contentType(MediaType.APPLICATION_JSON)

				.content(asJsonString(benificiaryResponseDto))).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	public static String asJsonString(final Object obj) {

		try {

			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}
		
	}
	


