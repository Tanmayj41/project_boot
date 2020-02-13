package com.app.dao;

import java.util.List;

import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.DistributorHelper;
import com.app.pojos.User;

public interface IDistributorDao 
{
	List<Customer> getCustomers();

	Distributor getDistributorById(int id);

	String updateDistributor(DistributorHelper dh, User u, Distributor d);

	Distributor getDistributorById(String distEmail);

	List<Customer> getCustomersByDistId(int id);

	boolean checkForDuplicateDistributor(int pincode);
	
	List<User> getUserByName(String name);
	
	Customer getCustomerByEmail(String userEmail);
}
