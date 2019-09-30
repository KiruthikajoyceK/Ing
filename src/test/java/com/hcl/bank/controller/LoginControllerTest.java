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

	MockMvc mockMvc;

	@Before
	public void setup() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setEmail("ki@gmail.com");
		loginRequestDto.setPassword("abc");

		loginResponseDto = new LoginResponseDto();
		loginResponseDto.setMessage("login success");
		loginResponseDto.setUserId(1);

		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

	}

	@Test
	public void testLogin() throws Exception {

		Mockito.when(loginServiceImpl.login(loginRequestDto)).thenReturn(loginResponseDto);

		ResponseEntity<LoginResponseDto> actual = loginController.login(loginRequestDto);

		ResponseEntity<LoginResponseDto> expected = new ResponseEntity<>(loginResponseDto, HttpStatus.OK);

		assertEquals(expected.getStatusCode().value(), actual.getStatusCodeValue());

		mockMvc.perform(MockMvcRequestBuilders.put("/bank/login").contentType(MediaType.APPLICATION_JSON)

				.content(asJsonString(loginResponseDto))).andExpect(MockMvcResultMatchers.status().isOk());

	}

	public static String asJsonString(final Object obj) {

		try {

			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

}
