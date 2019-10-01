package com.hcl.bank.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bank.dto.LoginRequestDto;
import com.hcl.bank.dto.LoginResponseDto;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.UserNotFoundException;
import com.hcl.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
	
	@InjectMocks
	LoginServiceImpl loginServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	LoginRequestDto loginRequestDto;
	LoginResponseDto loginResponseDto;
	User user;
	
	
	@Before
	public void setup()
	{
		loginRequestDto=new LoginRequestDto();
		loginRequestDto.setEmail("ki@gmail.com");
		loginRequestDto.setPassword("abc");
		
		loginResponseDto=new LoginResponseDto();
		loginResponseDto.setMessage("success");
		loginResponseDto.setUserId(1);
		
		user=new User();
		user.setUserId(1);
		
		
	}
	
	@Test
	public void testLogin()
	{
		
		Mockito.when(userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()))
		.thenReturn(Optional.of(user));
		LoginResponseDto actRes	=loginServiceImpl.login(loginRequestDto);
		
		Assert.assertEquals(loginResponseDto.getUserId(),actRes.getUserId());
	}
	
	@Test(expected = UserNotFoundException.class)
	public void testLogin1()
	{
		loginRequestDto=new LoginRequestDto();
		loginRequestDto.setPassword("bcde");
		loginRequestDto.setEmail("k@gmail.com");
		User user=new User();
		user.setPassword("b");
		user.setEmail("k@gmail.com");
		Mockito.when(userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()))
		.thenReturn(Optional.empty());
		loginResponseDto	=loginServiceImpl.login(loginRequestDto);
		
			}

}
