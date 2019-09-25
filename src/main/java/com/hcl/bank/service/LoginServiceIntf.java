package com.hcl.bank.service;

import com.hcl.bank.dto.LoginRequestDto;
import com.hcl.bank.dto.LoginResponseDto;

public interface LoginServiceIntf {

	public LoginResponseDto login(LoginRequestDto loginRequestDto);
}
