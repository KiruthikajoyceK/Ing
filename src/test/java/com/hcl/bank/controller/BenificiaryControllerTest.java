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
		benificiaryResponseDto.setMessage("added success");
		
		}
	
	@Test
	public void testAddBenificiary()
	{
		Mockito.when(benificiaryServiceImpl.addBenificiary(benificiaryRequestDto)).thenReturn(benificiaryResponseDto);
	
		ResponseEntity<BenificiaryResponseDto> actual=benificiaryController.addBenificiary(benificiaryRequestDto);
		ResponseEntity<BenificiaryResponseDto> expected=new ResponseEntity<>(benificiaryResponseDto, HttpStatus.CREATED);
		
		assertEquals(expected, actual);
		
	}
	

}
