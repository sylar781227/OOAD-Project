	package com.jj.spring.service;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jj.spring.dao.LoginDAO;
import com.jj.spring.model.User;

@Service
public class LoginServiceImpl implements LoginService
{
	private LoginDAO loginDao;
	
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDao = loginDAO;
	}
	
	public LoginDAO getLoginDAO()
	{
		return loginDao;
	}
	
	@Override
	@Transactional
	public User isValidUser(String username, String password) throws SQLException
	{
		return loginDao.isValidUser(username, password);
	}
}
