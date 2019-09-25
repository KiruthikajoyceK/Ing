package com.hcl.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.constants.FundtransferConstants;
import com.hcl.bank.dto.LoginRequestDto;
import com.hcl.bank.dto.LoginResponseDto;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.UserNotFoundException;
import com.hcl.bank.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginServiceIntf{

	@Autowired
	UserRepository userRepository;
	
	
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		
		String userName=loginRequestDto.getUserName();
		String password=loginRequestDto.getPassword();
		Optional<User> user=userRepository.findByUserNameAndPassword(userName,password);
		if(user.isPresent()) {
			User userr=user.get();
			return new LoginResponseDto(FundtransferConstants.SUCCESS,userr.getUserId());
			
		}
		else
			{
				throw new UserNotFoundException(FundtransferConstants.INVALID_CREDENTIALS);
			}
			
		
	}

}
