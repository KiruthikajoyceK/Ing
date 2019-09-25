package com.hcl.bank.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	
	private long accountNumber;
	private double accountBalance;
	private LocalDate accountCreationDate;
	private String accountType;

}
