
package com.hcl.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
import com.hcl.bank.exception.CommonException;
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
	User user;
	Benificiary benificiary1;
	Benificiary benificiary2;
	Benificiary benificiary3;
	List<Benificiary> benificiaries;
	BenificiaryResponseDto benificiaryResponseDto;

	@Before
	public void setup() {
		benificiaryRequestDto = new BenificiaryRequestDto();
		benificiaryRequestDto.setUserId(1);
		benificiaryRequestDto.setBenificiaryBankName("kiruthika");
		benificiaryRequestDto.setBenificiaryAccountNo(12345L);
		benificiaryRequestDto.setBenificiaryAccountType("saving");
		benificiaryRequestDto.setBenificiaryName("kiruthika");
		benificiaryRequestDto.setIfscCode("ING09");
		
		benificiary1=new Benificiary();
		benificiary1.setBenificiaryId(1);
		benificiary1.setBenificiaryAccountNo(1234L);
		
		benificiary2=new Benificiary();
		benificiary2.setBenificiaryId(2);
		
		benificiary3=new Benificiary();
		
		benificiaries=new ArrayList<Benificiary>();
		benificiaries.add(benificiary1);
		benificiaries.add(benificiary2);

		user = new User();
		user.setUserId(1);
		user.setBenificiaries(benificiaries);
	}

	@Test
	public void tetsAddBenificiary() {
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);

		Mockito.when(benificiaryRepository.save(Mockito.any())).thenReturn(benificiary3);

		BenificiaryResponseDto benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto);

		Assert.assertEquals("added successfully", benificiaryResponseDto.getMessage());

	}
	
	@Test
	public void tetsAddBenificiary1() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);

		List<Benificiary> benificiaries= new ArrayList<Benificiary>();
		benificiaries.isEmpty();
		user.setBenificiaries(benificiaries);
		
		Mockito.when(benificiaryRepository.save(Mockito.any())).thenReturn(benificiary3);

		BenificiaryResponseDto benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto);

		Assert.assertEquals("added successfully", benificiaryResponseDto.getMessage());

	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary2() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		String benificiaryAccountType="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);

		 benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary3() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
		benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary4() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="ING";
		 String benificiaryName="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryName(benificiaryName);
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
		benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
		benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary5() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="ING";
		 String benificiaryName="Kiruthika";
		String ifscCode="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryName(benificiaryName);
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
		benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
		benificiaryRequestDto1.setBenificiaryName(benificiaryName);
		benificiaryRequestDto1.setIfscCode(ifscCode);
		benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary6() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		List<Benificiary> benificiaries= new ArrayList<Benificiary>();
		benificiaries.isEmpty();
		user.setBenificiaries(benificiaries);
		String benificiaryAccountType="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);

		 benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary7() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		List<Benificiary> benificiaries= new ArrayList<Benificiary>();
		benificiaries.isEmpty();
		user.setBenificiaries(benificiaries);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
		benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	@Test(expected = CommonException.class)
	public void tetsAddBenificiary8() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		List<Benificiary> benificiaries= new ArrayList<Benificiary>();
		benificiaries.isEmpty();
		user.setBenificiaries(benificiaries);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="ING";
		 String benificiaryName="";
		BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
		benificiaryRequestDto1.setBenificiaryName(benificiaryName);
		benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
		benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
		benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}

	@Test(expected = CommonException.class)
	public void tetsAddBenificiary9() {
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);
		List<Benificiary> benificiaries= new ArrayList<Benificiary>();
		benificiaries.isEmpty();
		user.setBenificiaries(benificiaries);
		String benificiaryAccountType="Saving";
		String benificiaryBankName="ING";
		 String benificiaryName="Kiruthika";
			String ifscCode="";
			BenificiaryRequestDto benificiaryRequestDto1=new BenificiaryRequestDto();
			benificiaryRequestDto1.setBenificiaryName(benificiaryName);
			benificiaryRequestDto1.setBenificiaryAccountType(benificiaryAccountType);
			benificiaryRequestDto1.setBenificiaryBankName(benificiaryBankName);
			benificiaryRequestDto1.setBenificiaryName(benificiaryName);
			benificiaryRequestDto1.setIfscCode(ifscCode);
			benificiaryResponseDto = benificiaryServiceImpl.addBenificiary(benificiaryRequestDto1);
		
	}
	
	
}
