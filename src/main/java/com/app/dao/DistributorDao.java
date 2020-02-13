package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.DistributorHelper;
import com.app.pojos.User;
import com.app.pojos.UserType;

@Repository
@Transactional
public class DistributorDao implements IDistributorDao 
{
	@PersistenceContext
	private EntityManager mgr;

	@Override
	public List<Customer> getCustomers() {
		String jpql = "select c from Customer c";
		return mgr.unwrap(Session.class).createQuery(jpql, Customer.class).getResultList();
	}

	@Override
	public Distributor getDistributorById(int id) {
		return mgr.unwrap(Session.class).get(Distributor.class, id);
	}

	@Override
	public String updateDistributor(DistributorHelper c, User user, Distributor d) {
		user.setUserName(c.getUserName());
		user.setUserEmail(c.getUserEmail());
		user.setUserPassword(c.getUserPassword());
		user.setUserPhone(c.getUserPhone());
		d.setDomesticStock(c.getDomesticStock());
		d.setCommercialStock(c.getCommercialStock());
		d.setDistEmail(c.getUserEmail());
		d.setDistName(c.getUserName());
		mgr.unwrap(Session.class).update(user);
		mgr.unwrap(Session.class).update(d);
		return "Data updated successfully!!!";
	}

	@Override
	public Distributor getDistributorById(String distEmail) {
		String jpql = "select d from Distributor d where d.distEmail=:em";
		return mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).setParameter("em", distEmail)
				.getSingleResult();
	}

	@Override
	public List<Customer> getCustomersByDistId(int id) {
		Distributor distributor = mgr.unwrap(Session.class).get(Distributor.class, id);
		String jpql = "select c from Customer c where c.distributor=:id";
		return mgr.unwrap(Session.class).createQuery(jpql, Customer.class).setParameter("id", distributor).getResultList();
	}

	@Override
	public boolean checkForDuplicateDistributor(int pincode) 
	{
		System.out.println("Hii");
		String jpql = "select d from Distributor d where d.pin=:pin";
		Distributor distributor = mgr.unwrap(Session.class).createQuery(jpql, Distributor.class)
				.setParameter("pin", pincode).getSingleResult();
		System.out.println(distributor);
		return true;
	}
	@Override
	public List<User> getUserByName(String name)
	{
		String jpql="select u from User u where u.userName=:name and u.userType=:type";
		return mgr.unwrap(Session.class).createQuery(jpql, User.class).setParameter("name", name).setParameter("type", UserType.valueOf("CUSTOMER")).getResultList();
	}

	@Override
	public Customer getCustomerByEmail(String userEmail)
	{
		String jpql="select c from Customer c where c.custEmail=:em";
		return mgr.unwrap(Session.class).createQuery(jpql,Customer.class).setParameter("em", userEmail).getSingleResult();
	}
}
