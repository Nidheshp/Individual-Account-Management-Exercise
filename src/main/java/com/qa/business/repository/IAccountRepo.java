package com.qa.business.repository;

public interface IAccountRepo {
	
	String getAllAccounts();

	String getAAccount(Long id);

	String createAAccount(String accountJSON);

	String deleteAAccount(Long id);

	String updateAAccount(String updateTheAccount);

}
