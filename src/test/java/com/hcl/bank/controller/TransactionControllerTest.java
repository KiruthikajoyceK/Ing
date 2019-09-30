package com.hcl.bank.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bank.dto.FundTransferRequestDto;
import com.hcl.bank.dto.FundTransferResponseDto;
import com.hcl.bank.dto.TransactionResponseDto;
import com.hcl.bank.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class TransactionControllerTest {

	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionServiceImpl transactionServiceImpl;

	FundTransferRequestDto fundTransferRequestDto;
	FundTransferResponseDto fundTransferResponseDto;
	int userId;
	int noOfWeeks;
	int noOfMonths;
	List<TransactionResponseDto> transactionResponseDto;
	TransactionResponseDto transactionResponseDto1;
	TransactionResponseDto transactionResponseDto2;

	MockMvc mockMvc;

	@Before
	public void setup() {
		fundTransferRequestDto = new FundTransferRequestDto();
		fundTransferRequestDto.setFromAccountNo(1235);
		fundTransferRequestDto.setToAccountNo(87876);
		fundTransferRequestDto.setTransactionAmount(1000);

		fundTransferResponseDto = new FundTransferResponseDto();

		userId = 1;
		noOfWeeks = 1;
		noOfMonths = 0;

		transactionResponseDto1 = new TransactionResponseDto();
		transactionResponseDto1.setFromAccountNo(1235);
		transactionResponseDto1.setFromAccountNo(1235);
		transactionResponseDto1.setToAccountNo(87876);
		transactionResponseDto1.setTransactionAmount(1000);
		transactionResponseDto1.setTransactionType("credit");

		transactionResponseDto2 = new TransactionResponseDto();
		transactionResponseDto2.setFromAccountNo(1235);
		transactionResponseDto2.setFromAccountNo(1235);
		transactionResponseDto2.setToAccountNo(87876);
		transactionResponseDto2.setTransactionAmount(1000);
		transactionResponseDto2.setTransactionType("credit");

		transactionResponseDto = new ArrayList<TransactionResponseDto>();
		transactionResponseDto.add(transactionResponseDto1);
		transactionResponseDto.add(transactionResponseDto2);
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();

	}

	@Test
	public void testFundTransfer() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.post("/bank/fundTransfer").contentType(MediaType.APPLICATION_JSON)

				.content(asJsonString(fundTransferResponseDto))).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testTransactionHistory() throws Exception {

		Mockito.when(transactionServiceImpl.transactionHistory(userId, noOfWeeks, noOfMonths))
				.thenReturn(transactionResponseDto);
		ResponseEntity<List<TransactionResponseDto>> actual = transactionController.transactionHistory(userId,
				noOfWeeks, noOfMonths);
		ResponseEntity<List<TransactionResponseDto>> expected = new ResponseEntity<>(transactionResponseDto,
				HttpStatus.OK);

		assertEquals(expected, actual);
	}



	public static String asJsonString(final Object obj) {

		try {

			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}
}
