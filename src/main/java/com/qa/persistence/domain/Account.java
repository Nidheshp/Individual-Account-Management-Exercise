package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String surName;
	private String accountNumber;
	
	public Account() {
		
	}
	
public Account(String firstName, String surName, String accountNumber) {
		
		this.firstName = firstName;
		this.surName = surName;
		this.accountNumber = accountNumber;

	}

	public Long getId() {
		return id;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getsurName() {
		return surName;
	}

	public String getaccountNumber() {
		return accountNumber;
	}
}