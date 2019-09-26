package com.hcl.bank.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.bank.dto.RandomPasswordGenerator;
import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.User;
import com.hcl.bank.repository.AccountRepository;
import com.hcl.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplTest {
	
	@InjectMocks
	RegistrationServiceImpl registrationServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	UserRequestDto userRequestDto;
	UserResponseDto userResponseDto;
	long accountNo;
	RandomPasswordGenerator passwordGenerator=new RandomPasswordGenerator();
	User user;
	Account account;
	
	
	@SuppressWarnings("static-access")
	@Before
	public void setup()
	{
		userRequestDto=new UserRequestDto();
		
		  userRequestDto.setUserName("kiruthika"); 
		  userRequestDto.setAddress("trichy");
		  userRequestDto.setAge(19);
		  userRequestDto.setEmail("ki@gmail.com");
		  userRequestDto.setMobileNo(72358999);
		 
		 accountNo=passwordGenerator.random(500000, 900000);
		
		 user=new User();
		user.setUserId(1);
			
		account=new Account();
		account.setAccountBalance(10000);
		account.setAccountCreationDate(LocalDate.now());
		account.setAccountNumber(accountNo);
		account.setAccountType("savings");
		account.setUser(user);
		
		
		userResponseDto=new UserResponseDto();
		
		
		BeanUtils.copyProperties(account, userResponseDto);
	}
	
	@Test
	public void testRegistration1()
	{
		
       UserResponseDto  userResponseDtoo=registrationServiceImpl.registration(userRequestDto);
	
         Assert.assertEquals(userResponseDto.getClass(), userResponseDtoo.getClass()); 
        int userId= account.getUser().getUserId();
		Assert.assertEquals(user.getUserId(),userId);
	}
	
	@Test
	public void testRegistration2()
	{
		//Mockito.when(userRepository.findByMobileNo(userRequestDto.getMobileNo())).thenReturn(Optional.of(user));
	
       }

}
