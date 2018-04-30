package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.IAccountService;

@Path("/account")
public class AccountEndPoint {
	
	@Inject
	private IAccountService service;
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllAccounts() {
		
		return service.getAllAccounts();
	}
	
	@GET
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String getAAccount(@PathParam("id") Long id) {

		return service.getAAccount(id);

	}
	
	@POST
	@Path("/json")
	@Produces({ "application/json" })
	public String createAAccount(String jsonString) {

		return service.createAAccount(jsonString);

	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({ "application/json" })
	public String deleteAAccount(@PathParam("id") Long id) {

		return service.deleteAAccount(id);

	}
	
	@PUT
	@Path("/json")
	@Produces({ "application/json" })
	public String updateAAccount(String updateTheAccount) {

		return service.updateAAccount(updateTheAccount);

	}
}