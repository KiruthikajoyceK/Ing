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
		loginRequestDto.setUserName("kiruthika");
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
		//Optional<User> user=userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(),loginRequestDto.getPassword());
		
		Mockito.when(userRepository.findByUserNameAndPassword(loginRequestDto.getUserName(), loginRequestDto.getPassword()))
		.thenReturn(Optional.of(user));
		LoginResponseDto actRes	=loginServiceImpl.login(loginRequestDto);
		
		Assert.assertEquals(loginResponseDto.getUserId(),actRes.getUserId());
	}

}
