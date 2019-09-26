package com.hcl.bank.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bank.dto.BenificiaryRequestDto;
import com.hcl.bank.dto.BenificiaryResponseDto;
import com.hcl.bank.entity.Benificiary;
import com.hcl.bank.entity.User;
import com.hcl.bank.repository.BenificiaryRepository;
import com.hcl.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class BenificiaryServiceImplTest {
	
	@InjectMocks
	BenificiaryServiceImpl benificiaryServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BenificiaryRepository benificiaryRepository;
	
	BenificiaryRequestDto benificiaryRequestDto;
	BenificiaryResponseDto benificiaryResponseDto;
	User user;
	Benificiary benificiary;
	
	@Before
	public void setup()
	{
		benificiaryRequestDto=new BenificiaryRequestDto();
		benificiaryRequestDto.setUserId(1);
		benificiaryRequestDto.setBenificiaryBankName("kiruthika");
		
		benificiaryResponseDto=new BenificiaryResponseDto();
		benificiaryResponseDto.setMessage("added successfully");
		user=new User();
		user.setUserId(1);
		benificiary=new Benificiary();
		benificiary.setUser(user);
	}
	
	@Test
	public void tetsAddBenificiary()
	{
		Mockito.when(benificiaryServiceImpl.addBenificiary(benificiaryRequestDto)).thenReturn(benificiaryResponseDto);
		
		
			}
}
