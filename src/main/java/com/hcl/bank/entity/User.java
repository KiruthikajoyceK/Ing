package com.hcl.bank.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int userId;
	private String userName;
	private String email;
	private long mobileNo;
	private int age;
	private String address;
	private String password;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "user")
	private List<Benificiary> benificiaries;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "user")
	private List<Transaction> transactions;

}
