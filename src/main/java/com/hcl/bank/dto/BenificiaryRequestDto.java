package com.hcl.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BenificiaryRequestDto {
	
	private int userId;
	private String benificiaryName;
	private long benificiaryAccountNo;
	private String ifscCode;
	private String benificiaryAccountType;
	private String benificiaryBankName;

}
