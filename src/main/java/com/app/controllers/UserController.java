package com.app.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.IDistributorDao;
import com.app.dao.IUserDao;
import com.app.pojos.Address;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelper;
import com.app.pojos.CylinderType;
import com.app.pojos.Distributor;
import com.app.pojos.DistributorHelper;
import com.app.pojos.User;
import com.app.pojos.UserType;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private IUserDao dao;
	@Autowired
	private IDistributorDao dao1;
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User u,HttpSession hs)
	{
		try
		{
			User user = dao.validateUser(u.getUserEmail(), u.getUserPassword());
//			if(user.getUserType().equals(UserType.valueOf("CUSTOMER")))
//			{
//				Customer cust=dao.getCustomerByEmail(user.getUserEmail());
//				System.out.println(cust);
//				hs.setAttribute("cust_details", cust);
//			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		catch(RuntimeException ex)
		{
			ex.printStackTrace();
			return null ;
		}
	}
	
	@PostMapping("/register1")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerHelper custhelp)
	{
		System.out.println(custhelp);
		User user = new User(custhelp.getUserName(), custhelp.getUserEmail(), custhelp.getUserPassword(), new Date(), custhelp.getUserPhone(),UserType.valueOf("CUSTOMER"));
		System.out.println(user);
		Customer customer = new Customer(custhelp.getUserEmail(), custhelp.getCustAadhar(), custhelp.getPincode(), custhelp.getAadharImage(), custhelp.getCylinderType());
		System.out.println(customer);
		user.addAddress(new Address(custhelp.getArea(), custhelp.getCity(), custhelp.getDistrict(), custhelp.getPincode()));
		dao.registerCustomer(user,customer);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@PostMapping("/register2")
	public ResponseEntity<?> registerDistributor(@RequestBody DistributorHelper disthelp)
	{
		boolean status;
		String msg = null;
		try
		{
		status=dao1.checkForDuplicateDistributor(disthelp.getPincode());
		msg = "Distributor already exists for this pincode";
		}
		catch (RuntimeException ex)
		{
		Address addr = new Address(disthelp.getArea(), disthelp.getCity(), disthelp.getDistrict(),
		disthelp.getPincode());
		User user = new User(disthelp.getUserName(), disthelp.getUserEmail(), disthelp.getUserPassword(),
		new Date(), disthelp.getUserPhone(), UserType.valueOf("DISTRIBUTOR"));
		user.addAddress(addr);
		Distributor distributor = new Distributor(disthelp.getUserName(), disthelp.getPincode(),
		disthelp.getCommercialStock(), disthelp.getDomesticStock(), disthelp.getUserEmail());
		msg =  dao.addUser(user, distributor);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@PostMapping("/getByEmail")
	public ResponseEntity<?> getCustomerByEmail(@RequestBody String email)
	{
		System.out.println(email);
		User user=dao.getUserByEmail(email);
		System.out.println(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam MultipartFile[] file)
	{
		System.out.println("hii");
		return null;
	}
	@RequestMapping(value = "/upload3",method = RequestMethod.POST)
	public ResponseEntity<?> addEmpDetails(@RequestParam String userName,@RequestParam String userPhone,
										   @RequestParam String userEmail,@RequestParam String userPassword,
										   @RequestParam String custAadhar,@RequestParam String area,
										   @RequestParam String city,@RequestParam String district,
										   @RequestParam int pincode,@RequestParam String cylinderType,
										   @RequestParam MultipartFile fileUpload) throws Exception 
	{
		User user = new User(userName, userEmail, userPassword, new Date(), userPhone,UserType.valueOf("CUSTOMER"));
		System.out.println(user);
		Customer customer = new Customer(userEmail, custAadhar, pincode, fileUpload.getBytes(), CylinderType.valueOf(cylinderType));
		System.out.println(customer);
		user.addAddress(new Address(area, city, district, pincode));
		dao.registerCustomer(user,customer);
		System.out.println("Saving file: " + fileUpload.getOriginalFilename());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
