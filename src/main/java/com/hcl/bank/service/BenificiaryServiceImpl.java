package com.hcl.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.dto.BenificiaryRequestDto;
import com.hcl.bank.dto.BenificiaryResponseDto;
import com.hcl.bank.entity.Benificiary;
import com.hcl.bank.entity.User;
import com.hcl.bank.repository.BenificiaryRepository;
import com.hcl.bank.repository.UserRepository;

@Service
public class BenificiaryServiceImpl implements BenificiaryServiceIntf{

	@Autowired
	BenificiaryRepository benificiaryRepository;
	
	@Autowired
	UserRepository  userRepository;
	
	@Override
	public BenificiaryResponseDto addBenificiary(BenificiaryRequestDto benificiaryRequestDto) {
		
		int userId=benificiaryRequestDto.getUserId();
		User user=userRepository.findByUserId(userId);
		
		Benificiary benificiary=new Benificiary();
		benificiary.setBenificiaryAccountNo(benificiaryRequestDto.getBenificiaryAccountNo());
		benificiary.setBenificiaryAccountType(benificiaryRequestDto.getBenificiaryAccountType());
		benificiary.setBenificiaryBankName(benificiaryRequestDto.getBenificiaryBankName());
		benificiary.setBenificiaryName(benificiaryRequestDto.getBenificiaryName());
		benificiary.setIfscCode(benificiaryRequestDto.getIfscCode());
		benificiary.setUser(user);
		benificiaryRepository.save(benificiary);
		
		BenificiaryResponseDto  benificiaryResponseDto=new BenificiaryResponseDto();
		benificiaryResponseDto.setMessage("added successfully");
		
		return benificiaryResponseDto;
	}

}
