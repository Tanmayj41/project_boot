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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IAdminDao;
import com.app.dao.IUserDao;
import com.app.pojos.Address;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelperForAdmin;
import com.app.pojos.Distributor;
import com.app.pojos.DistributorPerformance;
import com.app.pojos.User;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminDao dao;
	@Autowired
	private IUserDao dao1;

	@GetMapping("/get")
	public ResponseEntity<?> getAdmin() {
		User user = dao.getAdmin();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/display")
	public ResponseEntity<?> getAllDistributors(HttpSession hs) {
		List<Distributor> list = dao.getAllDistributors();
		hs.setAttribute("dist_list", list);
		return new ResponseEntity<List<Distributor>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{distId}")
	public ResponseEntity<?> deleteDistributor(@PathVariable int distId) {
		Distributor dist = dao.deleteDist(distId);
		return new ResponseEntity<Distributor>(dist, HttpStatus.OK);
	}

	@GetMapping("/getCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> allCustomers = dao.getAllCustomers();
		List<CustomerHelperForAdmin> custList = new ArrayList<>();
		for (Customer customer : allCustomers) {
			Distributor distributorByCustomer = dao.getDistributorByCustomer(customer.getDistributor());
			User user = dao1.getUserByEmail(customer.getCustEmail());
			Address address = dao1.getAddress(user);
			custList.add(new CustomerHelperForAdmin(customer.getCustId(), user.getUserName(), user.getUserEmail(),
					user.getRegistrationDate(), address.getArea(), address.getPincode(), customer.getCylinderType(),
					distributorByCustomer.getDistName()));
		}
		return new ResponseEntity<List<CustomerHelperForAdmin>>(custList, HttpStatus.OK);
	}

	@PutMapping("/updateAdmin")
	public ResponseEntity<?> updateAdmin(@RequestBody User u) {
		String message = dao.updateAdmin(u);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/distributorPerformance")
	public ResponseEntity<List<DistributorPerformance>> getDistributorPerformance() 
	{
		List<DistributorPerformance> distPerformanceList = new ArrayList<>();
		List<Distributor> allDistributors = dao.getAllDistributors();
		for (Distributor distributor : allDistributors) 
		{
			int size = 0;
			DistributorPerformance dp = new DistributorPerformance();
			dp.setDistId(distributor.getDistId());
			dp.setDistName(distributor.getDistName());
			dp.setDistEmail(distributor.getDistEmail());
			List<Customer> customerList = distributor.getCustomerList();
			for (Customer customer : customerList) {
				List<Booking> bookingList = customer.getBookingList();
				size = size + bookingList.size();
			}
			if (size > 0 && size <= 10) {
				dp.setPerformance(1);
			} else if (size > 10 && size <= 20) {
				dp.setPerformance(2);
			} else if (size > 20 && size <= 30) {
				dp.setPerformance(3);
			} else if (size > 30 && size <= 40) {
				dp.setPerformance(4);
			} else if (size > 50) {
				dp.setPerformance(5);
			}
			distPerformanceList.add(dp);
		}
		distPerformanceList.sort(null);
		System.out.println(distPerformanceList);
		return new ResponseEntity<List<DistributorPerformance>>(distPerformanceList, HttpStatus.OK);
	}
	@GetMapping("/getDistByPin/{pin}")
	public ResponseEntity<?> getDistByPin(@PathVariable int pin)
	{
		Distributor dist = dao.getDistByPin(pin);
		return new ResponseEntity<Distributor>(dist, HttpStatus.OK);
	}
}
