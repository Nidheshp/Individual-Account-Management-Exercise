package com.qa.business.repository.testrepo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.repository.AccountDBRepo;
import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepoTest {
	
	@InjectMocks
	private AccountDBRepo repo;
	
	@Mock
	private EntityManager manager;
	
	@Mock
	private Query query;
	
	private JSONUtil util;
	
	private static final String mockDataArray = "[{\"firstName\":\"Nidhesh\",\"surName\":\"Pillai\",\"accountNumber\":\"10\"}]";

	private static final String mockObject = "{\"firstName\":\"Nidhesh\",\"surName\":\"Pillai\",\"accountNumber\":\"10\"}";
	
	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("Nidhesh", "Pillai", "10"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(mockDataArray, repo.getAllAccounts());
	}
	
	@Test
	public void testGetAAccount() {
		Mockito.when(repo.getAccount(1L)).thenReturn(util.getObjectForJSON(mockObject,Account.class));
		String reply = repo.getAAccount(1L);
		Assert.assertEquals(reply, mockObject);
	}
	
	@Test
	public void testGetAAccountFail() {
		
		String reply = repo.getAAccount(1L);
		Assert.assertEquals(reply, "{\"Response\":\"Account not found!\"}");
	}
	
	@Test
	public void testCreateAAccount() {
		
		String reply = repo.createAAccount(mockObject);
		Assert.assertEquals(reply, "{\"Response\":\"Account created!\"}");
	}
	
	@Test
	public void testDeleteAAccount() {
		
		Mockito.when(repo.getAccount(1L)).thenReturn(util.getObjectForJSON(mockObject,Account.class));
		String reply = (String)repo.deleteAAccount(1L);
		Assert.assertEquals(reply, "{\"Response\":\"Account deleted!\"}");
	}
	
	@Test
	public void testDeleteAAccountFail() {
		
		String reply = repo.deleteAAccount(null);
		Assert.assertEquals(reply, "{\"Response\": \"Account not found so unable to delete!\"}");
	}
	
	@Test
	public void testUpdateAAccount() {
		
		Mockito.when(repo.getAccount(1L)).thenReturn(util.getObjectForJSON(mockObject,Account.class));
		String reply = repo.updateAAccount("{\"id\":\"1\",\"firstName\":\"Nidhesh\",\"surName\":\"Pillai\",\"accountNumber\":\"10\"}");
		Assert.assertEquals(reply, "{\"Response\":\"Account updated!\"}");
	}
	
	@Test
	public void testUpdateAAccountFail() {
		
		String reply = repo.updateAAccount(mockObject);
		Assert.assertEquals(reply, "{\"Response\":\"Account not found so unable to update!\"}");
	}
}
