package com.qa.business.service;

public interface IAccountService {
	
	String getAllAccounts();

	String getAAccount(Long id);

	String createAAccount(String jsonAccount);

	String deleteAAccount(Long id);
	
	String updateAAccount(String updateTheAccount);
}
