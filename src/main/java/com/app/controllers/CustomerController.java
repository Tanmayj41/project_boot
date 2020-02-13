package com.app.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IAdminDao;
import com.app.dao.ICustomerDao;
import com.app.dao.IUserDao;
import com.app.pojos.Address;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelper;
import com.app.pojos.Distributor;
import com.app.pojos.Email;
import com.app.pojos.User;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	private ICustomerDao dao;
	@Autowired
	private IUserDao dao1;
	@Autowired
	private IAdminDao dao2;
	@Autowired
	private JavaMailSender sender;
	@PostMapping("/book")
	public ResponseEntity<?> bookCylinder(@RequestBody String email)
	{
		System.out.println(email);
		Customer customer=dao1.getCustomerByEmail(email);
		Distributor distributor = dao2.getDistributorByPin(customer.getPin());
		System.out.println(customer);
		System.out.println(distributor);
		String message = dao.addBooking(customer,distributor);
		System.out.println(message);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@PostMapping("/getByEmail")
	public ResponseEntity<?> getCustomerByEmail(@RequestBody String email)
	{
		System.out.println(email);
		Customer customer=dao1.getCustomerByEmail(email);
		System.out.println(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerHelper custhelp,@PathVariable int id)
	{
		System.out.println(custhelp);
		User user = dao1.getUserById(id);
		System.out.println(user);
		Customer customer = dao1.getCustomerByEmail(custhelp.getUserEmail());
		System.out.println(customer);
		String message = dao.updateCustomer(custhelp,user,customer);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	@PostMapping("/getBooking")
	public ResponseEntity<?> getBookingList(@RequestBody String userEmail)
	{
		Customer customer = dao1.getCustomerByEmail(userEmail);
		System.out.println(customer);
		List<Booking> bookList = dao.getBookingList(customer);
		return new ResponseEntity<List<Booking>>(bookList, HttpStatus.OK);
	}
	@PostMapping("/getAddress")
	public ResponseEntity<?> getAddress(@RequestBody String userEmail)
	{
		System.out.println(userEmail);
		User user = dao1.getUserByEmail(userEmail);
		Address address = dao.getAddressById(user);
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	@PostMapping("/cancelBooking")
	public ResponseEntity<?> cancelBooking(@RequestBody String userEmail)
	{
		System.out.println(userEmail);
		Customer customer = dao1.getCustomerByEmail(userEmail);
		System.out.println(customer.getCustId());
		String status = "Cancel failed";
		try {
			status = dao.cancelBooking(customer.getCustId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	@PostMapping("/sendMail")
	public ResponseEntity<?> sendMail(@RequestBody Email em)
	{
		System.out.println(em.getDestEmail()+"  "+em.getMessage());
		SimpleMailMessage mesg=new SimpleMailMessage();
		if(em.getSourceEmail()!=null)
			mesg.setFrom(em.getSourceEmail());
		mesg.setTo(em.getDestEmail());
		mesg.setSubject(em.getSubject());
		mesg.setText(em.getMessage());
		sender.send(mesg);
		return new ResponseEntity<String>("email sent", HttpStatus.OK);
	}
}
