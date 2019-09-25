package com.hcl.bank.service;

import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;

public interface RegistrationServiceIntf {
	
	public UserResponseDto registration(UserRequestDto userRequestDto);
	
}
