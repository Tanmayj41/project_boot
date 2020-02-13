package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Customer;
import com.app.pojos.Distributor;
import com.app.pojos.User;
import com.app.pojos.UserType;

@Repository
@Transactional
public class AdminDao implements IAdminDao 
{
	@PersistenceContext
	private EntityManager mgr;

	@Override
	public List<Distributor> getAllDistributors() 
	{
		String jpql = "select d from Distributor d";
		return mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).getResultList();
	}
	@Override
	public Distributor deleteDist(int id) 
	{
		Distributor dist = mgr.unwrap(Session.class).get(Distributor.class, id);
		mgr.unwrap(Session.class).delete(dist);
		return dist;
	}
	@Override
	public Distributor getDistributorByPin(int pin) 
	{
		String jpql = "select d from Distributor d where d.pin=:pin";
		return mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).setParameter("pin", pin).getSingleResult();
	}
	@Override
	public List<Customer> getAllCustomers() 
	{
		String jpql = "select c from Customer c";
		return mgr.unwrap(Session.class).createQuery(jpql, Customer.class).getResultList();
	}
	@Override
	public Distributor getDistributorByCustomer(Distributor distributor) 
	{
		String jpql = "select d from Distributor d where d.distId =:dist";
		return mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).setParameter("dist", distributor.getDistId()).getSingleResult();
	}
	@Override
	public User getAdmin() 
	{
		String jpql = "select u from User u where u.userType=:type";
		return mgr.unwrap(Session.class).createQuery(jpql, User.class).setParameter("type", UserType.valueOf("ADMIN")).getSingleResult();
	}
	@Override
	public String updateAdmin(User u) 
	{
		mgr.unwrap(Session.class).update(u);
		return "Admin updated successfully";
	}
	@Override
	public Distributor getDistByPin(int pin) 
	{
		String jpql = "select d from Distributor d where d.pin=:pin";
		return mgr.unwrap(Session.class).createQuery(jpql, Distributor.class).setParameter("pin", pin).getSingleResult();
	}
}
