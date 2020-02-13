package com.app.dao;

import java.text.ParseException;
import java.util.List;

import com.app.pojos.Address;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelper;
import com.app.pojos.Distributor;
import com.app.pojos.User;

public interface ICustomerDao 
{
	String addBooking(Customer c, Distributor distributor);

	String updateCustomer(CustomerHelper custhelp, User user, Customer customer);

	List<Booking> getBookingList(Customer c);

	Customer getCustomerById(int id);

	Address getAddressById(User user);
	
	String cancelBooking(int custId) throws ParseException;

	String deleteCustomer(Customer customer, User user, Address address); 
}
