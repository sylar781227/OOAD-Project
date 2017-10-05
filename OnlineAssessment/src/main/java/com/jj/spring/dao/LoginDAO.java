package com.jj.spring.dao;

import java.sql.SQLException;

import com.jj.spring.model.User;

public interface LoginDAO
{
	public User isValidUser(String username, String password) throws SQLException;
}
