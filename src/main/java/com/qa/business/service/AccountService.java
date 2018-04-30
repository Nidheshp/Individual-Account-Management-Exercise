package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.IAccountRepo;

public class AccountService implements IAccountService {
	
	@Inject
	private IAccountRepo repo;
	
	@Override
	public String getAllAccounts() {

		return repo.getAllAccounts();
	}
	
	@Override
	public String getAAccount(Long id) {
		return repo.getAAccount(id);
	}
	
	@Override
	public String createAAccount(String jsonAccount) {
		return repo.createAAccount(jsonAccount);
	}
	
	@Override
	public String deleteAAccount(Long id) {
		return repo.deleteAAccount(id);
	}
	
	@Override
	public String updateAAccount(String updateTheAccount) {
		return repo.updateAAccount(updateTheAccount);
	}
}