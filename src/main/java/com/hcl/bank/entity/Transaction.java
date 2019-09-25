package com.hcl.bank.entity;

import java.time.LocalDate;

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
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int transactionId;
	private long fromAccountNo;
	private long toAccountNo;
	private String transactionType;
	private int transactionAmount;
	private LocalDate transactionDate;
	
	@ManyToOne
	//@JoinColumn(name="user_id")
	private User user;

}
