package com.app.dao;

import java.util.List;

import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.User;

public interface IAdminDao 
{
	List<Distributor> getAllDistributors();
	Distributor deleteDist(int id);
	Distributor getDistributorByPin(int pin);
	List<Customer> getAllCustomers();
	Distributor getDistributorByCustomer(Distributor distributor);
	User getAdmin();
	String updateAdmin(User u);
	Distributor getDistByPin(int pin);
}
