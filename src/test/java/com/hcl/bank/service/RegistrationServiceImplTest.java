package com.hcl.bank.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.CommonException;
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

	Account account;
	User user1;
	UserResponseDto userResponseDto;
	
	Optional<User> user = null;
	@Before
	public void setup() {
		user1 = new User();
		user1.setUserId(1);
		user1.setUserName("kiruthika");
		user1.setAddress("trichy");
		user1.setAge(19);
		user1.setEmail("ki@gmail.com");
		user1.setMobileNo(72358999L);

		userRequestDto = new UserRequestDto();
		userRequestDto.setUserName("kiruthika");
		userRequestDto.setAddress("trichy");
		userRequestDto.setAge(19);
		userRequestDto.setEmail("ki@gmail.com");
		userRequestDto.setMobileNo(72358999L);

		account = new Account();
		account.setAccountBalance(10000);
		account.setAccountCreationDate(LocalDate.now());
		account.setAccountNumber(12345678L);
		account.setAccountType("savings");
		account.setUser(user1);

	}

	@Test
	public void testRegistration1() {

		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user1);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);

		UserResponseDto userResponseDto = registrationServiceImpl.registration(userRequestDto);
		Assert.assertEquals(12345678L, userResponseDto.getAccountNumber());

	}

	@Test(expected = CommonException.class)
	public void testRegistration2() {

		User user=new User();
		
		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.of(user));
		 userResponseDto = registrationServiceImpl.registration(userRequestDto);
		

	}
	@Test(expected = CommonException.class)
	public void testRegistration3() {

		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
		String userName="";
		userRequestDto.setUserName(userName);
		 userResponseDto = registrationServiceImpl.registration(userRequestDto);
		
	}
	@Test(expected = CommonException.class)
	public void testRegistration4() {

		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
		String userName="kiruthika";
		String address="";
		userRequestDto.setUserName(userName);
		userRequestDto.setAddress(address);
		 userResponseDto = registrationServiceImpl.registration(userRequestDto);
		
	}
	@Test(expected = CommonException.class)
	public void testRegistration5() {

		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
		String userName="kiruthika";
		String address="";
		int age=16;
		userRequestDto.setUserName(userName);
		userRequestDto.setAddress(address);
		userRequestDto.setAge(age);
		 userResponseDto = registrationServiceImpl.registration(userRequestDto);
		
	}
	@Test(expected = CommonException.class)
	public void testRegistration6() {

		Mockito.when(userRepository.findByMobileNoAndEmail(Mockito.anyLong(),Mockito.anyString())).thenReturn(Optional.empty());
		String userName="kiruthika";
		String address="";
		int age=16;
		String email="k.com";
		userRequestDto.setUserName(userName);
		userRequestDto.setAddress(address);
		userRequestDto.setAge(age);
		userRequestDto.setEmail(email);
		 userResponseDto = registrationServiceImpl.registration(userRequestDto);
		
	}
}
