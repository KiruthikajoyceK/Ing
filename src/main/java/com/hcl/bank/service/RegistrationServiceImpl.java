package com.hcl.bank.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bank.constants.FundtransferConstants;
import com.hcl.bank.dto.RandomPasswordGenerator;
import com.hcl.bank.dto.UserRequestDto;
import com.hcl.bank.dto.UserResponseDto;
import com.hcl.bank.entity.Account;
import com.hcl.bank.entity.User;
import com.hcl.bank.exception.CommonException;
import com.hcl.bank.repository.AccountRepository;
import com.hcl.bank.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationServiceIntf {
	
	
	 private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public UserResponseDto registration(UserRequestDto userRequestDto) {
		
		Optional<User> user1=userRepository.findByMobileNo(userRequestDto.getMobileNo());
		
		RandomPasswordGenerator passwordGenerator=new RandomPasswordGenerator();
		
		int len = 10;
		@SuppressWarnings("static-access")
		String password = passwordGenerator.generatePassword(len, ALPHA_CAPS + ALPHA);
		UserResponseDto userResponseDto=new UserResponseDto();
		
		if(user1.isPresent())
		{
			throw new CommonException(FundtransferConstants.EXISTS_USER);
		}
		else
		{
			User user=new User();
			
			    if(userRequestDto.getUserName().isEmpty())
			    {
				throw new CommonException(FundtransferConstants.EMPTY_USER_NAME);
					
			    }
				user.setUserName(userRequestDto.getUserName());
				
				
				
				if(userRequestDto.getAddress().isEmpty())
				{
					throw new CommonException(FundtransferConstants.EMPTY_USER_ADDRESS);
						
				}
				user.setAddress(userRequestDto.getAddress());
				
				
				
				if(userRequestDto.getAge()<18)
				{
					throw new CommonException(FundtransferConstants.INVALID_AGE);
				}
				user.setAge(userRequestDto.getAge());
				
				
				
				if(!emailValidation(userRequestDto.getEmail()))
				{
					throw new CommonException(FundtransferConstants.INVALID_EMAIL);
				}				
				user.setEmail(userRequestDto.getEmail());
				
				
				
				user.setMobileNo(userRequestDto.getMobileNo());
				user.setPassword(password);
				userRepository.save(user);
				@SuppressWarnings("static-access")
				long accountNo=passwordGenerator.random(500000, 900000);
				
				Account account=new Account();
				
				account.setAccountNumber(accountNo+3);
				account.setAccountBalance(10000);
				account.setAccountCreationDate(LocalDate.now());
				account.setAccountType("Savings");
				account.setUser(user);
				accountRepository.save(account);
				BeanUtils.copyProperties(account, userResponseDto);
				
				return userResponseDto;
		}
						
			}
	 public static boolean emailValidation(String email)
	    {
	        String email_pattern = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pat = Pattern.compile(email_pattern);
	        Matcher mat = pat.matcher(email);
	        return mat.matches();
	    }
				
			}

			
		
		
		
	

	

