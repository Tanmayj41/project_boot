package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICustomerDao;
import com.app.dao.IDistributorDao;
import com.app.dao.IUserDao;
import com.app.pojos.Address;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelper;
import com.app.pojos.CustomerHelperForDistributor;
import com.app.pojos.Distributor;
import com.app.pojos.DistributorHelper;
import com.app.pojos.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/distributer")
public class DistributorController 
{
	@Autowired
	private IDistributorDao dao;
	@Autowired
	private IUserDao dao1;
	@Autowired
	private ICustomerDao dao3;
	
	@GetMapping("/display")
	public ResponseEntity<?> getCustomers(HttpSession hs)
	{
		List<Customer> list=dao.getCustomers();
		hs.setAttribute("cust_list", list);
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getDistributorById(@PathVariable int id)
	{
		Distributor distributor = dao.getDistributorById(id);
		return new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateDistributor(@RequestBody DistributorHelper dh,@PathVariable int id)
	{
		User u = dao1.getUserById(dh.getUserId());
		Distributor d = dao1.getDistByEmail(dh.getUserEmail());
		String msg =  dao.updateDistributor(dh,u,d);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	@PostMapping("/getByEmail")
	public ResponseEntity<?> getDistributorByEmail(@RequestBody String distEmail)
	{
		try 
		{
			Distributor distributor = dao.getDistributorById(distEmail);
			return new ResponseEntity<Distributor>(distributor, HttpStatus.OK);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	@GetMapping("/getCustomers/{distId}")
	public ResponseEntity<?> getCustomersByDist(@PathVariable Integer distId)
	{
		List<Customer> custList = dao.getCustomersByDistId(distId);
		List<CustomerHelper> chlist = new ArrayList<CustomerHelper>();
		for (Customer customer : custList) 
		{
			User user = dao1.getUserByEmail(customer.getCustEmail());
			Address address = dao3.getAddressById(user);
			CustomerHelper ch = new CustomerHelper(user.getUserName(), customer.getCustEmail(), user.getUserPassword(), user.getRegistrationDate(), user.getUserPhone(), address.getArea(), address.getCity(), address.getDistrict(), address.getPincode(), customer.getCustAadhar(), customer.getCylinderType(),customer.getAadharImage());
			ch.setUserId(customer.getCustId());
			byte[] imgContent=ch.getAadharImage();
			String url="data:image/jpeg;base64,"+Base64.encode(imgContent);
			ch.setUrl(url);
			chlist.add(ch);
		}
		return new ResponseEntity<List<CustomerHelper>>(chlist, HttpStatus.OK);
	}
	@GetMapping("/getBookingByCustId/{custId}")
	public ResponseEntity<?> getBookingByCustId(@PathVariable int custId)
	{
		Customer customer = dao3.getCustomerById(custId);
		List<Booking> list = dao3.getBookingList(customer);
		return new ResponseEntity<List<Booking>>(list, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable int custId)
	{
		User user = null;
		String message = null;
		Address address = null;
		Customer customer = dao3.getCustomerById(custId);
		System.out.println(customer);
		user = dao1.getUserByEmail(customer.getCustEmail());
		System.out.println(user);
		address = dao1.getAddress(user);
		System.out.println(address);
		message = dao3.deleteCustomer(customer,user,address);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@PostMapping("/customerStats")
	public ResponseEntity<List<CustomerHelperForDistributor>> getCustomerByName(@RequestBody String name)
	{
		List<CustomerHelperForDistributor> custHelperList=new ArrayList<CustomerHelperForDistributor>();
		List<User> userList=dao.getUserByName(name);
		for (User user : userList)
		{
			CustomerHelperForDistributor ch=new CustomerHelperForDistributor();
			Customer customerByEmail = dao.getCustomerByEmail(user.getUserEmail());
			List<Booking> bookingList = customerByEmail.getBookingList();

			ch.setName(user.getUserName());
			ch.setEmail(user.getUserEmail());
			ch.setType(customerByEmail.getCylinderType());
			ch.setOrdersNumber(bookingList.size());
			System.out.println(user);
			System.out.println(customerByEmail);
			custHelperList.add(ch);
		}
		return new ResponseEntity<List<CustomerHelperForDistributor>>(custHelperList, HttpStatus.OK);
	}
	@GetMapping("/getCustomerById/{custId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int custId)
	{
		Customer customerById = dao3.getCustomerById(custId);
		return new ResponseEntity<Customer>(customerById, HttpStatus.OK);
	}
}
