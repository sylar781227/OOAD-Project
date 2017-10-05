package com.jj.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jj.spring.model.User;

@Repository
public class LoginDAOImpl implements LoginDAO
{
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf)
	{
		this.sessionFactory = sf;
	}
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public User isValidUser(String username, String password) throws SQLException
	{
		Session session = this.sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("FROM User U WHERE U.userName = '" + username + "'").list();
		if(users==null || users.isEmpty())
		{
			return null;
		}
		else
		{
			String actualPassword = (String)session.createQuery("SELECT U.password FROM User U WHERE U.userName = '" + username + "'").list().get(0);
			if(password.equals(actualPassword))
			{
				return (User)users.get(0);
			}
			else
			{
				return null;
			}
		}
		
	 }
}
