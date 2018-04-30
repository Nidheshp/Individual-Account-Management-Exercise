package com.qa.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

public class AccountDBRepo implements IAccountRepo {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		

		Query query = manager.createQuery("Select n FROM Account n");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
	
	@Override
	public String getAAccount(Long id) {
		Account aAccount = getAccount(id);
		if (aAccount != null) {
			return util.getJSONForObject(aAccount);
		}

		else {

			return "{\"Response\":\"Account not found!\"}";
		}
	}
	
	public Account getAccount(Long id) {
		return manager.find(Account.class, id);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String createAAccount(String accountJSON) {
		Account aAccount = util.getObjectForJSON(accountJSON, Account.class);
		manager.persist(aAccount);
		return "{\"Response\":\"Account created!\"}";

	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteAAccount(Long id) {
		Account oldAccount = getAccount(id);
		if (oldAccount != null) {
			manager.remove(oldAccount);
			return "{\"Response\":\"Account deleted!\"}";
		}

		else {

			return "{\"Response\": \"Account not found so unable to delete!\"}";
		}
	}
	
	@Override
	@Transactional(REQUIRED)
	public String updateAAccount(String updateTheAccount) {
		Account updateAccount = util.getObjectForJSON(updateTheAccount, Account.class);
		Account originalAccount = getAccount(updateAccount.getId());
		if (originalAccount != null) {
			manager.merge(updateAccount);

			return "{\"Response\":\"Account updated!\"}";
		}

		else {

			return "{\"Response\":\"Account not found so unable to update!\"}";
		}
	}
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
