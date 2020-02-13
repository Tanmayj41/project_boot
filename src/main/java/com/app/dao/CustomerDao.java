package com.app.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.Booking;
import com.app.pojos.Customer;
import com.app.pojos.CustomerHelper;
import com.app.pojos.CylinderType;
import com.app.pojos.Distributor;
import com.app.pojos.User;

@Repository
@Transactional
public class CustomerDao implements ICustomerDao 
{
	@PersistenceContext
	private EntityManager mgr;
	@Override
	public String addBooking(Customer customer,Distributor distributor) 
	{
		List<Booking> booklist = customer.getBookingList();
		List<Date> dateList ;
		Date today = new Date();
		if(!booklist.isEmpty())
		{
			dateList = new ArrayList<>();
			for (Booking booking : booklist) {
				System.out.println(booking);
				Date date = booking.getDeliveryDate();
				dateList.add(date);
			}
			Collections.reverse(dateList);
			Date lastBookDate = dateList.get(0);
			if(today.compareTo(lastBookDate)<30)
			{
				return "Booking falied___Cannot book 2 cylinders in one month";
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, 3); 
		double bookingAmount;
		int stock;
		Date modifiedDate = cal.getTime();
		if(customer.getCylinderType().equals(CylinderType.valueOf("DOMESTIC")))
		{
			bookingAmount = 1000;
			stock = distributor.getDomesticStock();
			if(stock==0)
				return "Out of Stock";
			distributor.setDomesticStock(stock-1);
		}
		else
		{
			bookingAmount = 2000;
			stock = distributor.getCommercialStock();
			if(stock==0)
				return "Out of Stock";
			distributor.setCommercialStock(stock-1);
		}
		
		Booking booking = new Booking(today, modifiedDate, customer.getCylinderType(), bookingAmount);
		customer.addBooking(booking);
		mgr.unwrap(Session.class).update(customer);
		mgr.unwrap(Session.class).update(distributor);
		return "Booking done!!";
	}
	@Override
	public String updateCustomer(CustomerHelper c, User user, Customer customer) 
	{
		user.setUserName(c.getUserName());
		user.setUserEmail(c.getUserEmail());
		user.setUserPassword(c.getUserPassword());
		user.setUserPhone(c.getUserPhone());
		customer.setAadharImage(c.getAadharImage());
		customer.setCustAadhar(c.getCustAadhar());
		customer.setCylinderType(c.getCylinderType());
		mgr.unwrap(Session.class).update(user);
		mgr.unwrap(Session.class).update(customer);
		return "Data updated successfully!!!";
	}
	@Override
	public List<Booking> getBookingList(Customer c) 
	{
		String jpql = "select b from Booking b where b.customer=:id";
		return mgr.unwrap(Session.class).createQuery(jpql, Booking.class).setParameter("id", c).getResultList();
	}
	@Override
	public Customer getCustomerById(int id) 
	{
		return mgr.unwrap(Session.class).get(Customer.class, id);
	}
	@Override
	public Address getAddressById(User user) 
	{
		String jpql = "select a from Address a where a.user=:id";
		return mgr.unwrap(Session.class).createQuery(jpql, Address.class).setParameter("id", user).getSingleResult();
	}
	@SuppressWarnings("deprecation")
	@Override
	public String cancelBooking(int custId) throws ParseException 
	{
		Customer customer = mgr.unwrap(Session.class).get(Customer.class, custId);
		List<Booking> bookingList = getBookingList(customer);
		Distributor distributor = customer.getDistributor();
		Collections.reverse(bookingList);
		if(!bookingList.isEmpty())
		{
			System.out.println("***********");
			Booking book= bookingList.get(0);
			System.out.println(book);
			Date deliveryDate=book.getDeliveryDate();
			Date today=new Date();
			
//			System.out.println("today is  "+today);
//			System.out.println("delivery date is "+deliveryDate );
//			System.out.println("today year" +(today.getYear()+1900));
//			System.out.println("today month" +(today.getMonth()+1));
//			System.out.println("today day" +today.getDate());
//			System.out.println("delivery year" +(today.getYear()+1900));
//			System.out.println("delivery month" +(today.getMonth()+1));
//			System.out.println("delivery day" +today.getDate());

			if(today.compareTo(deliveryDate)<=2)
			{
				System.out.println("cancel possible");
			}
			if(today.compareTo(deliveryDate)<=2)
			{
				int newStock;
				CylinderType cylinderType = customer.getCylinderType();
				if(cylinderType==CylinderType.valueOf("COMMERCIAL"))
				{
					newStock=distributor.getCommercialStock()+1;
					distributor.setCommercialStock(newStock);
				}
				else
				{
					newStock=distributor.getDomesticStock()+1;
					distributor.setDomesticStock(newStock);
				}
				customer.removeBooking(book);
				mgr.unwrap(Session.class).update(distributor);
				mgr.unwrap(Session.class).saveOrUpdate(book);
				return "Booking cancelled";
			}
			else
			{
				return "Already Delivered";
			}
		}
		return "No bookings to cancel";
	}
	@Override
	public String deleteCustomer(Customer customer,User user,Address address) 
	{
		if(customer!=null && user!=null && address != null)
		{	
			mgr.unwrap(Session.class).remove(customer);
			mgr.unwrap(Session.class).remove(user);
			return "Customer deleted successfully!!";
		}
		else
			return "Customer doesn't exist";
	}
}
