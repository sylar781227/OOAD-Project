package com.jj.spring.service;
import java.sql.SQLException;

import com.jj.spring.model.User;

public interface LoginService {

	public User isValidUser(String username, String password) throws SQLException;
	
}
