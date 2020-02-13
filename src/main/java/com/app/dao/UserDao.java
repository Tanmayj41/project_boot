package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Address;
import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.User;
import com.app.pojos.temp;

@Repository
@Transactional
public class UserDao implements IUserDao 
{
	@PersistenceContext
	private EntityManager mgr;
	@Override
	public User validateUser(String email, String password) 
	{
		String jpql = "select u from User u where u.userEmail=:em and u.userPassword=:pass";
		return mgr.unwrap(Session.class).createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", password).getSingleResult();
	}
	@Override
	public String addUser(User user, Distributor distributor) 
	{
		mgr.unwrap(Session.class).persist(user);
		mgr.unwrap(Session.class).persist(distributor);
		return "User registered successfully..";
	}
	@Override
	public String registerCustomer(User user, Customer customer) 
	{
		String jpql = "select d from Distributor d where d.pin=:pin";
		Distributor distributor = mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).setParameter("pin", customer.getPin()).getSingleResult();
		distributor.addCustomer(customer);
		mgr.unwrap(Session.class).persist(distributor);
		mgr.unwrap(Session.class).persist(user);
		return "Customer added successfully..";
	}
	@Override
	public Customer getCustomerByEmail(String userEmail)
	{
		String jpql="select c from Customer c where c.custEmail=:em";
		return mgr.unwrap(Session.class).createQuery(jpql,Customer.class).setParameter("em", userEmail).getSingleResult();
		
	}
	@Override
	public Distributor getDistByEmail(String userEmail)
	{
		String jpql="select d from Distributor d where d.distEmail=:em";
		return mgr.unwrap(Session.class).createQuery(jpql,Distributor.class).setParameter("em", userEmail).getSingleResult();
		
	}
	@Override
	public User getUserByEmail(String userEmail) 
	{
		String jpql="select u from User u where u.userEmail=:em";
		return mgr.unwrap(Session.class).createQuery(jpql,User.class).setParameter("em", userEmail).getSingleResult();
	}
	@Override
	public User getUserById(int id) 
	{
		return mgr.unwrap(Session.class).get(User.class, id);
	}
	@Override
	public Address getAddress(User userId) 
	{
		String jpql = "select a from Address a where a.user=:userId";
		return mgr.unwrap(Session.class).createQuery(jpql,Address.class).setParameter("userId", userId).getSingleResult();
	}
	@Override
	public void uplaod(MultipartFile file) 
	{
		String jpql = "insert into temp values(:file)";
		mgr.unwrap(Session.class).createQuery(jpql, temp.class).setParameter("file", file).executeUpdate();
	}
	
}
