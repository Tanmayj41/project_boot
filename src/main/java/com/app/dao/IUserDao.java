package com.app.dao;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.User;

public interface IUserDao 
{
	User validateUser(String email,String password);
	String addUser(User user, Distributor distributor);
	String registerCustomer(User user, Customer customer);
	Customer getCustomerByEmail(String userEmail);
	User getUserByEmail(String userEmail);
	User getUserById(int id);
	Distributor getDistByEmail(String userEmail);
	Address getAddress(User userId);
	void uplaod(MultipartFile file);
}
