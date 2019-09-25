package com.hcl.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Benificiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int benificiaryId;
	private String benificiaryName;
	private long benificiaryAccountNo;
	private String ifscCode;
	private String benificiaryAccountType;
	private String benificiaryBankName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

}
