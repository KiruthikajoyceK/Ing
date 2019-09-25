package com.hcl.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class UserRequestDto {
	
	@NotEmpty(message = "Please provide an userName")
	private String userName;
	
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@Min(value = 10)
	@NotEmpty(message = "Please provide an mobilno")
	private long mobileNo;
	
	@Value("age>18")
	@NotEmpty(message = "Please provide an age")
	private int age;
	
	@NotEmpty(message = "Please provide an address")
	@Size(min = 5, max = 255, message = "Please enter between {min} and {max} characters.")
	 private String address;
	
	

}
