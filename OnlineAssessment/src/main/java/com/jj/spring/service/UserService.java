package com.jj.spring.service;

import java.util.List;

import com.jj.spring.model.User;

public interface UserService {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserByUserName(String userName);
	public void removeUser(int id);
	
}
